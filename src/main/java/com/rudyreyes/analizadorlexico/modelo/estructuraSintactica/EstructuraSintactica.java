
package com.rudyreyes.analizadorlexico.modelo.estructuraSintactica;

import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class EstructuraSintactica {
    
    /*nombreEstructura
list<Tokens>
boolean estructuraValida
tipoError:  
*/
    
    private String nombreEstructura;
    private List<Token> tokensEstructura;
    private boolean estructuraValida;
    private String error;

    public EstructuraSintactica() {
    }

    public EstructuraSintactica(String nombreEstructura, List<Token> tokensEstructura, boolean estructuraValida, String error) {
        this.nombreEstructura = nombreEstructura;
        this.tokensEstructura = tokensEstructura;
        this.estructuraValida = estructuraValida;
        this.error = error;
    }

    public String getNombreEstructura() {
        return nombreEstructura;
    }

    public void setNombreEstructura(String nombreEstructura) {
        this.nombreEstructura = nombreEstructura;
    }

    public List<Token> getTokensEstructura() {
        return tokensEstructura;
    }

    public void setTokensEstructura(List<Token> tokensEstructura) {
        this.tokensEstructura = tokensEstructura;
    }

    public boolean isEstructuraValida() {
        return estructuraValida;
    }

    public void setEstructuraValida(boolean estructuraValida) {
        this.estructuraValida = estructuraValida;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
    
}
