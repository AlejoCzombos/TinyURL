package com.example.tiny_url.application.mapper;

import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.domain.model.dto.request.UrlUpdate;

public class UrlUpdateMapper {

    public static Url toDomain(UrlUpdate request){
        return Url.builder()
                .url(request.getUrl())
                .alias(request.getAlias())
                .expiresAt(request.getExpiresAt())
                .build();
    }

}
