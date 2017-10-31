import java.util.*;
import java.io.*;

public class SumaArboles1{

	public static void main(String[] args) {
		
		/*Lectura del archivo*/

		LinkedList<String> listaVerificarLargoCaminos;
		LinkedList<GrafoDirigido> listaGrafos;

		try {
            Scanner in = new Scanner(new FileReader("pruebaLAB5.txt"));
			listaVerificarLargoCaminos = new LinkedList<String>();
			listaGrafos = new LinkedList<GrafoDirigido>();

            /*Se lee los numeros que se comprobaran luego para verificar si existen
            caminos de esa longitud*/

            while (in.hasNextLine()){
                String[] parts = in.nextLine().split(" ");
                for (int i=0;i<parts.length;i++){
                    if (parts[i].substring(0,1).equals("(") || parts[i].substring(0,1).equals(")")){
                        
                    }
                    else{
                        listaVerificarLargoCaminos.add(parts[i]);
                        
                    }
                    
                }
            }

            in = new Scanner(new FileReader("pruebaLAB5.txt"));
            int contadorParentesisAbiertos = 0;
            int contadorParentesisCerrados = 0;
            boolean noEsNumero=false;
            while (contadorParentesisAbiertos != contadorParentesisCerrados || contadorParentesisAbiertos==0){
            	String expresionArbol = "";
            	while (in.hasNextLine()){
            		String[] parts = in.nextLine().split(" ");
                	for (int i=0;i<parts.length;i++){
                		for (int j=0;j<parts[i].length();j++){
                			if (parts[i].substring(j,j+1).equals("(")){
                				contadorParentesisAbiertos++;
                                noEsNumero = true;
                				
                			}
                			else if (parts[i].substring(j,j+1).equals(")")){
                				contadorParentesisCerrados++;


                				noEsNumero = true;
                			}

                			if(parts[i].substring(0,1).equals("(")){
                				noEsNumero = true;
                			}

                			else if(parts[i].substring(0,1).equals(")")){
                				noEsNumero = true;
                			}

                			else{
                				noEsNumero = false;
                			}

                		}

                		if (noEsNumero==true){
                			expresionArbol+=parts[i];
                		}

                		if (contadorParentesisAbiertos==contadorParentesisCerrados && expresionArbol!=""){
                			GrafoDirigido miGrafoDirigido = new GrafoDirigido();
                			miGrafoDirigido.cargarGrafo(expresionArbol);
                			listaGrafos.add(miGrafoDirigido);
            				expresionArbol = "";	
            			}

                	}




            	}

            }

            /*Se terminan de leer los numeros luego de construir el grafo*/



            


        }
        catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("No se pudo abrir el archivo: ");
        }

        /*Se procede a utilizar el DFS Backtracking */
        int tamanoLista;
        tamanoLista = listaVerificarLargoCaminos.size();
        for (int i=0; i<tamanoLista; i++)
        {
            GrafoDirigido miGrafo;
            List<Vertice> listaVertices;
            miGrafo = listaGrafos.get(i);
            listaVertices = miGrafo.vertices();
            Vertice raiz;
            if (miGrafo.numeroDeVertices()!=0){
              raiz = listaVertices.get(0);  
            }
            else{
                raiz = null;
            }
            
            LinkedList<Vertice> cola;
            cola = new LinkedList<Vertice>();
            DFSBacktracking miBusqueda;
            miBusqueda = new DFSBacktracking();
            int verificarSolucion = Integer.parseInt(listaVerificarLargoCaminos.get(i));
            boolean solucion;

            /*Inicio de la funcion recursiva que va a hallar si existe el camino de
            dicha longitud */

            if (miGrafo.numeroDeVertices()!=0){
                solucion = miBusqueda.busquedaDFSBacktracking(miGrafo,raiz,raiz,cola,0,verificarSolucion);
                System.out.println(solucion);
            }
            else{
                System.out.println("false");
            }
            
	}

}
}