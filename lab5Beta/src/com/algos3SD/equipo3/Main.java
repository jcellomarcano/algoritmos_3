package com.algos3SD.equipo3;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        GrafoDirigido miGrafoDirigido;
        miGrafoDirigido = new GrafoDirigido();
        miGrafoDirigido.cargarGrafo("../resources/prueba1.txt");
        System.out.println(miGrafoDirigido.toString());
        System.out.println();
        System.out.println();
        System.out.println("Creare mi clon de GrafoDirigido");
        GrafoDirigido clonDeMiGrafoDirigido;
        //clonDeMiGrafoDirigido = new GrafoDirigido();
        clonDeMiGrafoDirigido = miGrafoDirigido.clone();
        System.out.println(miGrafoDirigido.toString());
        System.out.println("Voy a agregarVertice");
        clonDeMiGrafoDirigido.agregarVertice("bien",20);
        System.out.println(clonDeMiGrafoDirigido.toString());




    }
}