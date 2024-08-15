package com.example.tiny_url.application.mapper;

import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.domain.model.dto.UrlDto;

public class UrlDtoMapper {

    public static UrlDto toDto(Url domain){
        return UrlDto.builder()
                .key(domain.getKey())
                .url(domain.getUrl())
                .alias(domain.getAlias())
                .hit(domain.getHit())
                .expiresAt(domain.getExpiresAt())
                .createdAt(domain.getCreatedAt())
                .build();
    }

}
