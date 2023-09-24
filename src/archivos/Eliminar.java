
package archivos;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import javax.swing.JOptionPane;

/**
 * @author Daniel Capa
 * @author Josue Barba
 * @author Elkin Cuenca
 * @author Michael Carreño
 */
public class Eliminar {
    /**
     * Metodo que sirve para eliminar un archivo en caso de existir
     * Se abre un cuadro de archivos en donde se debe escoger el archivo a eliminar
     * Si el archivo existe se eliminara, sino se reportara su nula existencia
     */
 public static void eliminarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos DAT", "dat"));
        int option = fileChooser.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String rutaArchivo = selectedFile.getAbsolutePath();
            File archivo = new File(rutaArchivo);
            if (archivo.exists()) {
                if (archivo.delete()) {
                    JOptionPane.showMessageDialog(null, "Archivo eliminado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el archivo.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El archivo no existe en la ruta especificada.");
            }
        }
    }
}
