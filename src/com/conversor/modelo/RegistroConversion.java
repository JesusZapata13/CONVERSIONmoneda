package com.conversor.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroConversion {

    private final String monedaOrigen;
    private final String monedaDestino;
    private final double monto;
    private final double resultado;
    private final LocalDateTime fecha;

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public RegistroConversion(String monedaOrigen, String monedaDestino, double monto, double resultado) {
        this.monedaOrigen = monedaOrigen.toUpperCase();
        this.monedaDestino = monedaDestino.toUpperCase();
        this.monto = monto;
        this.resultado = resultado;
        this.fecha = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("[%s]  %.2f %s  ->  %.2f %s",
                fecha.format(FORMATO), monto, monedaOrigen, resultado, monedaDestino);
    }
}
