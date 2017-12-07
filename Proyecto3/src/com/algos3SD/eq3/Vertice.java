//package com.algos3SD.eq3;
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
  private int sumaAcumulada;
  private boolean visitado;
  private boolean terminado;
  private int tiempo;
  private Vertice padre;
  
  public Vertice(String id, double peso) {
    this.id = id;
    this.peso = peso;
    representacion = this.id + " " + this.peso;
    this.visitado = false;
    this.tiempo = 0;
    this.terminado = false;
  }

  public double getPeso() {
    return this.peso;
  }

  public void setPeso(double peso){
    this.peso = peso;
  }

  public String getId() {
    return this.id;
  }

  public String toString() {
    return this.representacion; 
  }

  public void setSuma(int suma){
    this.sumaAcumulada = suma;
  }

  public int getSuma(){
    return sumaAcumulada;
  }

  public boolean getVisitado(){
    return this.visitado;
  }

  public void setVisitado(boolean visitado){
    this.visitado = visitado;
  }

  public boolean getTerminado(){
    return this.terminado;
  }

  public void setTerminado(boolean terminado){
    this.terminado = terminado;
  }

  public int getTiempo(){
    return this.tiempo;
  }

  public void setTiempo(int tiempo){
    this.tiempo = tiempo;
  }

  public Vertice getPadre(){
    return this.padre;
  }

  public void setPadre(Vertice padre){
    this.padre = padre;
  }
}