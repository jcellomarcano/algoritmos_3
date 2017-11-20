/**
 * Lado.java
 * Autores:
 * @author Jesus Marcano USB-ID 12-10359
 * @author Jose Basanta USB-ID 13-10125
 */

public abstract class Lado
{
  protected String id;
  protected double peso;
  protected String representacion;

  public Lado(String id, double peso) {
  		this.id = id;
		this.peso = peso;
		representacion = this.id + " " + this.peso;
  }

  public String getId() {
  		return this.id;
  }

  public double getPeso() {
  		return this.peso;
  }

  public String toString(){
		return this.representacion;
  }

}