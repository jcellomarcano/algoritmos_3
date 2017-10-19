/**
 * 
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
}