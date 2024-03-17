package br.com.josemarinho.picpaysimplificado.infra.exception;

import lombok.Data;

@Data
public class ExceptionCustomized extends RuntimeException {
    private String code;
    private String message;

    public ExceptionCustomized(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
}