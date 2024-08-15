package com.example.tiny_url.domain.port.service;

import com.example.tiny_url.domain.model.dto.UrlDto;
import com.example.tiny_url.domain.model.dto.request.UrlCreate;
import com.example.tiny_url.domain.model.dto.request.UrlUpdate;

import java.util.List;

public interface UrlService {
    List<UrlDto> findAll();
    UrlDto findByIdOrAlias(String idOrAlias);
    UrlDto createUrl(UrlCreate request);
    UrlDto updateUrl(UrlUpdate request, String id);
    UrlDto deleteUrl(String id);
}
