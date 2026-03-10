#!/bin/bash

# ─── Compilar y ejecutar Conversor de Monedas ───────────────────────────────

LIBS="libs/gson-2.10.1.jar"
SRC=$(find src -name "*.java")
OUT="out"

echo "🔧 Compilando..."
mkdir -p $OUT

javac -cp "$LIBS" -d "$OUT" $SRC

if [ $? -eq 0 ]; then
    echo "✅ Compilación exitosa."
    echo "🚀 Iniciando aplicación..."
    echo ""
    java -cp "$OUT:$LIBS" com.conversor.Main
else
    echo "❌ Error durante la compilación."
    exit 1
fi
