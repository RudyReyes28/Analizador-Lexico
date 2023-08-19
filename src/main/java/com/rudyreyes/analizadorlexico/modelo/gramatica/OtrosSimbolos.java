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
public class OtrosSimbolos {
    
    private final String nombreToken = "Otros";
    
    // Crear un conjunto para almacenar otros simbolos
    HashSet<String> otros = new HashSet<>();

    /*
    Paréntesis ( , )
Llaves { , }
Corchetes [ , ]
Coma ,
Punto y coma ;
Dos puntos :
*/
    public OtrosSimbolos() {
        otros.add("(");
        otros.add(")");
        otros.add("{");
        otros.add("}");
        otros.add("[");
        otros.add("]");
        otros.add(",");
        otros.add(";");
        otros.add(":");
        
    }
    
    public boolean verificandoPalabra(String palabra){
        if(otros.contains(palabra)){
            return true;
        }else{
            return false;
        }
    }

    public String getNombreToken() {
        return nombreToken;
    }
    
}
