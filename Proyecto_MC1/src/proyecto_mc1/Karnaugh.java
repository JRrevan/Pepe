package proyecto_mc1;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Karnaugh {
    
    public static class MapaKarnaugh {
        public int variables;
        public List<Integer> valores;
        public List<String> nombresVariables;

        public MapaKarnaugh(int variables, List<Integer> valores, List<String> nombresVariables) {
            this.variables = variables;
            this.valores = valores;
            this.nombresVariables = nombresVariables;
        }
    }
    
    public MapaKarnaugh leerXML(String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.getDocumentElement().normalize();

            Element mapa = (Element) doc.getElementsByTagName("mapa").item(0);
            int variables = Integer.parseInt(mapa.getAttribute("v"));

            List<String> nombresVariables = new ArrayList<>();
            for (int i = 1; i <= variables; i++) {
                String nombre = mapa.getAttribute("val" + i);
                nombresVariables.add(nombre);
            }

            NodeList valores = mapa.getElementsByTagName("valor");
            List<Integer> listaValores = new ArrayList<>();
            for (int i = 0; i < valores.getLength(); i++) {
                Element valor = (Element) valores.item(i);
                listaValores.add(Integer.parseInt(valor.getTextContent()));
            }
            
            return new MapaKarnaugh(variables, listaValores, nombresVariables);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
