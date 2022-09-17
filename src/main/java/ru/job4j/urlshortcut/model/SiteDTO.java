package ru.job4j.urlshortcut.model;

import java.util.Objects;
import javax.validation.constraints.*;

public class SiteDTO {
    @NotBlank(message = "site must be not empty")
    private String site;

    public static SiteDTO of(String site) {
        SiteDTO siteDTO = new SiteDTO();
        siteDTO.site = site;
        return siteDTO;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SiteDTO siteDTO = (SiteDTO) o;
        return Objects.equals(site, siteDTO.site);
    }

    @Override
    public int hashCode() {
        return Objects.hash(site);
    }

    @Override
    public String toString() {
        return "SiteDTO{" + "site=" + site + "}";
    }
}