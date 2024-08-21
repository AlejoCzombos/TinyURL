package com.example.tiny_url.infrastructure.rest.controller;

import com.example.tiny_url.domain.model.dto.UrlDto;
import com.example.tiny_url.domain.port.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
public class RedirectionController implements RedirectionControllerApi {

    private final UrlService service;

    @Override
    public RedirectView redirect(String keyOrAlias) {
        UrlDto url = service.findByKeyOrAlias(keyOrAlias);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url.getUrl());

        return redirectView;
    }
}
