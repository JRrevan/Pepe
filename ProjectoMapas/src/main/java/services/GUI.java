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

        // Encabezado superior izquierdo vacío
        mapaPanel.add(new JLabel(""));

        // Encabezados de columna
        for (int col = 0; col < columnas; col++) {
            String etiqueta = generarEtiqueta(col, variables - variables / 2, nombresVariables, variables / 2);
            mapaPanel.add(new JLabel(etiqueta, SwingConstants.CENTER));
        }

        // Filas
        for (int fila = 0; fila < filas; fila++) {
            String etiqueta = generarEtiqueta(fila, variables / 2, nombresVariables, 0);
            mapaPanel.add(new JLabel(etiqueta, SwingConstants.CENTER));

            for (int col = 0; col < columnas; col++) {
                int index = fila * columnas + col;
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

    private String generarEtiqueta(int valor, int numVariables, List<String> nombresVariables, int offset) {
        StringBuilder etiqueta = new StringBuilder();
        for (int i = 0; i < numVariables; i++) {
            etiqueta.append(((valor & (1 << (numVariables - i - 1))) != 0)
                    ? nombresVariables.get(offset + i)
                    : nombresVariables.get(offset + i) + "'");
        }
        return etiqueta.toString();
    }
}
