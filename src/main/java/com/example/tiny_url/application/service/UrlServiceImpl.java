package com.example.tiny_url.application.service;

import com.example.tiny_url.application.mapper.UrlCreateMapper;
import com.example.tiny_url.application.mapper.UrlDtoMapper;
import com.example.tiny_url.application.mapper.UrlUpdateMapper;
import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.domain.model.dto.UrlDto;
import com.example.tiny_url.domain.model.dto.request.UrlCreate;
import com.example.tiny_url.domain.model.dto.request.UrlUpdate;
import com.example.tiny_url.domain.port.service.UrlService;
import com.example.tiny_url.infrastructure.adapter.UrlMongoDBAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UrlServiceImpl implements UrlService {

    private final UrlMongoDBAdapter repository;

    @Override
    public List<UrlDto> findAll() {
        List<Url> urls = repository.findAll();

        return urls.stream().map(UrlDtoMapper::toDto).toList();
    }

    @Override
    public UrlDto findByKeyOrAlias(String keyOrAlias) {
        Url url = repository.findByKeyOrAlias(keyOrAlias);

        return UrlDtoMapper.toDto(url);
    }

    @Override
    public UrlDto createUrl(UrlCreate request) {
        Url urlToSave = UrlCreateMapper.toDomain(request);

        Url urlSaved = repository.createUrl(urlToSave);

        return UrlDtoMapper.toDto(urlSaved);
    }

    @Override
    public UrlDto updateUrl(UrlUpdate request, String key) {
        Url urlToUpdate = UrlUpdateMapper.toDomain(request);

        Url urlUpdated = repository.updateUrl(urlToUpdate, key);

        return UrlDtoMapper.toDto(urlUpdated);
    }

    @Override
    public UrlDto deleteUrl(String key) {
        Url urlDeleted = repository.deleteUrl(key);

        return UrlDtoMapper.toDto(urlDeleted);
    }
}
