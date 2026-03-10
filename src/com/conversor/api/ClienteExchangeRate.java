package com.conversor.api;

import com.conversor.modelo.TasaCambio;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteExchangeRate {

    private static final String API_KEY = "71c0545096a8b80db70ef5e2";
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/";

    private final HttpClient cliente;
    private final Gson gson;

    public ClienteExchangeRate() {
        this.cliente = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public TasaCambio obtenerTasas(String monedaBase) throws IOException, InterruptedException {
        String url = URL_BASE + API_KEY + "/latest/" + monedaBase.toUpperCase();

        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() != 200) {
            throw new IOException("Error al consultar la API. Código HTTP: " + respuesta.statusCode());
        }

        return gson.fromJson(respuesta.body(), TasaCambio.class);
    }
}
