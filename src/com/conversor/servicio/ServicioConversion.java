package com.conversor.servicio;

import com.conversor.api.ClienteExchangeRate;
import com.conversor.modelo.RegistroConversion;
import com.conversor.modelo.TasaCambio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServicioConversion {

    private final ClienteExchangeRate clienteApi;
    private final List<RegistroConversion> historial;

    public ServicioConversion() {
        this.clienteApi = new ClienteExchangeRate();
        this.historial = new ArrayList<>();
    }

    public double convertir(String monedaOrigen, String monedaDestino, double monto)
            throws IOException, InterruptedException {

        TasaCambio tasas = clienteApi.obtenerTasas(monedaOrigen);

        if (!tasas.contieneMoneda(monedaDestino)) {
            throw new IllegalArgumentException(
                    "La moneda destino no está disponible: " + monedaDestino
            );
        }

        double tasaDestino = tasas.obtenerTasa(monedaDestino);
        double resultado = monto * tasaDestino;

        historial.add(new RegistroConversion(monedaOrigen, monedaDestino, monto, resultado));

        return resultado;
    }

    public List<RegistroConversion> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    public boolean tieneHistorial() {
        return !historial.isEmpty();
    }
}
