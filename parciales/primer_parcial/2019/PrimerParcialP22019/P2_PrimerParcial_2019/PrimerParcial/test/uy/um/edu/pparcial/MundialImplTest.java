package uy.um.edu.pparcial;

import org.junit.Before;
import org.junit.Test;
import uy.um.edu.pparcial.excepciones.EntradasAgotadas;
import uy.um.edu.pparcial.excepciones.EspectadorYaExiste;
import uy.um.edu.pparcial.excepciones.InformacionInvalida;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MundialImplTest {

    MundialMgt m;

    @Before
    public void before(){
        m = new MundialImpl();
    }

    @Test
    public void flujoNormal() {
        try {
            m.crearPartido(0, 3);
            m.crearPartido(4, 3);
            m.crearPartido(1, 3);
            m.crearPartido(63, 3);
            m.crearPartido(54, 3);

            m.crearEspectador(1L, "h", "h");
            m.crearEspectador(2L, "h", "h");
            m.crearEspectador(6L, "h", "h");
            m.crearEspectador(4L, "h", "h");
            m.crearEspectador(45L, "h", "h");
            m.crearEspectador(17L, "h", "h");
        } catch (InformacionInvalida | EspectadorYaExiste e) {
            fail();
        }

        try {
            m.comprarEntrada(1, 45L);
            m.comprarEntrada(0, 45L);
            m.comprarEntrada(4, 45L);
            m.comprarEntrada(4, 45L);
            m.comprarEntrada(63, 45L);
            System.out.println("hola");
            m.comprarEntrada(4, 1L);
            m.comprarEntrada(4, 6L);
            m.comprarEntrada(1, 6L);
        } catch (InformacionInvalida | EntradasAgotadas e) {
            fail();
        }


        // 45 tiene 5 entradas, va a estar en colas de espera
        // partido 4 va a tener a 45 en espera, y a 45, 1 y 6 en estadio.
        assertEquals(1, m.obtenerCuposEnEspera(4));
        assertEquals(0, m.obtenerCuposLibres(4));
        assertEquals(1, m.obtenerCuposLibres(1));
        assertEquals(1, m.obtenerCuposEnEspera(63));
        assertEquals(new ArrayList<Long>(Arrays.asList(1L, 6L, 45L)), m.obtenerListaDeEspectadoresOrdenadosPorPasaporte(4));
    }

    @Test(expected = EntradasAgotadas.class)
    public void probarEntradasAgotadas() throws EntradasAgotadas, InformacionInvalida, EspectadorYaExiste{

        m.crearPartido(2, 1);
        m.crearEspectador(5L, "h", "h");

        m.comprarEntrada(2, 5L);
        m.comprarEntrada(2, 5L);
    }

    @Test(expected = EspectadorYaExiste.class)
    public void probarEspectadorYaExiste() throws EspectadorYaExiste, InformacionInvalida {
        m.crearEspectador(5L, "h", "h");
        m.crearEspectador(5L, "h", "h");
    }

}