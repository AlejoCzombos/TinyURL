package com.example.tiny_url.infrastructure.adapter;

import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.domain.port.repository.UrlRepositoryPort;
import com.example.tiny_url.infrastructure.adapter.entity.UrlEntity;
import com.example.tiny_url.infrastructure.adapter.mapper.UrlEntityMapper;
import com.example.tiny_url.infrastructure.adapter.repository.UrlMongoDBRepository;
import com.example.tiny_url.infrastructure.rest.interceptor.exception.AliasAlreadyExistsException;
import com.example.tiny_url.infrastructure.rest.interceptor.exception.UrlHasExpiredException;
import com.example.tiny_url.infrastructure.rest.interceptor.exception.UrlNotFoundException;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UrlMongoDBAdapter implements UrlRepositoryPort {

    private final UrlMongoDBRepository repository;

    @Override
    public List<Url> findAll() {
        List<UrlEntity> urls = repository.findAll();

        return urls.stream().map(UrlEntityMapper::toDomain).toList();
    }

    @Override
    public Url findByKeyOrAlias(String key) {
        Optional<UrlEntity> optionalUrl = repository.findByKeyOrAlias(key);

        if (optionalUrl.isEmpty()) {
            throw new UrlNotFoundException(UrlNotFoundException.URL_NOT_FOUND + key);
        }

        UrlEntity url = optionalUrl.get();

        if (url.getExpiresAt() != null && url.getExpiresAt().isBefore(LocalDateTime.now())) {
            repository.delete(url);
            throw new UrlHasExpiredException(UrlHasExpiredException.MESSAGE_URL_HAS_EXPIRED);
        }

        // Actualizar el numero de visitas para el recuento total
        url.setHit(url.getHit() + 1);
        repository.save(url);

        return UrlEntityMapper.toDomain(url);
    }

    @Override
    public Url createUrl(Url url) {
        if (url.getAlias() != null && checkExistsByAlias(url.getAlias())) {
            throw new AliasAlreadyExistsException(AliasAlreadyExistsException.MESSAGE_ALIAS_ALREADY_EXISTS);
        }

        UrlEntity urlToSave = UrlEntityMapper.toEntity(url);

        if (urlToSave.getKey() == null || urlToSave.getKey().isEmpty()) {
            urlToSave.setKey(generateKey(urlToSave.getUrl()));
        }
        if (urlToSave.getCreatedAt() == null) {
            urlToSave.setCreatedAt(LocalDateTime.now());
        }

        UrlEntity urlSaved = repository.save(urlToSave);

        return UrlEntityMapper.toDomain(urlSaved);
    }

    @Override
    public Url updateUrl(Url url, String key) {
        UrlEntity existingUrl = repository.findByKeyOrAlias(key).orElseThrow(
                () -> new UrlNotFoundException(UrlNotFoundException.URL_NOT_FOUND + key)
        );

        if (url.getAlias() != null && !url.getAlias().isEmpty()) {

            if (checkExistsByAlias(url.getAlias())) {
                throw new AliasAlreadyExistsException(AliasAlreadyExistsException.MESSAGE_ALIAS_ALREADY_EXISTS);
            }
            existingUrl.setAlias(url.getAlias());
        }
        if (url.getUrl() != null && !url.getUrl().isEmpty()) {
            existingUrl.setUrl(url.getUrl());
        }
        if (url.getExpiresAt() != null) {
            existingUrl.setExpiresAt(url.getExpiresAt());
        }

        UrlEntity urlUpdated = repository.save(existingUrl);
        return UrlEntityMapper.toDomain(urlUpdated);
    }

    @Override
    public Url deleteUrl(String key) {
        UrlEntity urlToDelete = repository.findByKeyOrAlias(key).orElseThrow(
                () -> new UrlNotFoundException(UrlNotFoundException.URL_NOT_FOUND + key)
        );

        repository.delete(urlToDelete);

        return UrlEntityMapper.toDomain(new UrlEntity());
    }

    private boolean checkExistsByAlias(String alias){
        return repository.existsByAlias(alias);
    }

    private String generateKey(String url){
        return Hashing.murmur3_32().hashString(url + LocalDateTime.now(), Charset.defaultCharset()).toString();
    }
}
