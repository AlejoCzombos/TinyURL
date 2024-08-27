package com.example.tiny_url.application.mapper;
import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.domain.model.dto.UrlDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class UrlDtoMapperTest {

    private UrlDto urlDto;
    private Url url;

    @BeforeEach
    public void setUp() {
        urlDto = new UrlDto().builder()
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
        UrlDto result = UrlDtoMapper.toDto(url);

        assertEquals(urlDto, result);
    }

}
