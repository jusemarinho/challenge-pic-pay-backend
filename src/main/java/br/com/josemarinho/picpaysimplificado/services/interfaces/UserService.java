package br.com.josemarinho.picpaysimplificado.services.interfaces;

import br.com.josemarinho.picpaysimplificado.domain.User;
import br.com.josemarinho.picpaysimplificado.domain.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
    User getUserByDocument(String document);
    User getUserByEmail(String email);
    User saveUser(UserDTO userDTO);
    List<User> getAllUsers();
}
