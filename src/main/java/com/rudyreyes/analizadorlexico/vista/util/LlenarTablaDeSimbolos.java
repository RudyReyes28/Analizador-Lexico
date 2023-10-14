
package com.rudyreyes.analizadorlexico.vista.util;

import com.rudyreyes.analizadorlexico.modelo.estructuraSintactica.EstructuraSintactica;
import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rudyo
 */
public class LlenarTablaDeSimbolos {
    
    public static void llenarTablaSimbolos(List<EstructuraSintactica> sintaxis, DefaultTableModel model){
        for(EstructuraSintactica es: sintaxis){
            if(es.isEstructuraValida()){
                //1. SIMBOLO 2. TIPO 3. VALOR 4.Linea 5. COLUMNA
                switch (es.getNombreEstructura()) {
                //LLAMAR UN METODO QUE LLENE ESTA TABLA
                    case "Asignacion de variables":
                        asignacionDeVariables(es.getTokensEstructura(), model);
                        break;
                    case "Arreglo":
                        asignacionDeArreglosDic(es.getTokensEstructura(), model, "Arreglo");
                        break;
                    case "Diccionario":
                        asignacionDeArreglosDic(es.getTokensEstructura(), model, "Diccionario");
                        break;
                    case "Condicional if":
                        estructuraIf_Elif_While(es.getTokensEstructura(), model, es.getTokensEstructura().get(0).getValor(), "Condicional if");
                        break;
                    case "Condicional else":
                        llenarTabla("else", "Condicional Else", "---", es.getTokensEstructura().get(0).getLinea(), es.getTokensEstructura().get(0).getColumna(), model);
                        break;
                    case "Ciclo while":
                        estructuraIf_Elif_While(es.getTokensEstructura(), model, "while", "Ciclo while");
                        break;
                    case "Ciclo for":
                        estructuraFor(es.getTokensEstructura(), model);
                        break;
                    case "Declaracion Metodo":
                        llenarTabla(es.getTokensEstructura().get(1).getValor(), "Metodo", "---", es.getTokensEstructura().get(0).getLinea(), es.getTokensEstructura().get(0).getColumna(), model);

                        break;
                    case "Llamar Metodo":
                        llenarTabla(es.getTokensEstructura().get(0).getValor(), "Llamar Metodo", "---", es.getTokensEstructura().get(0).getLinea(), es.getTokensEstructura().get(0).getColumna(), model);
                        break;
                    case "Operador Ternario":
                        operadorTernario(es.getTokensEstructura(), model);

                        break;
                    case "print":
                        funcionPrint(es.getTokensEstructura(), model);
                        break;
                    case "return":
                        funcionReturn(es.getTokensEstructura(), model);
                        break;
                    case "Otros":
                        llenarTabla(es.getTokensEstructura().get(0).getValor(), es.getTokensEstructura().get(0).getValor(), "---", es.getTokensEstructura().get(0).getLinea(), es.getTokensEstructura().get(0).getColumna(), model);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    private static void asignacionDeVariables(List<Token> tokens, DefaultTableModel model){
        //1. SIMBOLO 2. TIPO 3. VALOR 4. COLUMNA
        String simbolo = "";
        String tipo = "Variable";
        String valor = "";
        int operador = 0;
        
        
        for(Token token: tokens){
            if(token.getTipo().equals("OperadorAsignacion")){
                operador++;
                break;
            }else{
                simbolo += token.getValor()+" ";
            }
            operador++;
        }
        
        for(int i = operador; i < tokens.size(); i++){
            if(tokens.get(i).getTipo().equals("Comentario")){
                break;
            }
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla(simbolo, tipo, valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    }

    private static void asignacionDeArreglosDic(List<Token> tokens, DefaultTableModel model, String tipo){
        String simbolo = "";
        String valor = "";
        int operador = 0;
        
        for(Token token: tokens){
            if(token.getValor().equals("=")){
                operador++;
                break;
            }else{
                simbolo += token.getValor()+" ";
            }
            operador++;
        }
        
        for(int i = operador+1; i < tokens.size(); i++){
            if(tokens.get(i).getValor().equals("]") || tokens.get(i).getValor().equals("}")){
                break;
            }
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla(simbolo, tipo, valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    }
    
    private static void estructuraIf_Elif_While(List<Token> tokens, DefaultTableModel model,String simbolo, String tipo){
        
        String valor = "";
        int operador = 0;
        
        
        
        for(int i = 1; i < tokens.size(); i++){
            if(tokens.get(i).getValor().equals(":")){
                break;
            }
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla(simbolo, tipo, valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    
    }
   
    private static void estructuraFor(List<Token> tokens, DefaultTableModel model){
        String valor = "";
        int operador = 0;
        
        
        
        for(int i = 1; i < tokens.size(); i++){
            if(tokens.get(i).getValor().equals(":")){
                break;
            }
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla("for", "Ciclo for", valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    
    }
    
    private static void operadorTernario(List<Token> tokens, DefaultTableModel model){
        String simbolo = tokens.get(0).getValor();
        String valor = "";
        
        
        for(int i = 2; i < tokens.size(); i++){
            if(tokens.get(i).getTipo().equals("Comentario")){
                break;
            }
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla(simbolo, "Operador Ternario", valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    }
    
    private static void funcionPrint(List<Token> tokens, DefaultTableModel model){
        String simbolo = tokens.get(0).getValor();
        String valor = "";
        
        
        for(int i = 2; i < tokens.size(); i++){
            if(tokens.get(i).getValor().equals(")")){
                break;
            }
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla(simbolo, "print", valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    }
    
    private static void funcionReturn(List<Token> tokens, DefaultTableModel model){
        String simbolo = tokens.get(0).getValor();
        String valor = "";
        
        
        for(int i = 1; i < tokens.size(); i++){
            if(tokens.get(i).getTipo().equals("Comentario")){
                break;
            }
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla(simbolo, "return", valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    }
    
    private static void llenarTabla(String simbolo, String tipo, String valor, int linea, int columna, DefaultTableModel model){
        model.addRow(new Object[]{
                   simbolo,
                   tipo,
                   valor,
                   linea,
                   columna
                });
    }
    
}
