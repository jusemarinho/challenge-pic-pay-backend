package br.com.josemarinho.picpaysimplificado.domain;

import br.com.josemarinho.picpaysimplificado.domain.dto.TransferDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "payment_transfer")
@Table(name = "payment_transfer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Double amount;

    @OneToOne
    private User creditedUser;

    @OneToOne
    private User debitedUser;

    public TransferPayment(TransferDTO transferDTO, User creditedUser, User debitedUser) {
        this.amount = transferDTO.amount();
        this.creditedUser = creditedUser;
        this.debitedUser = debitedUser;
    }
}
