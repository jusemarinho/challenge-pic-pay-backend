package br.com.josemarinho.picpaysimplificado.controllers;

import br.com.josemarinho.picpaysimplificado.domain.User;
import br.com.josemarinho.picpaysimplificado.domain.dto.TransferDTO;
import br.com.josemarinho.picpaysimplificado.services.interfaces.TransferPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/transfers")
public class TransferPaymentController {

    private final TransferPaymentService transferPaymentService;

    public TransferPaymentController(TransferPaymentService transferPaymentService) {
        this.transferPaymentService = transferPaymentService;
    }

    @PostMapping
    public ResponseEntity transfer(@RequestBody TransferDTO transferDTO) throws URISyntaxException, IOException, InterruptedException {
        return ResponseEntity.status(HttpStatus.OK).body(this.transferPaymentService.saveTransfer(transferDTO));
    }
}
