
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
public class CicloFor {
    
    final static private int ESTADO_INICIAL = 0;
    final static private int ESTADO_S0 = 1;
    final static private int ESTADO_S1 = 2;
    final static private int ESTADO_S2 = 3;
    final static private int ESTADO_S3 = 4;
    final static private int ESTADO_S4 = 5;
    final static private int ESTADO_ERROR = 6;
    
    /*
    for x in range:
for fruta in frutas: 
for i in fib():
for i in fib(1,2,3):
for n in range(2, 10): 

→ Expresion regular:
for variable in variable:
*/
    
    public static EstructuraSintactica analizarCicloFor(List<Token> tokens) {
        int estadoActual = ESTADO_INICIAL;
        
        EstructuraSintactica estructura = new EstructuraSintactica();
        estructura.setNombreEstructura("Ciclo for");
        
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
                    
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una variable, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;

                case ESTADO_S1:
                    if (tokens.get(i).getValor().equals("in")) {
                        estadoActual = ESTADO_S2;
                    
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba 'in', Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;

                case ESTADO_S2:
                    //AQUI TENGO QUE VER SI LO QUE VIENE ES UNA VARIABLE O UN METODO, O RANGE
                    if (isMetodo(i, tokens)) {
                        EstructuraSintactica es = Metodos.analizarLlamarMetodo(obtenerMetodos(i, tokens));
                        if(es.isEstructuraValida()){
                            estadoActual = ESTADO_S3;
                            estructura.setMetodosLlamados(new MetodoLlamado(es.getTokensEstructura()));
                            estructura.setHayMetodo(true);
                            i =  obtenerPosicionMetodos(i, tokens) -1;
                            
                        }else{
                            estadoActual = ESTADO_ERROR;
                            estructura.setError("Error de Sintaxis, se esperaba una metodo, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    
                        }
                    }
                    
                    else if(tokens.get(i).getValor().equals("range")){
                        EstructuraSintactica es = Metodos.analizarMetodoRange(obtenerMetodos(i, tokens));
                        if(es.isEstructuraValida()){
                            estadoActual = ESTADO_S3;
                            estructura.setMetodosLlamados(new MetodoLlamado(es.getTokensEstructura()));
                            estructura.setHayMetodo(true);
                            i =  obtenerPosicionMetodos(i, tokens) -1;
                            
                        }else{
                            estadoActual = ESTADO_ERROR;
                            estructura.setError("Error de Sintaxis, se esperaba una metodo, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    
                        }
                    }
                    
                    else if(tokens.get(i).getTipo().equals("Identificador")){
                        estadoActual = ESTADO_S3;
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una variable, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;
                
                case ESTADO_S3:
                    if (tokens.get(i).getValor().equals(":")) {
                        estadoActual = ESTADO_S4;
                    
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba un ':', Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;
                case ESTADO_S4:
                    if(tokens.get(i).getTipo().equals("Comentario")){
                        estadoActual = ESTADO_S4;
                    
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, no se esperaba "+ tokens.get(i).getValor()+", " + "Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;

                default:
                // Manejo de otros casos si es necesario.
            }
        
            i++;
        }
        if(estadoActual == ESTADO_S4){
            estructura.setEstructuraValida(true);
        }else if(estadoActual == ESTADO_ERROR){
            estructura.setEstructuraValida(false);
        }else{
            estructura.setError("Error de Sintaxis, estructura incompleta se espera una variable/constante o ':' al final de la linea "+tokens.get(0).getLinea());
            estructura.setEstructuraValida(false);
        }
        
        estructura.setTokensEstructura(tokens);
        return estructura;
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
    
    private static boolean isMetodo(int i, List<Token> tokens){
        
        if(i+1 < tokens.size()){
            if(tokens.get(i).getTipo().equals("Identificador") && tokens.get(i+1).getValor().equals("(")){
                return true;
            }
        }
        return false;
    }
}
