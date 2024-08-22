package com.example.tiny_url.infrastructure.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Tag(name = "Redirection")
public interface RedirectionControllerApi {

    @Operation(
            summary = "Redirect to the URL"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Redirected successfully", content = @io.swagger.v3.oas.annotations.media.Content),
            @ApiResponse(responseCode = "400", description = "Url has expired"),
            @ApiResponse(responseCode = "404", description = "URL not found by key or alias"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(
            value = "/{keyOrAlias}",
            method = RequestMethod.GET
    )
    RedirectView redirect(@PathVariable String keyOrAlias);

}
