package com.example.tiny_url.infrastructure.rest.controller;

import com.example.tiny_url.domain.model.dto.UrlDto;
import com.example.tiny_url.domain.model.dto.request.UrlCreate;
import com.example.tiny_url.domain.model.dto.request.UrlUpdate;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/api/url")
public interface UrlControllerApi {

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    ResponseEntity<List<UrlDto>> getAllUrls();

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    ResponseEntity<UrlDto> getUrlByIdOrAlias(@PathVariable String id);

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST
    )
    ResponseEntity<UrlDto> createUrl(@Valid @RequestBody UrlCreate request);

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT
    )
    ResponseEntity<UrlDto> updateUrlById(
            @PathVariable String id,
            @Valid @RequestBody UrlUpdate request
    );

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    ResponseEntity<UrlDto> deleteUrlById(@PathVariable String id);

}
