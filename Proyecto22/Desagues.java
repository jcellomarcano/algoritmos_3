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

        Tarjan tar = new Tarjan(miGrafo);
        
        //System.out.println(miGrafo.sucesores("0").iterator().hasNext());
        long c = 00;
        c =+ 1;
        System.out.println(c);

	}
}