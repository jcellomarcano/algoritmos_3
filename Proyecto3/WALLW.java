import java.util.*;
import java.io.*;

public class WALLW{

	public static void main(String[] args) {
		
		if (args.length!=1){
			System.out.println("Error en el pase de parametros");
			System.exit(0);
		}

		try {
			Scanner in = new Scanner(new FileReader(args[0]));
            int contador;

            /* Ciclo que crea cada grafo*/
            while(in.hasNext()){


            	GrafoDirigido miGrafo;
				miGrafo = new GrafoDirigido();

				/*Parte que crea los vertices */

            	int numeroCubos;
                numeroCubos = Integer.parseInt(in.next());
                contador = 0;

                /*Ciclo que crea cada cubo */
                for (int i=0; i<numeroCubos; i++){
                	int[] arregloMateriales = new int[6];

                	/*Ciclo que guarda los materiales del cubo */
                	
                	for(int j= 0; j<=5; j++){
                		arregloMateriales[j] = Integer.parseInt(in.next());
                	}
                	Vertice nuevoCubo;
                    nuevoCubo = new Vertice(Integer.toString(contador),contador+1);
                    
                    nuevoCubo.setMateriales(arregloMateriales);

                    miGrafo.agregarVertice(nuevoCubo);

                    contador++;
                }

                /*Parte que crea los arcos */
                List<Vertice> listaVertice;
                listaVertice = miGrafo.vertices();
                int contador1;
                contador1 = 0;
                for (Vertice cubo1 : listaVertice){
                	List<Vertice> listaVertice2;
                	listaVertice2 = miGrafo.vertices();
                	for (Vertice cubo2 : listaVertice2){
                		if (cubo1.getPeso()<cubo2.getPeso()){
                			int[] materiales1 = cubo1.getMateriales();
                			int[] materiales2 = cubo2.getMateriales();
                			for (int i=0;i<=5;i++){
                				int materialcubo1 = materiales1[i];
                				for (int j = 0;j<=5;j++){
                					int materialcubo2 = materiales2[j];
                					if(materialcubo1 == materialcubo2){
                						Arco nuevoArco;
                						nuevoArco = new Arco(Integer.toString(contador1),0,cubo1,cubo2);
                						nuevoArco.setMaterial(materialcubo1);
                						miGrafo.agregarArco(nuevoArco);
                						contador1++;
                					}
                				}
                			}
                		}
                	}

                }

                System.out.println(miGrafo);          
            }

		}

		catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("No se pudo abrir el archivo: " + args[0]);
        }
	}
}