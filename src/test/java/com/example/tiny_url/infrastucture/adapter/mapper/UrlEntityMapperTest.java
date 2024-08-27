package com.example.tiny_url.infrastucture.adapter.mapper;

import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.infrastructure.adapter.entity.UrlEntity;
import com.example.tiny_url.infrastructure.adapter.mapper.UrlEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UrlEntityMapperTest {

    private UrlEntity urlEntity;
    private Url url;

    @BeforeEach
    public void setUp() {
        urlEntity = new UrlEntity().builder()
                .key("key")
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .hit(0)
                .build();

        url = new Url().builder()
                .key("key")
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .hit(0)
                .build();
    }

    @Test
    public void testToDomain() {
        Url result = UrlEntityMapper.toDomain(urlEntity);

        assertEquals(url, result);
    }

    @Test
    public void testToEntity() {
        UrlEntity result = UrlEntityMapper.toEntity(url);

        assertEquals(urlEntity, result);
    }

}
