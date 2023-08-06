/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.rudyreyes.analizadorlexico.gramatica;

import java.util.HashSet;

/**
 *
 * @author rudyo
 */
public class PalabrasClave {

    private final String nombreToken = "PalabraClave";
    
    // Crear un conjunto para almacenar las palabras reservadas
        HashSet<String> palabrasReservadas = new HashSet<>();
    // Agregar las palabras reservadas al conjunto

    public PalabrasClave() {
        //AGREGANDO LAS PALABRAS RESERVADAS
        palabrasReservadas.add("as");
        palabrasReservadas.add("assert");
        palabrasReservadas.add("break");
        palabrasReservadas.add("class");
        palabrasReservadas.add("continue");
        palabrasReservadas.add("def");
        palabrasReservadas.add("del");
        palabrasReservadas.add("elif");
        palabrasReservadas.add("else");
        palabrasReservadas.add("except");
        palabrasReservadas.add("finally");
        palabrasReservadas.add("for");
        palabrasReservadas.add("from");
        palabrasReservadas.add("global");
        palabrasReservadas.add("if");
        palabrasReservadas.add("import");
        palabrasReservadas.add("in");
        palabrasReservadas.add("is");
        palabrasReservadas.add("lambda");
        palabrasReservadas.add("None");
        palabrasReservadas.add("nonlocal");
        palabrasReservadas.add("pass");
        palabrasReservadas.add("raise");
        palabrasReservadas.add("return");
        palabrasReservadas.add("try");
        palabrasReservadas.add("while");
        palabrasReservadas.add("with");
        palabrasReservadas.add("yield");
    }
    
    
    public boolean verificandoPalabra(String palabra){
        if(palabrasReservadas.contains(palabra)){
            return true;
        }else{
            return false;
        }
    }

    public String getNombreToken() {
        return nombreToken;
    }
    
   
} 
