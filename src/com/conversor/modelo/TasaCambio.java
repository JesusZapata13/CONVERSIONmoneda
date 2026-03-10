package com.conversor.modelo;

import java.util.Map;

public class TasaCambio {

    private String base_code;
    private Map<String, Double> conversion_rates;

    public String getMonedaBase() {
        return base_code;
    }

    public Map<String, Double> getTasas() {
        return conversion_rates;
    }

    public boolean contieneMoneda(String codigo) {
        return conversion_rates != null && conversion_rates.containsKey(codigo.toUpperCase());
    }

    public double obtenerTasa(String codigoMoneda) {
        if (!contieneMoneda(codigoMoneda)) {
            throw new IllegalArgumentException("Moneda no encontrada: " + codigoMoneda);
        }
        return conversion_rates.get(codigoMoneda.toUpperCase());
    }
}
