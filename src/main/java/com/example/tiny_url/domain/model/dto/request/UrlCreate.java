package com.example.tiny_url.domain.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlCreate {

    @NotEmpty(message = "The URL is required.")
    @Size(min = 5, max = 80, message = "The URL must be between 5 and 80 characters.")
    private String url;

    @NotBlank(message = "The alias must not be blank.")
    @Size(max = 25, message = "The alias must be less than 25 characters.")
    private String alias;

    @Future(message = "The expiration date must be in the future.")
    private LocalDateTime expiresAt;
}
