package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.UrlRedirect;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<UrlRedirect, Integer> {
    Optional<UrlRedirect> findByCode(String code);

    Optional<UrlRedirect> findByUrlOut(String url);

    Optional<UrlRedirect> findByUrlOutLike(String url);
}