/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rudyreyes.analizadorlexico.token;

/**
 *
 * @author rudyo
 */
public class Token {
    
    private String tipo;
    private String patron;
    private String valor;
    private int linea;
    private int columna;

    public Token(String tipo, String valor, int linea, int columna) {
        this.tipo = tipo;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }

    public Token(String tipo, String patron, String valor, int linea, int columna) {
        this.tipo = tipo;
        this.patron = patron;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }
    
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    public String obtenerToken(){
        return "Tipo: " + this.getTipo() + ", Lexema: " + this.getValor()+
                    ", Linea: " + this.getLinea() + ", Columna: " + this.getColumna();
    }

    public String getPatron() {
        return patron;
    }
    
    
    
}
