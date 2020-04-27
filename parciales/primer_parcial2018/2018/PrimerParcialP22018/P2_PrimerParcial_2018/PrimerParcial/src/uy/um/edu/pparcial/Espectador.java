package uy.um.edu.pparcial;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Espectador implements Comparable<Espectador>{

    private long numeroPasaporte;
    private String nombre;
    private String pais;
    private List<Entrada> entradas = new ArrayList<>();

    public Espectador(long numeroPasaporte, String nombre, String pais) {
        this.numeroPasaporte = numeroPasaporte;
        this.nombre = nombre;
        this.pais = pais;
    }

    public int getCantidadEntradas(){
        return entradas.size();
    }

    public void comprarEntrada(int id){
        entradas.add(new Entrada(id));
    }

    public static boolean datosCorrectos(long numeroPasaporte, String nombre, String pais) {
        // "".equals(algo) es lo mismo que algo.equals("")
        return numeroPasaporte > 0 && !"".equals(nombre) && !"".equals(pais);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Espectador that = (Espectador) o;
        return numeroPasaporte == that.numeroPasaporte;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroPasaporte);
    }

    @Override
    public int compareTo(Espectador o) {
        int val = 0;
        if (this.numeroPasaporte > o.numeroPasaporte) {
            val = 1;
        } else if (this.numeroPasaporte < o.numeroPasaporte) {
            val = -1;
        }
        return val;
    }

    public Long getNumeroPasaporte() {
        return this.numeroPasaporte;
    }
}
