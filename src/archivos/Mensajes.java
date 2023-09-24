package archivos;

import arbolBinario.ArbolBB;
import java.awt.Dimension;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Daniel Capa
 * @author Josue Barba
 * @author Elkin Cuenca
 * @author Michael Carreño
 */
public class Mensajes {

    /**
     * Metodo que muestra los recorridos PREORDER, INORDER, POSTORDER, EN
     * AMPLITUD, NIVELES
     *
     * @param arbol
     * @param textArea
     */
    public static void Recorridos(ArbolBB<Integer> arbol, JTextArea textArea) {

        StringBuilder mensaje = new StringBuilder();
        mensaje.append("\tRECORRIDOS");
        mensaje.append("\n\nPre-Orden: ");
        //() -> ARBOL.PREORDER()  ...... ES UNA FUNCION LAMBDA 

        mensaje.append(tipoRecorridos(() -> arbol.preOrder()));
        mensaje.append("\n\nIn-Orden: ");
        mensaje.append(tipoRecorridos(() -> arbol.inOrder()));
        mensaje.append("\n\nPost-Orden: ");
        mensaje.append(tipoRecorridos(() -> arbol.postOrder()));
        mensaje.append("\n\nAmplitud: ");
        mensaje.append(tipoRecorridos(() -> arbol.listarAmplitud()));
        mensaje.append("\n\nNiveles:\n");
        mensaje.append(tipoRecorridos(() -> arbol.listarAmplitudNiveles()));

        textArea.setText(mensaje.toString());
    }

    /**
     * Metodo que muestra los nodos, altura, dato mayor y dato menor del arbol
     *
     * @param arbol
     * @param textArea
     */
    public static void estadoArbol(ArbolBB<Integer> arbol, JTextArea textArea) {
        StringBuffer mensaje = new StringBuffer();

        mensaje.append("\tEstado del Árbol");

        mensaje.append("\nNodos: ");
        mensaje.append(arbol.numeroDeNodos());
        mensaje.append("\nAltura: ");
        mensaje.append(arbol.altura());
        mensaje.append("\nDato Mayor: ");
        mensaje.append(arbol.mayor());
        mensaje.append("\nDato Menor: ");
        mensaje.append(arbol.menor());

        textArea.setText(mensaje.toString());

    }

    /**
     * Metodo que se utiliza para capturar la salida de un proceso que se
     * ejecuta como una interfaz Runnable
     *
     * @param ingreso
     * @return devuelve una salida como una cadena de caracteres
     */
    public static String tipoRecorridos(Runnable ingreso) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        ingreso.run();

        // Restaura la salida estándar
        System.out.flush();
        System.setOut(old);

        // Captura el contenido de la salida en forma de cadena
        return baos.toString();
    }
}
