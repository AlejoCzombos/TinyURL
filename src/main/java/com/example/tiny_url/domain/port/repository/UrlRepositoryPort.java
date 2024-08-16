package com.example.tiny_url.domain.port.repository;

import com.example.tiny_url.domain.model.Url;

import java.util.List;

public interface UrlRepositoryPort {

    List<Url> findAll();
    Url findByKeyOrAlias(String key);
    Url createUrl(Url url);
    Url updateUrl(Url url, String key);
    Url deleteUrl(String key);

}
