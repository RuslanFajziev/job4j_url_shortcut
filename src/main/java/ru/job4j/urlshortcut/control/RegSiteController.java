package ru.job4j.urlshortcut.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.Logins;
import ru.job4j.urlshortcut.model.RegDTO;
import ru.job4j.urlshortcut.model.SiteDTO;
import ru.job4j.urlshortcut.service.LoginService;

import java.net.URISyntaxException;

@RestController
public class RegSiteController {
    private static final Logger LOG = LoggerFactory.getLogger(RegSiteController.class.getSimpleName());
    private final LoginService loginService;

    public RegSiteController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/registration")
    public ResponseEntity<RegDTO> registration(@RequestBody SiteDTO siteDTO) throws URISyntaxException {
        String site = siteDTO.getSite();
        boolean flag = loginService.checkSiteReg(site);
        Logins loginsOut = loginService.generateLogin(site);
        RegDTO regDTOOut = RegDTO.of(flag, loginsOut.getlogin(), loginsOut.getPassword());
        LOG.info("Output RegDTO={}", regDTOOut);
        return new ResponseEntity<RegDTO>(
                regDTOOut,
                HttpStatus.CREATED
        );
    }
}