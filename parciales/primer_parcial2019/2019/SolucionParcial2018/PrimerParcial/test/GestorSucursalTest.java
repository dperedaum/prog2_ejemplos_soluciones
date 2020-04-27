import static org.junit.Assert.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class GestorSucursalTest {

    @Test
    void flujoPrincipalTest() {

        GestorSucursal gestorSucursal = new GestorSucursal();

        Cliente cliente1 = new Cliente("0123456234", "Dani", "09922222");
        Cliente cliente2 = new Cliente("0123456235", "Pepe", "099111111");
        Cliente cliente3 = new Cliente("0123456236", "Fran", "09922223");

        Expediente expediente1 = new Expediente("1", "descripcion1", "tipo1", cliente1);
        Expediente expediente2 = new Expediente("2", "descripcion2", "tipo2", cliente2);

        try {
            //saco numeros
            gestorSucursal.sacarNumero(cliente1.getCedula(), cliente1.getNombre(), cliente1.getTelefono());
            gestorSucursal.sacarNumero(cliente2.getCedula(), cliente2.getNombre(), cliente2.getTelefono());
            gestorSucursal.sacarNumero(cliente3.getCedula(), cliente3.getNombre(), cliente3.getTelefono());

            assertEquals(cliente1.getCedula(), gestorSucursal.llamarProximoCliente().getCedula());
            assertEquals(cliente2.getCedula(), gestorSucursal.llamarProximoCliente().getCedula());

            //creo expediente
            gestorSucursal.crearExpedienteYColocarEnEscritorio(expediente1.getIdExpediente(), expediente1.getDescripcion(), expediente1.getTipoTramite(), cliente1);
            gestorSucursal.crearExpedienteYColocarEnEscritorio(expediente2.getIdExpediente(), expediente2.getDescripcion(), expediente2.getTipoTramite(), cliente2);

            assertEquals(expediente2.getIdExpediente(), gestorSucursal.obtenerProximoExpedienteAResolver().getIdExpediente());
            assertEquals(expediente1.getIdExpediente(), gestorSucursal.obtenerProximoExpedienteAResolver().getIdExpediente());

            //archivo expediente
            gestorSucursal.archivarExpediente(expediente2);
            gestorSucursal.archivarExpediente(expediente1);

            assertEquals(expediente1.getIdExpediente(), gestorSucursal.consultaExpedienteArchivado(expediente1.getIdExpediente(), cliente1.getCedula()).getIdExpediente());
            assertEquals(expediente2.getIdExpediente(), gestorSucursal.consultaExpedienteArchivado(expediente2.getIdExpediente(), cliente2.getCedula()).getIdExpediente());


        } catch (DatosInvalidosException | ListaVaciaException | ExpedienteYaExisteException e) {
            fail();
        }

    }

    @Test()
    void flujoExpedienteNullTest() {

        GestorSucursal gestorSucursal = new GestorSucursal();

        Cliente cliente1 = new Cliente("0123456234", "Dani", "09922222");
        Cliente cliente2 = new Cliente("0123456235", "Pepe", "099111111");
        Expediente expediente1 = new Expediente("1", "descripcion1", "tipo1", cliente1);
        Expediente expediente2 = new Expediente("2", "descripcion2", "tipo2", cliente2);

        try {
            //creo expediente 1 pero no exp 2
            gestorSucursal.crearExpedienteYColocarEnEscritorio(expediente1.getIdExpediente(), expediente1.getDescripcion(), expediente1.getTipoTramite(), cliente1);

            assertEquals(expediente1.getIdExpediente(), gestorSucursal.obtenerProximoExpedienteAResolver().getIdExpediente());

            //busco expediente sin archivar
            assertNull(gestorSucursal.consultaExpedienteArchivado(expediente2.getIdExpediente(), cliente2.getCedula()));


        } catch (ListaVaciaException | DatosInvalidosException e) {
            fail();
        }

    }

    @Test()
    void flujoErrorDeExpedienteRepetidoTest() {

        GestorSucursal gestorSucursal = new GestorSucursal();

        Cliente cliente1 = new Cliente("0123456234", "Dani", "09922222");
        Expediente expediente1 = new Expediente("1", "descripcion1", "tipo1", cliente1);

        try {
            //creo expediente 1 pero no exp 2
            gestorSucursal.crearExpedienteYColocarEnEscritorio(expediente1.getIdExpediente(), expediente1.getDescripcion(), expediente1.getTipoTramite(), cliente1);

            gestorSucursal.archivarExpediente(expediente1);

            Assertions.assertThrows(ExpedienteYaExisteException.class, () -> {
                //agregro exp a archivado
                gestorSucursal.archivarExpediente(expediente1);

            });


        } catch (ExpedienteYaExisteException | DatosInvalidosException e) {
            fail();
        }

    }

    @Test()
    void flujoErrorDeDatosTest() {

        GestorSucursal gestorSucursal = new GestorSucursal();

        Cliente cliente1 = new Cliente("0123456234", "Dani", "09922222");

        Assertions.assertThrows(DatosInvalidosException.class, () -> gestorSucursal.crearExpedienteYColocarEnEscritorio(null, null, null, null));

        Assertions.assertThrows(DatosInvalidosException.class, () -> gestorSucursal.sacarNumero(null, cliente1.getNombre(), cliente1.getTelefono()));


    }

    @Test()
    void flujoListaVaciaTest() {

        GestorSucursal gestorSucursal = new GestorSucursal();

        Assertions.assertThrows(ListaVaciaException.class, () -> gestorSucursal.obtenerProximoExpedienteAResolver());

        Assertions.assertThrows(ListaVaciaException.class, () -> gestorSucursal.llamarProximoCliente());


    }
}
