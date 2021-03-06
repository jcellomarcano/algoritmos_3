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

  public void setSuma(int suma){
    this.sumaAcumulada = suma;
  }

  public int getSuma(){
    return sumaAcumulada;
  }
}