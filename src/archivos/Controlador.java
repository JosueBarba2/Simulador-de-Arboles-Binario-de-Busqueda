package archivos;

import arbolBinario.ArbolBB;

/**
 *
 * @author Daniel Capa
 * @author Josue Barba
 * @author Elkin Cuenca
 * @author Michael Carreño
 */
public class Controlador {
  private final  Lienzo objLienzo;
    private final  ArbolBB objArbol;
    
    
    public Controlador(Lienzo objLienzo, ArbolBB objArbol) {
        this.objLienzo = objLienzo;
        this.objArbol = objArbol;
    }
    
    public void iniciar(){
        objLienzo.setObjArbol(objArbol);
    }
     
 
}
