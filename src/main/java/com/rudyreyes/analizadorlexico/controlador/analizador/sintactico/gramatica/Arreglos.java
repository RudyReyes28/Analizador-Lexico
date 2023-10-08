
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica;

import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class Arreglos {
    
    /*
    mi_lista = [1, 2, 3, 4, 5]   # Una lista de números enteros
mi_lista_de_strings = ["manzana", "banana", "cereza"]  # Una lista de cadenas de texto
mezcla = [1, "dos", True, 3.14]  # Una lista con elementos de diferentes tipos
arreglo2 = [{"id":1, "nombre":"Sin nombre"}, {"id":2, "nombre":"Xalarga"}]

variable  operadorAsignacion [ (constante | variable )  [operadorAritmetico (constante | variable )]*    [, (constante | variable ) [operadorAritmetico (constante | variable )]* ]* ]

x = constante | variable | diccionario
v= variable
o = operadorAsignaicon
c = coma ,

v =  [  ] | x   [c x ]* ]
    */
    
    final static private int ESTADO_INICIAL = 0;
    final static private int ESTADO_S0 = 1;
    final static private int ESTADO_S1 = 2;
    final static private int ESTADO_S2 = 3;
    final static private int ESTADO_S3 = 4;
    final static private int ESTADO_S4 = 5;
    final static private int ESTADO_S5 = 6;
    
    public static void analizarArreglo(List<Token> tokens) {
        int estadoActual = ESTADO_INICIAL;
        int i = 0;
        while (i < tokens.size()) {

            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (tokens.get(i).getTipo().equals("Identificador")) {
                       estadoActual = ESTADO_S0;
                    }
                    break;

                case ESTADO_S0:
                    if (tokens.get(i).getValor().equals("=")) {
                        estadoActual = ESTADO_S1;
                    
                    }
                    break;

                case ESTADO_S1:
                    if (tokens.get(i).getValor().equals("[")) {
                        estadoActual = ESTADO_S2;
                    
                    }
                    break;

                case ESTADO_S2:
                    if (tokens.get(i).getValor().equals("]")) {
                        estadoActual = ESTADO_S3;
                    
                    }else if(isDiccionario(i, tokens)){
                        if(Diccionarios.analizarDiccionario(obtenerDiccionario(i, tokens))){
                            estadoActual = ESTADO_S4;
                            i = obtenerPosicionDiccionario(i, tokens);
                        }else{
                            estadoActual = ESTADO_INICIAL;
                        }
                    }else if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S4;
                    }
                    break;
                
                case ESTADO_S3:
                    if(tokens.get(i).getTipo().equals("Comentario")){
                        estadoActual = ESTADO_S3;
                    
                    }else{
                        estadoActual = ESTADO_INICIAL;
                    }
                    break;
                case ESTADO_S4:
                    if(tokens.get(i).getValor().equals(",")){
                        estadoActual = ESTADO_S5;
                    
                    }else if (tokens.get(i).getValor().equals("]")) {
                        estadoActual = ESTADO_S3;
                    
                    }
                    break;
                    
                case ESTADO_S5:
                    if(isDiccionario(i, tokens)){
                        if(Diccionarios.analizarDiccionario(obtenerDiccionario(i, tokens))){
                            estadoActual = ESTADO_S4;
                            i = obtenerPosicionDiccionario(i, tokens);
                        }else{
                            estadoActual = ESTADO_INICIAL;
                        }
                    }else if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S4;
                    }
                    break;

                default:
                // Manejo de otros casos si es necesario.
            }
        
            i++;
        }
        if(estadoActual == ESTADO_S3){
            
            System.out.println("ARREGLO VALIDO");
        }else{
            System.out.println("ARREGLO INVALIDO");
        }
        
    }
    
    
    private static boolean verificarX(Token token){
        return token.getTipo().equals("Constante") || token.getTipo().equals("Identificador");
    }
    
    
    private static List<Token> obtenerDiccionario(int i,List<Token> tokens ){
        List<Token> tokensDic = new ArrayList<>();
        int posicion = obtenerPosicionDiccionario(i, tokens);
        
        for(int n = i; n <= posicion; n++ ){
            tokensDic.add(tokens.get(n));
        }
        
        return tokensDic;
    }
    
    private static int obtenerPosicionDiccionario(int i, List<Token> tokens){
        for(int n = i; n < tokens.size(); n++ ){
            if (tokens.get(n).getValor().equals("}")){
                return n;
            }
        }
        
        return tokens.size() -1;
    }
    
    private static boolean isDiccionario(int i, List<Token> tokens){
        
        return tokens.get(i).getValor().equals("{");
    }
}
