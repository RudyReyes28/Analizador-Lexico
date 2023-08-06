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
public class OperadoresAritmeticos {
    
    private final String nombreToken = "OperadorAritmetico";
    
    // Crear un conjunto para almacenar los operadores aritméticos
    HashSet<String> operadoresAritmeticos = new HashSet<>();

    public OperadoresAritmeticos() {
        // Agregar los operadores aritméticos al conjunto
        operadoresAritmeticos.add("+");
        operadoresAritmeticos.add("-");
        operadoresAritmeticos.add("**");
        operadoresAritmeticos.add("/");
        operadoresAritmeticos.add("//");
        operadoresAritmeticos.add("%");
        operadoresAritmeticos.add("*");
    }
    
    public boolean verificandoPalabra(String palabra){
        if(operadoresAritmeticos.contains(palabra)){
            return true;
        }else{
            return false;
        }
    }

    public String getNombreToken() {
        return nombreToken;
    }
}
