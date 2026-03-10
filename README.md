# 💱 Conversor de Monedas - Challenge ONE

Aplicación de consola en Java que permite convertir entre distintas monedas usando tasas de cambio en tiempo real obtenidas desde [ExchangeRate-API](https://www.exchangerate-api.com/).

---

## 🛠 Tecnologías

- Java 17+
- [Gson 2.10.1](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.10.1)
- Java `HttpClient` (nativo desde Java 11)

---

## 📂 Estructura del Proyecto

```
ConversorMonedas/
├── src/
│   └── com/conversor/
│       ├── Main.java                        ← Punto de entrada
│       ├── api/
│       │   └── ClienteExchangeRate.java     ← Comunicación con la API
│       ├── modelo/
│       │   ├── TasaCambio.java              ← Mapeo de la respuesta JSON
│       │   └── RegistroConversion.java      ← Historial de conversiones
│       ├── servicio/
│       │   └── ServicioConversion.java      ← Lógica de conversión
│       └── ui/
│           └── MenuPrincipal.java           ← Interfaz de consola
├── libs/
│   └── gson-2.10.1.jar                      ← Dependencia externa
├── compile.sh                               ← Script de compilación (Linux/Mac)
├── compile.bat                              ← Script de compilación (Windows)
└── README.md
```

---

## ⚙️ Configuración antes de compilar

1. **Obtén tu API Key** gratuita en [https://www.exchangerate-api.com/](https://www.exchangerate-api.com/)
2. Abre el archivo `src/com/conversor/api/ClienteExchangeRate.java`
3. Reemplaza `TU_API_KEY_AQUI` con tu clave real:
   ```java
   private static final String API_KEY = "abc123...";
   ```
4. Descarga `gson-2.10.1.jar` y colócalo en la carpeta `libs/`

---

## 🚀 Compilar y Ejecutar

### Linux / Mac
```bash
chmod +x compile.sh
./compile.sh
```

### Windows
```cmd
compile.bat
```

---

## 💬 Opciones del menú

| Opción | Conversión |
|--------|-----------|
| 1 | Dólar estadounidense → Peso argentino |
| 2 | Peso argentino → Dólar estadounidense |
| 3 | Dólar estadounidense → Real brasileño |
| 4 | Real brasileño → Dólar estadounidense |
| 5 | Dólar estadounidense → Peso colombiano |
| 6 | Peso colombiano → Dólar estadounidense |
| 7 | Ver historial de conversiones |
| 0 | Salir |

---

## ✨ Funcionalidades extras incluidas

- ✅ Historial de conversiones con fecha y hora
- ✅ Validación de montos ingresados
- ✅ Manejo de errores de red y de entrada

---

## 👤 Autor

Challenge completado como parte del programa **Oracle Next Education (ONE)** con Alura Latam.
