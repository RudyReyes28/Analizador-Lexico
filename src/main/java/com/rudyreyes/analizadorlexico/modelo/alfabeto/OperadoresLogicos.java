/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.analizadorlexico.modelo.alfabeto;

import java.util.HashSet;

/**
 *
 * @author rudyo
 */
public class OperadoresLogicos {
    
    private final String nombreToken = "OperadorLogico";
    
    // Crear un conjunto para almacenar los operadores logicos
    HashSet<String> operadoresLogicos = new HashSet<>();

    public OperadoresLogicos() {
        operadoresLogicos.add("and");
        operadoresLogicos.add("or");
        operadoresLogicos.add("not");
    }
    
    public boolean verificandoPalabra(String palabra){
        if(operadoresLogicos.contains(palabra)){
            return true;
        }else{
            return false;
        }
    }

    public String getNombreToken() {
        return nombreToken;
    }
    
    
}
