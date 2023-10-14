/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.analizadorlexico.controlador.analizador.lexico;

import com.rudyreyes.analizadorlexico.modelo.alfabeto.Comentarios;
import com.rudyreyes.analizadorlexico.modelo.alfabeto.Constantes;
import com.rudyreyes.analizadorlexico.modelo.alfabeto.Identificadores;
import com.rudyreyes.analizadorlexico.modelo.alfabeto.OperadoresAritmeticos;
import com.rudyreyes.analizadorlexico.modelo.alfabeto.OperadoresAsignacion;
import com.rudyreyes.analizadorlexico.modelo.alfabeto.OperadoresComparacion;
import com.rudyreyes.analizadorlexico.modelo.alfabeto.OperadoresLogicos;
import com.rudyreyes.analizadorlexico.modelo.alfabeto.OtrosSimbolos;
import com.rudyreyes.analizadorlexico.modelo.alfabeto.PalabrasClave;
import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.ArrayList;
import java.util.List;

public class AnalizadorLexico {
    
    //DEFINIMOS LA GRAMATICA
    private final Identificadores identificador = new Identificadores();
    private final Comentarios comentarios = new Comentarios();
    private final Constantes constantes = new Constantes();
    private final OperadoresAritmeticos aritmeticos = new OperadoresAritmeticos();
    private final OperadoresAsignacion asignacion = new OperadoresAsignacion();
    private final OperadoresComparacion comparacion = new OperadoresComparacion();
    private final OperadoresLogicos logicos = new OperadoresLogicos();
    private final OtrosSimbolos otros = new OtrosSimbolos();
    private final PalabrasClave reservadas = new PalabrasClave();


    // Definir los estados
        final private int ESTADO_INICIAL = 0;
        final private  int ESTADO_LEYENDO_IDENTIFICADOR = 1;
        final private int ESTADO_COMENTARIO = 2;
        final private int ESTADO_ARITMETICO= 3;
        final private int ESTADO_ASIGNACION = 4;
        final private int ESTADO_COMPARACION = 5;
        final private int ESTADO_NUMEROS = 6;
        final private int ESTADO_CADENAS = 7;
        final private int ESTADO_ERROR = 8;
        
        private int estadoActual = ESTADO_INICIAL;
    
        private List<Token> tokens = new ArrayList<>();
         
        private  int linea = 1;
        private int columna = 1;
        private StringBuilder lexema = new StringBuilder();

    public AnalizadorLexico() {
    }
        
        
    
     public List<Token> analizador(String codigoFuente) {
         

        // Recorrer el código fuente caracter por caracter
        for (int i = 0; i < codigoFuente.length(); i++) {
            char caracter = codigoFuente.charAt(i);
            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (Character.isLetter(caracter) || caracter == '_') {
                        // Comenzar a leer un identificador
                        lexema.append(caracter);
                        if(Character.isLetterOrDigit(codigoFuente.charAt(i+1)) || codigoFuente.charAt(i+1) == '_'){
                            estadoActual = ESTADO_LEYENDO_IDENTIFICADOR;
                        }else{
                            tokens.add(new Token(identificador.getNombreToken(), "[a-zA-Z_][a-zA-Z0-9_]*", lexema.toString(), linea, columna));
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
                            tokens.add(new Token(aritmeticos.getNombreToken(),"[+-*/%]", lexema.toString(), linea, columna));
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
                            tokens.add(new Token(asignacion.getNombreToken(),"[=]", lexema.toString(), linea, columna));
                            lexema.setLength(0); // Reiniciar el lexema
                            estadoActual = ESTADO_INICIAL; // Volver al estado inicial
                        }
                        
                    }else if (comparacion.verificandoPalabra(Character.toString(caracter))) {
                        //Estos seran de comparacion
                        lexema.append(caracter);
                        if(comparacion.verificandoPalabra(Character.toString(caracter)) && codigoFuente.charAt(i+1) == '='){
                            estadoActual = ESTADO_COMPARACION;
                        }else{
                            tokens.add(new Token(comparacion.getNombreToken(),"[<>]", lexema.toString(), linea, columna));
                            lexema.setLength(0); // Reiniciar el lexema
                            estadoActual = ESTADO_INICIAL; // Volver al estado inicial
                        }
                        
                    }else if (Character.isDigit(caracter)) {
                        //Estos seran de numeros
                        lexema.append(caracter);
                        
                        if(Character.isDigit(codigoFuente.charAt(i+1)) || codigoFuente.charAt(i+1)== '.'){
                            estadoActual = ESTADO_NUMEROS;
                        }else{
                            tokens.add(new Token(constantes.getNombreToken(),"[0-9]*\\.?[0-9]*", lexema.toString(), linea, columna));
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
                        tokens.add(new Token(otros.getNombreToken(),"[:;,()[]{}]", lexema.toString(), linea, columna));
                        lexema.setLength(0); // Reiniciar el lexema
                        estadoActual = ESTADO_INICIAL; // Volver al estado inicial
                        
                    }else if (!Character.isWhitespace(caracter)) {
                        // Caracter inválido, generar error o ignorar
                        lexema.append(caracter);
                        if(Character.isWhitespace(codigoFuente.charAt(i+1)) || codigoFuente.charAt(i+1)== '\n'){
                            tokens.add(new Token("Error",lexema.toString(), linea, columna));
                            lexema.setLength(0); // Reiniciar el lexema
                            estadoActual = ESTADO_INICIAL; // Volver al estado inicial
                        
                        }else{
                            estadoActual = ESTADO_ERROR;
                        }
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
                    leyendoNumeros(caracter, i, codigoFuente);
                  
                    break; 
                    
                case ESTADO_CADENAS:
                    
                    leyendoCadenas(caracter, i);
                    
                    break; 
                    
                case ESTADO_ERROR:
                    leyendoErrores(caracter, i);
                    break;
                default:
                    // Estado inválido, generar error o manejar de acuerdo a tus necesidades
            }
            
            

            // Actualizar la posición del carácter
            if (caracter != '\n') {
                columna++;
            }else{
                columna = 1;
            }
            
        }

        // Comprobar si hay un identificador sin finalizar al final del código fuente
        if (estadoActual == ESTADO_LEYENDO_IDENTIFICADOR) {
            tokens.add(new Token(identificador.getNombreToken(), lexema.toString(), linea, columna));
        }
         
         return tokens;
     }
     

     private void leyendoIdentificador(char caracter, int i, String codigoFuente) {
        if (Character.isLetterOrDigit(caracter) || caracter == '_') {
            // Continuar leyendo el identificador
            lexema.append(caracter);
            
            if (!Character.isLetterOrDigit(codigoFuente.charAt(i + 1)) && codigoFuente.charAt(i + 1) != '_') {
                asignandoTokenIdentificador();
            }
            
        }else {
            // Final del identificador, generar token

            asignandoTokenIdentificador();
        }
     }
     
     private void asignandoTokenIdentificador(){
         if (isOperadorLogico(lexema.toString())) {
                    tokens.add(new Token(logicos.getNombreToken(), lexema.toString(), lexema.toString(), linea, columna));

                } else if (isPalabraReservada(lexema.toString())) {
                    tokens.add(new Token(reservadas.getNombreToken(), lexema.toString(), lexema.toString(), linea, columna));

                } else if (isConstanteBooleana(lexema.toString())) {
                    tokens.add(new Token(constantes.getNombreToken(),lexema.toString(), lexema.toString(), linea, columna));
                } else {

                    tokens.add(new Token(identificador.getNombreToken(),"[a-zA-Z_][a-zA-Z0-9_]*", lexema.toString(), linea, columna));
                }
                lexema.setLength(0); // Reiniciar el lexema
                estadoActual = ESTADO_INICIAL; // Volver al estado inicial
     }
     
     private void leyendoComentarios(char caracter, int i){
         if (caracter != '\n') {
             lexema.append(caracter);
         } else {
             tokens.add(new Token(comentarios.getNombreToken(),"[#][a-zA-Z0-9_]*", lexema.toString(), linea, columna));
             linea++;
             columna = 1;
             lexema.setLength(0); // Reiniciar el lexema
             estadoActual = ESTADO_INICIAL; // Volver al estado inicial
         }
     }
     
     private void leyendoAritmeticos(char caracter, int i){
         lexema.append(caracter);
        tokens.add(new Token(aritmeticos.getNombreToken(),"[+-*/%]", lexema.toString(), linea, columna));
        lexema.setLength(0); // Reiniciar el lexema
        estadoActual = ESTADO_INICIAL;
    }
     
     private void leyendoAsignacion(char caracter, int i){
         lexema.append(caracter);
        tokens.add(new Token(asignacion.getNombreToken(),"[+-*/%][=]", lexema.toString(), linea, columna));
        lexema.setLength(0); // Reiniciar el lexema
        estadoActual = ESTADO_INICIAL; // Volver al estado inicial
    }
     
     private void leyendoComparacion(char caracter, int i){
        lexema.append(caracter);
        tokens.add(new Token(comparacion.getNombreToken(),"[<>!=][=]", lexema.toString(), linea, columna));
        lexema.setLength(0); // Reiniciar el lexema
        estadoActual = ESTADO_INICIAL; // Volver al estado inicial
    }
     
     private void leyendoNumeros(char caracter, int i, String codigoFuente){
         if (Character.isDigit(caracter) || caracter == '.') {
             lexema.append(caracter);
             if (!Character.isDigit(codigoFuente.charAt(i + 1)) && codigoFuente.charAt(i + 1) != '.') {
                 if (verificandoPuntosDecimal() >= 2) {
                     tokens.add(new Token("Error", lexema.toString(), linea, columna));
                     lexema.setLength(0); // Reiniciar el lexema
                     estadoActual = ESTADO_INICIAL; // Volver al estado inicial
                 } else {
                     tokens.add(new Token(constantes.getNombreToken(), "[0-9]*\\.?[0-9]*", lexema.toString(), linea, columna));
                     lexema.setLength(0); // Reiniciar el lexema
                     estadoActual = ESTADO_INICIAL; // Volver al estado inicial
                 }
             }
         }/* else {
             
             if(verificandoPuntosDecimal()>=2){
                 tokens.add(new Token("Error", lexema.toString(), linea, columna));
                 lexema.setLength(0); // Reiniciar el lexema
                 estadoActual = ESTADO_INICIAL; // Volver al estado inicial
             }else{
                 tokens.add(new Token(constantes.getNombreToken(), "[0-9]*\\.?[0-9]*", lexema.toString(), linea, columna));
                 lexema.setLength(0); // Reiniciar el lexema
                 estadoActual = ESTADO_INICIAL; // Volver al estado inicial
             }
             
         }*/
     }
     
     private int verificandoPuntosDecimal(){
         int contadorPuntos = 0;
         for (char c : lexema.toString().toCharArray()) {
             if (c == '.') {
                 contadorPuntos++;
             }
         }
        return contadorPuntos;
    }
     
     private void leyendoCadenas(char caracter, int i){
         if (caracter == '"' || caracter == '\'') {
             lexema.append(caracter);
             tokens.add(new Token(constantes.getNombreToken(),"[\"'][a-zA-Z0-9_*+-#/]*[\"']", lexema.toString(), linea, columna));
             lexema.setLength(0); // Reiniciar el lexema
             estadoActual = ESTADO_INICIAL; // Volver al estado inicial
             
         }else if(caracter == '\n'){
             tokens.add(new Token("Error", lexema.toString(), linea, columna));
             lexema.setLength(0); // Reiniciar el lexema
             estadoActual = ESTADO_INICIAL; // Volver al estado inicial
         }else  {
             lexema.append(caracter);
             
         }
     }

     private void leyendoErrores(char caracter, int i){
         if (Character.isWhitespace(caracter) || caracter == '\n') {
             tokens.add(new Token("Error", lexema.toString(), linea, columna));
             lexema.setLength(0); // Reiniciar el lexema
             estadoActual = ESTADO_INICIAL; // Volver al estado inicial

         }else{
             lexema.append(caracter);
         }
     }
//CREAR UN METODO PARA VERIFICAR SI ES UN OPERADOR LOGICO/ PALABRA RESERVADA/ O CONSTANTE BOOLEANA
     
     //VERIFICANDO OPERADOR LOGICO
     private boolean isOperadorLogico(String lexema){
         return logicos.verificandoPalabra(lexema);
     }
     
     //VERIFICANDO PALABRA RESERVADA
     private boolean isPalabraReservada(String lexema){
         return reservadas.verificandoPalabra(lexema);
     }
     
     //VERIFICANDO CONSTANTE BOOLEANA
     private boolean isConstanteBooleana(String lexema){
         return constantes.verificandoPalabra(lexema);
         
     }
     
}
