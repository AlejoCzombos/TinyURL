package com.example.tiny_url.infrastructure.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "url")
public class UrlEntity {
    private String id;
    private String url;
    private int hit;
    private String alias;
    private LocalDateTime expiresAt;
    @CreatedDate
    private LocalDateTime createdAt;
}
