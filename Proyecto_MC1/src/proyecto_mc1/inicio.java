package proyecto_mc1;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class inicio extends javax.swing.JFrame {

 
    public inicio() {
        initComponents();
        this.setLocationRelativeTo(null);
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/imagen/fondo.jpg"));
        Image imgEscalada = imgIcon.getImage().getScaledInstance(jLabel1.getWidth(),
        jLabel1.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
        jLabel1.setIcon(iconoEscalado); 
        
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        rutaText = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        cargarButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        crearButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        opButton = new javax.swing.JButton();
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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 340, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mapa de Karnaugh");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, -1, -1));

        cargarButton.setBackground(new java.awt.Color(153, 153, 255));
        cargarButton.setText("CARGAR ARCHIVO");
        cargarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cargarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 350, 270));

        crearButton.setText("CREAR MAPA DE KARNAUGH");
        crearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearButtonActionPerformed(evt);
            }
        });
        getContentPane().add(crearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 430, -1, -1));

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 280, 340));

        opButton.setText("OPERACIONES BOOLEANAS");
        getContentPane().add(opButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, -1, -1));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarButtonActionPerformed
               
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("XML files", "xml"));
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            rutaText.setText(selectedFile.getAbsolutePath());
        }
        
    }//GEN-LAST:event_cargarButtonActionPerformed

    private void crearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_crearButtonActionPerformed

    private void rutaTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rutaTextFocusLost
        if (rutaText.getText().isEmpty()) {
                    rutaText.setText("Arrastra, coloca o busca el archivo CSV");
                    //rutaText.setForeground(Color.GRAY);
                }
    }//GEN-LAST:event_rutaTextFocusLost

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
    private javax.swing.JButton crearButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JButton opButton;
    private javax.swing.JTextArea rutaText;
    // End of variables declaration//GEN-END:variables

    private void mostrarContenidoXML(File archivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object rutaText() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
}
