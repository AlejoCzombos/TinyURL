package com.example.tiny_url.application.mapper;

import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.domain.model.dto.request.UrlCreate;

public class UrlCreateMapper {

    public static Url toDomain(UrlCreate request){
        return Url.builder()
                .url(request.getUrl())
                .alias(request.getAlias())
                .expiresAt(request.getExpiresAt())
                .build();
    }

}
