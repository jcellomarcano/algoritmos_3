/**
 *
 */

public class Arco extends Lado
{
  private Vertice extremoInicial;
  private Vertice extremoFinal;
  private String representacion;
  
  public Arco(String id, double peso, Vertice extremoInicial, Vertice extremoFinal) {
    super(id,peso);
    this.extremoInicial = extremoInicial;
    this.extremoFinal = extremoFinal;
    this.representacion = "El arco " + this.id + " tiene peso: " + this.peso + " y es de la forma: " + this.extremoInicial.toString() + "--" + this.peso + "-->" + this.extremoFinal.toString();
  }

  public Vertice getExtremoInicial() {
    return this.extremoInicial;
  }

  public Vertice getExtremoFinal() {
    return this.extremoFinal;
  }

  public String toString() {
    return this.representacion;
  }
}