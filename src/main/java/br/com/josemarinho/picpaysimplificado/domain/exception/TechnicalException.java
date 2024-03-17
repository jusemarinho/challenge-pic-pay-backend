package br.com.josemarinho.picpaysimplificado.domain.exception;

import br.com.josemarinho.picpaysimplificado.infra.exception.ExceptionCustomized;

public class TechnicalException extends ExceptionCustomized {
    public TechnicalException(String code, String message) {
        super(code, message);
    }
}