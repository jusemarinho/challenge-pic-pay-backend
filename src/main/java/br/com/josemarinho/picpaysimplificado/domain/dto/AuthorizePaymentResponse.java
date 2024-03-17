package br.com.josemarinho.picpaysimplificado.domain.dto;

import lombok.Data;

@Data
public class AuthorizePaymentResponse {
    private String message;

    public boolean isAuthorized() {
        return this.message.equals("Autorizado");
    }
}