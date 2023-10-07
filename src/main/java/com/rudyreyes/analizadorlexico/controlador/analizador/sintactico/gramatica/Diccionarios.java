
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica;

import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class Diccionarios {
    
    /*
    mi_diccionario = {
    "nombre": "Juan",
    "edad": 30,
    "ciudad": "Ejemploville"
}


variable = { [ } | [ (constante|variable) : (constante|variable)  [, (constante|variable) : (constante|variable) ]*  }  ]

x = constante | variable
v= variable
o = operadorAsignaicon
c = coma ,

{ [ } |  x : x  [, x : x ]* }  ] 

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
    
    public static boolean analizarDiccionario(List<Token> tokens) {
        int estadoActual = ESTADO_INICIAL;
        int i = 0;
        while (i < tokens.size()) {

            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (tokens.get(i).getValor().equals("{")) {
                       estadoActual = ESTADO_S0;
                    }
                    break;

                case ESTADO_S0:
                    if (tokens.get(i).getValor().equals("}")) {
                        estadoActual = ESTADO_S1;
                    
                    }else if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S2;
                    }
                    break;

                case ESTADO_S1:
                    if(tokens.get(i).getTipo().equals("Comentario")){
                        estadoActual = ESTADO_S1;
                    
                    }else{
                        estadoActual = ESTADO_INICIAL;
                    }
                    break;

                case ESTADO_S2:
                     if(tokens.get(i).getValor().equals(":")){
                        estadoActual = ESTADO_S3;
                    
                    }
                    break;
                
                case ESTADO_S3:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S4;
                    }
                    break;
                case ESTADO_S4:
                    if(tokens.get(i).getValor().equals("}")){
                        estadoActual = ESTADO_S1;
                    
                    }else if (tokens.get(i).getValor().equals(",")) {
                        estadoActual = ESTADO_S5;
                    
                    }
                    break;
                    
                case ESTADO_S5:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S6;
                    }
                    break;
                case ESTADO_S6:
                    if(tokens.get(i).getValor().equals(":")){
                        estadoActual = ESTADO_S7;
                    
                    }
                    break;
                case ESTADO_S7:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S4;
                    }
                    break;

                default:
                // Manejo de otros casos si es necesario.
            }
        
            i++;
        }
        if(estadoActual == ESTADO_S1){
            
            System.out.println("DICCIONARIO VALIDO");
            return true;
        }else{
            System.out.println("DICCIONARIO INVALIDO");
            return false;
        }
        
    }
    
    
    private static boolean verificarX(Token token){
        return token.getTipo().equals("Constante") || token.getTipo().equals("Identificador");
    }
}
