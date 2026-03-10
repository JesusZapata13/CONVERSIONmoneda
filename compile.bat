@echo off
REM ─── Compilar y ejecutar Conversor de Monedas ────────────────────────────

set LIBS=libs\gson-2.10.1.jar
set OUT=out

echo Compilando...
if not exist %OUT% mkdir %OUT%

for /r src %%f in (*.java) do set SRC=!SRC! "%%f"

javac -encoding UTF-8 -cp "%LIBS%" -d "%OUT%" src\com\conversor\Main.java src\com\conversor\api\ClienteExchangeRate.java src\com\conversor\modelo\TasaCambio.java src\com\conversor\modelo\RegistroConversion.java src\com\conversor\servicio\ServicioConversion.java src\com\conversor\ui\MenuPrincipal.java

if %errorlevel% == 0 (
    echo Compilacion exitosa.
    echo Iniciando aplicacion...
    echo.
    java -cp "%OUT%;%LIBS%" com.conversor.Main
) else (
    echo Error durante la compilacion.
    exit /b 1
)
