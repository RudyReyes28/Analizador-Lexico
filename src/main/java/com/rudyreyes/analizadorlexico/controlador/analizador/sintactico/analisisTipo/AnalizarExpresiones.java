
package com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.analisisTipo;

import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class AnalizarExpresiones {

    /*
        FORMAS DE UNA EXPRESION ASIGNACION
        variable = valor
        variable1, variable2, variable3 = valor1, valor2, valor3
    variable1, variable2, variable3 = valor1;
        valores = [10, 20, 30]
     */
    private List<Token> tokens;
    private int indice; // Índice actual en la lista de tokens

    public AnalizarExpresiones(List<Token> tokens) {
        this.tokens = tokens;
        this.indice = 0;
    }

    // Método para obtener el siguiente token
    private Token siguienteToken() {
        if (indice < tokens.size()) {
            return tokens.get(indice++);
        }
        return null;
    }

    // Método principal para analizar la expresión de asignación
    public void analizarExpresionDeclaracion() {
        Token token = siguienteToken();

        if (token != null && token.getTipo().equals("Identificador")) {
            // Verifica si sigue un "="
            Token igual = siguienteToken();

            if (igual != null && igual.getTipo().equals("OperadorAsignacion")) {
                // Aquí puedes analizar la expresión del lado derecho
                analizarExpresion();
            } else {
                // Error: Se esperaba un "=" después del identificador
                // Puedes lanzar una excepción o manejar el error de otra manera
            }
        } else {
            // Error: Se esperaba un identificador
            // Puedes lanzar una excepción o manejar el error de otra manera
        }
    }

    // Método para analizar una expresión
    private void analizarExpresion() {
        // Aquí implementa la lógica para analizar una expresión
        
    }

}
    
    /*
            private boolean analizarAsignacion(List<Token> tokens) {
    // Verificar si los tokens comienzan con un identificador
    if (tokens.get(0).getTipoToken().equals("Identificador")) {
        // Verificar si el siguiente token es un "="
        if (tokens.get(1).getLexema().equals("=")) {
            // Verificar si el tercer token es una expresión válida
            if (analizarExpresion(tokens.subList(2, tokens.size() - 1))) {
                // Verificar si el último token es un ";"
                if (tokens.get(tokens.size() - 1).getLexema().equals(";")) {
                    // La asignación es válida
                    return true;
                }
            }
        }
    }
    return false;
}
    */

