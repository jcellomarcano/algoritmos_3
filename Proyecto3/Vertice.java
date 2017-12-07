import java.util.*;
import java.io.*;

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

  private int[] materiales;

  private int materialCaraArriba;
  private int caraArriba;

  private LinkedList<Integer> caraConexion;
  private LinkedList<Vertice> caminoHastaEseVertice;
  
  public Vertice(String id, double peso) {
    this.id = id;
    this.peso = peso;
    this.materiales = new int[6];
    representacion = this.id + " " + this.peso + " ";
    this.visitado = false;
    this.padre = null;
    this.tiempo = 0;
    this.terminado = false;
    this.caraConexion = new LinkedList<Integer>();

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
    
    /*
    for (int i=0;i<=5;i++){
      this.representacion+=this.materiales[i] + ", ";
    }
    */

    representacion+= caraArriba;
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

  public void setMateriales(int[] materiales1){
    this.materiales = materiales1;
  }

  public int[] getMateriales(){
    return this.materiales;
  }

  public void setMaterialCaraArriba(int materialCaraArriba){
    this.materialCaraArriba = materialCaraArriba;
  }

  public int getMaterialCaraArriba(){
    return this.materialCaraArriba;
  }


  public void setCaraArriba(int caraArriba){
    this.caraArriba = caraArriba;
  }

  public int getCaraArriba(){
    return this.caraArriba;
  }

  public void setCaraConexion(LinkedList<Integer> caraConexion){
    this.caraConexion = caraConexion;
  }

  public LinkedList<Integer> getCaraConexion(){
    return this.caraConexion;
  }

  public void setCaminoHastaEseVertice(LinkedList<Vertice> caminoHastaEseVertice){
    this.caminoHastaEseVertice = caminoHastaEseVertice;
  }

  public LinkedList<Vertice> getCaminoHastaEseVertice(){
    return this.caminoHastaEseVertice;
  }
}