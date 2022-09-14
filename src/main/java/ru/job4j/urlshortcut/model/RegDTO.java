package ru.job4j.urlshortcut.model;

import java.util.Objects;

public class RegDTO {
    private boolean registration;
    private String login;
    private String password;

    public static RegDTO of(boolean registration, String login, String password) {
        RegDTO regDto = new RegDTO();
        regDto.registration = registration;
        regDto.login = login;
        regDto.password = password;
        return regDto;
    }

    public boolean isRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RegDTO regDto = (RegDTO) o;
        return registration == regDto.registration
                && password.equals(regDto.password) && login.equals(regDto.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registration, password, login);
    }

    @Override
    public String toString() {
        return "RegDto{"
                + "registration=" + registration
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
