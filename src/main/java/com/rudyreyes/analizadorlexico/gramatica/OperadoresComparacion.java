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
public class OperadoresComparacion {
    private final String nombreToken = "OperadorComparacion";
    
    // Crear un conjunto para almacenar los operadores de comparacion
    HashSet<String> operadoresComparacion = new HashSet<>();

    public OperadoresComparacion() {
        operadoresComparacion.add("==");
        operadoresComparacion.add("!=");
        operadoresComparacion.add(">");
        operadoresComparacion.add("<");
        operadoresComparacion.add(">=");
        operadoresComparacion.add("<=");
    }
    
    public boolean verificandoPalabra(String palabra){
        if(operadoresComparacion.contains(palabra)){
            return true;
        }else{
            return false;
        }
    }

    public String getNombreToken() {
        return nombreToken;
    }
    
    
}
