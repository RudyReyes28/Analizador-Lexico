
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica;

import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class AsignacionVariables {

    /*
        ASIGNACIÓN DE VARIABLES CON OPERADORES Y VARIABLES
    minutos = 60 * horas
    minutos = 5 * 5 * 5 * 5
    a, b = b, a+b

	→ Expresion regular:

        variable [,variable]* operadorAsignacion (constante | variable )  [operadorAritmetico (constante | variable )]*    [, (constante | variable ) [operadorAritmetico (constante | variable )]* ]* 

        constante | variable = x
        v= variable
        o = operadorAsignaicon
        a = operadorAritmetico
        c = coma ,

        v [c v]* o x   [a  x]*  [c x (a x)*]* 

     */
    
    
    
    //private List<Token> tokens;
    private int indice; // Índice actual en la lista de tokens

    final private int ESTADO_INICIAL = 0;
    final private int ESTADO_S0 = 1;
    final private int ESTADO_S1 = 2;
    final private int ESTADO_S2 = 3;
    final private int ESTADO_S3 = 4;
    final private int ESTADO_S4 = 5;
    final private int ESTADO_S5 = 6;

    private int estadoActual = ESTADO_INICIAL;

    

    public void analizarExpresion(List<Token> tokens) {

        for (int i = 0; i < tokens.size(); i++) {

            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (tokens.get(i).getTipo().equals("Identificador")) {
                        // Realiza acciones para el caso ESTADO_INICIAL cuando es un "Identificador"
                        estadoActual = ESTADO_S0;
                    }
                    break;

                case ESTADO_S0:
                    //tokens.get(i).getValor().equals(",")
                    if (tokens.get(i).getValor().equals(",")) {
                        estadoActual = ESTADO_S1;
                    
                    } else if (tokens.get(i).getTipo().equals("OperadorAsignacion")) {
                        estadoActual = ESTADO_S2;
                    }
                    break;

                case ESTADO_S1:
                    if (tokens.get(i).getTipo().equals("Identificador")) {
                        estadoActual = ESTADO_S0;
                    }
                    break;

                case ESTADO_S2:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S3;
                    }
                    break;
                case ESTADO_S3:
                    //tokens.get(i).getValor().equals(",")
                    if (tokens.get(i).getValor().equals(",")) {
                        estadoActual = ESTADO_S4;
                    
                    } else if (tokens.get(i).getTipo().equals("OperadorAritmetico")) {
                        estadoActual = ESTADO_S5;
                    }
                    break;
                case ESTADO_S4:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S3;
                    }
                    break;
                case ESTADO_S5:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S3;
                    }
                    break;

                default:
                // Manejo de otros casos si es necesario.
            }

        }
        
        if(estadoActual == ESTADO_S3){
            System.out.println("ASIGNACION VALIDA");
        }else{
            System.out.println("NO ES UNA ASIGNACION DE VARIABLES");
        }

    }
    
    private boolean verificarX(Token token){
        return token.getTipo().equals("Constante") || token.getTipo().equals("Identificador");
    }

}

