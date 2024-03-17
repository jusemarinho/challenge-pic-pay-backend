package br.com.josemarinho.picpaysimplificado.services.interfaces;

import br.com.josemarinho.picpaysimplificado.domain.TransferPayment;
import br.com.josemarinho.picpaysimplificado.domain.User;
import br.com.josemarinho.picpaysimplificado.domain.dto.TransferDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface TransferPaymentService {
    TransferPayment saveTransfer(TransferDTO transferDTO) throws URISyntaxException, IOException, InterruptedException;
    TransferPayment getTransferById(String uuid);
    List<TransferPayment> getAllTransferByIdUser(String uuid);
    List<TransferPayment> listAllTransferences();
    User debitBalanceFromUser(User user, Double balancce) throws URISyntaxException, IOException, InterruptedException;
    User creditBalanceUser(User user, Double balance);
}
