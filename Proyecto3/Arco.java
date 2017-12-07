/**
 * Arco.java
 * Autores:
 * @author Jesus Marcano USB-ID 12-10359
 * @author Jose Basanta USB-ID 13-10125
 */

public class Arco extends Lado
{
  private Vertice extremoInicial;
  private Vertice extremoFinal;
  private String representacion;
  private int material;

  private int caraConexion;
  
  public Arco(String id, double peso, Vertice extremoInicial, Vertice extremoFinal) {
    super(id,peso);
    this.extremoInicial = extremoInicial;
    this.extremoFinal = extremoFinal;
    this.representacion = this.id + " " + this.extremoInicial.getId() + " "  + this.extremoFinal.getId() + " " + this.peso + " ";
  }

  public Vertice getExtremoInicial() {
    return this.extremoInicial;
  }

  public Vertice getExtremoFinal() {
    return this.extremoFinal;
  }

  public String toString() {
    representacion+=this.material + " " + getCaraConexion() ;

    return this.representacion;
  }

  public void setMaterial(int material){
    this.material = material;
  }

  public int getMaterial(){
    return this.material;
  }

  public void setCaraConexion(int caraConexion){
    this.caraConexion = caraConexion;
  }

  public int getCaraConexion(){
    return this.caraConexion;
  }
}