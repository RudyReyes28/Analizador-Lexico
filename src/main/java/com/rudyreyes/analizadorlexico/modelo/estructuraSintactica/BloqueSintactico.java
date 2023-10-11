/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.analizadorlexico.modelo.estructuraSintactica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rudyo
 */
public class BloqueSintactico {
    String nombreBloque;
    List<EstructuraSintactica> sintaxis;

    public BloqueSintactico() {
        sintaxis = new ArrayList<>();
    }

    public BloqueSintactico(String nombreBloque, List<EstructuraSintactica> sintaxis) {
        this.nombreBloque = nombreBloque;
        this.sintaxis = sintaxis;
    }

    public String getNombreBloque() {
        return nombreBloque;
    }

    public void setNombreBloque(String nombreBloque) {
        this.nombreBloque = nombreBloque;
    }

    public List<EstructuraSintactica> getSintaxis() {
        return sintaxis;
    }

    public void setSintaxis(List<EstructuraSintactica> sintaxis) {
        this.sintaxis = sintaxis;
    }
    
    public void setSintaxis(EstructuraSintactica sintaxis) {
        this.sintaxis.add(sintaxis);
    }
    
    
}
