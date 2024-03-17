package br.com.josemarinho.picpaysimplificado.repository;

import br.com.josemarinho.picpaysimplificado.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserById(UUID id);
    Optional<User> findUserByDocument(String document);
    Optional<User> findUserByEmail(String email);
    @Modifying
    @Transactional
    @Query("update users u set u.balance = :amountUpdated where u.id = :id")
    void updateUserSetBalance(@Param("amountUpdated") Double amountUpdated, @Param("id") UUID id);
}
