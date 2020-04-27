package uy.um.edu.pparcial;

import uy.edu.um.adt.queue.Queue;
import uy.edu.um.adt.queue.QueueImpl;
import uy.um.edu.pparcial.excepciones.EntradasAgotadas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partido {

    private static final int MAX_ENTRADAS = 3;

    private int id;
    private int cupo;

    List<Espectador> espectadores = new ArrayList<>();
    Queue<Espectador> listaDeEspera = new QueueImpl<>();

    public Partido(int id, int cupo){
        this.id = id;
        this.cupo = cupo;
    }

    public static boolean datosCorrectos(int id, int cupo) {
        return cupo > 0 && id <= 63 && id >= 0;
    }

    public List<Long> obtenerListaDeEspectadoresOrdenadosPorPasaporte() {
        // aprovechándonos de que ya estábamos usando BST
        // era tener espectadores guardados en un BST
        //y hacer sólo
        // List<Espectadores> espectadoresOrdenados = espectadores.inorder();
        // List<Long> pasaportes = new ArrayList<Long>(espectadoresOrdenados.size());
        // for (int i = 0; i < espectadoresOrdenados.size(); i++){
        //      pasaportes.add(espectadoresOrdenados.get(i).getNumeroPasaporte());
        // }
        // return pasaportes;

        // hace falta que Espectador implements Comparable<Espectador>
        List<Espectador> temp = new ArrayList<>(espectadores);
        Collections.sort(temp);
        List<Long> pasaportes = new ArrayList<>(temp.size());
        for (int i = 0; i < temp.size(); i++){
            pasaportes.add(temp.get(i).getNumeroPasaporte());
        }
        return pasaportes;
    }

    public int getCuposLibres() {
        return cupo - espectadores.size();
    }

    public int getCuposEnEspera() {
        return listaDeEspera.size();
    }

    public int procesarCompra(Espectador espectador) throws EntradasAgotadas {
        if (getCuposLibres() <= 0){
            throw new EntradasAgotadas();
        }
        int ret = 0;
        // si no hay lugar en el estadio, Entradas Agotadas
        // si tiene más de 3, a la cola
        if (espectador.getCantidadEntradas()>=MAX_ENTRADAS ) {
            listaDeEspera.enqueue(espectador);
            ret = listaDeEspera.size();
        } else {
            espectadores.add(espectador);
        }
        espectador.comprarEntrada(this.id);
        return ret;
    }
}
