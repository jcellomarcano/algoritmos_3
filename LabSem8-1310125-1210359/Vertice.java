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
  private double posX;
  private double posY;
  private Vertice padre;
  private boolean visto;
  
  public Vertice(String id, double peso) {
    this.id = id;
    this.peso = peso;
    representacion = this.id + " " + this.peso;
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

  public void setPos(double posX, double posY){
    this.posX = posX;
    this.posY = posY;
  }

  public double getPosX(){
    return this.posX;
  }

  public double getPosY(){
    return this.posY;
  }

  public void setPadre(Vertice padre){
    this.padre = padre;
  }

  public Vertice getPadre(){
    return this.padre;
  }

  public void setVisto(boolean visto){
    this.visto = visto;
  }

  public boolean getVisto(){
    return this.visto;
  }

}