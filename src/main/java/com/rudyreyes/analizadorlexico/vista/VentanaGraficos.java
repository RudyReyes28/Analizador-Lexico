/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.rudyreyes.analizadorlexico.vista;

import com.rudyreyes.analizadorlexico.token.Token;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author rudyo
 */
public class VentanaGraficos extends javax.swing.JDialog {

    /**
     * Creates new form VentanaGraficos
     */
    
    private List<Token> tokens;
    private DefaultMutableTreeNode nodoRaiz;
    private DefaultTreeModel modelo;
    private Map<String, Set<String>> tokenLexemasMap = new HashMap<>();
    
    public VentanaGraficos(java.awt.Frame parent, boolean modal, List<Token> tokens) {
        super(parent, modal);
        initComponents();
        
        this.tokens = tokens;
        
        nodoRaiz = new DefaultMutableTreeNode("Lexemas");
        modelo = new DefaultTreeModel(nodoRaiz);
        
        arbolToken.setModel(modelo);
        
        obteniendoLexemas();
        agregandoLexemasModeloArbol();
        
        
        
    }
    
    private void obteniendoLexemas() {
        for (Token token : tokens) {
            String tipoToken = token.getTipo();
            String lexema = token.getValor();

            tokenLexemasMap.putIfAbsent(tipoToken, new HashSet<>());
            tokenLexemasMap.get(tipoToken).add(lexema);
        }
    }
    
    private void agregandoLexemasModeloArbol() {
        for (String tipo : tokenLexemasMap.keySet()) {
            DefaultMutableTreeNode tipoNodo = new DefaultMutableTreeNode(tipo);
            nodoRaiz.add(tipoNodo);

            Set<String> lexemas = tokenLexemasMap.get(tipo);
            for (String lexema : lexemas) {
                DefaultMutableTreeNode lexemaNodo = new DefaultMutableTreeNode(lexema);
                lexemaNodo.setUserObject(lexema);
                tipoNodo.add(lexemaNodo);
            }
        }

        modelo.reload();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        arbolToken = new javax.swing.JTree();
        imagenGrafico = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        infoToken = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generar Grafico");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Graficos");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Identificadores");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Aritmeticos");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Comparacion");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Logicos");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Asignacion");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Palabras Clave");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Constantes");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Comentarios");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Otros");
        treeNode1.add(treeNode2);
        arbolToken.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        arbolToken.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                arbolTokenValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(arbolToken);

        jLabel1.setText("Graficos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(imagenGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(infoToken, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel1))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(infoToken, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(imagenGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void arbolTokenValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_arbolTokenValueChanged
       DefaultMutableTreeNode seleccionarNodo = (DefaultMutableTreeNode) arbolToken.getLastSelectedPathComponent();

        if (seleccionarNodo != null && seleccionarNodo.getUserObject() != null) {
            String lexema = seleccionarNodo.getUserObject().toString();

            MutableGraph g = guru.nidi.graphviz.model.Factory.mutGraph("stringToNode")
                    .setDirected(true)
                    .graphAttrs().add(Rank.dir(Rank.RankDir.LEFT_TO_RIGHT));

            MutableNode prevNode = null;
            MutableNode lastNode = null;
            for (char c : lexema.toCharArray()) {
                MutableNode node = guru.nidi.graphviz.model.Factory.mutNode(Character.toString(c))
                        .add(Label.of(Character.toString(c)));
                g.add(node);
                if (prevNode != null) {
                    prevNode.addLink(node);
                }
                prevNode = node;
                lastNode = node;
            }

            // Cambiar el shape del último nodo ("y") a círculo doble
            if (lastNode != null) {
                lastNode.add(Shape.DOUBLE_CIRCLE);
            }
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                Graphviz.fromGraph(g).height(60).scale(4.0).render(Format.PNG).toOutputStream(outputStream);

                byte[] imageBytes = outputStream.toByteArray();

                // Cargar la imagen en un objeto Image
                ImageIcon imageIcon = new ImageIcon(imageBytes);
                Image image = imageIcon.getImage();

                int labelWidth = imagenGrafico.getWidth();
                int labelHeight = imagenGrafico.getHeight();
                Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

                llenarInformacionToken(lexema);
                
                imagenGrafico.setIcon(scaledImageIcon);
                //ImageIcon imageIcon = new ImageIcon(imageBytes);

                //imagenGrafico.setIcon(imageIcon);
            } catch (IOException ex) {
                Logger.getLogger(VentanaAnalizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_arbolTokenValueChanged

    private void llenarInformacionToken(String lexema){
        String informacion = "<html>";
        for (Token token : tokens) {
            if (token.getValor().equals(lexema)) {
                informacion += "Tipo: " + token.getTipo() + "   Linea: " + token.getLinea() + "     Columna: " + token.getColumna() + "<br>";
            }
        }
        informacion += "</html>";

        infoToken.setText(informacion);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree arbolToken;
    private javax.swing.JLabel imagenGrafico;
    private javax.swing.JLabel infoToken;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
