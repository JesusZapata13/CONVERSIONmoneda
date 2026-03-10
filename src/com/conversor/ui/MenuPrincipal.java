package com.conversor.ui;

import com.conversor.servicio.ServicioConversion;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuPrincipal {

    private final ServicioConversion servicio;
    private final Scanner scanner;

    // Pares de conversión predefinidos [origen, destino, descripción]
    private static final String[][] OPCIONES_MENU = {
            {"USD", "ARS", "Dólar estadounidense  →  Peso argentino"},
            {"ARS", "USD", "Peso argentino        →  Dólar estadounidense"},
            {"USD", "BRL", "Dólar estadounidense  →  Real brasileño"},
            {"BRL", "USD", "Real brasileño        →  Dólar estadounidense"},
            {"USD", "COP", "Dólar estadounidense  →  Peso colombiano"},
            {"COP", "USD", "Peso colombiano       →  Dólar estadounidense"},
    };

    public MenuPrincipal() {
        this.servicio = new ServicioConversion();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("==========================================");
        System.out.println("       CONVERSOR DE MONEDAS - ONE         ");
        System.out.println("==========================================");

        boolean ejecutando = true;

        while (ejecutando) {
            mostrarMenu();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1, 2, 3, 4, 5, 6 -> procesarConversion(opcion - 1);
                case 7 -> mostrarHistorial();
                case 0 -> {
                    ejecutando = false;
                    System.out.println("\nHasta luego!\n");
                }
                default -> System.out.println("\n⚠  Opción no válida. Intente de nuevo.\n");
            }
        }

        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n------------------------------------------");
        System.out.println("  Seleccione una opcion de conversion:");
        System.out.println("------------------------------------------");

        for (int i = 0; i < OPCIONES_MENU.length; i++) {
            System.out.printf("  [%d]  %s%n", i + 1, OPCIONES_MENU[i][2]);
        }

        System.out.println("  [7]  Ver historial de conversiones");
        System.out.println("  [0]  Salir");
        System.out.println("------------------------------------------");
        System.out.print("  Opcion: ");
    }

    private void procesarConversion(int indice) {
        String origen  = OPCIONES_MENU[indice][0];
        String destino = OPCIONES_MENU[indice][1];

        System.out.printf("%nIngrese el monto en %s: ", origen);

        try {
            double monto = scanner.nextDouble();

            if (monto <= 0) {
                System.out.println("⚠  El monto debe ser mayor a cero.");
                return;
            }

            System.out.println("  Consultando tasas de cambio...");
            double resultado = servicio.convertir(origen, destino, monto);

            System.out.println("------------------------------------------");
            System.out.printf("  %.2f %s  =  %.2f %s%n", monto, origen, resultado, destino);
            System.out.println("------------------------------------------");

        } catch (InputMismatchException e) {
            scanner.nextLine(); // limpiar buffer
            System.out.println("⚠  Por favor ingrese un número válido.");
        } catch (IOException | InterruptedException e) {
            System.out.println("⚠  Error de conexión: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("⚠  " + e.getMessage());
        }
    }

    private void mostrarHistorial() {
        System.out.println("\n============== HISTORIAL ==============");

        if (!servicio.tieneHistorial()) {
            System.out.println("  Aún no se han realizado conversiones.");
        } else {
            servicio.getHistorial().forEach(r -> System.out.println("  " + r));
        }

        System.out.println("=======================================");
    }

    private int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();
            return opcion;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }
}
