package ru.job4j.urlshortcut.model;

import java.util.Objects;

public class UrlDTO {
    private String url;

    private long total;

    public static UrlDTO of(String url) {
        UrlDTO urlDTO = new UrlDTO();
        urlDTO.url = url;
        urlDTO.total = 0;
        return urlDTO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UrlDTO urlDTO = (UrlDTO) o;
        return Objects.equals(url, urlDTO.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "UrlDTO{"
                + "url='" + url + '\''
                + ", total=" + total
                + '}';
    }
}