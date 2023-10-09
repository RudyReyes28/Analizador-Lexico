
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica;

import com.rudyreyes.analizadorlexico.modelo.estructuraSintactica.EstructuraSintactica;
import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.ArrayList;
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
        a = operadorAritmetico / comparacion / logicos
        c = coma ,

        v [c v]* o x   [a  x]*  [c x (a x)*]* 

     */
    
    

    final static private int ESTADO_INICIAL = 0;
    final static private int ESTADO_S0 = 1;
    final static private int ESTADO_S1 = 2;
    final static private int ESTADO_S2 = 3;
    final static private int ESTADO_S3 = 4;
    final static private int ESTADO_S4 = 5;
    final static private int ESTADO_S5 = 6;
    final static private int ESTADO_ERROR = 7;

    

    

    public static EstructuraSintactica analizarExpresion(List<Token> tokens) {
        int estadoActual = ESTADO_INICIAL;
        EstructuraSintactica estructura = new EstructuraSintactica();
        estructura.setNombreEstructura("Asignacion de variables");

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
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una ',' o un operador de asignacion, Linea: "+ tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;

                case ESTADO_S1:
                    if (tokens.get(i).getTipo().equals("Identificador")) {
                        estadoActual = ESTADO_S0;
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una variable, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;

                case ESTADO_S2:
                    if (isMetodo(i, tokens)) {
                        if(Metodos.analizarLlamarMetodo(obtenerMetodos(i, tokens)).isEstructuraValida()){
                            estadoActual = ESTADO_S3;
                            i =  obtenerPosicionMetodos(i, tokens);
                            
                        }else{
                            estadoActual = ESTADO_ERROR;
                            estructura.setError("Error de Sintaxis, se esperaba una metodo, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    
                        }
                    }
                    else if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S3;
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una variable o constante, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;
                case ESTADO_S3:
                    //tokens.get(i).getValor().equals(",")
                    if (tokens.get(i).getValor().equals(",")) {
                        estadoActual = ESTADO_S4;
                    
                    }else if (verificarAritmetico(tokens.get(i))) {
                        estadoActual = ESTADO_S5;
                    
                    }else if(tokens.get(i).getTipo().equals("Comentario")){
                        estadoActual = ESTADO_S3;
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una ',' o un operador aritmetico, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    
                    }
                    break;
                case ESTADO_S4:
                    if (isMetodo(i, tokens)) {
                        if(Metodos.analizarLlamarMetodo(obtenerMetodos(i, tokens)).isEstructuraValida()){
                            estadoActual = ESTADO_S3;
                            i =  obtenerPosicionMetodos(i, tokens);
                            
                        }else{
                            estadoActual = ESTADO_ERROR;
                            estructura.setError("Error de Sintaxis, se esperaba una metodo, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    
                        }
                    }
                    else if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S3;
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una variable o constante, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;
                case ESTADO_S5:
                    if (isMetodo(i, tokens)) {
                        if(Metodos.analizarLlamarMetodo(obtenerMetodos(i, tokens)).isEstructuraValida()){
                            estadoActual = ESTADO_S3;
                            i =  obtenerPosicionMetodos(i, tokens);
                            
                        }else{
                            estadoActual = ESTADO_ERROR;
                            estructura.setError("Error de Sintaxis, se esperaba una metodo, Linea: "+tokens.get(i).getLinea() + "Columna"+tokens.get(i).getColumna());
                    
                        }
                    }
                    else if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S3;
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una variable o constante, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;

                default:
                // Manejo de otros casos si es necesario.
            }

        }
        
        if(estadoActual == ESTADO_S3){
            estructura.setEstructuraValida(true);
        }else if(estadoActual == ESTADO_ERROR){
            estructura.setEstructuraValida(false);
        }else{
            estructura.setError("Error de Sintaxis, estructura incompleta se espera algo al final de la linea "+tokens.get(0).getLinea());
            estructura.setEstructuraValida(false);
        }
        
        estructura.setTokensEstructura(tokens);
        
        return estructura;

    }
    
    private static boolean verificarX(Token token){
        return token.getTipo().equals("Constante") || token.getTipo().equals("Identificador");
    }
    
    private static boolean verificarAritmetico(Token token){
        
        if(token.getTipo().equals("OperadorAritmetico")){
            return true;
        
        } else if(token.getTipo().equals("OperadorComparacion")){
            return true;
        } else if(token.getTipo().equals("OperadorLogico")){
            return true;
        } else{
            return false;
        }
        
    }
    
    private static List<Token> obtenerMetodos(int i,List<Token> tokens ){
        List<Token> tokensMetodo = new ArrayList<>();
        int posicion = obtenerPosicionMetodos(i, tokens);
        
        for(int n = i; n <= posicion; n++ ){
            tokensMetodo.add(tokens.get(n));
        }
        
        return tokensMetodo;
    }
    
    private static int obtenerPosicionMetodos(int i, List<Token> tokens){
        for(int n = i; n < tokens.size(); n++ ){
            if (tokens.get(n).getValor().equals(")")){
                return n;
            }
        }
        
        return tokens.size() -1;
    }
    
    private static boolean isMetodo(int i, List<Token> tokens){
        
        if(i+1 < tokens.size()){
            if(tokens.get(i).getTipo().equals("Identificador") && tokens.get(i+1).getValor().equals("(")){
                return true;
            }
        }
        return false;
    }
    

}

