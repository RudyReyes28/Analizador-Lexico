
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica;

import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class funcionPrint {
    
    /*
    print(10 + 10 * 10) # 80
print(suma + 10 * resta / multiplicacion ** division % exponente) # 81
print (result)

→ expresion regular
x = constante | variable
v= variable
o = operadorAsignaicon
a = operadorAritmetico
c = coma ,

print ( x   [a  x]*  [c x (a x)*]* )
*/
    
    final static private int ESTADO_INICIAL = 0;
    final static private int ESTADO_S0 = 1;
    final static private int ESTADO_S1 = 2;
    final static private int ESTADO_S2 = 3;
    final static private int ESTADO_S3 = 4;
    final static private int ESTADO_S4 = 5;
    final static private int ESTADO_S5 = 6;
    
    public static void analizarPrint(List<Token> tokens) {
        int estadoActual = ESTADO_INICIAL;
        int i = 0;
        while (i < tokens.size()) {

            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (tokens.get(i).getValor().equals("print")) {
                       estadoActual = ESTADO_S0;
                    }
                    break;

                case ESTADO_S0:
                    if (tokens.get(i).getValor().equals("(")) {
                        estadoActual = ESTADO_S1;
                    
                    }
                    break;

                case ESTADO_S1:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S2;
                    }
                    break;

                case ESTADO_S2:
                    if (tokens.get(i).getTipo().equals("OperadorAritmetico")) {
                        estadoActual = ESTADO_S3;
                    
                    }else if(tokens.get(i).getValor().equals(",")){
                        estadoActual = ESTADO_S4;
                    
                    }else if(tokens.get(i).getValor().equals(")")){
                        estadoActual = ESTADO_S5;
                    
                    }
                    break;
                
                case ESTADO_S3:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S2;
                    }
                    break;
                case ESTADO_S4:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S2;
                    }
                    break;
                    
                case ESTADO_S5:
                    if(tokens.get(i).getTipo().equals("Comentario")){
                        estadoActual = ESTADO_S5;
                    
                    }else{
                        estadoActual = ESTADO_INICIAL;
                    }
                    break;
                default:
                // Manejo de otros casos si es necesario.
            }
        
            i++;
        }
        if(estadoActual == ESTADO_S5){
            
            System.out.println("PRINT VALIDO");
        }else{
            System.out.println("PRINT INVALIDO");
        }
        
    }
    
    
    private static boolean verificarX(Token token){
        return token.getTipo().equals("Constante") || token.getTipo().equals("Identificador");
    }
}
