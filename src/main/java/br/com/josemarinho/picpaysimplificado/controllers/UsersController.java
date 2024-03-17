package br.com.josemarinho.picpaysimplificado.controllers;

import br.com.josemarinho.picpaysimplificado.domain.User;
import br.com.josemarinho.picpaysimplificado.domain.dto.UserDTO;
import br.com.josemarinho.picpaysimplificado.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
        var userSaved = this.userService.saveUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @GetMapping
    public ResponseEntity<List<User>> listAllUsers() {
        var listUsers = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(listUsers);
    }
}
