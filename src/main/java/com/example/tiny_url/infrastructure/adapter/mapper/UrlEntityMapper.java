package com.example.tiny_url.infrastructure.adapter.mapper;

import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.infrastructure.adapter.entity.UrlEntity;

public class UrlEntityMapper {

    public static UrlEntity toEntity(Url domain){
        return UrlEntity.builder()
                .key(domain.getKey())
                .url(domain.getUrl())
                .alias(domain.getAlias())
                .hit(domain.getHit())
                .expiresAt(domain.getExpiresAt())
                .createdAt(domain.getCreatedAt())
                .build();
    }

    public static Url toDomain(UrlEntity entity){
        return Url.builder()
                .key(entity.getKey())
                .url(entity.getUrl())
                .alias(entity.getAlias())
                .hit(entity.getHit())
                .expiresAt(entity.getExpiresAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
