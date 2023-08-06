/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.analizadorlexico.gramatica;

import java.util.HashSet;

/**
 *
 * @author rudyo
 */
public class OperadoresAsignacion {
    
    private final String nombreToken = "OperadorAsignacion";
    
    // Crear un conjunto para almacenar los operadores de asignacion
    HashSet<String> operadoresAsignacion = new HashSet<>();

    public OperadoresAsignacion() {
        operadoresAsignacion.add("=");
        operadoresAsignacion.add("+=");
        operadoresAsignacion.add("-=");
        operadoresAsignacion.add("*=");
        operadoresAsignacion.add("/=");
        operadoresAsignacion.add("%=");
        
    }
    
    public boolean verificandoPalabra(String palabra){
        if(operadoresAsignacion.contains(palabra)){
            return true;
        }else{
            return false;
        }
    }

    public String getNombreToken() {
        return nombreToken;
    }
    
}
