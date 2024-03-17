package br.com.josemarinho.picpaysimplificado.services.impl;

import br.com.josemarinho.picpaysimplificado.domain.TransferPayment;
import br.com.josemarinho.picpaysimplificado.domain.User;
import br.com.josemarinho.picpaysimplificado.domain.dto.TransferDTO;
import br.com.josemarinho.picpaysimplificado.domain.enums.TypeUser;
import br.com.josemarinho.picpaysimplificado.domain.exception.BusinessException;
import br.com.josemarinho.picpaysimplificado.domain.exception.NotFoundException;
import br.com.josemarinho.picpaysimplificado.repository.TranferRepository;
import br.com.josemarinho.picpaysimplificado.repository.UserRepository;
import br.com.josemarinho.picpaysimplificado.services.interfaces.AuthorizePaymentService;
import br.com.josemarinho.picpaysimplificado.services.interfaces.TransferPaymentService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@Service
public class TransferPaymentServiceImpl implements TransferPaymentService {

    private final AuthorizePaymentService authorizePaymentService;
    private final UserRepository userRepository;
    private final TranferRepository tranferRepository;

    public TransferPaymentServiceImpl(AuthorizePaymentService authorizePaymentService, UserRepository userRepository, TranferRepository tranferRepository) {
        this.authorizePaymentService = authorizePaymentService;
        this.userRepository = userRepository;
        this.tranferRepository = tranferRepository;
    }

    @Override
    public TransferPayment saveTransfer(TransferDTO transferDTO) throws URISyntaxException, IOException, InterruptedException {
        var userDebit = this.userRepository.findUserById(UUID.fromString(transferDTO.idUserDebited()));
        var userCredit = this.userRepository.findUserById(UUID.fromString(transferDTO.idUserCredited()));

        if (userDebit.isEmpty()) {
            throw new NotFoundException("USER001", "User Debited not found");
        }

        if (userCredit.isEmpty()) {
            throw new NotFoundException("USER002", "User Credited not found");
        }

        var transfer = new TransferPayment(transferDTO, userCredit.get(), userDebit.get());

        this.debitBalanceFromUser(userDebit.get(), transfer.getAmount());
        this.creditBalanceUser(userCredit.get(), transfer.getAmount());

        this.tranferRepository.save(transfer);
        return transfer;
    }

    @Override
    public TransferPayment getTransferById(String uuid) {
        var transfer = this.tranferRepository.findById(UUID.fromString(uuid));

        if (transfer.isEmpty()) {
            throw new NotFoundException("TRANSFER003", "Transfer not found by id: " + uuid);
        }

        return transfer.get();
    }

    @Override
    public List<TransferPayment> getAllTransferByIdUser(String uuid) {
        return this.tranferRepository.findAllByDebitedUserId(UUID.fromString(uuid));
    }

    @Override
    public List<TransferPayment> listAllTransferences() {
        return this.tranferRepository.findAll();
    }

    @Override
    public User debitBalanceFromUser(User user, Double balance) throws URISyntaxException, IOException, InterruptedException {
        if (user.getTypeUser() == TypeUser.SHOPKEEPER) {
            throw new BusinessException("TRANSFER001", "User of type shopkeeper not authorized for transfer");
        }

        if (user.getBalance() <= 0) {
            throw new BusinessException("USER003", "User amount is not availablle");
        }

        if (!this.authorizePaymentService.authorize()) {
            throw new BusinessException("TRANSFER002", "User not authorized to transfer");
        }


        var currentAmountUser = user.getBalance();
        var amountSubtracted = currentAmountUser - balance;
        if (amountSubtracted <= 0) {
            throw new BusinessException("TRANSFER003", "User does not have enough balance");
        }

        this.userRepository.updateUserSetBalance(amountSubtracted, user.getId());
        return this.userRepository.findUserById(user.getId()).get();
    }

    @Override
    public User creditBalanceUser(User user, Double balance) {
        var currentAmountUser = user.getBalance();
        var amountPlus = currentAmountUser + balance;

        this.userRepository.updateUserSetBalance(amountPlus, user.getId());
        return this.userRepository.findUserById(user.getId()).get();
    }

}
