package com.example.tiny_url.application.mapper;

import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.domain.model.dto.request.UrlUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class UrlUpdateMapperTest {

    private UrlUpdate urlUpdate;
    private Url url;

    @BeforeEach
    public void setUp() {
        urlUpdate = new UrlUpdate().builder()
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
        Url result = UrlUpdateMapper.toDomain(urlUpdate);

        assertEquals(url, result);
    }
}
