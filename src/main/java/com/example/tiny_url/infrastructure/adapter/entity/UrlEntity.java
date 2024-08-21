package com.example.tiny_url.infrastructure.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "url")
public class UrlEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String key;
    private String url;
    private int hit;
    private String alias;
    private LocalDateTime expiresAt;
    @CreatedDate
    private LocalDateTime createdAt;
}
