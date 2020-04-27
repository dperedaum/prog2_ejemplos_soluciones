import uy.edu.um.adt.queue.EmptyQueue;
import uy.edu.um.adt.queue.Queue;
import uy.edu.um.adt.queue.QueueImpl;
import uy.edu.um.adt.stack.Stack;
import uy.edu.um.adt.stack.StackImpl;

import java.util.ArrayList;


class GestorSucursal {

    //como en el UML se indica que gestor sucursal tiene * = muchos clientes, exp en proceso y archivados usamos tads para almacenarlos

    private Queue<Cliente> clientesEnFila = new QueueImpl<>();      //se usa FIFO para atender entonces hacemos una queue

    private Stack<Expediente> expedientesEnProceso = new StackImpl<>();     //cuando lleg un expediente se deja en el escritorio una arriba del otro, entonces usamos un stack


    private ArrayList<Expediente> expedientesArchivados = new ArrayList<>();    /*Como vamos a buscar al expediente por el id de expediente, se podria usar un hash cuya key sea el id, de esta manera se encuentra al exp en O(1).
                                                                                 Tambien se puede usar un binary search tree para buscar al expediente, esto es O(log n).
                                                                                Como aun no vieron hash ni bst, lo hacemos con un arraylist que es dinamico ya que no sabemos cuantos tendremos de ante mano */

    void sacarNumero(String cedula, String nombre, String telefono) throws DatosInvalidosException {

        if (cedula == null || nombre == null || telefono == null)
            throw new DatosInvalidosException();

        clientesEnFila.enqueue(new Cliente(cedula, nombre, telefono));      //creo un cliente y lo agrego a la fila

    }

    Cliente llamarProximoCliente() throws ListaVaciaException {

        try {                                           //si hay clietes en espera saco el primero y lo retorno, si la fila esta vacia tiro la excepcion
            return clientesEnFila.dequeue();
        } catch (EmptyQueue emptyQueue) {
            throw new ListaVaciaException();
        }

    }


    void crearExpedienteYColocarEnEscritorio(String idExpediente, String tipoTramite, String descripcion, Cliente cliente) throws DatosInvalidosException {

        if (idExpediente == null || cliente == null)
            throw new DatosInvalidosException();

        expedientesEnProceso.push(new Expediente(idExpediente, descripcion, tipoTramite, cliente));

    }

    Expediente obtenerProximoExpedienteAResolver() throws ListaVaciaException {

        if (expedientesEnProceso.size() > 0)
            return expedientesEnProceso.pop();
        else
            throw new ListaVaciaException();

    }

    void archivarExpediente(Expediente expediente) throws ExpedienteYaExisteException {

        if (!expedientesArchivados.contains(expediente))
            expedientesArchivados.add(expediente);
        else
            throw new ExpedienteYaExisteException();

    }

    Expediente consultaExpedienteArchivado(String idExpediente, String cedula) {

        Expediente expediente = null;                                                    /*recorro la lista de expedientes archivados para encontrar el expediente con ese id
                                                                                          DISCLAIMER: esto es O(n) en el caso promedio, no es una estrategia eficiente */
        for (Expediente expedienteArchivado : expedientesArchivados) {
            if (idExpediente.equals(expedienteArchivado.getIdExpediente())) {
                expediente = expedienteArchivado;
                break;                                                              //termino el for para no seguir buscando algo que ya encontre
            }
        }

        if (expediente == null || !expediente.getCliente().getCedula().equals(cedula))
            return null;

        else
            return expediente;

    }


}
