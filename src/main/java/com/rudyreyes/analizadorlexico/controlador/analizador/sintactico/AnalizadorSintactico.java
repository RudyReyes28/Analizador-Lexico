
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico;

import com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica.AsignacionVariables;
import com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica.IfCondicional;
import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class AnalizadorSintactico {
    
    public static void analizarSintaxis (List<Token> tokens ){
        int filaMaxima = filaMaxima(tokens);

        for (int fila = 1; fila <= filaMaxima; fila++) {
            List<Token> tokensFilaActual = new ArrayList<>();

            // Obtener los tokens para la fila actual
            for (Token token : tokens) {
                if (token.getLinea()== fila) {
                    tokensFilaActual.add(token);
                }
            }

            // Llama a tu método y pasa los tokens de la fila actual
            if(!tokensFilaActual.isEmpty()){
                if (tokensFilaActual.get(0).getTipo().equals("Identificador")) {
                    AsignacionVariables asignacionV = new AsignacionVariables();
                    asignacionV.analizarExpresion(tokensFilaActual);
                
                }else if(tokensFilaActual.get(0).getValor().equals("if") || tokensFilaActual.get(0).getValor().equals("elif") ){
                    IfCondicional.analizarExpresion(tokensFilaActual);
                
                }else if(tokensFilaActual.get(0).getValor().equals("else")){
                    IfCondicional.analizarExpresionElse(tokensFilaActual);
                }
            }
        }
        
    }
    
    
    private static int filaMaxima(List<Token> tokens) {
        int filaMaxima = 0;
        for (Token token : tokens) {
            int filaToken = token.getLinea();
            if (filaToken > filaMaxima) {
                filaMaxima = filaToken;
            }
        }
        
        return filaMaxima;
    }
    
}
