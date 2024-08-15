package com.example.tiny_url.application.service;

import com.example.tiny_url.domain.model.dto.UrlDto;
import com.example.tiny_url.domain.model.dto.request.UrlRequest;
import com.example.tiny_url.domain.port.service.UrlService;

import java.util.List;

public class UrlServiceImpl implements UrlService {
    @Override
    public List<UrlDto> findAll() {
        return null;
    }

    @Override
    public UrlDto findByIdOrAlias(String id) {
        return null;
    }

    @Override
    public void redirectToUrl(String id) {

    }

    @Override
    public UrlDto createUrl(UrlRequest request) {
        return null;
    }

    @Override
    public UrlDto updateUrl(UrlRequest request) {
        return null;
    }

    @Override
    public void deleteUrl(String id) {

    }
}
