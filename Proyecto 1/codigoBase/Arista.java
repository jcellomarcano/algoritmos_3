/**
 *
 */

public class Arista extends Lado
{
  private Vertice u;
  private Vertice v;
  private String representacion;
  
  public Arista(String id, double peso, Vertice u, Vertice v) {
    super(id,peso);
    this.u = u;
    this.v = v;
    representacion = "La arista: " + this.id + " tiene peso: " + this.peso + " y es de la forma: " + this.u.toString() + "--" + this.peso + "---" + this.v.toString();
  }

  public Vertice getExtremo1() {
    return this.u;
  }

  public Vertice getExtremo2() {
    return this.v;
  }

  public String toString() {
    return representacion;
  }
}