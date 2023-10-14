/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.rudyreyes.analizadorlexico.vista;

import com.rudyreyes.analizadorlexico.controlador.analizador.lexico.AnalizadorLexico;
import com.rudyreyes.analizadorlexico.controlador.analizador.sintactico.AnalizadorSintactico;
import com.rudyreyes.analizadorlexico.modelo.estructuraSintactica.EstructuraSintactica;
import com.rudyreyes.analizadorlexico.modelo.token.Token;
import com.rudyreyes.analizadorlexico.vista.util.NumeroDeLinea;
import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author rudyo
 */
public class VentanaAnalizador extends javax.swing.JFrame {

    /**
     * Creates new form VentanaAnalizador
     */
    private NumeroDeLinea numeroLinea;
    private List<Token> tokens;
    private List<EstructuraSintactica> sintaxis;

    public VentanaAnalizador() {
        initComponents();

        numeroLinea = new NumeroDeLinea(codigoTextPane);
        
        scrollTextPane.setRowHeaderView(numeroLinea);
        
        this.setLocationRelativeTo(null);
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
        subirArchivo = new javax.swing.JButton();
        generarGrafico = new javax.swing.JButton();
        ejecutarCodigo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tokenArea = new javax.swing.JTextArea();
        reportesBoton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        scrollTextPane = new javax.swing.JScrollPane();
        codigoTextPane = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        erroresSintacticos = new javax.swing.JTextArea();
        reportesSintacticos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Analilzador Lexico");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        subirArchivo.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        subirArchivo.setText("Archivo");
        subirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subirArchivoActionPerformed(evt);
            }
        });

        generarGrafico.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        generarGrafico.setText("Generar Grafico");
        generarGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarGraficoActionPerformed(evt);
            }
        });

        ejecutarCodigo.setBackground(new java.awt.Color(102, 255, 102));
        ejecutarCodigo.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        ejecutarCodigo.setText("Play");
        ejecutarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarCodigoActionPerformed(evt);
            }
        });

        tokenArea.setColumns(20);
        tokenArea.setRows(5);
        jScrollPane1.setViewportView(tokenArea);

        reportesBoton.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        reportesBoton.setText("Reportes Lexicos");
        reportesBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesBotonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel1.setText("Errores Lexicos");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Analizador Lexico");

        scrollTextPane.setViewportView(codigoTextPane);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel3.setText("Errores Sintacticos");

        erroresSintacticos.setColumns(20);
        erroresSintacticos.setRows(5);
        jScrollPane2.setViewportView(erroresSintacticos);

        reportesSintacticos.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        reportesSintacticos.setText("Reportes Sintacticos");
        reportesSintacticos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesSintacticosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(subirArchivo)
                        .addGap(48, 48, 48)
                        .addComponent(reportesBoton)
                        .addGap(36, 36, 36)
                        .addComponent(generarGrafico)
                        .addGap(39, 39, 39)
                        .addComponent(reportesSintacticos)
                        .addGap(0, 118, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(scrollTextPane)
                        .addGap(18, 18, 18)
                        .addComponent(ejecutarCodigo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))))
                .addGap(14, 14, 14))
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(subirArchivo)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(reportesBoton)
                        .addComponent(generarGrafico)
                        .addComponent(reportesSintacticos)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ejecutarCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollTextPane, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subirArchivoActionPerformed
        //Creamos el objeto JFileChooser
        JFileChooser fc = new JFileChooser();

//Creamos el filtro//Archivos de texto (*.txt, *.py)", "txt", "py"
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto (*.txt, *.py)", "txt", "py");

//Le indicamos el filtro
        fc.setFileFilter(filtro);
//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
        int seleccion = fc.showOpenDialog(this);

//Si el usuario, pincha en aceptar
        if (seleccion == JFileChooser.APPROVE_OPTION) {

            //Seleccionamos el fichero
            File fichero = fc.getSelectedFile();

            //Ecribe la ruta del fichero seleccionado en el campo de texto
            //textField.setText(fichero.getAbsolutePath());
            try (FileReader fr = new FileReader(fichero)) {
                String cadena = "";
                int valor = fr.read();
                while (valor != -1) {
                    cadena = cadena + (char) valor;
                    valor = fr.read();
                }
                codigoTextPane.setText(cadena);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }//GEN-LAST:event_subirArchivoActionPerformed

    private void ejecutarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarCodigoActionPerformed
        //String codigoFuente = codigoArea.getText();
        String codigoFuente = codigoTextPane.getText();
        codigoFuente = codigoFuente + "\n";
        AnalizadorLexico analisis = new AnalizadorLexico();
        tokens =analisis.analizador(codigoFuente);
        
        if(tokens != null && !tokens.isEmpty() ){
            sintaxis = AnalizadorSintactico.analizarSintaxis(tokens);
        }
        
        
        resaltarTextoCodigo();
        
        tokenArea.setText("");
        erroresSintacticos.setText("");
        for (Token token : tokens) {
            if(token.getTipo().equals("Error")){
                tokenArea.append(token.obtenerToken()+ "\n");
            }
        }
        for(EstructuraSintactica estructura: sintaxis){
            if(!estructura.isEstructuraValida()){
                erroresSintacticos.append(estructura.getError()+ "\n");
            }
        }
        
    }//GEN-LAST:event_ejecutarCodigoActionPerformed

    
    private void resaltarTextoCodigo(){
        StyledDocument doc = codigoTextPane.getStyledDocument();
        
        SimpleAttributeSet blackStyle = new SimpleAttributeSet();
        StyleConstants.setForeground(blackStyle, Color.BLACK);

        SimpleAttributeSet celesteStyle = new SimpleAttributeSet();
        StyleConstants.setForeground(celesteStyle, new Color(0, 191, 255));
        
        SimpleAttributeSet moradoStyle = new SimpleAttributeSet();
        StyleConstants.setForeground(moradoStyle, new Color(128, 0, 128));
        
        SimpleAttributeSet rojoStyle = new SimpleAttributeSet();
        StyleConstants.setForeground(rojoStyle, Color.red);
        
        SimpleAttributeSet comentarioStyle = new SimpleAttributeSet();
        StyleConstants.setForeground(comentarioStyle, Color.GRAY);
        
        SimpleAttributeSet otrosStyle = new SimpleAttributeSet();
        StyleConstants.setForeground(otrosStyle, Color.green);

        SimpleAttributeSet defaultStyle = new SimpleAttributeSet();
        StyleConstants.setForeground(defaultStyle, Color.BLACK);
        
        for (int i = 0; i < doc.getDefaultRootElement().getElementCount(); i++) {
            Element elemento = doc.getDefaultRootElement().getElement(i);
            int inicioT = elemento.getStartOffset();
            int finalT = elemento.getEndOffset();
            String texto = null;

            try {
                texto = doc.getText(inicioT, finalT - inicioT);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            
            if(texto != null  && !texto.trim().isEmpty()){
                //OBTENER TOKENS DE LA LINEA EN LA QUE ESTAMOS
                List<Token> lexemas = obteniendoLexemas(i+1);
                
                if(lexemas!= null){
                    int tokenInicio = 0;
                    int tokenFinal = 0;
                    for(Token token: lexemas){
                        String lexema = token.getValor();
                        String tipo = token.getTipo();
                        
                        
                        if (texto.startsWith("#") && token.getTipo().equals("Comentario")) {
                        
                            tokenInicio = 0;
                            tokenFinal = tokenInicio + lexema.length();
                            
                        }else if(!texto.startsWith("#") && token.getTipo().equals("Comentario")){
                            
                            tokenInicio = tokenFinal+1;
                            tokenFinal = tokenInicio + lexema.length();
                        }else{
                            tokenInicio = texto.indexOf(lexema, tokenFinal);
                            tokenFinal = tokenInicio + lexema.length();
                        }
                        
                        if (tokenInicio >= 0) {
                            AttributeSet style = null;

                            if (tipo.equals("Identificador")) {
                                style = blackStyle;
                            } else if (tipo.equals("OperadorAsignacion") || tipo.equals("OperadorLogico")
                                    || tipo.equals("OperadorComparacion") || tipo.equals("OperadorAritmetico")) {
                                style = celesteStyle;
                            } else if (tipo.equals("PalabraClave")) {
                                style = moradoStyle;

                            } else if (tipo.equals("Constante")) {
                                style = rojoStyle;

                            } else if (tipo.equals("Comentario")) {
                                style = comentarioStyle;

                            } else if (tipo.equals("Otros")) {
                                style = otrosStyle;
                            } else {
                                style = blackStyle;
                            }

                            doc.setCharacterAttributes(inicioT + tokenInicio, tokenFinal - tokenInicio, style, true);
                        }
                        
                    }
                }
            }
            
            
        }
    }
    
    private List<Token> obteniendoLexemas(int fila){
        List<Token>  tokensLexema = new ArrayList<>();
        
        for(Token token: tokens){
            if(token.getLinea() == fila){
                tokensLexema.add(token);
            }
        }
        
        return tokensLexema;
    }
    
    
    
    
    private void generarGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarGraficoActionPerformed
       
        if(tokens != null && !tokens.isEmpty() ){
            VentanaGraficos ventanaGraficos = new VentanaGraficos(this, true, tokens);
            ventanaGraficos.setLocationRelativeTo(this);
            ventanaGraficos.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Aun no se han generado tokens");
        }
    }//GEN-LAST:event_generarGraficoActionPerformed

    private void reportesBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportesBotonActionPerformed
        if(tokens != null && !tokens.isEmpty() ){
            VentanaReportes ventanaReportes = new VentanaReportes(this, true, tokens);
            ventanaReportes.setLocationRelativeTo(this);
            ventanaReportes.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Aun no se han generado tokens");
        }
    }//GEN-LAST:event_reportesBotonActionPerformed

    private void reportesSintacticosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportesSintacticosActionPerformed
        if(sintaxis != null && !sintaxis.isEmpty() ){
            VentanaReportesSintactico ventanaReportesSintacticos = new VentanaReportesSintactico(sintaxis);
            ventanaReportesSintacticos.setLocationRelativeTo(null);
            ventanaReportesSintacticos.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
            ventanaReportesSintacticos.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Aun no se han generado tokens");
        }
    }//GEN-LAST:event_reportesSintacticosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaAnalizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAnalizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAnalizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAnalizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAnalizador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane codigoTextPane;
    private javax.swing.JButton ejecutarCodigo;
    private javax.swing.JTextArea erroresSintacticos;
    private javax.swing.JButton generarGrafico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton reportesBoton;
    private javax.swing.JButton reportesSintacticos;
    private javax.swing.JScrollPane scrollTextPane;
    private javax.swing.JButton subirArchivo;
    private javax.swing.JTextArea tokenArea;
    // End of variables declaration//GEN-END:variables
}
