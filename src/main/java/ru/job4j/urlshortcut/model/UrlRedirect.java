package ru.job4j.urlshortcut.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "urlredirect")
public class UrlRedirect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "url_out")
    private String urlOut;
    @Column(name = "code")
    private String code;

    @Column(name = "request_count")
    private long requestCount;

    public static UrlRedirect of(String urlOut, String code) {
        UrlRedirect urlRedirect = new UrlRedirect();
        urlRedirect.urlOut = urlOut;
        urlRedirect.code = code;
        urlRedirect.requestCount = 0;
        return urlRedirect;
    }

    public int getId() {
        return id;
    }

    public String getUrlOut() {
        return urlOut;
    }

    public String getCode() {
        return code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrlOut(String urlOut) {
        this.urlOut = urlOut;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(long requestCount) {
        this.requestCount = requestCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UrlRedirect urlRedirect = (UrlRedirect) o;
        return id == urlRedirect.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UrlRedirect{"
                + "id=" + id
                + ", urlOut='" + urlOut + '\''
                + ", code='" + code + '\''
                + ", requestCount='" + requestCount + '\''
                + '}';
    }
}