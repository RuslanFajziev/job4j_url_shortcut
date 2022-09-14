package ru.job4j.urlshortcut.model;

import java.util.Objects;

public class CodeDTO {
    private String code;

    public static CodeDTO of(String code) {
        CodeDTO codeDTO = new CodeDTO();
        codeDTO.code = code;
        return codeDTO;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CodeDTO codeDTO = (CodeDTO) o;
        return Objects.equals(code, codeDTO.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "CodeDTO{" + "code=" + code + "}";
    }
}