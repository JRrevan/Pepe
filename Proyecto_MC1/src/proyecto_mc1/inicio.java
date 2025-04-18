package proyecto_mc1;

import java.awt.Image;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.List;
import java.awt.*;
import javax.swing.*;




public class inicio extends javax.swing.JFrame {
    
   

 
    public inicio() {
        
        
        initComponents();
        this.setLocationRelativeTo(null);
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/imagen/fondo.jpg"));
        Image imgEscalada = imgIcon.getImage().getScaledInstance(jLabel1.getWidth(),
        jLabel1.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
        jLabel1.setIcon(iconoEscalado); 
        
        new java.awt.dnd.DropTarget(rutaText, new java.awt.dnd.DropTargetListener() {
            @Override
            public void dragEnter(java.awt.dnd.DropTargetDragEvent dtde) {}

            @Override
            public void dragOver(java.awt.dnd.DropTargetDragEvent dtde) {}

            @Override
            public void dropActionChanged(java.awt.dnd.DropTargetDragEvent dtde) {}

            @Override
            public void dragExit(java.awt.dnd.DropTargetEvent dte) {}

            @Override
            public void drop(java.awt.dnd.DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(java.awt.dnd.DnDConstants.ACTION_COPY);
                    java.util.List<File> droppedFiles = (java.util.List<File>)
                            dtde.getTransferable().getTransferData(java.awt.datatransfer.DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        if (file.getName().toLowerCase().endsWith(".xml")) {
                            rutaText.setText(file.getAbsolutePath());

                            Karnaugh processor = new Karnaugh();
                            Karnaugh.MapaKarnaugh mapa = processor.leerXML(file.getAbsolutePath());

                            if (mapa != null) {
                                Bool solver = new Bool();
                                String canonica = solver.calcularFuncionBooleana(mapa.variables, mapa.valores, mapa.nombresVariables);
                                String simplificada = solver.simplificarFuncion(mapa.variables, mapa.valores, mapa.nombresVariables);

                                int compuertas = solver.calcularCompuertas(simplificada);

                                operacionesArea.setText("Función Booleana Canónica:\n" + canonica +
                                        "\n\nFunción Booleana Simplificada:\n" + simplificada +
                                        "\n\nCompuertas necesarias: " + compuertas);

                                dibujarMapaKarnaugh(mapa.variables, mapa.valores, mapa.nombresVariables);
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al procesar el archivo XML.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Por favor arrastra solo archivos XML.");
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al soltar archivo.");
                }
            }
        });

        
        
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        rutaText = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        cargarButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        operacionesArea = new javax.swing.JTextArea();
        mapaPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(394, 200));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rutaText.setColumns(20);
        rutaText.setRows(5);
        rutaText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                rutaTextFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(rutaText);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 390, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Operaciones Booleanas");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 590, 160, 30));

        cargarButton.setBackground(new java.awt.Color(153, 153, 255));
        cargarButton.setText("CARGAR ARCHIVO Y CREAR MAPA");
        cargarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cargarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, -1, -1));

        operacionesArea.setColumns(20);
        operacionesArea.setRows(5);
        jScrollPane4.setViewportView(operacionesArea);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 680, 140));

        mapaPanel.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(mapaPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 680, 440));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mapa de Karnaugh");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, 130, 40));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 780));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rutaTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rutaTextFocusLost
        if (rutaText.getText().isEmpty()) {
                    rutaText.setText("Arrastra, coloca o busca el archivo XML");
                    rutaText.setForeground(Color.GRAY);
                }
    }//GEN-LAST:event_rutaTextFocusLost

       
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (rutaText.getText().equals("Arrastra, coloca o busca el archivo CSV")) {
                    rutaText.setText("");
                    rutaText.setForeground(Color.BLACK); // Cambiar el color al texto normal
                }
            }
   
            
    
    private void cargarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarButtonActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            rutaText.setText(selectedFile.getAbsolutePath());
            
            Karnaugh processor = new Karnaugh();
            Karnaugh.MapaKarnaugh mapa = processor.leerXML(selectedFile.getAbsolutePath());
            
            if (mapa != null) {
                   
                Bool solver = new Bool();
                String canonica = solver.calcularFuncionBooleana(mapa.variables, mapa.valores, mapa.nombresVariables);
                String simplificada = solver.simplificarFuncion(mapa.variables, mapa.valores, mapa.nombresVariables);
                
                int compuertas = solver.calcularCompuertas(simplificada);

                operacionesArea.setText("Función Booleana Canónica:\n" + canonica +
                    "\n\nFunción Booleana Simplificada:\n" + simplificada +
                    "\n\nCompuertas necesarias: " + compuertas);

                dibujarMapaKarnaugh(mapa.variables, mapa.valores, mapa.nombresVariables);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al procesar el archivo XML.");
                }
        }
        
    }//GEN-LAST:event_cargarButtonActionPerformed

    private void dibujarMapaKarnaugh(int variables, List<Integer> valores, List<String> nombresVariables) {
        mapaPanel.removeAll();

        int filas = (int) Math.pow(2, variables / 2);
        int columnas = (int) Math.pow(2, variables - variables / 2);
        mapaPanel.setLayout(new GridLayout(filas + 1, columnas + 1)); // +1 para encabezados

        JLabel vacia = new JLabel("");
        vacia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mapaPanel.add(vacia);

        for (int col = 0; col < columnas; col++) {
            int gray = col ^ (col >> 1);
            String etiqueta = String.format("%" + (variables - variables / 2) + "s", Integer.toBinaryString(gray)).replace(' ', '0');
            JLabel encabezado = new JLabel(etiqueta, SwingConstants.CENTER);
            encabezado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            mapaPanel.add(encabezado);
        }

  
        for (int fila = 0; fila < filas; fila++) {
            int gray = fila ^ (fila >> 1);
            String etiqueta = String.format("%" + (variables / 2) + "s", Integer.toBinaryString(gray)).replace(' ', '0');
            JLabel encabezadoFila = new JLabel(etiqueta, SwingConstants.CENTER);
            encabezadoFila.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            mapaPanel.add(encabezadoFila);

            for (int col = 0; col < columnas; col++) {
                int grayCol = col ^ (col >> 1);
                int grayFila = fila ^ (fila >> 1);

                int index = grayFila * columnas + grayCol;

                JLabel celda = new JLabel(String.valueOf(valores.get(index)), SwingConstants.CENTER);
                celda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                celda.setOpaque(true);
                celda.setBackground(Color.WHITE);
                mapaPanel.add(celda);
            }
        }

        mapaPanel.revalidate();
        mapaPanel.repaint();
    }
    
    
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
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cargarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel mapaPanel;
    private javax.swing.JTextArea operacionesArea;
    private javax.swing.JTextArea rutaText;
    // End of variables declaration//GEN-END:variables

    private void mostrarContenidoXML(File archivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object rutaText() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
}
