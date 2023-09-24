package archivos;

import java.awt.Graphics;
import arbolBinario.ArbolBB;
import archivos.Lienzo;

/**
 * @author Daniel Capa
 * @author Josue Barba
 * @author Elkin Cuenca
 * @author Michael Carreño
 */
public class Nuevo {

    /**
     * Sirve para crear un nuevo frame
     */
    public static void NuevoArchivo(ArbolBB<Integer> aux, Lienzo objLienzo) {
        aux.setRaiz(null);
        Graphics g = objLienzo.getGraphics();
        g.clearRect(0, 0, objLienzo.getWidth(), objLienzo.getHeight());
    }
}
