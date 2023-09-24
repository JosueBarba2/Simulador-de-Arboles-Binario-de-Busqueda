package Formulario;

import archivos.Importar;
import arbolBinario.ArbolBB;
import archivos.Controlador;
import archivos.Eliminar;
import static archivos.Guardar.guardarArchivo;
import archivos.Lienzo;
import archivos.Mensajes;
import java.awt.Desktop;
import java.awt.Color;
import PlaceHolders.TextPrompt;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Timer;
import archivos.Nuevo;

public final class frmInicio extends javax.swing.JFrame {

    public frmInicio() {
        initComponents();
        placeholders();
        setIconImage(icono_Arbol());
        setSize(1255, 553);
        setLocationRelativeTo(this);
        mostrarFechaYHora();
        objControlador.iniciar();
    }

    //Declaracion de variables globales
    Lienzo objLienzo = new Lienzo();
    boolean var1 = false, var2 = false, vent = false;
    ArbolBB<Integer> arbolito = new ArbolBB<>();
    Controlador objControlador = new Controlador(objLienzo, arbolito);
    ArbolBB<Integer> arbol2 = null;
    String men = "";
    ArbolBinario frmArbolito = new ArbolBinario();
    Atajos frmAtajos = new Atajos();

    /**
     * Metodo que carga una imagen desde el sistema de recursos.
     *
     * @return devuelve una imagen existente en un paquete de recursos
     */
    public Image icono_Arbol() {
        Image imagenArbol = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/Arbol2.png"));
        return imagenArbol;
    }

    /**
     * Metodo que sirve para para abri una pagina web en el navegador
     * predeterminado de la máquina.
     */
    public static void abrirEnlace() {
        String url = "https://barbajosue2004.wixsite.com/my-site";
        try
        {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e)
        {
        }
    }

    /**
     * Metodo que sirve para eliminar un nodo del arbol
     */
    public void Eliminar() {
        try
        {
            int num = Integer.parseInt(txtEliminar.getText());
            if (arbolito.buscar(num))
            {
                objLienzo.setNodoEncontrado(num);
                objLienzo.setColorNodoEncontrado(Color.BLUE);
                objLienzo.setObjArbol(arbolito);
                men = men + "Se ha eliminado con exito: " + num + "\n";
                jTextArea4.setText(men);
                txtEliminar.setEnabled(false);
            } else
            {
                men = men + "No encontrado, no se pudo eliminar: " + num + "\n";
                jTextArea4.setText(men);
            }
            Timer timer = new Timer(1500, (ActionEvent e) ->
            {
                txtEliminar.setEnabled(true);
                arbolito.eliminar(num);
                objLienzo.setColorNodoEncontrado(Color.GREEN);
                MostrarEnTextArea();
                objLienzo.setObjArbol(arbolito);
            });
            timer.setRepeats(false); // No se repite
            timer.start();
        } catch (NumberFormatException e)
        {
            men = men + "Solo se permite el ingreso de números.\n";
            jTextArea4.setText(men);
        }
        objLienzo.repaint();

        txtEliminar.setText("");

    }

    /**
     * Metodo que sirve para buscar un nodo en el arbol
     */
    public void Buscar() {
        try
        {
            int num = Integer.parseInt(txtBuscar.getText());
            boolean encontrado = arbolito.buscar(num);
            if (encontrado)
            {
                txtBuscar.setEnabled(false);
                jTextArea4.setText("Elemento Encontrado: " + num);
                objLienzo.setNodoEncontrado(num);
                objLienzo.setColorNodoEncontrado(Color.BLUE);
                objLienzo.setObjArbol(arbolito);
            } else
            {
                men = men + "Elemento no Encontrado: " + num + "\n";
                jTextArea4.setText(men);
            }
            //"Esto srive para que se muestre el mensaje durante un periodo de tiempo
            Timer timer;
            timer = new Timer(1500, (ActionEvent e) ->
            {
                txtBuscar.setEnabled(true);
                objLienzo.setColorNodoEncontrado(Color.GREEN);
                objLienzo.setObjArbol(arbolito);
            });
            timer.setRepeats(false); // No se repite
            timer.start();

        } catch (NumberFormatException e)
        {
            men = men + "Solo se permite el ingreso de números.\n";
            jTextArea4.setText(men);
        }
        txtBuscar.setText("");
    }

    /**
     * Metodo que sirve para insertar un nodo en el arbol
     */
    public void Insertar() {
        try
        {
            int num = Integer.parseInt(txtInsertar.getText());
            jScrollPane1.setViewportView(objLienzo);
            if (!arbolito.buscar(num))
            {
                arbolito.insertar(num);
                jScrollPane1.setWheelScrollingEnabled(true);
                jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
                jScrollPane1.setBorder(null);
                objLienzo.repaint();
                men = men + "Ingresado con Exito " + num + "\n";
                jTextArea4.setText(men);
            } else
            {
                men = men + "Esta clave ya ha sido ingresada: " + num + "\n";
                jTextArea4.setText(men);
            }
        } catch (NumberFormatException e)
        {
            men = men + "Solo se permite el ingreso de números\n";
            jTextArea4.setText(men);
        }
        MostrarEnTextArea();
        txtInsertar.setText("");
    }

    /**
     * Metodo que sirve para mostrar la fecha y hora actual de la maquina
     */
    public void mostrarFechaYHora() {
        Timer timer;
        timer = new Timer(1000, (ActionEvent e) ->
        {
            //Obtiene la hora actual 
            LocalTime hora = LocalTime.now();
            // Formatea la hora en un formato deseado
            DateTimeFormatter formatearHora = DateTimeFormatter.ofPattern("HH:mm:ss");
            String horaa = hora.format(formatearHora);
            //Obtiene la fecha actual
            LocalDate fecha = LocalDate.now();
            // Formatea la fecha en un formato deseado
            DateTimeFormatter formatearFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaa = fecha.format(formatearFecha);
            // Mostrar la fecha en un componente jLabel
            jLabel2.setText(fechaa);
            // Mostrar la hora en un componente jLabel
            jLabel3.setText(horaa);
        });
        timer.start();
    }

    /**
     * Metodo que sirve para abrir una pagina web desde un enlace
     */
    public static void abrirEnlaceSobreLaApp() {
        String url = "https://barbajosue2004.wixsite.com/my-site/about-6";
        try
        {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e)
        {
        }
    }

    /**
     * Metodo que sirve para controlar recorridos del arbol
     */
    public void MostrarEnTextArea() {
        if (var1)
        {
            Mensajes.estadoArbol(arbolito, jTextArea2);
        }
        if (var2)
        {
            Mensajes.Recorridos(arbolito, jTextArea1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        txtInsertar = new javax.swing.JTextField();
        BotonInsertar = new javax.swing.JButton();
        BotonBuscar = new javax.swing.JButton();
        BotonEliminar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        txtEliminar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        BotonEstadoArbol = new javax.swing.JButton();
        BotonRecorridos = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        nuevoArchivo = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        btEliminar = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        Guardar = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        btImportar = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        Salir = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        PantallaCompleta = new javax.swing.JCheckBoxMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Árbol Binario de Búsqueda\n");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 845;
        gridBagConstraints.ipady = 435;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 0);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);
        jTextArea1.getAccessibleContext().setAccessibleDescription("");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 350;
        gridBagConstraints.ipady = 171;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 13, 0, 0);
        getContentPane().add(jScrollPane2, gridBagConstraints);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 350;
        gridBagConstraints.ipady = 103;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 13, 0, 0);
        getContentPane().add(jScrollPane3, gridBagConstraints);

        jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea4.setEditable(false);
        jTextArea4.setColumns(20);
        jTextArea4.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jTextArea4.setRows(5);
        jScrollPane5.setViewportView(jTextArea4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 350;
        gridBagConstraints.ipady = 117;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 13, 1, 0);
        getContentPane().add(jScrollPane5, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(224, 239, 218));
        jPanel2.setMinimumSize(new java.awt.Dimension(2255, 50));
        jPanel2.setPreferredSize(new java.awt.Dimension(2255, 50));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtInsertar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtInsertarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInsertarKeyTyped(evt);
            }
        });
        jPanel2.add(txtInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 30));

        BotonInsertar.setBackground(new java.awt.Color(153, 153, 153));
        BotonInsertar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        BotonInsertar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mas.png"))); // NOI18N
        BotonInsertar.setText("  Insertar");
        BotonInsertar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        BotonInsertar.setBorderPainted(false);
        BotonInsertar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonInsertar.setIconTextGap(0);
        BotonInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotonInsertarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotonInsertarMouseExited(evt);
            }
        });
        BotonInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonInsertarActionPerformed(evt);
            }
        });
        jPanel2.add(BotonInsertar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 100, 30));

        BotonBuscar.setBackground(new java.awt.Color(153, 153, 153));
        BotonBuscar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        BotonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupa.png"))); // NOI18N
        BotonBuscar.setText("  Buscar");
        BotonBuscar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BotonBuscar.setBorderPainted(false);
        BotonBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonBuscar.setIconTextGap(0);
        BotonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotonBuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotonBuscarMouseExited(evt);
            }
        });
        BotonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(BotonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 100, 30));

        BotonEliminar.setBackground(new java.awt.Color(153, 153, 153));
        BotonEliminar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        BotonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/menos.png"))); // NOI18N
        BotonEliminar.setText("  Eliminar");
        BotonEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BotonEliminar.setBorderPainted(false);
        BotonEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonEliminar.setIconTextGap(0);
        BotonEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotonEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotonEliminarMouseExited(evt);
            }
        });
        BotonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(BotonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 100, 30));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
        });
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 90, 30));

        txtEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEliminarActionPerformed(evt);
            }
        });
        txtEliminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEliminarKeyPressed(evt);
            }
        });
        jPanel2.add(txtEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 90, 30));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 30, 80, 20));
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 30, 70, 20));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Calendario.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 0, -1, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reloj.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, 30, 30));

        BotonEstadoArbol.setBackground(new java.awt.Color(153, 153, 153));
        BotonEstadoArbol.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        BotonEstadoArbol.setText("Estado Del Árbol");
        BotonEstadoArbol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonEstadoArbol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotonEstadoArbolMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotonEstadoArbolMouseExited(evt);
            }
        });
        BotonEstadoArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEstadoArbolActionPerformed(evt);
            }
        });
        jPanel2.add(BotonEstadoArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 130, 30));

        BotonRecorridos.setBackground(new java.awt.Color(153, 153, 153));
        BotonRecorridos.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        BotonRecorridos.setText("Recorridos");
        BotonRecorridos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonRecorridos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotonRecorridosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotonRecorridosMouseExited(evt);
            }
        });
        BotonRecorridos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRecorridosActionPerformed(evt);
            }
        });
        jPanel2.add(BotonRecorridos, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 100, 30));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = -340;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel2, gridBagConstraints);

        nuevoArchivo.setBorder(null);
        nuevoArchivo.setText("Archivo");
        nuevoArchivo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nuevo.png"))); // NOI18N
        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        nuevoArchivo.add(jMenuItem1);
        nuevoArchivo.add(jSeparator5);

        btEliminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        btEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar.png"))); // NOI18N
        btEliminar.setText("Eliminar");
        btEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarActionPerformed(evt);
            }
        });
        nuevoArchivo.add(btEliminar);
        nuevoArchivo.add(jSeparator6);

        Guardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Guardar.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        nuevoArchivo.add(Guardar);
        nuevoArchivo.add(jSeparator7);

        btImportar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        btImportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Importar.png"))); // NOI18N
        btImportar.setText("Importar");
        btImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImportarActionPerformed(evt);
            }
        });
        nuevoArchivo.add(btImportar);
        nuevoArchivo.add(jSeparator8);

        Salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Salir.png"))); // NOI18N
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        nuevoArchivo.add(Salir);

        jMenuBar1.add(nuevoArchivo);

        jMenu3.setBorder(null);
        jMenu3.setText("Ventana");
        jMenu3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        PantallaCompleta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        PantallaCompleta.setText("Pantalla Completa");
        PantallaCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PantallaCompletaActionPerformed(evt);
            }
        });
        jMenu3.add(PantallaCompleta);

        jMenuBar1.add(jMenu3);

        jMenu4.setBorder(null);
        jMenu4.setText("Ayuda");
        jMenu4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenuItem8.setText("Árbol Binario de Búsqueda");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);
        jMenu4.add(jSeparator10);

        jMenuItem9.setText("Sobre la App");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);
        jMenu4.add(jSeparator9);

        jMenuItem10.setText("Atajos del programa");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);
        jMenu4.add(jSeparator11);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
        Eliminar.eliminarArchivo();
    }//GEN-LAST:event_btEliminarActionPerformed

    private void btImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImportarActionPerformed
        try
        {
            arbol2 = Importar.abrirArchivo();
            if (arbol2 != null)
            {
                objLienzo.setObjArbol(arbol2);
                jScrollPane1.setViewportView(objLienzo);
            } else
            {
                System.out.println("Formulario vacío");
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        arbolito = arbol2;
    }//GEN-LAST:event_btImportarActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SalirActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        jMenuItem8.setEnabled(false);
        frmArbolito.setVisible(true);
        frmArbolito.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                jMenuItem8.setEnabled(true);
            }
        });
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void BotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_BotonEliminarActionPerformed

    private void BotonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBuscarActionPerformed
        Buscar();
    }//GEN-LAST:event_BotonBuscarActionPerformed

    private void PantallaCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PantallaCompletaActionPerformed

        if (PantallaCompleta.isSelected())
        {

            this.setExtendedState(6);
        } else
        {
            setSize(1255, 555);
            setLocationRelativeTo(null);

        }

    }//GEN-LAST:event_PantallaCompletaActionPerformed

    private void BotonRecorridosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRecorridosActionPerformed
        Mensajes.Recorridos(arbolito, jTextArea1);
        var2 = true;
    }//GEN-LAST:event_BotonRecorridosActionPerformed
    private void BotonEstadoArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEstadoArbolActionPerformed
        Mensajes.estadoArbol(arbolito, jTextArea2);
        var1 = true;
    }//GEN-LAST:event_BotonEstadoArbolActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        guardarArchivo(arbolito);
    }//GEN-LAST:event_GuardarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Nuevo.NuevoArchivo(arbolito, objLienzo);
        men = men + "Se ha limpiado la mesa de trabajo" + "\n";
        jTextArea4.setText(men);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        abrirEnlaceSobreLaApp();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        jMenuItem10.setEnabled(false);
        frmAtajos.setVisible(true);
        frmAtajos.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                jMenuItem10.setEnabled(true);
            }
        });
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void txtInsertarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInsertarKeyTyped
        if (txtInsertar.getText().length() >= 4)
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtInsertarKeyTyped

    private void txtInsertarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInsertarKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            Insertar();
        }
    }//GEN-LAST:event_txtInsertarKeyPressed

    private void txtEliminarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEliminarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            Eliminar();
        }
    }//GEN-LAST:event_txtEliminarKeyPressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            Buscar();
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void BotonInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonInsertarActionPerformed
        Insertar();
        System.out.println(jTextArea1.getSize());
    }//GEN-LAST:event_BotonInsertarActionPerformed

    private void txtEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEliminarActionPerformed

    }//GEN-LAST:event_txtEliminarActionPerformed

    private void BotonInsertarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonInsertarMouseEntered
        BotonInsertar.setBackground(new Color(153, 255, 153));
    }//GEN-LAST:event_BotonInsertarMouseEntered

    private void BotonInsertarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonInsertarMouseExited
        BotonInsertar.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_BotonInsertarMouseExited

    private void BotonEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonEliminarMouseEntered
        BotonEliminar.setBackground(new Color(255, 153, 153));
    }//GEN-LAST:event_BotonEliminarMouseEntered

    private void BotonEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonEliminarMouseExited
        BotonEliminar.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_BotonEliminarMouseExited

    private void BotonBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonBuscarMouseEntered
        BotonBuscar.setBackground(new Color(153, 204, 255));
    }//GEN-LAST:event_BotonBuscarMouseEntered

    private void BotonBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonBuscarMouseExited
        BotonBuscar.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_BotonBuscarMouseExited

    private void BotonEstadoArbolMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonEstadoArbolMouseEntered
        BotonEstadoArbol.setBackground(new Color(255, 255, 153));
    }//GEN-LAST:event_BotonEstadoArbolMouseEntered

    private void BotonEstadoArbolMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonEstadoArbolMouseExited
        BotonEstadoArbol.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_BotonEstadoArbolMouseExited

    private void BotonRecorridosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonRecorridosMouseEntered
        BotonRecorridos.setBackground(new Color(255, 255, 153));
    }//GEN-LAST:event_BotonRecorridosMouseEntered

    private void BotonRecorridosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonRecorridosMouseExited
        BotonRecorridos.setBackground(new Color(153, 153, 153));
    }//GEN-LAST:event_BotonRecorridosMouseExited

    public void placeholders() {
        TextPrompt txtInsert = new TextPrompt("Ingrese clave", txtInsertar);
        TextPrompt txtDelete = new TextPrompt("Ingrese clave", txtEliminar);
        TextPrompt txtSearch = new TextPrompt("Ingrese clave", txtBuscar);
    }

    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonBuscar;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton BotonEstadoArbol;
    private javax.swing.JButton BotonInsertar;
    private javax.swing.JButton BotonRecorridos;
    private javax.swing.JMenuItem Guardar;
    private javax.swing.JCheckBoxMenuItem PantallaCompleta;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JMenuItem btEliminar;
    private javax.swing.JMenuItem btImportar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JMenu nuevoArchivo;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtEliminar;
    private javax.swing.JTextField txtInsertar;
    // End of variables declaration//GEN-END:variables

}
