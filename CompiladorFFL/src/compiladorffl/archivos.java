package compiladorffl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilizada para administrar archivos
 *
 * @author fredy Gamer
 */
public class archivos {

    /**
     * Metodo utilizado para cargar datos de un archivo
     * @param xArchivo Ubicación en disco del archivo txt
     * @return array con lineas del archivo
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public List<String> leerArchivo(String xArchivo) throws FileNotFoundException, IOException {
        List<String> lstArchivos;
        try (FileReader fr = new FileReader(xArchivo)) {
            BufferedReader br = new BufferedReader(fr);
            lstArchivos = new ArrayList<String>() {};
            String s;
            while (null != (s = br.readLine())) {
                lstArchivos.add(s);
            }
        }
        return lstArchivos;
    }

    
    public boolean escribirArchivo(){
        return false;
    }
    
}
