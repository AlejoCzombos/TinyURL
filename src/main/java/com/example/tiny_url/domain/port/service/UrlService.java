package com.example.tiny_url.domain.port.service;

import com.example.tiny_url.domain.model.dto.UrlDto;
import com.example.tiny_url.domain.model.dto.request.UrlCreate;
import com.example.tiny_url.domain.model.dto.request.UrlUpdate;

import java.util.List;

public interface UrlService {
    List<UrlDto> findAll();
    UrlDto findByKeyOrAlias(String keyOrAlias);
    UrlDto createUrl(UrlCreate request);
    UrlDto updateUrl(UrlUpdate request, String key);
    UrlDto deleteUrl(String key);
}
