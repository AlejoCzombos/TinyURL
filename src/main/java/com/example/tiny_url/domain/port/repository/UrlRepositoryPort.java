package com.example.tiny_url.domain.port.repository;

import com.example.tiny_url.domain.model.Url;

import java.util.List;

public interface UrlRepositoryPort {

    List<Url> findAll();
    Url findByIdOrAlias(String id);
    Url redirectToUrl(String id);
    Url createUrl(Url url);
    Url updateUrl(Url url);
    void deleteUrl(String id);

}
