package br.com.josemarinho.picpaysimplificado.domain.dto;

import java.util.UUID;

public record TransferDTO(Double amount, String idUserCredited, String idUserDebited) {
}
