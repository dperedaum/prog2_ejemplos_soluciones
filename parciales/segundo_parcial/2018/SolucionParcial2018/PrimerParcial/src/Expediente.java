
class Expediente {

    private String idExpediente;
    private String descripcion;
    private String tipoTramite;
    private boolean estaAbierto;
    private String resolucion;

    private Cliente cliente; //segun el UML el expediente le pertenece a un cliente

    Expediente(String idExpediente, String descripcion, String tipoTramite, Cliente cliente) {
        this.idExpediente = idExpediente;
        this.descripcion = descripcion;
        this.tipoTramite = tipoTramite;
        this.cliente = cliente;
        this.estaAbierto = false;
        this.resolucion = null;
    }

    String getIdExpediente() {
        return idExpediente;
    }

    String getDescripcion() {
        return descripcion;
    }

    String getTipoTramite() {
        return tipoTramite;
    }

    boolean EstaAbierto() {
        return estaAbierto;
    }

    String getResolucion() {
        return resolucion;
    }

    Cliente getCliente() {
        return cliente;
    }

    //el id no se cambia nunca, el tipo de tramite y la descripcion dependen de la implementacion
    //pero estaAbierto y setResolucion seguro se modifican (obs: no hay que implementar el metodo para modificar en este parcial)

    public void setEstaAbierto(boolean estaAbierto) {
        this.estaAbierto = estaAbierto;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }


    //dos expedientes son iguales si su id es igual
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expediente that = (Expediente) o;
        return idExpediente.equals(that.idExpediente);
    }
}
