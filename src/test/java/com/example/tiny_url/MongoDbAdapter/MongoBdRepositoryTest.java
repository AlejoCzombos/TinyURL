package com.example.tiny_url.MongoDbAdapter;

import com.example.tiny_url.infrastructure.adapter.entity.UrlEntity;
import com.example.tiny_url.infrastructure.adapter.repository.UrlMongoDBRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

@DataMongoTest
public class MongoBdRepositoryTest {

    @Autowired
    private UrlMongoDBRepository repository;

    private UrlEntity url;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
        url = UrlEntity.builder().alias("alias").url("url").key("key").build();
    }

    @Test
    public void UrlRepository_save_returnSavedUrl() {
        UrlEntity urlSaved = repository.save(url);

        Assertions.assertNotNull(urlSaved.getKey());
        Assertions.assertTrue(repository.count() > 0);
    }

    @Test
    public void UrlRepository_findByKeyOrAlias_returnUrl() {
        repository.save(url);
        UrlEntity urlFound = repository.findByKeyOrAlias("alias").orElse(null);

        Assertions.assertNotNull(urlFound);
        Assertions.assertEquals(url, urlFound);
    }

    @Test
    public void UrlRepository_getAll_returnGraterThanZero() {
        repository.save(url);

        List<UrlEntity> urls = repository.findAll();
        Assertions.assertTrue(urls.size() > 0);
        Assertions.assertTrue(urls != null);
    }

    @Test
    public void UrlRepository_delete_returnZero() {
        repository.save(url);
        repository.delete(url);

        Assertions.assertTrue(repository.count() == 0);
    }

    @Test
    public void UrlRepository_deleteAll_returnZero() {
        repository.save(url);
        repository.deleteAll();

        Assertions.assertTrue(repository.count() == 0);
    }

    @Test
    public void UrlRepository_update_returnUpdatedUrl() {
        repository.save(url);
        url.setAlias("newAlias");
        UrlEntity urlUpdated = repository.save(url);

        Assertions.assertEquals(url, urlUpdated);
    }

}
