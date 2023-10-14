
package com.rudyreyes.analizadorlexico.modelo.estructuraSintactica;

import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class MetodoLlamado {
    private List<Token> metodo;

    public MetodoLlamado() {
        metodo = new ArrayList<>();
    }

    public MetodoLlamado(List<Token> metodo) {
        this.metodo = metodo;
    }
    
    

    public List<Token> getMetodo() {
        return metodo;
    }

    public void setMetodo(List<Token> metodo) {
        this.metodo = metodo;
    }
    

    
    
    
    
}
