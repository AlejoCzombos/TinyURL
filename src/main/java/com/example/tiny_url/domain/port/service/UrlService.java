package com.example.tiny_url.domain.port.service;

import com.example.tiny_url.domain.model.dto.UrlDto;
import com.example.tiny_url.domain.model.dto.request.UrlRequest;

import java.util.List;

public interface UrlService {
    List<UrlDto> findAll();
    UrlDto findByIdOrAlias(String id);
    void redirectToUrl(String id);
    UrlDto createUrl(UrlRequest request);
    UrlDto updateUrl(UrlRequest request);
    void deleteUrl(String id);
}
