public class pruebaCola{

	public static void main(String[] args) {
		ColaPrioridad miCola;
		Nodo nodo1;
		Vertice vertice1;
		Elemento elemento1;

		Nodo nodo2;
		Vertice vertice2;
		Elemento elemento2;

		Nodo nodo3;
		Vertice vertice3;
		Elemento elemento3;

		Nodo nodo4;
		Vertice vertice4;
		Elemento elemento4;

		Nodo nodo5;
		Vertice vertice5;
		Elemento elemento5;

		Nodo nodo6;
		Vertice vertice6;
		Elemento elemento6;

		miCola = new ColaPrioridad();
		vertice1 = new Vertice("hola",0);
		nodo1 = new Nodo(vertice1);
		elemento1 = new Elemento(nodo1,1);

		vertice2 = new Vertice("chao",0);
		nodo2 = new Nodo(vertice2);
		elemento2 = new Elemento(nodo2,1);

		vertice3 = new Vertice("buenasNoches",0);
		nodo3 = new Nodo(vertice3);
		elemento3 = new Elemento(nodo3,2);

		vertice4 = new Vertice("buenasDias",0);
		nodo4 = new Nodo(vertice4);
		elemento4 = new Elemento(nodo4,3);

		vertice5 = new Vertice("Epale",0);
		nodo5 = new Nodo(vertice5);
		elemento5 = new Elemento(nodo5,4);

		vertice6 = new Vertice("Hey",0);
		nodo6 = new Nodo(vertice6);
		elemento6 = new Elemento(nodo6,3);
		
		miCola.agregarElemento(elemento1);
		miCola.agregarElemento(elemento2);
		miCola.agregarElemento(elemento3);
		miCola.agregarElemento(elemento4);
		miCola.agregarElemento(elemento5);
		miCola.agregarElemento(elemento6);
		miCola.imprimirCola();
		miCola.extraerMinimo();
		System.out.println();
		miCola.imprimirCola();

	}
}