public class Cliente {

    private String nombre;
    private String cedula;
    private String telefono;

    Cliente(String cedula, String nombre, String telefono) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
    }

    String getNombre() {
        return nombre;
    }

    String getCedula() {
        return cedula;
    }

    String getTelefono() {
        return telefono;
    }


    //no tiene sentido cambiar la cedula de un cliente pero si su telefono
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //dos clientes son iguales si su cedula es igual
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cedula.equals(cliente.cedula);
    }


}
