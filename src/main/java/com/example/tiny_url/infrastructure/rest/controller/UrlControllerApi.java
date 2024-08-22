package com.example.tiny_url.infrastructure.rest.controller;

import com.example.tiny_url.domain.model.dto.UrlDto;
import com.example.tiny_url.domain.model.dto.request.UrlCreate;
import com.example.tiny_url.domain.model.dto.request.UrlUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Tag(name = "Urls")
@RequestMapping("/api/urls")
public interface UrlControllerApi {

    @Operation(
            summary = "Get all URLs"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    ResponseEntity<List<UrlDto>> getAllUrls();

    @Operation(
            summary = "Get URL by ID or alias"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Url found successfully"),
            @ApiResponse(responseCode = "400", description = "Url has expired or Invalid input"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(
            value = "/{key}",
            method = RequestMethod.GET
    )
    ResponseEntity<UrlDto> getUrlByIdOrAlias(@PathVariable String key);

    @Operation(
            summary = "Create URL"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Url created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or Alias already exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Url to be created",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UrlCreate.class)
            )
    )
    @RequestMapping(
            value = "/",
            method = RequestMethod.POST
    )
    ResponseEntity<UrlDto> createUrl(@Valid @RequestBody UrlCreate request);

    @Operation(
            summary = "Update URL by key"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Url updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or Alias already exists"),
            @ApiResponse(responseCode = "404", description = "Url not found with key"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Url to be updated",
            required = true,
            content = @io.swagger.v3.oas.annotations.media.Content(
                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UrlUpdate.class)
            )
    )
    @RequestMapping(
            value = "/{key}",
            method = RequestMethod.PUT
    )
    ResponseEntity<UrlDto> updateUrlById(
            @PathVariable String key,
            @Valid @RequestBody UrlUpdate request
    );

    @Operation(
            summary = "Delete URL by key"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Url deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Url not found with key"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(
            value = "/{key}",
            method = RequestMethod.DELETE
    )
    ResponseEntity<UrlDto> deleteUrlById(@PathVariable String key);

}
