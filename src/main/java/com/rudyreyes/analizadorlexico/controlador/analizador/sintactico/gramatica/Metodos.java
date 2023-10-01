
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica;

import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class Metodos {
    
   
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
    DECLARAR METODO
def nombre_de_la_funcion(parametro1, parametro2, ...):  
def nombre_de_la_funcion(): 

def identificador ( [ ) |  {[identificador [, identificador]*] ) } ]: 

v = identificador
c = coma
→ Expresion regular:
def v ( [ ) |  v [c v]*  )  ] : 

    */
    public static void analizarDeclaracionMetodo(List<Token> tokens) {
        int estadoActual = ESTADO_INICIAL;
        for (int i = 0; i < tokens.size(); i++) {

            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (tokens.get(i).getValor().equals("def")) {
                        // Realiza acciones para el caso ESTADO_INICIAL cuando es un "Identificador"
                        estadoActual = ESTADO_S0;
                    }
                    break;

                case ESTADO_S0:
                    //tokens.get(i).getValor().equals(",")
                    if (tokens.get(i).getTipo().equals("Identificador")) {
                        estadoActual = ESTADO_S1;
                    
                    }
                    break;

                case ESTADO_S1:
                    if (tokens.get(i).getValor().equals("(")) {
                        estadoActual = ESTADO_S2;
                    }
                    break;

                case ESTADO_S2:
                    if(tokens.get(i).getTipo().equals("Identificador")){
                        estadoActual = ESTADO_S3;
                    
                    }else if(tokens.get(i).getValor().equals(")")){
                        estadoActual = ESTADO_S4;
                    }
                        
                    break;
                case ESTADO_S3:
                    
                    if (tokens.get(i).getValor().equals(",")) {
                        estadoActual = ESTADO_S5;
                    
                    } else if (tokens.get(i).getValor().equals(")")) {
                        estadoActual = ESTADO_S4;
                    }
                    break;
                case ESTADO_S4:
                    if(tokens.get(i).getValor().equals(":")){
                        estadoActual = ESTADO_S6;
                    }
                    break;
                case ESTADO_S5:
                    if(tokens.get(i).getTipo().equals("Identificador")){
                        estadoActual = ESTADO_S3;
                    }
                    break;

                case ESTADO_S6:
                    if(tokens.get(i).getTipo().equals("Comentario")){
                        estadoActual = ESTADO_S6;
                    
                    }else{
                        estadoActual = ESTADO_INICIAL;
                    }
                    break;
                default:
                // Manejo de otros casos si es necesario.
            }

        }
        
        if(estadoActual == ESTADO_S6){
            System.out.println("DECLARACION DE METODOS VALIDA ");
        }else{
            System.out.println("NO ES UNA DECLARACION DE METODOS");
        }

    }
    
    /*
    LLAMAR METODO
nombre_funcion(argumentos) 
mi_funcion_vacia() 

identificador ( [ ) |  {(constante | variable )  [operadorAritmetico (constante | variable )]*    [, (constante | variable ) [operadorAritmetico (constante | variable )]* ]* ) } ]


constante | variable = x
v= identificador
o = operadorAsignaicon
a = operadorAritmetico
c = coma , 
→ Expresion regular:
v ( [ ) | { x   [a  x]*  [c x (a x)*]*  ) } ]
*/
    
    public static boolean analizarLlamarMetodo(List<Token> tokens) {
        int estadoActual = ESTADO_INICIAL;
        for (int i = 0; i < tokens.size(); i++) {

            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (tokens.get(i).getTipo().equals("Identificador")) {
                        // Realiza acciones para el caso ESTADO_INICIAL cuando es un "Identificador"
                        estadoActual = ESTADO_S0;
                    }
                    break;

                case ESTADO_S0:
                    if (tokens.get(i).getValor().equals("(")) {
                        estadoActual = ESTADO_S1;
                    
                    }
                    break;

                case ESTADO_S1:
                    if (tokens.get(i).getValor().equals(")")) {
                        estadoActual = ESTADO_S2;
                    
                    }else if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S3;
                    }
                    break;

                case ESTADO_S2:
                    if(tokens.get(i).getTipo().equals("Comentario")){
                        estadoActual = ESTADO_S2;
                    
                    }else{
                        estadoActual = ESTADO_INICIAL;
                    }
                    break;
                case ESTADO_S3:
                    
                    if (tokens.get(i).getValor().equals(",")) {
                        estadoActual = ESTADO_S5;
                    
                    } else if (tokens.get(i).getTipo().equals("OperadorAritmetico")) {
                        estadoActual = ESTADO_S4;
                    
                    }else if(tokens.get(i).getValor().equals(")")) {
                        estadoActual = ESTADO_S2;
                    
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
        
        if(estadoActual == ESTADO_S2){
            System.out.println("LLAMADO DE METODO VALIDO");
            return true;
        }else{
            System.out.println("LLAMADO DE METODO INVALIDO");
            return false;
        }

    }
    
    /*range(max)
range(min, max)
range(min, max, step)

x = variable | constante
range ( x | x , x | x , x , x ) 
*/
    public static boolean analizarMetodoRange(List<Token> tokens){
        int estadoActual = ESTADO_INICIAL;
        for (int i = 0; i < tokens.size(); i++) {

            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (tokens.get(i).getValor().equals("range")) {
                        // Realiza acciones para el caso ESTADO_INICIAL cuando es un "Identificador"
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
                    if (tokens.get(i).getValor().equals(",")) {
                        estadoActual = ESTADO_S3;
                    
                    }else if(tokens.get(i).getValor().equals(")")) {
                        estadoActual = ESTADO_S4;
                    
                    }
                    break;
                case ESTADO_S3:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S5;
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
                    if (tokens.get(i).getValor().equals(",")) {
                        estadoActual = ESTADO_S6;
                    
                    }else if(tokens.get(i).getValor().equals(")")) {
                        estadoActual = ESTADO_S4;
                    
                    }
                    break;
                    
                case ESTADO_S6:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S7;
                    }
                    break;
                    
                    
                case ESTADO_S7:
                    if(tokens.get(i).getValor().equals(")")) {
                        estadoActual = ESTADO_S4;
                    
                    }
                    break;

                default:
                // Manejo de otros casos si es necesario.
            }

        }
        
        if(estadoActual == ESTADO_S4){
            System.out.println("LLAMADO DE METODO RANGE VALIDO");
            return true;
        }else{
            System.out.println("LLAMADO DE METODO RANGE INVALIDO");
            return false;
        }
    }
    
    private static boolean verificarX(Token token){
        return token.getTipo().equals("Constante") || token.getTipo().equals("Identificador");
    }
    
    
    
}
