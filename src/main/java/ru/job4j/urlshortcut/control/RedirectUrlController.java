package ru.job4j.urlshortcut.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.job4j.urlshortcut.model.UrlRedirect;
import ru.job4j.urlshortcut.service.UrlService;

import java.util.Optional;

@RestController
public class RedirectUrlController {
    private static final Logger LOG = LoggerFactory.getLogger(RedirectUrlController.class.getSimpleName());
    private final UrlService urlService;

    public RedirectUrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/redirect/{code}")
    public RedirectView redirect(@PathVariable String code, RedirectAttributes attributes) {
        Optional<UrlRedirect> url = urlService.findUrlRedirect(code);
        if (url.isEmpty()) {
            LOG.info("Redirect url not found on Code={}", code);
            // описать исключения для вывода в выхлоп ответа
        }
        String urlRedirect = url.get().getUrlOut();
        LOG.info("Redirect url={} found on Code={} (request count:{})", urlRedirect, code, url.get().getRequestCount());
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");
        return new RedirectView(urlRedirect);
    }
}
