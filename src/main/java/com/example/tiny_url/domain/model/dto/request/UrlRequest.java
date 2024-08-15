package com.example.tiny_url.domain.model.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlRequest {

    @NotNull(message = "")
    @NotEmpty(message = "")
    @Size(min = 5, max = 80, message = "")
    private String url;

    @NotEmpty(message = "")
    @Size(max = 25, message = "")
    private String alias;

    @Future(message = "")
    private LocalDateTime expiresAt;
}
