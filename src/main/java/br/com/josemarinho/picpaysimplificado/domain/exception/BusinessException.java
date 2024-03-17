package br.com.josemarinho.picpaysimplificado.domain.exception;

import br.com.josemarinho.picpaysimplificado.infra.exception.ExceptionCustomized;

public class BusinessException extends ExceptionCustomized {
    public BusinessException(String code, String message) {
        super(code, message);
    }
}
