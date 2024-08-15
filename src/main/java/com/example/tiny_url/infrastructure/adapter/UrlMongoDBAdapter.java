package com.example.tiny_url.infrastructure.adapter;

import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.domain.port.repository.UrlRepositoryPort;
import com.example.tiny_url.infrastructure.adapter.entity.UrlEntity;
import com.example.tiny_url.infrastructure.adapter.mapper.UrlEntityMapper;
import com.example.tiny_url.infrastructure.adapter.repository.UrlMongoDBRepository;
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
    public Url findByIdOrAlias(String id) {
        Optional<UrlEntity> optionalUrl = repository.findByIdOrAlias(id);

        if (optionalUrl.isEmpty()) {
            throw new RuntimeException("Url not found with ID or Alias: " + id);
        }

        UrlEntity url = optionalUrl.get();
        return UrlEntityMapper.toDomain(url);
    }

    @Override
    public Url createUrl(Url url) {
        UrlEntity urlToSave = UrlEntityMapper.toEntity(url);

        if (repository.existsByAlias(urlToSave.getAlias())) {
            throw new RuntimeException("Alias already exists: " + urlToSave.getAlias());
        }

        urlToSave.setCreatedAt(LocalDateTime.now());
        urlToSave.setKey(generateKey(urlToSave.getUrl()));

        UrlEntity urlSaved = repository.save(urlToSave);

        return UrlEntityMapper.toDomain(urlSaved);
    }

    @Override
    public Url updateUrl(Url url, String id) {
        UrlEntity urlToUpdate = UrlEntityMapper.toEntity(url);

        UrlEntity urlUpdated = repository.save(urlToUpdate);

        return UrlEntityMapper.toDomain(urlUpdated);
    }

    @Override
    public Url deleteUrl(String id) {
        UrlEntity urlToDelete = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Url not found with ID: " + id)
        );

        repository.delete(urlToDelete);

        return UrlEntityMapper.toDomain(null);
    }

    private String generateKey(String url){
        String key = Hashing.murmur3_32().hashString(url + LocalDateTime.now(), Charset.defaultCharset()).toString();
        return key;
    }
}
