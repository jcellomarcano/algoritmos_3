package com.algos3SD.equipo3;

import java.util.LinkedList;

/**
 * Vertice.java
 * Autores:
 * @author Jesus Marcano USB-ID 12-10359
 * @author Jose Basanta USB-ID 13-10125
 */

public class Vertice
{
    private String id;
    private double peso;
    private String representacion;

    public Vertice(String id, double peso) {
        this.id = id;
        this.peso = peso;
        representacion = this.id + " " + this.peso;
    }

    public double getPeso() {
        return this.peso;
    }

    public String getId() {
        return this.id;
    }

    public String toString() {
        return this.representacion;
    }

    public static class GrafoDirigido {
        private int numV; //numero de vertices
        private int numL; //numero de lados
        private LinkedList<LinkedList<Vertice>> g; // lista doblemente en

    }
}