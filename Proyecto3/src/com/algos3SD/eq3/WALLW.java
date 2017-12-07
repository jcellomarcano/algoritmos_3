//package com.algos3SD.eq3;

import java.util.*;

public class WALLW {

    public static void main(String[] args) {
        GrafoDirigido cuartosBasura = new GrafoDirigido();
        cuartosBasura.cargarGrafo(args[0]);
        System.out.println(cuartosBasura.vertices());
        System.out.println(cuartosBasura);

        DFSBacktracking miDFS;
        miDFS = new DFSBacktracking(cuartosBasura);
        LinkedList<LinkedList<Vertice>> posiblesSoluciones;
        posiblesSoluciones = miDFS.retornaListaSoluciones();
        System.out.println(posiblesSoluciones);
        LinkedList<Vertice> solucionReal = miDFS.torreMasAlta(posiblesSoluciones);
        System.out.println( miDFS.formatoSalida(solucionReal)); 

        /*int ia[][] = {{1,2},{3,4}};
        for (int[] ea : ia){
            for (int e: ea ) {
                System.out.println(ia);

            }
        }*/


    }
}
