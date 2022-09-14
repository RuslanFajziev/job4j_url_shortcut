package ru.job4j.urlshortcut.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "logins")
public class Logins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private String site;

    public static Logins of(String login, String password, String site) {
        Logins logins = new Logins();
        logins.login = login;
        logins.password = password;
        logins.site = site;
        return logins;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getlogin() {
        return login;
    }

    public void setlogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        Logins logins = (Logins) o;
        return id == logins.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", site='" + site + '\''
                + '}';
    }
}