package services;

import java.util.*;

public class Bool {

    public String simplificarFuncion(int variables, List<Integer> valores, List<String> nombresVariables) {
        List<String> minterminos = new ArrayList<>();

        // Recolectar los índices de los 1s (minterminos)
        for (int i = 0; i < valores.size(); i++) {
            if (valores.get(i) == 1) {
                minterminos.add(toBinary(i, variables));
            }
        }

        // Aplicar algoritmo de Quine-McCluskey
        Set<String> implicantesPrimos = obtenerImplicantesPrimos(minterminos);
        List<String> expresiones = new ArrayList<>();

        for (String implicante : implicantesPrimos) {
            StringBuilder term = new StringBuilder();
            for (int i = 0; i < implicante.length(); i++) {
                char bit = implicante.charAt(i);
                if (bit == '1') {
                    term.append(nombresVariables.get(i));
                } else if (bit == '0') {
                    term.append(nombresVariables.get(i)).append("'");
                } // si es '-', se omite
            }
            expresiones.add(term.toString());
        }

        return String.join(" + ", expresiones);
    }

    private String toBinary(int num, int bits) {
        String bin = Integer.toBinaryString(num);
        while (bin.length() < bits) bin = "0" + bin;
        return bin;
    }

    private Set<String> obtenerImplicantesPrimos(List<String> minterms) {
        List<Set<String>> grupos = agruparPorUnos(minterms);
        Set<String> usados = new HashSet<>();
        Set<String> siguienteNivel = new HashSet<>();
        boolean puedeCombinar;

        do {
            puedeCombinar = false;
            List<Set<String>> nuevosGrupos = new ArrayList<>();

            for (int i = 0; i < grupos.size() - 1; i++) {
                Set<String> grupoA = grupos.get(i);
                Set<String> grupoB = grupos.get(i + 1);
                Set<String> combinado = new HashSet<>();

                for (String a : grupoA) {
                    for (String b : grupoB) {
                        String combinadoBin = combinar(a, b);
                        if (combinadoBin != null) {
                            puedeCombinar = true;
                            usado(usados, a, b);
                            combinado.add(combinadoBin);
                        }
                    }
                }
                nuevosGrupos.add(combinado);
            }

            // Agregar todos los elementos no usados de esta ronda a siguienteNivel
            for (Set<String> grupo : grupos) {
                for (String s : grupo) {
                    if (!usados.contains(s)) {
                        siguienteNivel.add(s);
                    }
                }
            }

            grupos = nuevosGrupos;

        } while (puedeCombinar);

        return siguienteNivel;
    }

    private void usado(Set<String> usados, String a, String b) {
        usados.add(a);
        usados.add(b);
    }

    private List<Set<String>> agruparPorUnos(List<String> minterms) {
        int maxBits = minterms.get(0).length();
        List<Set<String>> grupos = new ArrayList<>();
        for (int i = 0; i <= maxBits; i++) grupos.add(new HashSet<>());

        for (String m : minterms) {
            int count = contarUnos(m);
            grupos.get(count).add(m);
        }

        return grupos;
    }

    private int contarUnos(String bin) {
        int count = 0;
        for (char c : bin.toCharArray()) if (c == '1') count++;
        return count;
    }

    private String combinar(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int diferencias = 0;

        for (int i = 0; i < a.length(); i++) {
            char ca = a.charAt(i);
            char cb = b.charAt(i);
            if (ca == cb) {
                sb.append(ca);
            } else {
                sb.append('-');
                diferencias++;
            }
            if (diferencias > 1) return null;
        }

        return diferencias == 1 ? sb.toString() : null;
    }

    public String calcularFuncionBooleana(int numeroVariables, List<Integer> valores, List<String> nombresVariables) {
        StringBuilder funcion = new StringBuilder();
        int totalCeldas = valores.size();

        for (int i = 0; i < totalCeldas; i++) {
            if (valores.get(i) == 1) {
                StringBuilder termino = new StringBuilder();

                String binario = String.format("%" + numeroVariables + "s", Integer.toBinaryString(i)).replace(' ', '0');

                for (int j = 0; j < numeroVariables; j++) {
                    String nombreVar = nombresVariables.get(j);
                    if (binario.charAt(j) == '0') {
                        termino.append(nombreVar).append("'");
                    } else {
                        termino.append(nombreVar);
                    }
                }

                if (funcion.length() > 0) {
                    funcion.append(" + ");
                }
                funcion.append(termino);
            }
        }

        if (funcion.length() == 0) {
            return "0"; // función siempre falsa
        }

        return funcion.toString();
    }

    public int calcularCompuertas(String funcionBooleana) {
        int ands = 0;
        int nots = 0;

        String[] terminos = funcionBooleana.split("\\+");

        for (String termino : terminos) {
            termino = termino.trim();
            if (termino.isEmpty()) continue;

            int varCount = 0;
            for (int i = 0; i < termino.length(); i++) {
                char c = termino.charAt(i);
                if (Character.isLetter(c)) {
                    varCount++;
                } else if (c == '\'') {
                    nots++;
                }
            }

            if (varCount > 1) {
                ands++;
            }
        }

        int ors = (terminos.length > 1) ? terminos.length - 1 : 0;

        return ands + nots + ors;
    }



}
