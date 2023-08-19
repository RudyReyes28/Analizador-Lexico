/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.analizadorlexico.modelo.gramatica;

import java.util.HashSet;

/**
 *
 * @author rudyo
 */
public class Constantes {
    
    private final String nombreToken = "Constante";
    
    // Crear un conjunto para almacenar las constantes
    HashSet<String> constantes = new HashSet<>();

    public Constantes() {
        constantes.add("True");
        constantes.add("False");
        constantes.add(".");
        constantes.add("'");
    }
    
    public boolean verificandoPalabra(String palabra){
        if(constantes.contains(palabra)){
            return true;
        }else{
            return false;
        }
    }

    public String getNombreToken() {
        return nombreToken;
    }
    
    
    
}
