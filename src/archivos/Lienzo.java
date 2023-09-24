package archivos;

import arbolBinario.Arbol;
import arbolBinario.ArbolBB;
import arbolBinario.NodoArbol;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Daniel Capa
 * @author Josue Barba
 * @author Elkin Cuenca
 * @author Michael Carreño
 */
public class Lienzo extends JPanel {

    private Arbol objArbol;
    private int nodoEncontrado;
    private Color colorNodoEncontrado = Color.GREEN;
    private static final int DIAMETRO = 35;
    private static final int RADIO = DIAMETRO / 2;
    private static final int ANCHO = 70;
    private static final int AUMENTOLIENZO = 0;

    public void setObjArbol(ArbolBB objArbolito) {
        this.objArbol = objArbolito;
        repaint();
    }

    /**
     * Metodo que dibuja un árbol binario de búsqueda utilizando Graphics2D
     *
     * @param g
     */
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        insertar(g2d, (int) (getWidth() / 2.3), 25, objArbol.getRaiz(), 0, 0, nodoEncontrado, colorNodoEncontrado);
    }

    /**
     * El nodo ingresado, se lo guarda
     *
     * @param nodoEncontrado
     */
    public void setNodoEncontrado(int nodoEncontrado) {
        this.nodoEncontrado = nodoEncontrado;
    }

    /**
     * El color ingresado se guarda
     *
     * @param colorNodoEncontrado
     */
    public void setColorNodoEncontrado(Color colorNodoEncontrado) {
        this.colorNodoEncontrado = colorNodoEncontrado;
    }

    /**
     * Este metodo dibuja nodos y conexiones en un árbol binario de búsqueda
     * usando Graphics 2D Antes de cualquier inserccion se verificara que el
     * nodo enviado no este vacio Este metodo se encarga de dibujar lineas,
     * circulos y agregarle color a los mismos Se ingresa el nodo a buscar, si
     * lo encuentra cambia de color con ayuda del parametro color.
     *
     * @param g
     * @param x
     * @param y
     * @param n
     * @param nivel
     * @param aumento
     * @param nodoEncontrado
     * @param colorNodoEncontrado
     */
    public void insertar(Graphics g, int x, int y, NodoArbol<Integer> n, int nivel, int aumento, int nodoEncontrado, Color colorNodoEncontrado) {
        if (n == null)
        {

        } else
        {
            this.setPreferredSize(new Dimension(AUMENTOLIENZO + aumento + aumento, AUMENTOLIENZO + aumento + aumento));
            String valor = n.getClave().toString();
            FontMetrics fontMetrics = g.getFontMetrics();
            int anchoValor = fontMetrics.stringWidth(valor);

            // Calculamos la posición X para centrar el valor
            int posX = x + (DIAMETRO - anchoValor) / 2;

            int EXTRA = nodosCompletos(n) * (ANCHO / 2);
            g.setColor(Color.BLACK);
            g.drawOval(x, y, DIAMETRO, DIAMETRO + 1);

            if (n.getClave().equals(nodoEncontrado))
            {

                g.setColor(colorNodoEncontrado);
                Timer timer = new Timer(1500, (ActionEvent e) ->
                {
                });
                timer.setRepeats(false); // No se repite
                timer.start();
            } else
            {
                g.setColor(Color.GREEN);
            }

            g.fillOval(x + 1, y + 1, DIAMETRO - 1, DIAMETRO - 1);
            g.setColor(Color.BLACK);
            g.drawString(n.getClave().toString(), posX, y + 22);
            g.setColor(Color.GREEN);
            if (n.getIz() != null)
            {
                // Calcula la posición para el hijo izquierdo
                int hijoIzquierdoX = x - (EXTRA + 20);//Separacion horizontal
                int hijoIzquierdoY = y + ANCHO + 25; //Separacion vertical
                g.setColor(Color.BLACK);
                g.drawLine(x + RADIO - 8, y + DIAMETRO, hijoIzquierdoX + RADIO, hijoIzquierdoY);
                insertar(g, hijoIzquierdoX, hijoIzquierdoY, n.getIz(), nivel + 1, aumento + 250, nodoEncontrado, colorNodoEncontrado);
            }
            if (n.getDe() != null)
            {
                // Calcula la posición para el hijo derecho
                int hijoDerechoX = x + (EXTRA + 20); //Separacion horizontal
                int hijoDerechoY = y + ANCHO + 25; //Separacion vertical

                g.setColor(Color.BLACK);
                g.drawLine(x + RADIO + 8, y + DIAMETRO, hijoDerechoX + RADIO, hijoDerechoY);
                insertar(g, hijoDerechoX, hijoDerechoY, n.getDe(), nivel + 1, aumento + 250, nodoEncontrado, colorNodoEncontrado);
            }
            this.revalidate();
        }
        this.revalidate();
    }

    /**
     *
     * @param n
     * @return Retorna si
     */
    public int nodosCompletos(NodoArbol n) {
        if (n == null)
        {
            return 0;
        } else
        {
            if (n.getIz() != null && n.getDe() != null)
            {
                return nodosCompletos(n.getIz()) + nodosCompletos(n.getDe()) + 1;
            } else
            {
                return nodosCompletos(n.getIz()) + nodosCompletos(n.getDe());
            }

        }
    }

}
