package br.com.josemarinho.picpaysimplificado.services.impl;

import br.com.josemarinho.picpaysimplificado.domain.User;
import br.com.josemarinho.picpaysimplificado.domain.dto.UserDTO;
import br.com.josemarinho.picpaysimplificado.domain.exception.BusinessException;
import br.com.josemarinho.picpaysimplificado.domain.exception.NotFoundException;
import br.com.josemarinho.picpaysimplificado.repository.UserRepository;
import br.com.josemarinho.picpaysimplificado.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(UUID id) {
        var userOptional = this.userRepository.findUserById(id);

        if (userOptional.isEmpty()) {
            throw new NotFoundException("USER003", "User not found by id: " + id);
        }

        return userOptional.get();
    }

    @Override
    public User getUserByDocument(String document) {
        var userOptional = this.userRepository.findUserByDocument(document);

        return userOptional.orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        var userOptional = this.userRepository.findUserByEmail(email);

        return userOptional.orElse(null);
    }

    @Override
    public User saveUser(UserDTO userDTO) {
        if (this.getUserByDocument(userDTO.document()) != null || this.getUserByEmail(userDTO.email()) != null) {
            throw new BusinessException("USER006", "User already exists");
        }

        var user = new User(userDTO);
        this.userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
