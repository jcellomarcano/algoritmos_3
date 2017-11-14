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
  private int indice;
  private boolean frontera;

  
  public Vertice(String id, double peso) {
    this.id = id;
    this.peso = peso;
    representacion = this.id + " " + this.peso;
    this.frontera = false;
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

  public int getIndice(){
    return this.indice;
  }

  public void setIndice(int indice){
    this.indice = indice;
  }

  public boolean getFrontera(){
    return this.frontera;
  }

  public void setFrontera (boolean frontera){
    this.frontera = frontera;
  }

}