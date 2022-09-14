package ru.job4j.urlshortcut.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.urlshortcut.filter.PasswordGenerator;

import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.UrlDTO;
import ru.job4j.urlshortcut.model.UrlRedirect;
import ru.job4j.urlshortcut.repository.UrlRepository;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UrlService {
    private static final Logger LOG = LoggerFactory.getLogger(UrlService.class.getSimpleName());

    private final UrlRepository urlRepository;

    private final PasswordGenerator codeGen;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
        this.codeGen = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
    }

    private UrlRedirect saveUrlRedirect(String urlDencode) {
        String codeNew = codeGen.generate(6);
        UrlRedirect urlRedirectNew = urlRepository.save(UrlRedirect.of(urlDencode, codeNew));
        LOG.info("Registration new UrlRedirect={}", urlRedirectNew);
        return urlRedirectNew;
    }

    public UrlRedirect generateUrlRedirect(String urlEncode) throws URISyntaxException {
        String urlDencode;
        if (urlEncode.contains("%")) {
            urlDencode = new java.net.URI(urlEncode).getPath();
        } else {
            urlDencode = urlEncode;
        }
        Optional<UrlRedirect> optionalUrlRedirect = urlRepository.findByUrlOut(urlDencode);
        return optionalUrlRedirect.orElseGet(() -> saveUrlRedirect(urlDencode));
    }

    public Optional<UrlRedirect> findUrlRedirect(String code) {
        Optional<UrlRedirect> optionalUrlRedirect = urlRepository.findByCode(code);
        if (optionalUrlRedirect.isPresent()) {
            UrlRedirect urlRedirect = optionalUrlRedirect.get();
            urlRedirect.setRequestCount(urlRedirect.getRequestCount() + 1);
            urlRepository.save(urlRedirect);
            return Optional.of(urlRedirect);
        }
        return optionalUrlRedirect;
    }

    public List<UrlDTO> getListStatistic() {
        List<UrlDTO> urlDTOList = new ArrayList<>();
        Iterable<UrlRedirect> urlRedirectIterable = urlRepository.findAll();
        urlRedirectIterable.forEach((urlRedirect -> {
            UrlDTO urlDTO = UrlDTO.of(urlRedirect.getUrlOut());
            urlDTO.setTotal(urlRedirect.getRequestCount());
            urlDTOList.add(urlDTO);
        }));
        return urlDTOList;
    }

    public UrlDTO getListStatisticUrl(String url) {
        String mask = "%%%s%%";
        String urlLike = String.format(mask, url);
        Optional<UrlRedirect> optionalUrlRedirect = urlRepository.findByUrlOutLike(urlLike);
        UrlDTO urlDTO = new UrlDTO();
        if (optionalUrlRedirect.isPresent()) {
            UrlRedirect urlRedirect = optionalUrlRedirect.get();
            urlDTO = UrlDTO.of(urlRedirect.getUrlOut());
            urlDTO.setTotal(urlRedirect.getRequestCount());
        }
        return urlDTO;
    }
}