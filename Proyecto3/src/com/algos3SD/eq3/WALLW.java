//package com.algos3SD.eq3;

import java.util.*;
import java.io.*;

public class WALLW {

    public static void main(String[] args) {


        

        
        try {

            Scanner in = new Scanner(new FileReader(args[0]));
            boolean grafoCargado;
            grafoCargado = false;
            double contador = 0;
            int contadorCasos = 0;
            int idV = 0;
            
            //System.out.println("Esta es la cantidad de cubos" + numOfCubes + "\n");

            
            while(in.hasNext()){
                GrafoDirigido cuartosBasura = new GrafoDirigido();
                int numOfCubes = Integer.parseInt(in.next());
                Vertice[][] cubos = new Vertice[numOfCubes][6];
                //System.out.println(lineas);
                for (int i = 0; i < numOfCubes; i++){
                    for (int j = 0; j < 6; j++){
                        String lineas = in.next();
                        
                        String xy = String.valueOf(i)+"i" + String.valueOf(j)+"j";
                        Vertice verticePorAgregar = new Vertice(xy,contador);
                        verticePorAgregar.setPosX(i);
                        verticePorAgregar.setPosY(j);
                        verticePorAgregar.setMaterial(Integer.parseInt(lineas));
                        cuartosBasura.agregarVertice(verticePorAgregar);
                        cubos[i][j] = verticePorAgregar;
                        idV ++;

                    }
                    contador ++;

                }

                cuartosBasura.setCubos(cubos);

            

                int contadorArcos;
                contadorArcos = 0;

                for (int i = 0; i < numOfCubes; i++){
                    for (int j = 0; j < 6; j++){
                    
                        Vertice v = cubos[i][j];
                        int material1 = v.getMaterial();

                    for (int k=i+1;k<numOfCubes;k++){

                        for (int l = 0; l<6; l++){

                            Vertice w = cubos[k][l];
                            int material2 = w.getMaterial();
                            if (material1 == material2){
                                Arco nuevoArco;
                                nuevoArco = new Arco(Integer.toString(contadorArcos),0,v,w);

                                cuartosBasura.agregarArco(nuevoArco);
                                contadorArcos++;
                            }
                        }
                    }
                }

            }


            DFSBacktracking miDFS;
            miDFS = new DFSBacktracking(cuartosBasura);
            LinkedList<LinkedList<Vertice>> posiblesSoluciones;
            posiblesSoluciones = miDFS.retornaListaSoluciones();
            //System.out.println(posiblesSoluciones);
            LinkedList<Vertice> solucionReal = miDFS.torreMasAlta(posiblesSoluciones);
            System.out.println( miDFS.formatoSalida(solucionReal)); 

            }
            
        }

        catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("No se pudo abrir el archivo: " + args[0]);
        }
        


        //GrafoDirigido cuartosBasura = new GrafoDirigido();
        //cuartosBasura.cargarGrafo(args[0]);
        //System.out.println(cuartosBasura.vertices());
        //System.out.println(cuartosBasura);

        

        /*int ia[][] = {{1,2},{3,4}};
        for (int[] ea : ia){
            for (int e: ea ) {
                System.out.println(ia);

            }
        }*/


    }
}
