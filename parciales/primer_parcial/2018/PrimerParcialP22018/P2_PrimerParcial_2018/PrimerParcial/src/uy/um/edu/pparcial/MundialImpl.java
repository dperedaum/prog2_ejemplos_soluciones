package uy.um.edu.pparcial;

import uy.um.edu.pparcial.excepciones.EntradasAgotadas;
import uy.um.edu.pparcial.excepciones.EspectadorYaExiste;
import uy.um.edu.pparcial.excepciones.InformacionInvalida;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MundialImpl implements MundialMgt {

    private final static int CANTIDAD_PARTIDOS = 64;

    Partido[] partidos = new Partido[CANTIDAD_PARTIDOS]; // valores default = null
    /*
    o también
    List<Partido> partidos = new ArrayList<>(CANTIDAD_PARTIDOS);
    les facilita ciertas cosas como buscar un partido,
    o acceder a una pos en particular
    */

    List<Espectador> espectadores = new ArrayList<>();
    /*
    en los partidos da lo mismo usar array estático o dinámico, porque la cantidad de partidos es fija
    pero para la cantidad de espectadores, podrías reservar los 5 millones de huecos en el array
    pero creo que está mejor ir pidiendo tamaño de a poquito, que ya está implementado en el ArrayList de Java
    igualmente, lo mejor es usar un Binary Tree, o capaz hasta un Hash, que dan búsquedas más rápidas,
    y en el caso del BST van pidiendo tamaño con el tiempo
    aparte te ahorrás tener que mantener una variable size
    */
    @Override
    public void crearPartido(int id, int cupo) throws InformacionInvalida {
        if(!Partido.datosCorrectos(id, cupo) || partidos[id] != null){
            // si datos mal o partido ya creado
            // para el partido ya creado estaría "lindo" agregar un throws PartidoYaExiste, pero no está pedido
            throw new InformacionInvalida();
            // podrias lanzar la excepcion dentro del consctructor de Partido
        }
        partidos[id] = new Partido(id, cupo);
    }



    @Override
    public void crearEspectador(long numeroPasaporte, String nombre, String pais) throws InformacionInvalida, EspectadorYaExiste {
        if (!Espectador.datosCorrectos(numeroPasaporte, nombre, pais)) {
            throw new InformacionInvalida();
        }
        Espectador nuevo = new Espectador(numeroPasaporte, nombre, pais);
        if (indexOf(espectadores, nuevo) < 0) {
            /*
            Hay que implementar equals para que funcionen ambas!
            otra alternativa:
            espectadores.contains(nuevo)
            o
            espectadores.indexOf(nuevo) > 0
             */
            throw new EspectadorYaExiste();
        }
        espectadores.add(nuevo);
    }

    @Override
    public int comprarEntrada(int idPartido, long numeroPasaporte) throws InformacionInvalida, EntradasAgotadas {
        if ( idPartido < 0 || idPartido > 63 || numeroPasaporte < 0) {
            // chequeo datos
            throw new InformacionInvalida();
        }
        // creo este trucho para poder usar indexOf, sabiendo que el identificador es solo el pasaporte
        Espectador trucho = new Espectador(numeroPasaporte, "trucho", "trucho");
        // tmbn podes usar contains en el if, o el indexOf de Java
        int idx = indexOf(espectadores, trucho);
        if (partidos[idPartido] == null || idx < 0) {
            throw new InformacionInvalida();
        }

        // ahora se que existe el espectador y el partido
        Partido partido = partidos[idPartido];
        Espectador espectador = espectadores.get(idx);
        // si espectador ya tiene muchas entradas, va al queue
        // si el estadio esta lleno, tambien. Pero eso lo manejda el Partido
        return partido.procesarCompra(espectador);
    }

    @Override
    public List<Long> obtenerListaDeEspectadoresOrdenadosPorPasaporte(int idPartido) {
        // tambien es una opcion chequear el idPartido, throweando InformacionInvalida
        return partidos[idPartido].obtenerListaDeEspectadoresOrdenadosPorPasaporte();

    }

    @Override
    public int obtenerCuposLibres(int idPartido) {
        // tambien es una opcion chequear el idPartido, throweando InformacionInvalida
        return partidos[idPartido].getCuposLibres();
    }

    @Override
    public int obtenerCuposEnEspera(int idPartido) {
        // tambien es una opcion chequear el idPartido, throweando InformacionInvalida
        return partidos[idPartido].getCuposEnEspera();
    }

    private <T> int indexOf(List<T> lista, T o){
        int idx = -1;
        for (int i = 0; i < lista.size(); i++){
            // el lista.get(i) == o chequea para el caso en que el objeto sea null
            // y lista tenga elementos nulos. Devuelve primera ocurrencia. Ojo
            if (lista.get(i) == o || lista.get(i).equals(o)) {
                return i;
            }
        }
        return idx;
    }
}
