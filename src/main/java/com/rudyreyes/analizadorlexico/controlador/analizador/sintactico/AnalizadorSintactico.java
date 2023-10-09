
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico;

import com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica.Arreglos;
import com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica.AsignacionVariables;
import com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica.CicloFor;
import com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica.Diccionarios;
import com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica.IfCondicional;
import com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica.Metodos;
import com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica.OperadorTernario;
import com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica.SentenciaIf;
import com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica.funcionPrint;
import com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.gramatica.funcionReturn;
import com.rudyreyes.analizadorlexico.modelo.estructuraSintactica.EstructuraSintactica;
import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class AnalizadorSintactico {
    
    public static void analizarSintaxis (List<Token> tokens ){
        List<EstructuraSintactica> estructuraSintactica = new ArrayList<>();
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
                
                //LLAMAR PRINT
                if(tokensFilaActual.get(0).getValor().equals("print")){
                    funcionPrint.analizarPrint(tokensFilaActual);
                }
                
                //LLAMAR METODOS
                else if(tokensFilaActual.get(0).getTipo().equals("Identificador") && tokensFilaActual.get(1).getValor().equals("(")){
                    Metodos.analizarLlamarMetodo(tokensFilaActual);
                }
                
                //OPERADOR TERNARIO
                else if(verificarOperadorTernario(tokensFilaActual)){
                    OperadorTernario.analizarOperadorTernario(tokensFilaActual);
                }
                
                //ASIGNACION DE ARREGLOS
                else if(tokensFilaActual.get(0).getTipo().equals("Identificador") && buscarArreglo(tokensFilaActual)){
                    Arreglos.analizarArreglo(tokensFilaActual);
                }
                
                //ASIGNACION DE DICCIONARIOS
                
                else if(verificarDiccionario(tokensFilaActual)){
                    Diccionarios.analizarDiccionario(tokensFilaActual);
                }
                
                //ASIGNACION DE VARIABLES
                else if (tokensFilaActual.get(0).getTipo().equals("Identificador")) {
                    
                    estructuraSintactica.add(AsignacionVariables.analizarExpresion(tokensFilaActual));
                
                }
                
                //RETURN
                else if(tokensFilaActual.get(0).getValor().equals("return")){
                    funcionReturn.analizarReturn(tokensFilaActual);
                
                }
                
                //IF ELIF
                else if(tokensFilaActual.get(0).getValor().equals("if") || tokensFilaActual.get(0).getValor().equals("elif") ){
                    estructuraSintactica.add(SentenciaIf.analizarExpresion(tokensFilaActual));
                
                }
                //ELSE 
                else if(tokensFilaActual.get(0).getValor().equals("else")){
                    estructuraSintactica.add(SentenciaIf.analizarExpresionElse(tokensFilaActual));
                
                }
                //DECLARACION DE METODOS
                else if(tokensFilaActual.get(0).getValor().equals("def")){
                    Metodos.analizarDeclaracionMetodo(tokensFilaActual);
                
                }
                
                //METODO RANGE
                else if(tokensFilaActual.get(0).getValor().equals("range")){
                    Metodos.analizarMetodoRange(tokensFilaActual);
                
                }
                
                //CICLO FOR
                else if(tokensFilaActual.get(0).getValor().equals("for")){
                    estructuraSintactica.add(CicloFor.analizarCicloFor(tokensFilaActual));
                
                }
                
                //CICLO WHILE
                else if(tokensFilaActual.get(0).getValor().equals("while")){
                    estructuraSintactica.add(SentenciaIf.analizarExpresion(tokensFilaActual));
                
                }
            }
        }
        
        for(EstructuraSintactica estructura: estructuraSintactica){
            if(!estructura.isEstructuraValida()){
                System.out.println(estructura.getError());
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
    
    private static boolean buscarArreglo(List<Token> tokens){
        for (Token token : tokens) {
            
            if (token.getValor().equals("[")) {
                return true;
            }
        }
        
        return false;
    }
    
    private static boolean verificarDiccionario(List<Token> tokens){
        for (Token token : tokens) {
            
            if (token.getValor().equals("{")) {
                if(tokens.get(0).getTipo().equals("Identificador") && tokens.get(1).getValor().equals("=") ){
                    return true;
                }
            }
        }
        
        
        return false;
    }
    
    private static boolean verificarOperadorTernario(List<Token> tokens){
        for (Token token : tokens) {
            
            if (token.getValor().equals("if")) {
                if(tokens.get(0).getTipo().equals("Identificador") && tokens.get(1).getValor().equals("=") ){
                    return true;
                }
            }
        }
        
        
        return false;
        
    }
    
}
