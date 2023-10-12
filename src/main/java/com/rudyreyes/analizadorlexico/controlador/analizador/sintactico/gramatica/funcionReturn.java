
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica;

import com.rudyreyes.analizadorlexico.modelo.estructuraSintactica.EstructuraSintactica;
import com.rudyreyes.analizadorlexico.modelo.estructuraSintactica.MetodoLlamado;
import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class funcionReturn {
   
    /*return  n [a n]* | n [a n]* c n [a n]*
    n = [ variables | constantes | metodos]
L = logicos
a = aritmeticos
c = condicional
    */
    final static private int ESTADO_INICIAL = 0;
    final static private int ESTADO_S0 = 1;
    final static private int ESTADO_S1 = 2;
    final static private int ESTADO_S2 = 3;
    final static private int ESTADO_S3 = 4;
    final static private int ESTADO_ERROR = 5;
    
    public static EstructuraSintactica analizarReturn(List<Token> tokens) {
        int estadoActual = ESTADO_INICIAL;
        
        EstructuraSintactica estructura = new EstructuraSintactica();
        estructura.setNombreEstructura("return");
        
        int i = 0;
        while (i < tokens.size()) {

            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (tokens.get(i).getValor().equals("return")) {
                       estadoActual = ESTADO_S0;
                    }
                    break;

                case ESTADO_S0:
                    
                    if (isMetodo(i, tokens)) {
                        EstructuraSintactica es = Metodos.analizarLlamarMetodo(obtenerMetodos(i, tokens));
                        if(es.isEstructuraValida()){
                            estadoActual = ESTADO_S1;
                            estructura.setMetodosLlamados(new MetodoLlamado(es.getTokensEstructura()));
                            estructura.setHayMetodo(true);
                            i =  obtenerPosicionMetodos(i, tokens);
                            
                        }else{
                            estadoActual = ESTADO_ERROR;
                            estructura.setError("Error de Sintaxis, se esperaba una metodo, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    
                        }
                    }
                    else if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S1;
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una variable o constante  , Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;

                case ESTADO_S1:
                    if(tokens.get(i).getTipo().equals("OperadorAritmetico")){
                        estadoActual = ESTADO_S2;
                    
                    }else if(tokens.get(i).getTipo().equals("OperadorComparacion")){
                        estadoActual = ESTADO_S3;
                    
                    }else if(tokens.get(i).getTipo().equals("Comentario")){
                        estadoActual = ESTADO_S1;
                    
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba un operador aritmetico o de comparacion  , Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;

                case ESTADO_S2:
                    if (isMetodo(i, tokens)) {
                        EstructuraSintactica es = Metodos.analizarLlamarMetodo(obtenerMetodos(i, tokens));
                        if(es.isEstructuraValida()){
                            estadoActual = ESTADO_S1;
                            estructura.setMetodosLlamados(new MetodoLlamado(es.getTokensEstructura()));
                            estructura.setHayMetodo(true);
                            i =  obtenerPosicionMetodos(i, tokens);
                            
                        }else{
                            estadoActual = ESTADO_ERROR;
                            estructura.setError("Error de Sintaxis, se esperaba una metodo, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    
                        }
                    }
                    else if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S1;
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una variable o constante  , Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;
                
                case ESTADO_S3:
                    if (isMetodo(i, tokens)) {
                        EstructuraSintactica es = Metodos.analizarLlamarMetodo(obtenerMetodos(i, tokens));
                        if(es.isEstructuraValida()){
                            estadoActual = ESTADO_S1;
                            estructura.setMetodosLlamados(new MetodoLlamado(es.getTokensEstructura()));
                            estructura.setHayMetodo(true);
                            i =  obtenerPosicionMetodos(i, tokens);
                            
                        }else{
                            estadoActual = ESTADO_ERROR;
                            estructura.setError("Error de Sintaxis, se esperaba una metodo, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    
                        }
                    }
                    else if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S1;
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una variable o constante  , Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;
                
                default:
                // Manejo de otros casos si es necesario.
            }
        
            i++;
        }
        if(estadoActual == ESTADO_S1){
            estructura.setEstructuraValida(true);
        }else if(estadoActual == ESTADO_ERROR){
            estructura.setEstructuraValida(false);
        }else{
            estructura.setError("Error de Sintaxis, estructura incompleta se esperaba una valor al final de la linea "+tokens.get(0).getLinea());
            estructura.setEstructuraValida(false);
        }
        
        estructura.setTokensEstructura(tokens);
        
        return estructura;
        
    }
    
    
    private static boolean verificarX(Token token){
        return token.getTipo().equals("Constante") || token.getTipo().equals("Identificador");
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
