package ru.job4j.urlshortcut.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.job4j.urlshortcut.filter.PasswordGenerator;
import ru.job4j.urlshortcut.model.Logins;
import ru.job4j.urlshortcut.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.Optional;

@Service
public class LoginService {
    private static final Logger LOG = LoggerFactory.getLogger(LoginService.class.getSimpleName());

    private final LoginRepository loginRepository;

    private final PasswordGenerator passwordGenerator;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginService(LoginRepository loginRepository,
                        PasswordGenerator passwordGenerator, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.loginRepository = loginRepository;
        this.passwordGenerator = passwordGenerator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private Logins savelogin(String siteDencode) {
        String login = passwordGenerator.generate(6);
        String passwordNotCrypt = passwordGenerator.generate(8);
        String passwordCrypt = bCryptPasswordEncoder.encode(passwordNotCrypt);
        Logins newLoginReg = loginRepository.save(Logins.of(login, passwordCrypt, siteDencode));
        LOG.info("Registration new login={}", newLoginReg);
        newLoginReg.setPassword(passwordNotCrypt);
        return newLoginReg;
    }

    public Logins generateLogin(String siteEncode) throws URISyntaxException {
        String siteDencode;
        if (siteEncode.contains("%")) {
            siteDencode = new java.net.URI(siteEncode).getPath();
        } else {
            siteDencode = siteEncode;
        }
        Optional<Logins> optionalLogins = loginRepository.findBySite(siteDencode);
        return optionalLogins.orElseGet(() -> savelogin(siteDencode));
    }

    public boolean checkSiteReg(String site) {
        Optional<Logins> optionalLogins = loginRepository.findBySite(site);
        return optionalLogins.isEmpty();
    }
}