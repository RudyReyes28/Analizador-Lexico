/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.rudyreyes.analizadorlexico.token;

import com.rudyreyes.analizadorlexico.analizador.AnalizadorLexico;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class Prueba {

    public static void main(String args[]) {
        
        String codigoFuente = "def factorial(numero):\n" +
"    resultado = 1\n" +
"\n" +
"    for i in range(1, numero + 1):\n" +
"        resultado *= i\n" +
"\n" +
"    return resultado\n" +
"\n" +
"# Función para saludar\n" +
"def saludar(nombre):\n" +
"\n"+
"x // 5\n" +
"y = 10.5\n" +
"z += 10.5\n" +
"j == 10.5\n" +
"exp ** 10.5\n" +                
"nombre = \"Juan\"\n" +
"es_valido = True\n" +
"\n" +
"resultado_factorial = factorial(x)\n" +
"saludar(nombre)\n" +
"\n" +
"# Condicionales\n" +
"if es_valido:";
        List<Token> tokens = AnalizadorLexico.analizador(codigoFuente);

        for (Token token : tokens) {
            System.out.println("Tipo: " + token.getTipo() + ", Lexema: " + token.getValor()+
                    ", Linea: " + token.getLinea() + ", Columna: " + token.getColumna());
        }
    }
}
