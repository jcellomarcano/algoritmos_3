import java.io.IOException;


public class Desagues{

	public static void main(String[] args) throws IOException{
		
		GrafoDirigido miGrafo;
		miGrafo = new GrafoDirigido();
		miGrafo.cargarGrafo("prueba.txt");
		System.out.println(miGrafo.toString());
		System.out.println(miGrafo.vertices());
		System.out.println(miGrafo.lados());
        System.out.println("\n");
        System.out.println(miGrafo.sucesores("00 3.0"));
	}
}