package com.example.tiny_url.application.service;

import com.example.tiny_url.application.mapper.UrlCreateMapper;
import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.domain.model.dto.UrlDto;
import com.example.tiny_url.domain.model.dto.request.UrlCreate;
import com.example.tiny_url.domain.model.dto.request.UrlUpdate;
import com.example.tiny_url.infrastructure.adapter.UrlMongoDBAdapter;
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

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

    @Mock
    private UrlMongoDBAdapter adapter;

    @InjectMocks
    private UrlServiceImpl service;

    private Url url;
    private UrlCreate urlCreate;
    private UrlUpdate urlUpdate;
    private UrlDto urlDto;

    @BeforeEach
    public void setUp() {
        url = Url.builder().alias("alias").url("url").key("key").createdAt(LocalDateTime.of(2024,10,20,0,0)).build();
        urlCreate = UrlCreate.builder().alias("alias").url("url").build();
        urlUpdate = UrlUpdate.builder().alias("alias").url("url").build();
        urlDto = UrlDto.builder().alias("alias").url("url").key("key").createdAt(LocalDateTime.of(2024,10,20,0,0)).build();
    }

    @Test
    public void findAll_returnAllUrls_whenUrlsExists() {
        when(adapter.findAll()).thenReturn(List.of(url));
        List<UrlDto> urls = service.findAll();

        Assertions.assertEquals(List.of(urlDto), urls);
        verify(adapter, times(1)).findAll();
    }

    @Test
    public void findByKeyOrAlias_returnUrl_whenUrlExists() {
        when(adapter.findByKeyOrAlias(url.getKey())).thenReturn(url);
        UrlDto urlDto = service.findByKeyOrAlias(url.getKey());

        Assertions.assertEquals(this.urlDto, urlDto);
        verify(adapter, times(1)).findByKeyOrAlias(url.getKey());
    }

    @Test
    public void createUrl_returnUrl_whenUrlIsCreated() {
        Url urlToSave = Url.builder().alias("alias").url("url").build();
        when(adapter.createUrl(urlToSave)).thenReturn(url);
        UrlDto urlDto = service.createUrl(urlCreate);

        Assertions.assertEquals(this.urlDto, urlDto);
        verify(adapter, times(1)).createUrl(urlToSave);
    }

    @Test
    public void updateUrl_returnUrl_whenUrlIsUpdated() {
        Url urlToUpdate = Url.builder().alias("alias").url("url").build();
        when(adapter.updateUrl(urlToUpdate, url.getKey())).thenReturn(url);
        UrlDto urlDto = service.updateUrl(urlUpdate, url.getKey());

        Assertions.assertEquals(this.urlDto, urlDto);
        verify(adapter, times(1)).updateUrl(urlToUpdate, url.getKey());
    }

    @Test
    public void deleteUrl_returnUrl_whenUrlIsDeleted() {
        when(adapter.deleteUrl(url.getKey())).thenReturn(url);
        UrlDto urlDto = service.deleteUrl(url.getKey());

        Assertions.assertEquals(this.urlDto, urlDto);
        verify(adapter, times(1)).deleteUrl(url.getKey());
    }

}
