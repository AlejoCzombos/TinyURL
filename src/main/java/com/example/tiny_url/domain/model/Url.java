package com.example.tiny_url.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    private String id;
    private String url;
    private String alias;
    private int hit;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
}
