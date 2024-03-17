package br.com.josemarinho.picpaysimplificado.domain.exception;

import br.com.josemarinho.picpaysimplificado.infra.exception.ExceptionCustomized;

public class NotFoundException extends ExceptionCustomized {
    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
