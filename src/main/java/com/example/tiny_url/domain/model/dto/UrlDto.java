package com.example.tiny_url.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UrlDto {
    private String key;
    private String url;
    private String alias;
    private int hit;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
}