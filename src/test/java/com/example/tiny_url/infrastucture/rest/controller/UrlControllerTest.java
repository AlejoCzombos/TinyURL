package com.example.tiny_url.infrastucture.rest.controller;

import com.example.tiny_url.domain.model.dto.UrlDto;
import com.example.tiny_url.domain.model.dto.request.UrlCreate;
import com.example.tiny_url.domain.model.dto.request.UrlUpdate;
import com.example.tiny_url.domain.port.service.UrlService;
import com.example.tiny_url.infrastructure.rest.controller.UrlController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

@WebMvcTest(UrlController.class)
@AutoConfigureMockMvc
public class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UrlCreate urlCreate;
    private UrlUpdate urlUpdate;
    private UrlDto urlDto;
    private String key;
    @BeforeEach
    public void setUp() {
        key = "key";
        urlCreate = UrlCreate.builder().alias("alias").url("url.com").build();
        urlUpdate = UrlUpdate.builder().alias("alias").url("url.com").build();
        urlDto = UrlDto.builder().alias("alias").url("url.com").key("key").createdAt(LocalDateTime.of(2024,10,20,0,0)).build();
    }

    @Test
    public void getAllUrls_returnAllUrls_whenUrlsExists() throws Exception {
        when(service.findAll()).thenReturn(List.of(urlDto));

        ResultActions results = mockMvc.perform(get("/api/urls/")
                .contentType("application/json")
        );

        results.andExpect(
                status().isOk())
                .andExpect(jsonPath("$[0].alias").value(urlDto.getAlias()))
                .andExpect(jsonPath("$[0].url").value(urlDto.getUrl()))
                .andDo(print());
    }

    @Test
    public void getUrlByIdOrAlias_returnUrl_whenUrlExists() throws Exception {
        when(service.findByKeyOrAlias(key)).thenReturn(urlDto);

        ResultActions results = mockMvc.perform(get("/api/urls/{key}", key)
                .contentType("application/json")
        );

        results.andExpect(
                status().isOk())
                .andExpect(jsonPath("$.alias").value(urlDto.getAlias()))
                .andExpect(jsonPath("$.url").value(urlDto.getUrl()))
                .andDo(print());
    }

    @Test
    public void createUrl_returnUrl_whenUrlIsCreated() throws Exception {
        when(service.createUrl(urlCreate)).thenReturn(urlDto);

        ResultActions results = mockMvc.perform(post("/api/urls/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(urlCreate))
        );

        results.andExpect(
                status().isCreated())
                .andExpect(jsonPath("$.alias").value(urlDto.getAlias()))
                .andExpect(jsonPath("$.url").value(urlDto.getUrl()))
                .andDo(print());
    }

    @Test
    public void createUrl_throwValidationException_whenUrlIsInvalid() throws Exception {
        urlCreate.setUrl("url");
        when(service.createUrl(urlCreate)).thenReturn(urlDto);

        ResultActions results = mockMvc.perform(post("/api/urls/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(urlCreate))
        );

        results.andExpect(
                status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void updateUrlById_returnUrl_whenUrlIsUpdated() throws Exception {
        when(service.updateUrl(urlUpdate, key)).thenReturn(urlDto);

        ResultActions results = mockMvc.perform(put("/api/urls/{key}", key)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(urlUpdate))
        );

        results.andExpect(
                status().isOk())
                .andExpect(jsonPath("$.alias").value(urlDto.getAlias()))
                .andExpect(jsonPath("$.url").value(urlDto.getUrl()))
                .andDo(print());
    }

    @Test
    public void updateUrlById_throwValidationException_whenUrlIsInvalid() throws Exception {
        urlUpdate.setUrl("url");
        when(service.updateUrl(urlUpdate, key)).thenReturn(urlDto);

        ResultActions results = mockMvc.perform(put("/api/urls/{key}", key)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(urlUpdate))
        );

        results.andExpect(
                status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void deleteUrlById_returnNoContent_whenUrlIsDeleted() throws Exception {
        when(service.deleteUrl(key)).thenReturn(urlDto);

        ResultActions results = mockMvc.perform(delete("/api/urls/{key}", key)
                .contentType("application/json")
        );

        results.andExpect(
                status().isNoContent())
                .andDo(print());
    }

}
