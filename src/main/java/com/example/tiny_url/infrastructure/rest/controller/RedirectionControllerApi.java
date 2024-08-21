package com.example.tiny_url.infrastructure.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

public interface RedirectionControllerApi {

    @RequestMapping(
            value = "/{keyOrAlias}",
            method = RequestMethod.GET
    )
    RedirectView redirect(@PathVariable String keyOrAlias);

}
