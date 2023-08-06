/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.analizadorlexico.analizador;

import com.rudyreyes.analizadorlexico.gramatica.Comentarios;
import com.rudyreyes.analizadorlexico.gramatica.Constantes;
import com.rudyreyes.analizadorlexico.gramatica.Identificadores;
import com.rudyreyes.analizadorlexico.gramatica.OperadoresAritmeticos;
import com.rudyreyes.analizadorlexico.gramatica.OperadoresAsignacion;
import com.rudyreyes.analizadorlexico.gramatica.OperadoresComparacion;
import com.rudyreyes.analizadorlexico.gramatica.OperadoresLogicos;
import com.rudyreyes.analizadorlexico.gramatica.OtrosSimbolos;
import com.rudyreyes.analizadorlexico.gramatica.PalabrasClave;
import com.rudyreyes.analizadorlexico.token.Token;
import java.util.ArrayList;
import java.util.List;

public class AnalizadorLexico {
    
    
    
    
    
     public static List<Token> analizador(String codigoFuente) {
         //DEFINIMOS LA GRAMATICA
         Identificadores identificador = new Identificadores();
         Comentarios comentarios = new Comentarios();
         Constantes constantes = new Constantes();
         OperadoresAritmeticos aritmeticos = new OperadoresAritmeticos();
         OperadoresAsignacion asignacion = new OperadoresAsignacion();
         OperadoresComparacion comparacion = new OperadoresComparacion();
         OperadoresLogicos logicos = new OperadoresLogicos();
         OtrosSimbolos otros = new OtrosSimbolos();
         PalabrasClave reservadas = new PalabrasClave();
         List<Token> tokens = new ArrayList<>();
         
        int linea = 1;
        int columna = 1;
        StringBuilder lexema = new StringBuilder();
        
        // Definir los estados
        final int ESTADO_INICIAL = 0;
        final int ESTADO_LEYENDO_IDENTIFICADOR = 1;

        int estadoActual = ESTADO_INICIAL;

        // Recorrer el código fuente caracter por caracter
        for (char caracter : codigoFuente.toCharArray()) {
            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (Character.isLetter(caracter) || caracter == '_') {
                        // Comenzar a leer un identificador
                        lexema.append(caracter);
                        estadoActual = ESTADO_LEYENDO_IDENTIFICADOR;
                    } else if (caracter == '\n') {
                        // Nueva línea, actualizar posición
                        linea++;
                        columna = 1;
                    } else if (!Character.isWhitespace(caracter)) {
                        // Caracter inválido, generar error o ignorar
                    }
                    break;
                case ESTADO_LEYENDO_IDENTIFICADOR:
                    if (Character.isLetterOrDigit(caracter) || caracter == '_') {
                        // Continuar leyendo el identificador
                        lexema.append(caracter);
                    } else {
                        // Final del identificador, generar token
                        tokens.add(new Token(identificador.getNombreToken(), lexema.toString(), linea, columna));
                        lexema.setLength(0); // Reiniciar el lexema
                        estadoActual = ESTADO_INICIAL; // Volver al estado inicial
                    }
                    break;
                default:
                    // Estado inválido, generar error o manejar de acuerdo a tus necesidades
            }

            // Actualizar la posición del carácter
            if (caracter != '\n') {
                columna++;
            }
        }

        // Comprobar si hay un identificador sin finalizar al final del código fuente
        if (estadoActual == ESTADO_LEYENDO_IDENTIFICADOR) {
            tokens.add(new Token(identificador.getNombreToken(), lexema.toString(), linea, columna));
        }
         
         return tokens;
     }
    
}
