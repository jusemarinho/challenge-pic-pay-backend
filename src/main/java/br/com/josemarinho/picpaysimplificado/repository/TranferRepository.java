package br.com.josemarinho.picpaysimplificado.repository;

import br.com.josemarinho.picpaysimplificado.domain.TransferPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TranferRepository extends JpaRepository<TransferPayment, UUID> {
    @Query("SELECT tp FROM payment_transfer tp WHERE tp.debitedUser.id = :userId")
    List<TransferPayment> findAllByDebitedUserId(@Param("userId") UUID userId);
}
