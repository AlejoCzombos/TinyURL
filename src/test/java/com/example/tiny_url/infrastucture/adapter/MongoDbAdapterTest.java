package com.example.tiny_url.infrastucture.adapter;

import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.infrastructure.adapter.UrlMongoDBAdapter;
import com.example.tiny_url.infrastructure.adapter.entity.UrlEntity;
import com.example.tiny_url.infrastructure.adapter.repository.UrlMongoDBRepository;
import com.example.tiny_url.infrastructure.rest.interceptor.exception.AliasAlreadyExistsException;
import com.example.tiny_url.infrastructure.rest.interceptor.exception.UrlHasExpiredException;
import com.example.tiny_url.infrastructure.rest.interceptor.exception.UrlNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class MongoDbAdapterTest {

    @Mock
    private UrlMongoDBRepository repository;

    @InjectMocks
    private UrlMongoDBAdapter adapter;

    private Url url;
    private UrlEntity urlEntity;

    @BeforeEach
    public void setUp() {
        url = Url.builder().alias("alias").url("url").key("key").createdAt(LocalDateTime.of(2024,10,20,0,0)).build();
        urlEntity = UrlEntity.builder().alias("alias").url("url").key("key").createdAt(LocalDateTime.of(2024,10,20,0,0)).build();
    }

    @Test
    public void findAll_returnAllUrls_whenUrlsExists() {
        when(repository.findAll()).thenReturn(List.of(urlEntity));

        Assertions.assertEquals(List.of(url), adapter.findAll());
        verify(repository, times(1)).findAll();
    }

    @Test
    void findByKeyOrAlias_throwUrlNotFoundException_whenUrlNotExists() {
        when(repository.findByKeyOrAlias(url.getKey())).thenReturn(Optional.empty());

        Assertions.assertThrows(UrlNotFoundException.class, () -> adapter.findByKeyOrAlias(url.getKey()));
        verify(repository, times(1)).findByKeyOrAlias(url.getKey());
        verify(repository, never()).save(urlEntity);
    }

    @Test
    void findByKeyOrAlias_throwUrlHasExpiredExceptionAndDelete_whenUrlHasExpired(){
        when(repository.findByKeyOrAlias(url.getKey())).thenReturn(Optional.of(urlEntity));
        doNothing().when(repository).delete(urlEntity);
        urlEntity.setExpiresAt(LocalDateTime.of(2020,10,20,0,0));

        Assertions.assertThrows(UrlHasExpiredException.class, () -> adapter.findByKeyOrAlias(url.getKey()));
        verify(repository, times(1)).findByKeyOrAlias(url.getKey());
        verify(repository, times(1)).delete(urlEntity);
        verify(repository, never()).save(urlEntity);
    }

    @Test
    void findByKeyOrAlias_addHitAndReturnUrl_whenUrlExists(){
        url.setHit(1);
        when(repository.findByKeyOrAlias(url.getKey())).thenReturn(Optional.of(urlEntity));

        Url urlFound = adapter.findByKeyOrAlias(url.getKey());
        Assertions.assertEquals(urlFound, url);
        Assertions.assertEquals(urlFound.getAlias(), url.getAlias());
        Assertions.assertEquals(urlFound.getUrl(), url.getUrl());
        Assertions.assertEquals(urlFound.getKey(), url.getKey());
        Assertions.assertTrue(urlFound.getHit() == 1);
        verify(repository, times(1)).findByKeyOrAlias(url.getKey());
        verify(repository, times(1)).save(urlEntity);
    }

    @Test
    void save_returnSavedUrl() {
        when(repository.save(urlEntity)).thenReturn(urlEntity);
        Url urlCreated = adapter.createUrl(url);

        Assertions.assertEquals(urlCreated, url);
        Assertions.assertEquals(urlCreated.getAlias(), url.getAlias());
        Assertions.assertEquals(urlCreated.getUrl(), url.getUrl());
        Assertions.assertEquals(urlCreated.getKey(), url.getKey());
        verify(repository, times(1)).save(urlEntity);
    }

    @Test
    void createUrl_throwAliasAlreadyExistsException_whenAliasAlreadyExists(){
        when(repository.existsByAlias(url.getAlias())).thenReturn(true);

        Assertions.assertThrows(AliasAlreadyExistsException.class, () -> adapter.createUrl(url));
        verify(repository, times(1)).existsByAlias(url.getAlias());
        verify(repository, never()).save(urlEntity);
    }

    @Test
    void updateUrl_throwUrlNotFoundException_whenUrlNotExists(){
        when(repository.findByKeyOrAlias(url.getKey())).thenReturn(Optional.empty());

        Assertions.assertThrows(UrlNotFoundException.class, () -> adapter.updateUrl(url, url.getKey()));
        verify(repository, times(1)).findByKeyOrAlias(url.getKey());
        verify(repository, never()).save(urlEntity);
    }

    @Test
    void updateUrl_returnUpdatedUrl(){
        when(repository.findByKeyOrAlias(url.getKey())).thenReturn(Optional.of(urlEntity));
        when(repository.save(urlEntity)).thenReturn(urlEntity);

        Url urlUpdated = adapter.updateUrl(url, url.getKey());
        Assertions.assertEquals(urlUpdated, url);
        Assertions.assertEquals(urlUpdated.getAlias(), url.getAlias());
        Assertions.assertEquals(urlUpdated.getUrl(), url.getUrl());
        Assertions.assertEquals(urlUpdated.getKey(), url.getKey());
        verify(repository, times(1)).findByKeyOrAlias(url.getKey());
        verify(repository, times(1)).save(urlEntity);
    }

    @Test
    void updateUrl_throwAliasAlreadyExistsException_whenAliasAlreadyExists(){
        when(repository.findByKeyOrAlias(url.getKey())).thenReturn(Optional.of(urlEntity));
        when(repository.existsByAlias(url.getAlias())).thenReturn(true);

        Assertions.assertThrows(AliasAlreadyExistsException.class, () -> adapter.updateUrl(url, url.getKey()));
        verify(repository, times(1)).findByKeyOrAlias(url.getKey());
        verify(repository, times(1)).existsByAlias(url.getAlias());
        verify(repository, never()).save(urlEntity);
    }

    @Test
    void deleteUrl_throwUrlNotFoundException_whenUrlNotExists(){
        when(repository.findByKeyOrAlias(url.getKey())).thenReturn(Optional.empty());

        Assertions.assertThrows(UrlNotFoundException.class, () -> adapter.deleteUrl(url.getKey()));
        verify(repository, times(1)).findByKeyOrAlias(url.getKey());
        verify(repository, never()).delete(urlEntity);
    }

    @Test
    void deleteUrl_returnDeletedUrl(){
        when(repository.findByKeyOrAlias(url.getKey())).thenReturn(Optional.of(urlEntity));
        doNothing().when(repository).delete(urlEntity);

        Url urlDeleted = adapter.deleteUrl(url.getKey());
        Assertions.assertEquals(urlDeleted, new Url());
        verify(repository, times(1)).findByKeyOrAlias(url.getKey());
        verify(repository, times(1)).delete(urlEntity);
    }

}
