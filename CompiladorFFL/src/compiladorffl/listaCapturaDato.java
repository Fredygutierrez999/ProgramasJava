/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorffl;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author fredyalejandrogutierrezvelasquez
 */
public class listaCapturaDato {

    /**
     * Constructor
     */
    public listaCapturaDato() {
        this.xDato = new ArrayList<>();
    }

    private List<String> xDato;

    /**
     * Agrega dato a la lista y avisa al hilo para que lo tome
     *
     * @param xdato
     */
    public void agregarDato(String xdato) {
        this.xDato.add(xdato);
        synchronized (this.xDato) {
            this.xDato.notify();
        }
    }

    /**
     * Metodo encargado de obtener el ultimo dato cargado a la lista
     *
     * @return
     * @throws InterruptedException
     */
    public String getDato() throws InterruptedException {
        synchronized (this.xDato) {
            this.xDato.wait();
            String remove = this.xDato.remove(0);
            return remove;
        }
    }
}
