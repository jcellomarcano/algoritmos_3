package com.algos3SD.equipo3;

/**
 * Arista.java
 * Autores:
 * @author Jesus Marcano USB-ID 12-10359
 * @author Jose Basanta USB-ID 13-10125
 */

public class Arista extends Lado{
    private Vertice u;
    private Vertice v;
    private String representacion;

    public Arista(String id, double peso, Vertice u, Vertice v) {
        super(id,peso);
        this.u = u;
        this.v = v;
        representacion = this.id + " " + this.u.getId() + " " + this.v.getId() + " " + this.peso;
    }

    public Vertice getExtremo1() {
        return this.u;
    }

    public Vertice getExtremo2() {
        return this.v;
    }

    public String toString() {
        return representacion;
    }
}
