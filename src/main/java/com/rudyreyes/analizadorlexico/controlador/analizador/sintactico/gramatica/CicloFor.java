
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica;

import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class CicloFor {
    
    final static private int ESTADO_INICIAL = 0;
    final static private int ESTADO_S0 = 1;
    final static private int ESTADO_S1 = 2;
    final static private int ESTADO_S2 = 3;
    final static private int ESTADO_S3 = 4;
    final static private int ESTADO_S4 = 5;
    final static private int ESTADO_S5 = 6;
    final static private int ESTADO_S6 = 7;
    final static private int ESTADO_S7 = 8;
    
    /*
    for x in range:
for fruta in frutas: 
for i in fib():
for i in fib(1,2,3):
for n in range(2, 10): 

→ Expresion regular:
for variable in variable:
*/
    
    public static void analizarCicloFor(List<Token> tokens) {
        int estadoActual = ESTADO_INICIAL;
        int i = 0;
        while (i < tokens.size()) {

            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (tokens.get(i).getValor().equals("for")) {
                       estadoActual = ESTADO_S0;
                    }
                    break;

                case ESTADO_S0:
                    if (tokens.get(i).getTipo().equals("Identificador")) {
                        estadoActual = ESTADO_S1;
                    
                    }
                    break;

                case ESTADO_S1:
                    if (tokens.get(i).getValor().equals("in")) {
                        estadoActual = ESTADO_S2;
                    
                    }
                    break;

                case ESTADO_S2:
                    //AQUI TENGO QUE VER SI LO QUE VIENE ES UNA VARIABLE O UN METODO, O RANGE
                    if (tokens.get(i).getTipo().equals("Identificador") && tokens.get(i+1).getValor().equals("(")) {
                        if(Metodos.analizarLlamarMetodo(obtenerMetodos(i, tokens))){
                            estadoActual = ESTADO_S3;
                            i =  obtenerPosicionMetodos(i, tokens) -1;
                            
                        }
                    }
                    
                    else if(tokens.get(i).getValor().equals("range")){
                        if(Metodos.analizarMetodoRange(obtenerMetodos(i, tokens))){
                            estadoActual = ESTADO_S3;
                            i =  obtenerPosicionMetodos(i, tokens) -1;
                            
                        }
                    }
                    
                    else if(tokens.get(i).getTipo().equals("Identificador")){
                        estadoActual = ESTADO_S3;
                    }
                    break;
                
                case ESTADO_S3:
                    if (tokens.get(i).getValor().equals(":")) {
                        estadoActual = ESTADO_S4;
                    
                    }
                    break;
                case ESTADO_S4:
                    if(tokens.get(i).getTipo().equals("Comentario")){
                        estadoActual = ESTADO_S4;
                    
                    }else{
                        estadoActual = ESTADO_INICIAL;
                    }
                    break;

                default:
                // Manejo de otros casos si es necesario.
            }
        
            i++;
        }
        if(estadoActual == ESTADO_S4){
            
            System.out.println("CICLO FOR VALIDO");
        }else{
            System.out.println("CICLO FOR INVALIDO");
        }
        
         

    }
    
    
    private static List<Token> obtenerMetodos(int i,List<Token> tokens ){
        List<Token> tokensMetodo = new ArrayList<>();
        int posicion = obtenerPosicionMetodos(i, tokens);
        
        for(int n = i; n < posicion; n++ ){
            tokensMetodo.add(tokens.get(n));
        }
        
        return tokensMetodo;
    }
    
    private static int obtenerPosicionMetodos(int i, List<Token> tokens){
        for(int n = i; n < tokens.size(); n++ ){
            if (tokens.get(n).getValor().equals(":")){
                return n;
            }
        }
        
        return tokens.size() -1;
    }
}
