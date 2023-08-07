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
    
    //DEFINIMOS LA GRAMATICA
    private static final Identificadores identificador = new Identificadores();
    private static final Comentarios comentarios = new Comentarios();
    private static final Constantes constantes = new Constantes();
    private static final OperadoresAritmeticos aritmeticos = new OperadoresAritmeticos();
    private static final OperadoresAsignacion asignacion = new OperadoresAsignacion();
    private static final OperadoresComparacion comparacion = new OperadoresComparacion();
    private static final OperadoresLogicos logicos = new OperadoresLogicos();
    private static final OtrosSimbolos otros = new OtrosSimbolos();
    private static final PalabrasClave reservadas = new PalabrasClave();


    // Definir los estados
        final static int ESTADO_INICIAL = 0;
        final static int ESTADO_LEYENDO_IDENTIFICADOR = 1;
        final static int ESTADO_COMENTARIO = 2;
        final static int ESTADO_ARITMETICO= 3;
        final static int ESTADO_ASIGNACION = 4;
        final static int ESTADO_COMPARACION = 5;
        final static int ESTADO_NUMEROS = 6;
        final static int ESTADO_CADENAS = 7;
        
        static int estadoActual = ESTADO_INICIAL;
    
        static List<Token> tokens = new ArrayList<>();
         
        static int linea = 1;
        static int columna = 1;
        static StringBuilder lexema = new StringBuilder();
    
     public static List<Token> analizador(String codigoFuente) {
         

        // Recorrer el código fuente caracter por caracter
        for (int i = 0; i < codigoFuente.length(); i++) {
            char caracter = codigoFuente.charAt(i);
            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (Character.isLetter(caracter) || caracter == '_') {
                        // Comenzar a leer un identificador
                        lexema.append(caracter);
                        if(Character.isLetter(codigoFuente.charAt(i+1)) || codigoFuente.charAt(i+1) == '_'){
                            estadoActual = ESTADO_LEYENDO_IDENTIFICADOR;
                        }else{
                            tokens.add(new Token(identificador.getNombreToken(), lexema.toString(), linea, columna));
                            lexema.setLength(0); // Reiniciar el lexema
                            estadoActual = ESTADO_INICIAL; // Volver al estado inicial
                        }
                        
                    } else if (caracter == '\n') {
                        // Nueva línea, actualizar posición
                        linea++;
                        columna = 1;
                    
                    } else if (comentarios.verificandoPalabra(Character.toString(caracter))) {
                        //SI LA LINEA EMPIEZA CON UN # ES UN COMENTARIO
                        lexema.append(caracter);
                        estadoActual = ESTADO_COMENTARIO;
                    
                    }else if (aritmeticos.verificandoPalabra(Character.toString(caracter)) && codigoFuente.charAt(i+1) != '=') {
                        //SI LA LINEA EMPIEZA CON UN ARITMETICO
                        lexema.append(caracter);
                        if(codigoFuente.charAt(i+1) == '*' || codigoFuente.charAt(i+1) == '/' ){
                            estadoActual = ESTADO_ARITMETICO;
                        }else{
                            tokens.add(new Token(aritmeticos.getNombreToken(), lexema.toString(), linea, columna));
                            lexema.setLength(0); // Reiniciar el lexema
                            estadoActual = ESTADO_INICIAL; // Volver al estado inicial
                        }
                        
                    }else if ((aritmeticos.verificandoPalabra(Character.toString(caracter)) && codigoFuente.charAt(i+1) == '=')
                            || (caracter == '=' && codigoFuente.charAt(i+1) != '=')) {
                        //SI LA LINEA EMPIEZA CON UNA ASIGNACION O ARITMETICO
                        lexema.append(caracter);
                        if(aritmeticos.verificandoPalabra(Character.toString(caracter))){
                            estadoActual = ESTADO_ASIGNACION;
                        }else{
                            tokens.add(new Token(asignacion.getNombreToken(), lexema.toString(), linea, columna));
                            lexema.setLength(0); // Reiniciar el lexema
                            estadoActual = ESTADO_INICIAL; // Volver al estado inicial
                        }
                        
                    }else if (comparacion.verificandoPalabra(Character.toString(caracter))) {
                        //Estos seran de comparacion
                        lexema.append(caracter);
                        if(comparacion.verificandoPalabra(Character.toString(caracter)) && codigoFuente.charAt(i+1) == '='){
                            estadoActual = ESTADO_COMPARACION;
                        }else{
                            tokens.add(new Token(comparacion.getNombreToken(), lexema.toString(), linea, columna));
                            lexema.setLength(0); // Reiniciar el lexema
                            estadoActual = ESTADO_INICIAL; // Volver al estado inicial
                        }
                        
                    }else if (Character.isDigit(caracter)) {
                        //Estos seran de numeros
                        lexema.append(caracter);
                        
                        if(Character.isDigit(codigoFuente.charAt(i+1)) || codigoFuente.charAt(i+1)== '.'){
                            estadoActual = ESTADO_NUMEROS;
                        }else{
                            tokens.add(new Token(constantes.getNombreToken(), lexema.toString(), linea, columna));
                            lexema.setLength(0); // Reiniciar el lexema
                            estadoActual = ESTADO_INICIAL; // Volver al estado inicial
                        }
                    }else if (caracter == '"' || caracter == '\''){
                    //Estos seran cadenas
                        lexema.append(caracter);
                        estadoActual = ESTADO_CADENAS;
                    }else if (otros.verificandoPalabra(Character.toString(caracter))){
                    //Estos seran otros
                        lexema.append(caracter);
                        tokens.add(new Token(otros.getNombreToken(), lexema.toString(), linea, columna));
                        lexema.setLength(0); // Reiniciar el lexema
                        estadoActual = ESTADO_INICIAL; // Volver al estado inicial
                        
                    }else if (!Character.isWhitespace(caracter)) {
                        // Caracter inválido, generar error o ignorar
                    }
                    break;
                case ESTADO_LEYENDO_IDENTIFICADOR:
                    
                    leyendoIdentificador(caracter, i, codigoFuente);
                    
                    break;
                    
                case ESTADO_COMENTARIO:
                    leyendoComentarios(caracter, i);
                    
                    break;
                    
                case ESTADO_ARITMETICO:
                    
                    leyendoAritmeticos(caracter, i);
                      
                    break;
                    
                case ESTADO_ASIGNACION:
                    leyendoAsignacion(caracter, i);
                   
                    break;
                case ESTADO_COMPARACION:
                    leyendoComparacion(caracter, i);
                   
                    break;
                    
                case ESTADO_NUMEROS:
                    leyendoNumeros(caracter, i);
                  
                    break; 
                    
                case ESTADO_CADENAS:
                    
                    leyendoCadenas(caracter, i);
                    
                    
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
     

     private static void leyendoIdentificador(char caracter, int i, String codigoFuente) {
        if (Character.isLetterOrDigit(caracter) || caracter == '_') {
            // Continuar leyendo el identificador
            lexema.append(caracter);
            
            if (!Character.isLetterOrDigit(codigoFuente.charAt(i + 1)) && codigoFuente.charAt(i + 1) != '_') {
                if (isOperadorLogico(lexema.toString())) {
                    tokens.add(new Token(logicos.getNombreToken(), lexema.toString(), linea, columna));

                } else if (isPalabraReservada(lexema.toString())) {
                    tokens.add(new Token(reservadas.getNombreToken(), lexema.toString(), linea, columna));

                } else if (isConstanteBooleana(lexema.toString())) {
                    tokens.add(new Token(constantes.getNombreToken(), lexema.toString(), linea, columna));
                } else {

                    tokens.add(new Token(identificador.getNombreToken(), lexema.toString(), linea, columna));
                }
                lexema.setLength(0); // Reiniciar el lexema
                estadoActual = ESTADO_INICIAL; // Volver al estado inicial
            }
            
        }else {
            // Final del identificador, generar token

            if (isOperadorLogico(lexema.toString())) {
                tokens.add(new Token(logicos.getNombreToken(), lexema.toString(), linea, columna));

            } else if (isPalabraReservada(lexema.toString())) {
                tokens.add(new Token(reservadas.getNombreToken(), lexema.toString(), linea, columna));

            } else if (isConstanteBooleana(lexema.toString())) {
                tokens.add(new Token(constantes.getNombreToken(), lexema.toString(), linea, columna));
            } else {

                tokens.add(new Token(identificador.getNombreToken(), lexema.toString(), linea, columna));
            }
            lexema.setLength(0); // Reiniciar el lexema
            estadoActual = ESTADO_INICIAL; // Volver al estado inicial
        }
     }
     
     private static void leyendoComentarios(char caracter, int i){
         if (caracter != '\n') {
             lexema.append(caracter);
         } else {
             tokens.add(new Token(comentarios.getNombreToken(), lexema.toString(), linea, columna));
             linea++;
             columna = 1;
             lexema.setLength(0); // Reiniciar el lexema
             estadoActual = ESTADO_INICIAL; // Volver al estado inicial
         }
     }
     
     private static void leyendoAritmeticos(char caracter, int i){
         lexema.append(caracter);
        tokens.add(new Token(aritmeticos.getNombreToken(), lexema.toString(), linea, columna));
        lexema.setLength(0); // Reiniciar el lexema
        estadoActual = ESTADO_INICIAL;
    }
     
     private static void leyendoAsignacion(char caracter, int i){
         lexema.append(caracter);
        tokens.add(new Token(asignacion.getNombreToken(), lexema.toString(), linea, columna));
        lexema.setLength(0); // Reiniciar el lexema
        estadoActual = ESTADO_INICIAL; // Volver al estado inicial
    }
     
     private static void leyendoComparacion(char caracter, int i){
        lexema.append(caracter);
        tokens.add(new Token(comparacion.getNombreToken(), lexema.toString(), linea, columna));
        lexema.setLength(0); // Reiniciar el lexema
        estadoActual = ESTADO_INICIAL; // Volver al estado inicial
    }
     
     private static void leyendoNumeros(char caracter, int i){
         if (Character.isDigit(caracter) || caracter == '.') {
             lexema.append(caracter);
         } else {
             tokens.add(new Token(constantes.getNombreToken(), lexema.toString(), linea, columna));
             lexema.setLength(0); // Reiniciar el lexema
             estadoActual = ESTADO_INICIAL; // Volver al estado inicial
         }
     }
     
     private static void leyendoCadenas(char caracter, int i){
         if (caracter == '"' || caracter == '\'') {
             lexema.append(caracter);
             tokens.add(new Token(constantes.getNombreToken(), lexema.toString(), linea, columna));
             lexema.setLength(0); // Reiniciar el lexema
             estadoActual = ESTADO_INICIAL; // Volver al estado inicial
             
         } else {
             lexema.append(caracter);
             
         }
     }

//CREAR UN METODO PARA VERIFICAR SI ES UN OPERADOR LOGICO/ PALABRA RESERVADA/ O CONSTANTE BOOLEANA
     
     //VERIFICANDO OPERADOR LOGICO
     private static boolean isOperadorLogico(String lexema){
         return logicos.verificandoPalabra(lexema);
     }
     
     //VERIFICANDO PALABRA RESERVADA
     private static boolean isPalabraReservada(String lexema){
         return reservadas.verificandoPalabra(lexema);
     }
     
     //VERIFICANDO CONSTANTE BOOLEANA
     private static boolean isConstanteBooleana(String lexema){
         return constantes.verificandoPalabra(lexema);
         
     }
     
}
