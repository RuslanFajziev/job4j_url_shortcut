package ru.job4j.urlshortcut.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.CodeDTO;
import ru.job4j.urlshortcut.model.UrlDTO;
import ru.job4j.urlshortcut.service.UrlService;

import java.net.URISyntaxException;

@RestController
public class RegUrlController {
    private static final Logger LOG = LoggerFactory.getLogger(RegUrlController.class.getSimpleName());
    private final UrlService urlService;

    public RegUrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/convert")
    public ResponseEntity<CodeDTO> convert(@RequestBody UrlDTO urlDTO) throws URISyntaxException {
        String url = urlDTO.getUrl();
        CodeDTO codeDTO = CodeDTO.of(urlService.generateUrlRedirect(url).getCode());
        LOG.info("Output CodeDTO={}", codeDTO);
        return new ResponseEntity<CodeDTO>(
                codeDTO,
                HttpStatus.CREATED
        );
    }
}
