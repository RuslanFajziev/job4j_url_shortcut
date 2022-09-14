package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Logins;

import java.util.Optional;

public interface LoginRepository extends CrudRepository<Logins, Integer> {
    Optional<Logins> findByLogin(String login);

    Optional<Logins> findBySite(String site);
}