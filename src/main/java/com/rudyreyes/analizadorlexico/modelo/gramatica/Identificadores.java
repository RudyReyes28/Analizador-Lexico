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
public class Identificadores {
    
    private final String nombreToken = "Identificador";
    
    // Crear un conjunto para almacenar los comentarios
    HashSet<String> identificadores = new HashSet<>();

    public Identificadores() {
        identificadores.add("_");
    }
    
    public boolean verificandoPalabra(String palabra){
        if(identificadores.contains(palabra)){
            return true;
        }else{
            return false;
        }
    }

    public String getNombreToken() {
        return nombreToken;
    }
    
}
