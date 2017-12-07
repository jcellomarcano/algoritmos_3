public class Orden{

	public static void main(String[] args) {
		if (args.length!=1){
			System.out.println("Introdujo mal los argumentos");
		}

		else{
			GrafoDirigido miGrafo;
			miGrafo = new GrafoDirigido();
			miGrafo.cargarGrafo(args[0]);
			System.out.println(miGrafo.toString());
			OrdenTopologico miOrdenTopologico = new OrdenTopologico(miGrafo);
			miOrdenTopologico.ordenacion();
			System.out.println(miOrdenTopologico.getLista()); 
		}
	}
}