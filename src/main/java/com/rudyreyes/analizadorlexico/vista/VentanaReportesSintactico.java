/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.rudyreyes.analizadorlexico.vista;

import com.rudyreyes.analizadorlexico.modelo.estructuraSintactica.EstructuraSintactica;
import com.rudyreyes.analizadorlexico.modelo.token.Token;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rudyo
 */
public class VentanaReportesSintactico extends javax.swing.JFrame {
    private List<EstructuraSintactica> sintaxis;

    /**
     * Creates new form VentanaReportesSintactico
     * @param sintaxis
     */
    public VentanaReportesSintactico(List<EstructuraSintactica> sintaxis) {
        this.sintaxis = sintaxis;
        
        initComponents();
        llenarTablaSimbolos();
    }
    
    private void llenarTablaSimbolos(){
        DefaultTableModel model = (DefaultTableModel) tablaReportes.getModel();
        for(EstructuraSintactica es: sintaxis){
            if(es.isEstructuraValida()){
                //1. SIMBOLO 2. TIPO 3. VALOR 4.Linea 5. COLUMNA
                switch (es.getNombreEstructura()) {
                //LLAMAR UN METODO QUE LLENE ESTA TABLA
                    case "Asignacion de variables":
                        asignacionDeVariables(es.getTokensEstructura(), model);
                        break;
                    case "Arreglo":
                        asignacionDeArreglosDic(es.getTokensEstructura(), model, "Arreglo");
                        break;
                    case "Diccionario":
                        asignacionDeArreglosDic(es.getTokensEstructura(), model, "Diccionario");
                        break;
                    case "Condicional if":
                        estructuraIf_Elif_While(es.getTokensEstructura(), model, es.getTokensEstructura().get(0).getValor(), "Condicional if");
                        break;
                    case "Condicional else":
                        llenarTabla("else", "Condicional Else", "---", es.getTokensEstructura().get(0).getLinea(), es.getTokensEstructura().get(0).getColumna(), model);
                        break;
                    case "Ciclo while":
                        estructuraIf_Elif_While(es.getTokensEstructura(), model, "while", "Ciclo while");
                        break;
                    case "Ciclo for":
                        estructuraFor(es.getTokensEstructura(), model);
                        break;
                    case "Declaracion Metodo":
                        llenarTabla(es.getTokensEstructura().get(1).getValor(), "Metodo", "---", es.getTokensEstructura().get(0).getLinea(), es.getTokensEstructura().get(0).getColumna(), model);

                        break;
                    case "Llamar Metodo":
                        llenarTabla(es.getTokensEstructura().get(0).getValor(), "Llamar Metodo", "---", es.getTokensEstructura().get(0).getLinea(), es.getTokensEstructura().get(0).getColumna(), model);
                        break;
                    case "Operador Ternario":
                        operadorTernario(es.getTokensEstructura(), model);

                        break;
                    case "print":
                        funcionPrint(es.getTokensEstructura(), model);
                        break;
                    case "return":
                        funcionReturn(es.getTokensEstructura(), model);
                        break;
                    default:
                        break;
                }
            }
        }
        
    }
    
    private void asignacionDeVariables(List<Token> tokens, DefaultTableModel model){
        //1. SIMBOLO 2. TIPO 3. VALOR 4. COLUMNA
        String simbolo = "";
        String tipo = "Variable";
        String valor = "";
        int operador = 0;
        
        
        for(Token token: tokens){
            if(token.getTipo().equals("OperadorAsignacion")){
                operador++;
                break;
            }else{
                simbolo += token.getValor()+" ";
            }
            operador++;
        }
        
        for(int i = operador; i < tokens.size(); i++){
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla(simbolo, tipo, valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    }

    private void asignacionDeArreglosDic(List<Token> tokens, DefaultTableModel model, String tipo){
        String simbolo = "";
        String valor = "";
        int operador = 0;
        
        for(Token token: tokens){
            if(token.getValor().equals("=")){
                operador++;
                break;
            }else{
                simbolo += token.getValor()+" ";
            }
            operador++;
        }
        
        for(int i = operador+1; i < tokens.size()-1; i++){
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla(simbolo, tipo, valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    }
    
    private void estructuraIf_Elif_While(List<Token> tokens, DefaultTableModel model,String simbolo, String tipo){
        
        String valor = "";
        int operador = 0;
        
        
        
        for(int i = 1; i < tokens.size(); i++){
            if(tokens.get(i).getValor().equals(":")){
                break;
            }
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla(simbolo, tipo, valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    
    }
   
    private void estructuraFor(List<Token> tokens, DefaultTableModel model){
        String valor = "";
        int operador = 0;
        
        
        
        for(int i = 1; i < tokens.size(); i++){
            if(tokens.get(i).getValor().equals(":")){
                break;
            }
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla("for", "Ciclo for", valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    
    }
    
    private void operadorTernario(List<Token> tokens, DefaultTableModel model){
        String simbolo = tokens.get(0).getValor();
        String valor = "";
        
        
        for(int i = 2; i < tokens.size(); i++){
            if(tokens.get(i).getTipo().equals("Comentario")){
                break;
            }
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla(simbolo, "Operador Ternario", valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    }
    
    private void funcionPrint(List<Token> tokens, DefaultTableModel model){
        String simbolo = tokens.get(0).getValor();
        String valor = "";
        
        
        for(int i = 2; i < tokens.size(); i++){
            if(tokens.get(i).getValor().equals(")")){
                break;
            }
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla(simbolo, "print", valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    }
    
    private void funcionReturn(List<Token> tokens, DefaultTableModel model){
        String simbolo = tokens.get(0).getValor();
        String valor = "";
        
        
        for(int i = 1; i < tokens.size(); i++){
            if(tokens.get(i).getTipo().equals("Comentario")){
                break;
            }
            valor += tokens.get(i).getValor()+" ";
        }
        
        llenarTabla(simbolo, "return", valor, tokens.get(0).getLinea(), tokens.get(0).getColumna(), model);
    }
    
    private void llenarTabla(String simbolo, String tipo, String valor, int linea, int columna, DefaultTableModel model){
        model.addRow(new Object[]{
                   simbolo,
                   tipo,
                   valor,
                   linea,
                   columna
                });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scrollReportes = new javax.swing.JScrollPane();
        tablaReportes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reportes Sintacticos");

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reportes Sintacticos");

        tablaReportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Simbolo", "Tipo", "Valor", "Linea", "Columna"
            }
        ));
        scrollReportes.setViewportView(tablaReportes);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tabla de Simbolos Global");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollReportes, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scrollReportes;
    private javax.swing.JTable tablaReportes;
    // End of variables declaration//GEN-END:variables
}
