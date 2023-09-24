package archivos;

import arbolBinario.Arbol;
import java.io.File;
import javax.swing.JFileChooser;

/**
 * @author Daniel Capa
 * @author Josue Barba
 * @author Elkin Cuenca
 * @author Michael Carreño
 */

public class Guardar {

    /**
     * Metodo que sirve para guardar un arbol en un archivo ".dat"
     * @param arbol
     * @param nombreArchivo
     * @return retorna un true en caso de que el archivo se guarde con exito, sino retornara false
     * 
     */
    private static boolean guardarLista(Arbol<Integer> arbol, String nombreArchivo) {
        boolean resul = false;
        Escritura<Arbol> salida = new archivos.Escritura<>(nombreArchivo);
        if (arbol != null) {
            try {
                salida.abrir();
                salida.escribir(arbol);
                salida.cerrar();
                resul = true;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return resul;
    }

    /**
     * @param arbol 
     * El metodo guarda el arbol que recibe en un archivo ".dat"
     * El usuario podra escoger el nombre que tendra el archivo que contiene el arbol
     */
    public static void guardarArchivo(Arbol arbol ) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String ruta = selectedFile.getAbsolutePath();
            if (!ruta.toLowerCase().endsWith(".dat")) {
                ruta += ".dat";
            }
            guardarLista(arbol, ruta);
        }

    }

}
