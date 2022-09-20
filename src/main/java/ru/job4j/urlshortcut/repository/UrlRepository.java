package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.urlshortcut.model.UrlRedirect;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<UrlRedirect, Integer> {
    Optional<UrlRedirect> findByCode(String code);

    Optional<UrlRedirect> findByUrlOut(String url);

    Optional<UrlRedirect> findByUrlOutLike(String url);

    @Modifying
    @Transactional
    @Query("update UrlRedirect u set u.requestCount = u.requestCount + 1 where u.code = :code")
    int updateReqCountUrl(String code);
}