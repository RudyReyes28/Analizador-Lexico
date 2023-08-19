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
public class Comentarios {
    
    private final String nombreToken = "Comentario";
    
    // Crear un conjunto para almacenar los comentarios
    HashSet<String> comentarios = new HashSet<>();

    public Comentarios() {
        comentarios.add("#");
        
    }
    
    public boolean verificandoPalabra(String palabra){
        if(comentarios.contains(palabra)){
            return true;
        }else{
            return false;
        }
    }

    public String getNombreToken() {
        return nombreToken;
    }
    
    
    
}
