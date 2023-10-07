
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica;

import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class OperadorTernario {
     /*valor_si_true if condicion else valor_si_false

estado = "Es bonito" if es_bonito else "No es bonito"

n = [ variables | constantes ]
L = logicos
a = aritmeticos
c = condicional
→ expresion regular
variable = n if  n [a n]* | n [a n]* c n [a n]* [ L n [a n]* c n [a n]* ] * else n 
*/
    final static private int ESTADO_A = 16;
    final static private int ESTADO_B = 17;
    final static private int ESTADO_C = 18;
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
    final static private int ESTADO_S14 = 15;
    
    public static void analizarOperadorTernario(List<Token> tokens) {
        int estadoActual = ESTADO_A;
        for (int i = 0; i < tokens.size(); i++) {

            switch (estadoActual) {
                case ESTADO_A:
                    if (tokens.get(i).getTipo().equals("Identificador")) {
                        // Realiza acciones para el caso ESTADO_INICIAL cuando es un "Identificador"
                        estadoActual = ESTADO_B;
                    }
                    break;
                case ESTADO_B:
                    if (tokens.get(i).getValor().equals("=")) {
                        estadoActual = ESTADO_C;
                    
                    }
                    break;
                case ESTADO_C:
                    if(verificarN(tokens.get(i))){
                        estadoActual = ESTADO_INICIAL;
                    }
                    break;
                case ESTADO_INICIAL:
                    if (tokens.get(i).getValor().equals("if") ) {
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
                    
                    }else if(tokens.get(i).getValor().equals("else")){
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
                    if(verificarN(tokens.get(i))){
                        estadoActual = ESTADO_S14;
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
                    if(tokens.get(i).getValor().equals("else")){
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
                    if(tokens.get(i).getValor().equals("else")){
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
                    
                    
                case ESTADO_S14:
                    if(tokens.get(i).getTipo().equals("Comentario")){
                        estadoActual = ESTADO_S14;
                    
                    }else{
                        estadoActual = ESTADO_INICIAL;
                    }
                    break;

                default:
                // Manejo de otros casos si es necesario.
            }

        }
        
        if(estadoActual == ESTADO_S14){
            System.out.println("OPERADOR TERNARIO VALIDO");
        }else{
            System.out.println("OPERADOR TERNARIO INVALIDO");
        }
    }
    
    private static boolean verificarN(Token token){
        return token.getTipo().equals("Constante") || token.getTipo().equals("Identificador");
    }

}
