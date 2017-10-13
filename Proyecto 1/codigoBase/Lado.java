/**
 * 
 */

public abstract class Lado
{
  protected String id;
  protected double peso;
  protected String representacion;

  public Lado(String id, double peso) {
  		this.id = id;
		this.peso = peso;
		representacion = "El lado con id: " + this.id + " tiene peso: " + this.peso;
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