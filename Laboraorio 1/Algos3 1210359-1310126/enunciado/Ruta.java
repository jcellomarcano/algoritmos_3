import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
/**
 * Realizado por José Basandta 13-10125 y Jesús Marcano 12-10359
 * Lab 1 Roy-Warshall
 * clase main y constructor de Grafo tal que crea a raiz de recibir el archivo de entrada
 * la matriz de Clausura Transitiva correspondiente al grafo*/
public class Ruta{

  	public static void main (String[] args) throws IOException{
  		/*Lectura de los argumentos y guardar en variables*/
  		if (args.length != 3) {
      System.out.println("Error en los argumentos del programa\nEjecutar:");
      System.out.println("java LeerEscribirArchivo <nombreArchivoEntrada> <nombreArchivoSalida>");
      System.exit(1);
    }
    
    String nombreArchivoEntrada = args[0];
    int inicioGrafo = Integer.parseInt(args[1]);
    int finalGrafo = Integer.parseInt(args[2]);

    /*Creacion del objeto grafo por el archivo txt*/
    Grafo miGrafo = new Grafo(nombreArchivoEntrada);
    miGrafo.imprimirGrafo(); //Imprimimos la matriz para confirmar (LUEGO BORRAR ESTA LINEA)
    int [][] matrizRoyWarshall;
    matrizRoyWarshall= miGrafo.warshall();
    System.out.println("Esta es la warshall");
    for(int k=0;k<matrizRoyWarshall.length;k++){
    	for(int l=0;l<matrizRoyWarshall.length;l++){
    		System.out.print(matrizRoyWarshall[k][l]);
    	}
    	System.out.println();
    }
    

}


	private int[][] grafo;
	
	public Ruta(int n){
		this.grafo = new int[n][n];
		//mAyacencia();
	}

/*
	private void mAyacencia(){

		for (int i = 0; i < grafo.length; i++){
			for (int j = 0; j < grafo.length ; j++) {
				if (i == j) {
					grafo[i][j] = 0;
					
				}
				else {
					grafo[i][j] = INT.POSITIVE_INFINITY;
				}
			}
		}
	}
*/
	public void Adyacente(int ini, int fin, int tamaño){
		grafo[ini][fin] = tamaño;
	}

	public int[][] warshall(){
	 int [][] camino;
		int n = this.grafo.length;
		camino = Arrays.copyOf(this.grafo,n);
		for (int i = 0; i < n ; i++) {
			for (int j = 0; j < n ; j++ ) {
				for (int k = 0; k < n  ; j++) {
					if (camino[j][i] ==camino[i][k]) {
						camino [j][k] = 1; 
					}
				}
				
			}
		}
		return camino;
	}

}

