package com.example.tiny_url.infrastructure.rest.controller;

import com.example.tiny_url.domain.model.dto.UrlDto;
import com.example.tiny_url.domain.model.dto.request.UrlCreate;
import com.example.tiny_url.domain.model.dto.request.UrlUpdate;
import com.example.tiny_url.domain.port.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UrlController implements UrlControllerApi {

    private final UrlService service;

    @Override
    public ResponseEntity<List<UrlDto>> getAllUrls() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UrlDto> getUrlByIdOrAlias(String key) {
        return new ResponseEntity<>(service.findByKeyOrAlias(key), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UrlDto> createUrl(UrlCreate request) {
        return new ResponseEntity<>(service.createUrl(request), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UrlDto> updateUrlById(String key, UrlUpdate request) {
        return new ResponseEntity<>(service.updateUrl(request, key), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UrlDto> deleteUrlById(String key) {
        return new ResponseEntity<>(service.deleteUrl(key), HttpStatus.NO_CONTENT);
    }
}
