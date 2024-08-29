package com.example.tiny_url.domain.model;

import com.example.tiny_url.domain.model.Url;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UrlTest {

    @Test
    public void testEqualsAndHashCode() {
        Url url1 = new Url().builder()
                .key("key")
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .createdAt(null)
                .hit(0)
                .build();
        Url url2 = new Url().builder()
                .key("key")
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .createdAt(null)
                .hit(0)
                .build();
        Url url3 = new Url().builder()
                .key("key")
                .alias("diferentAlias")
                .url("url")
                .expiresAt(null)
                .createdAt(null)
                .hit(0)
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
        Url url = new Url().builder()
                .key("key")
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .createdAt(null)
                .hit(0)
                .build();

        assertEquals("Url(key=key, url=url, alias=alias, hit=0, expiresAt=null, createdAt=null)", url.toString());
    }

}
