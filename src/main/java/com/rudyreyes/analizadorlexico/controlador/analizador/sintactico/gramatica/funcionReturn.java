
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica;

import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class funcionReturn {
   
    /*return  n [a n]* | n [a n]* c n [a n]**/
    final static private int ESTADO_INICIAL = 0;
    final static private int ESTADO_S0 = 1;
    final static private int ESTADO_S1 = 2;
    final static private int ESTADO_S2 = 3;
    final static private int ESTADO_S3 = 4;
    
    public static void analizarReturn(List<Token> tokens) {
        int estadoActual = ESTADO_INICIAL;
        int i = 0;
        while (i < tokens.size()) {

            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (tokens.get(i).getValor().equals("return")) {
                       estadoActual = ESTADO_S0;
                    }
                    break;

                case ESTADO_S0:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S1;
                    }
                    break;

                case ESTADO_S1:
                    if(tokens.get(i).getTipo().equals("OperadorAritmetico")){
                        estadoActual = ESTADO_S2;
                    
                    }else if(tokens.get(i).getTipo().equals("OperadorComparacion")){
                        estadoActual = ESTADO_S3;
                    
                    }
                    break;

                case ESTADO_S2:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S1;
                    }
                    break;
                
                case ESTADO_S3:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S1;
                    }
                    break;
                

                default:
                // Manejo de otros casos si es necesario.
            }
        
            i++;
        }
        if(estadoActual == ESTADO_S1){
            
            System.out.println("RETURN VALIDO");
        }else{
            System.out.println("RETURN INVALIDO");
        }
        
    }
    
    
    private static boolean verificarX(Token token){
        return token.getTipo().equals("Constante") || token.getTipo().equals("Identificador");
    }
}
