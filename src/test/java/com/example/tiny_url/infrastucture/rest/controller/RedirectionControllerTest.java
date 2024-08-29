package com.example.tiny_url.infrastucture.rest.controller;

import com.example.tiny_url.domain.model.dto.UrlDto;
import com.example.tiny_url.domain.port.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
@AutoConfigureMockMvc
public class RedirectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlService service;

    @Test
    public void redirect_returnRedirectView_whenKeyOrAliasExists() throws Exception {
        when(service.findByKeyOrAlias("keyOrAlias")).thenReturn(UrlDto.builder().url("www.google.com").build());

        mockMvc.perform(get("/{keyOrAlias}", "keyOrAlias"))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

}
