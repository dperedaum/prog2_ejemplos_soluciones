package uy.um.edu.pparcial;

import uy.um.edu.pparcial.excepciones.EntradasAgotadas;
import uy.um.edu.pparcial.excepciones.EspectadorYaExiste;
import uy.um.edu.pparcial.excepciones.InformacionInvalida;

import java.util.List;

public interface MundialMgt {
    // en las interfaces, si no hay modificador de visibilidad, son publicos
    // antes, todo era público; ahora, hay otras opciones
    void crearPartido(int id, int cupo) throws InformacionInvalida;
    void crearEspectador(long numeroPasaporte, String nombre, String pais) throws InformacionInvalida, EspectadorYaExiste;
    int comprarEntrada(int idPartido, long numeroPasaporte) throws InformacionInvalida, EntradasAgotadas;
    List<Long> obtenerListaDeEspectadoresOrdenadosPorPasaporte(int idPartido);
    int obtenerCuposLibres(int idPartido);
    int obtenerCuposEnEspera(int idPartido);

}
