package com.example.tiny_url.infrastructure.adapter;

import com.example.tiny_url.domain.model.Url;
import com.example.tiny_url.domain.port.repository.UrlRepositoryPort;

import java.util.List;

public class UrlMongoDBAdapter implements UrlRepositoryPort {
    @Override
    public List<Url> findAll() {
        return null;
    }

    @Override
    public Url findByIdOrAlias(String id) {
        return null;
    }

    @Override
    public Url redirectToUrl(String id) {
        return null;
    }

    @Override
    public Url createUrl(Url url) {
        return null;
    }

    @Override
    public Url updateUrl(Url url) {
        return null;
    }

    @Override
    public void deleteUrl(String id) {

    }
}
