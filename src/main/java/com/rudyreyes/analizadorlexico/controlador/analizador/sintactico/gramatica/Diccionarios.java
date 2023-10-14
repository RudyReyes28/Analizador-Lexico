
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica;

import com.rudyreyes.analizadorlexico.modelo.estructuraSintactica.EstructuraSintactica;
import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class Diccionarios {
    
    /*
    mi_diccionario = {
    "nombre": "Juan",
    "edad": 30,
    "ciudad": "Ejemploville"
}


variable = { [ } | [ (constante|variable) : (constante|variable)  [, (constante|variable) : (constante|variable) ]*  }  ]

x = constante | variable
v= variable
o = operadorAsignaicon
c = coma ,

{ [ } |  x : x  [, x : x ]* }  ] 

    */
    
    final static private int ESTADO_INICIAL = 0;
    final static private int ESTADO_S0 = 1;
    final static private int ESTADO_S1 = 2;
    final static private int ESTADO_S2 = 3;
    final static private int ESTADO_S3 = 4;
    final static private int ESTADO_S4 = 5;
    final static private int ESTADO_S5 = 6;
    final static private int ESTADO_S6 = 7;
    final static private int ESTADO_S7 = 8;
    final static private int ESTADO_ERROR = 9;
    
    public static EstructuraSintactica analizarDiccionario(List<Token> tokens) {
        EstructuraSintactica estructura = new EstructuraSintactica();
        estructura.setNombreEstructura("Diccionario");
        int estadoActual = ESTADO_INICIAL;
        int i = 0;
        while (i < tokens.size()) {

            switch (estadoActual) {
                case ESTADO_INICIAL:
                    if (tokens.get(i).getValor().equals("{")) {
                       estadoActual = ESTADO_S0;
                    }
                    break;

                case ESTADO_S0:
                    if (tokens.get(i).getValor().equals("}")) {
                        estadoActual = ESTADO_S1;
                    
                    }else if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S2;
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una variable/constante o '}', Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;

                case ESTADO_S1:
                    if(tokens.get(i).getTipo().equals("Comentario")){
                        estadoActual = ESTADO_S1;
                    
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, no se esperaba "+ tokens.get(i).getValor()+", " + "Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    
                    }
                    break;

                case ESTADO_S2:
                    if(tokens.get(i).getValor().equals(":")){
                        estadoActual = ESTADO_S3;
                    
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba ':', Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;
                
                case ESTADO_S3:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S4;
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una variable o constante, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;
                    
                case ESTADO_S4:
                    if(tokens.get(i).getValor().equals("}")){
                        estadoActual = ESTADO_S1;
                    
                    }else if (tokens.get(i).getValor().equals(",")) {
                        estadoActual = ESTADO_S5;
                    
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba ',' o '}', Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;
                    
                case ESTADO_S5:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S6;
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una variable o constante, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;
                    
                case ESTADO_S6:
                    if(tokens.get(i).getValor().equals(":")){
                        estadoActual = ESTADO_S7;
                    
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba ':', Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
                    }
                    break;
                case ESTADO_S7:
                    if(verificarX(tokens.get(i))){
                        estadoActual = ESTADO_S4;
                    }else{
                        estadoActual = ESTADO_ERROR;
                        estructura.setError("Error de Sintaxis, se esperaba una variable o constante, Linea: "+tokens.get(i).getLinea() + " Columna: "+tokens.get(i).getColumna());
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
            estructura.setError("Error de Sintaxis, estructura incompleta se esperaba '}' al final de la linea "+tokens.get(0).getLinea());
            estructura.setEstructuraValida(false);
        }
        estructura.setTokensEstructura(tokens);
        
        return estructura;
        
    }
    
    
    private static boolean verificarX(Token token){
        return token.getTipo().equals("Constante") || token.getTipo().equals("Identificador");
    }
}
