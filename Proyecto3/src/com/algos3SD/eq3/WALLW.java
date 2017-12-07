//package com.algos3SD.eq3;

public class WALLW {

    public static void main(String[] args) {
        GrafoDirigido cuartosBasura = new GrafoDirigido();
        cuartosBasura.cargarGrafo(args[0]);
        System.out.println(cuartosBasura.vertices());
        System.out.println(cuartosBasura);

        /*int ia[][] = {{1,2},{3,4}};
        for (int[] ea : ia){
            for (int e: ea ) {
                System.out.println(ia);

            }
        }*/


    }
}
