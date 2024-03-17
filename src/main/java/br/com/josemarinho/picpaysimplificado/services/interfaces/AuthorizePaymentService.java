package br.com.josemarinho.picpaysimplificado.services.interfaces;

import java.io.IOException;
import java.net.URISyntaxException;

public interface AuthorizePaymentService {
    boolean authorize() throws URISyntaxException, IOException, InterruptedException;
}
