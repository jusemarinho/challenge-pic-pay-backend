package br.com.josemarinho.picpaysimplificado.domain.dto;

import br.com.josemarinho.picpaysimplificado.domain.enums.TypeUser;

public record UserDTO(String name, String document, String email, String password, TypeUser typeUser, Double balance) {

}
