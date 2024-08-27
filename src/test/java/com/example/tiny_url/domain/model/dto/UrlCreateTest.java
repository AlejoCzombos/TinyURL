package com.example.tiny_url.domain.model.dto;

import com.example.tiny_url.domain.model.dto.request.UrlCreate;
import com.example.tiny_url.infrastructure.adapter.entity.UrlEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UrlCreateTest {

    @Test
    public void testEqualsAndHashCode() {
        UrlCreate url1 = new UrlCreate().builder()
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .build();
        UrlCreate url2 = new UrlCreate().builder()
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .build();
        UrlCreate url3 = new UrlCreate().builder()
                .alias("diferentAlias")
                .url("url")
                .expiresAt(null)
                .build();

        // Reflexividad
        assertEquals(url1, url1);

        // Simetría
        assertEquals(url1, url2);
        assertEquals(url2, url1);

        // Transitividad no aplica con la impolementación actual

        // Consistencia
        assertEquals(url1.hashCode(), url2.hashCode());

        // Comparación con objeto null
        assertNotEquals(url1, null);

        // Comparación de objetos de diferente tipo
        assertNotEquals(url1, "url");
        assertNotEquals(url1, 1);
        assertNotEquals(url1, url3);
    }

    @Test
    public void testToString() {
        UrlCreate url = new UrlCreate().builder()
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .build();
        String expected = "UrlCreate(url=url, alias=alias, expiresAt=null)";
        assertEquals(expected, url.toString());
    }

}
