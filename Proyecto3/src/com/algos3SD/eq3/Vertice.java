//package com.algos3SD.eq3;
/**
 * Vertice.java
 * Autores:
 * @author Jesus Marcano USB-ID 12-10359
 * @author Jose Basanta USB-ID 13-10125
 */

import java.util.*;

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

  private int posX;
  private int posY;
  private int material;
  private LinkedList<Vertice> caminoHastaEseVertice;
  
  public Vertice(String id, double peso) {
    this.id = id;
    this.peso = peso;
    representacion = this.id + " " + this.peso;
    this.visitado = false;
    this.tiempo = 0;
    this.terminado = false;
    this.caminoHastaEseVertice = new LinkedList<Vertice>();
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

  public LinkedList<Vertice> getCaminoHastaEseVertice(){
    return this.caminoHastaEseVertice;
  }

  public void setCaminoHastaEseVertice(LinkedList<Vertice> caminoHastaEseVertice){
    this.caminoHastaEseVertice = caminoHastaEseVertice;
  }

  public int getMaterial(){
    return this.material;
  }

  public void setMaterial(int material){
    this.material = material;
  }

  public int getPosX(){
    return this.posX;
  }

  public void setPosX(int posX){
    this.posX = posX;
  }


  public int getPosY(){
    return this.posY;
  }

  public void setPosY(int posY){
    this.posY = posY;
  }
}