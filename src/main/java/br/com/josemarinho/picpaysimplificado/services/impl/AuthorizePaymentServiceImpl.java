package br.com.josemarinho.picpaysimplificado.services.impl;

import br.com.josemarinho.picpaysimplificado.domain.dto.AuthorizePaymentResponse;
import br.com.josemarinho.picpaysimplificado.services.interfaces.AuthorizePaymentService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

@Service
public class AuthorizePaymentServiceImpl implements AuthorizePaymentService {

    private static final Logger logger = Logger.getLogger(AuthorizePaymentServiceImpl.class.getName());

    private final HttpClient httpClient;

    private final Gson gson;

    public AuthorizePaymentServiceImpl(HttpClient httpClient, Gson gson) {
        this.httpClient = httpClient;
        this.gson = gson;
    }

    @Override
    public boolean authorize() throws URISyntaxException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder().
                uri(new URI("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        logger.info(this.gson.toJson(response.body()));

        if (response.statusCode() == HttpStatus.OK.value()) {
            return this.gson.fromJson(response.body(), AuthorizePaymentResponse.class).isAuthorized();
        }

        return false;
    }
}
