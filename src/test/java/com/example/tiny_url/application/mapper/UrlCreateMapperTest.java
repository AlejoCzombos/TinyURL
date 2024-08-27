package com.example.tiny_url.application.mapper;

import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.domain.model.dto.request.UrlCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class UrlCreateMapperTest {

    private UrlCreate urlCreate;
    private Url url;

    @BeforeEach
    public void setUp() {
        urlCreate = new UrlCreate().builder()
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .build();

        url = new Url().builder()
                .key(null)
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .build();
    }

    @Test
    public void testToDomain() {
        Url result = UrlCreateMapper.toDomain(urlCreate);

        assertEquals(url, result);
    }
}
