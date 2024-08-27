package com.example.tiny_url.domain.model.dto;

import com.example.tiny_url.domain.model.dto.request.UrlUpdate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UrlUpdateTest {

    @Test
    public void testEqualsAndHashCode() {
        UrlUpdate url1 = new UrlUpdate().builder()
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .build();
        UrlUpdate url2 = new UrlUpdate().builder()
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .build();
        UrlUpdate url3 = new UrlUpdate().builder()
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
        UrlUpdate url = new UrlUpdate().builder()
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .build();
        String expected = "UrlUpdate(url=url, alias=alias, expiresAt=null)";
        assertEquals(expected, url.toString());
    }

}
