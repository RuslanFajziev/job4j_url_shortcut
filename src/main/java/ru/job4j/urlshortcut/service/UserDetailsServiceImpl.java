package ru.job4j.urlshortcut.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Logins;
import ru.job4j.urlshortcut.repository.LoginRepository;

import static java.util.Collections.emptyList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class.getSimpleName());
    private LoginRepository loginRep;

    public UserDetailsServiceImpl(LoginRepository loginRep) {
        this.loginRep = loginRep;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Logins> optionalLogin = loginRep.findByLogin(username);
        if (optionalLogin.isEmpty()) {
            LOG.info("Not found login={}", username);
            throw new UsernameNotFoundException(username);
        }

        Logins login = optionalLogin.get();
        LOG.info("Found login={}", username);
        return new User(login.getlogin(), login.getPassword(), emptyList());
    }
}