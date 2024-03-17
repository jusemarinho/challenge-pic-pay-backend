package br.com.josemarinho.picpaysimplificado.domain;

import br.com.josemarinho.picpaysimplificado.domain.enums.TypeUser;
import br.com.josemarinho.picpaysimplificado.domain.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String document;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;

    private Double balance;

    public User(UserDTO userDTO) {
        this.name = userDTO.name();
        this.document = userDTO.document();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.typeUser = userDTO.typeUser() == null ? TypeUser.COMMON : userDTO.typeUser();
        this.balance = userDTO.balance();
    }
}
