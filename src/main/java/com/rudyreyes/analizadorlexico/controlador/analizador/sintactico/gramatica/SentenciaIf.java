
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica;

import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class SentenciaIf {
    /*
        if [constante | variable]  [  [operadorAritmetico (constante | variable ) 
    comparación [constante | variable]  ]* | [comparacion  [constante | variable] ]* ] :
    
    n = [ variables | constantes ]
L = logicos
a = aritmeticos
c = condicional

if n [a n]* | n [a n]* c n [a n]* [ L n [a n]* c n [a n]* ] * :

    
    */
    final static private int ESTADO_INICIAL = 0;
    final static private int ESTADO_S0 = 1;
    final static private int ESTADO_S1 = 2;
    final static private int ESTADO_S2 = 3;
    final static private int ESTADO_S3 = 4;
    final static private int ESTADO_S4 = 5;
    final static private int ESTADO_S5 = 6;
    final static private int ESTADO_S6 = 7;
    final static private int ESTADO_S7 = 8;
    final static private int ESTADO_S8 = 9;
    final static private int ESTADO_S9 = 10;
    final static private int ESTADO_S10 = 11;
    final static private int ESTADO_S11 = 12;
    final static private int ESTADO_S12 = 13;
    final static private int ESTADO_S13 = 14;

    
    
    
    public static void analizarExpresion(List<Token> tokens) {
        int estadoActual = ESTADO_INICIAL;
        for (int i = 0; i < tokens.size(); i++) {

            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (tokens.get(i).getValor().equals("if") 
                            || tokens.get(i).getValor().equals("elif")
                            || tokens.get(i).getValor().equals("while")) {
                        // Realiza acciones para el caso ESTADO_INICIAL cuando es un "Identificador"
                        estadoActual = ESTADO_S0;
                    }
                    break;

                case ESTADO_S0:
                    if(verificarN(tokens.get(i))){
                        estadoActual = ESTADO_S1;
                    }
                    break;

                case ESTADO_S1:
                    if(tokens.get(i).getTipo().equals("OperadorAritmetico")){
                        estadoActual = ESTADO_S2;
                    
                    }else if(tokens.get(i).getTipo().equals("OperadorComparacion")){
                        estadoActual = ESTADO_S3;
                    
                    }else if(tokens.get(i).getValor().equals(":")){
                        estadoActual = ESTADO_S4;
                    }
                    break;

                case ESTADO_S2:
                    if(verificarN(tokens.get(i))){
                        estadoActual = ESTADO_S5;
                    }
                    break;
                    
                case ESTADO_S3:
                    if(verificarN(tokens.get(i))){
                        estadoActual = ESTADO_S6;
                    }
                    break;
                    
                case ESTADO_S4:
                    if(tokens.get(i).getTipo().equals("Comentario")){
                        estadoActual = ESTADO_S4;
                    
                    }else{
                        estadoActual = ESTADO_INICIAL;
                    }
                    break;
                    
                case ESTADO_S5:
                    if(tokens.get(i).getTipo().equals("OperadorComparacion")){
                        estadoActual = ESTADO_S3;
                    
                    }else if(tokens.get(i).getTipo().equals("OperadorAritmetico")){
                        estadoActual = ESTADO_S2;
                    
                    }
                    break;
                    
                case ESTADO_S6:
                    if(tokens.get(i).getValor().equals(":")){
                        estadoActual = ESTADO_S4;
                    
                    }else if(tokens.get(i).getTipo().equals("OperadorAritmetico")){
                        estadoActual = ESTADO_S7;
                    
                    }else if(tokens.get(i).getTipo().equals("OperadorLogico")){
                        estadoActual = ESTADO_S8;
                    
                    }
                    break;
                    
                case ESTADO_S7:
                    if(verificarN(tokens.get(i))){
                        estadoActual = ESTADO_S6;
                    }
                    break;
                    
                case ESTADO_S8:
                    if(verificarN(tokens.get(i))){
                        estadoActual = ESTADO_S9;
                    }
                    break;
                    
                case ESTADO_S9:
                    if(tokens.get(i).getTipo().equals("OperadorComparacion")){
                        estadoActual = ESTADO_S11;
                    
                    }else if(tokens.get(i).getTipo().equals("OperadorAritmetico")){
                        estadoActual = ESTADO_S10;
                    
                    }
                    break;
                    
                case ESTADO_S10:
                    if(verificarN(tokens.get(i))){
                        estadoActual = ESTADO_S9;
                    }
                    break;
                    
                case ESTADO_S11:
                    if(verificarN(tokens.get(i))){
                        estadoActual = ESTADO_S12;
                    }
                    break;
                    
                case ESTADO_S12:
                    if(tokens.get(i).getValor().equals(":")){
                        estadoActual = ESTADO_S4;
                    
                    }else if(tokens.get(i).getTipo().equals("OperadorLogico")){
                        estadoActual = ESTADO_S8;
                    
                    }else if(tokens.get(i).getTipo().equals("OperadorAritmetico")){
                        estadoActual = ESTADO_S13;
                    
                    }
                    break;
                    
                case ESTADO_S13:
                    if(verificarN(tokens.get(i))){
                        estadoActual = ESTADO_S12;
                    }
                    break;


                default:
                // Manejo de otros casos si es necesario.
            }

        }
        
        if(estadoActual == ESTADO_S4){
            System.out.println("CONDICIONAL VALIDA PARA EL IF");
        }else{
            System.out.println("NO ES UNA DECLARACION IF");
        }
    }
    
    private static boolean verificarN(Token token){
        return token.getTipo().equals("Constante") || token.getTipo().equals("Identificador");
    }
    
    
    public static void analizarExpresionElse(List<Token> tokens){
        int estadoActual = ESTADO_INICIAL;
        for (int i = 0; i < tokens.size(); i++) {

            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (tokens.get(i).getValor().equals("else")) {
                        // Realiza acciones para el caso ESTADO_INICIAL cuando es un "Identificador"
                        estadoActual = ESTADO_S0;
                    }
                    break;

                case ESTADO_S0:
                    if (tokens.get(i).getValor().equals(":")) {
                        estadoActual = ESTADO_S1;
                    }
                    break;
                    
                case ESTADO_S1:
                    if(tokens.get(i).getTipo().equals("Comentario")){
                        estadoActual = ESTADO_S1;
                    
                    }else{
                        estadoActual = ESTADO_INICIAL;
                    }
                    break;
                default:
            }
        }
        
        if(estadoActual == ESTADO_S1){
            System.out.println("DECLARACION VALIDA PARA EL ELSE");
        }else{
            System.out.println("NO ES UNA DECLARACION IF");
        }
        
    }
}
