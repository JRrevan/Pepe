package services;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class GUI {

    private JPanel mapaPanel;
    private JTextArea operacionesArea;

    public void pantallaPrincipal() {
        JFrame frame = new JFrame("Mapas de Karnaugh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 750);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JTextField rutaText = new JTextField("Arrastra, coloca o busca el archivo XML");
        rutaText.setBounds(10, 20, 600, 30);
        rutaText.setForeground(Color.GRAY);
        panel.add(rutaText);

        JButton cargarButton = new JButton("Buscar");
        cargarButton.setBounds(650, 20, 150, 30);
        panel.add(cargarButton);

        mapaPanel = new JPanel();
        mapaPanel.setBounds(10, 70, 800, 400);
        panel.add(mapaPanel);

        operacionesArea = new JTextArea();
        operacionesArea.setEditable(false);
        operacionesArea.setBorder(BorderFactory.createTitledBorder("Operaciones Booleanas"));
        JScrollPane scroll = new JScrollPane(operacionesArea);
        scroll.setBounds(10, 480, 800, 200);
        panel.add(scroll);

        cargarButton.addActionListener(e -> {
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
        });

        frame.setVisible(true);
    }

    private void dibujarMapaKarnaugh(int variables, List<Integer> valores, List<String> nombresVariables) {
        mapaPanel.removeAll();

        int filas = (int) Math.pow(2, variables / 2);
        int columnas = (int) Math.pow(2, variables - variables / 2);
        mapaPanel.setLayout(new GridLayout(filas + 1, columnas + 1)); // +1 para encabezados

        // Celda superior izquierda vacía
        JLabel vacia = new JLabel("");
        vacia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mapaPanel.add(vacia);

        // Encabezados de columna en código Gray
        for (int col = 0; col < columnas; col++) {
            int gray = col ^ (col >> 1);
            String etiqueta = String.format("%" + (variables - variables / 2) + "s", Integer.toBinaryString(gray)).replace(' ', '0');
            JLabel encabezado = new JLabel(etiqueta, SwingConstants.CENTER);
            encabezado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            mapaPanel.add(encabezado);
        }

        // Filas
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



    private String generarEtiqueta(int valor, int numBits) {
        String bin = Integer.toBinaryString(valor);
        while (bin.length() < numBits) {
            bin = "0" + bin;
        }
        return bin;
    }
}
