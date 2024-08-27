package com.example.tiny_url.infrastucture.adapter.entity;

import com.example.tiny_url.infrastructure.adapter.entity.UrlEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UrlEntityTest {

    @Test
    public void testEqualsAndHashCode() {
        UrlEntity url1 = new UrlEntity().builder()
                .key("key")
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .hit(0)
                .build();
        UrlEntity url2 = new UrlEntity().builder()
                .key("key")
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .hit(0)
                .build();
        UrlEntity url3 = new UrlEntity().builder()
                .key("key")
                .alias("diferentAlias")
                .url("url")
                .expiresAt(null)
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
        UrlEntity url = new UrlEntity().builder()
                .key("key")
                .alias("alias")
                .url("url")
                .expiresAt(null)
                .hit(0)
                .build();
        String expected = "UrlEntity(id=null, key=key, url=url, hit=0, alias=alias, expiresAt=null, createdAt=null)";
        assertEquals(expected, url.toString());
    }

}
