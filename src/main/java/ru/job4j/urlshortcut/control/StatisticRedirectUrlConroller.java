package ru.job4j.urlshortcut.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.model.UrlDTO;
import ru.job4j.urlshortcut.service.UrlService;

import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticRedirectUrlConroller {
    private static final Logger LOG = LoggerFactory.getLogger(StatisticRedirectUrlConroller.class.getSimpleName());
    private final UrlService urlService;

    public StatisticRedirectUrlConroller(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping()
    public List<UrlDTO> statistic() {
        List<UrlDTO> urlDTOList = urlService.getListStatistic();
        LOG.info("Output UrlDTO List statistic size list:{}", urlDTOList.size());
        return urlDTOList;
    }

    @GetMapping("/{url}")
    public ResponseEntity<UrlDTO> statisticUrl(@PathVariable String url) {
        UrlDTO urlDTO = urlService.getListStatisticUrl(url);
        boolean urlDTOEmpty = urlDTO.getUrl() == null;
        if (!urlDTOEmpty) {
            LOG.info("Output UrlDTO statistic url: {} - find site: {}", url, urlDTO.getUrl());
        } else {
            LOG.info("Output UrlDTO statistic url: {} - site not found", url);
        }
        return new ResponseEntity<UrlDTO>(
                urlDTO,
                !urlDTOEmpty ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }
}