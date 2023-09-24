package archivos;

import arbolBinario.ArbolBB;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * @author Daniel Capa
 * @author Josue Barba
 * @author Elkin Cuenca
 * @author Michael Carreño
 */

public class Importar extends JFrame {

   
    /**
     * 
     * @param nombreArchivo
     * @return retorna un arbol
     * El metodo recibe el nombre del archivo que el usuario escoja y lee el contenido que se encuentra en el
     * Si el archivo existe va a obtener el arbol contenido en el archivo, sino se mostrara un mensaje no encontrado
     */
     private static ArbolBB<Integer> cargarArbol(String nombreArchivo) {
        ArbolBB<Integer> arbol = null;
        Lectura<ArbolBB> entrada = new Lectura<>(nombreArchivo);
        try {
            entrada.abrir();
            arbol = entrada.leer();
            entrada.cerrar();
           
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return arbol;
    }
/**
 * Este metodo abre una ventana en donde se mostraran los archivos que existen en la maquina, aqui el usuario podra escoger el archivo a abrir
 * 
 * @return retorna el arbol contenido en el archivo ".dat"
 */
    public static ArbolBB<Integer> abrirArchivo() {
       JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de datos (*.dat)", "dat");
        fileChooser.setFileFilter(filter);
        ArbolBB<Integer> arbol = new ArbolBB<>();
        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String ruta = selectedFile.getAbsolutePath();
            arbol = cargarArbol(ruta);
        }
        return arbol;
    }
    
    
    
}
