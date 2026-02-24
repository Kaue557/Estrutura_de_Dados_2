package Arvore.exercicios.src;/*
Escreva um programa em Java que:
        Leia dois vetores de numeros inteiros, contendo cada um, 5 elementos.
        Intercale os elementos destes dois conjuntos formando um novo vetor de 10 elementos.
        Apresente o novo conjunto, assim obtido.
*/

import java.util.Scanner;

public class Concatena {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int []vet1 = new int[5];
        int []vet2 = new int[5];

        System.out.println("vetor 1: ");
        for(int i = 0; i < vet1.length; i++){
            vet1[i] = sc.nextInt();
        }

        System.out.println("vetor 2: ");
        for(int i = 0; i < vet1.length; i++){
            vet2[i] = sc.nextInt();
        }

        int []vet3 = new int[vet1.length + vet2.length]; //eh feio, mas funcina

        int pos = 0;

        // copia vet1
        for (int i = 0; i < vet1.length; i++) {
            vet3[i] = vet1[i];
        }

        // copia vet2
        for(int i = 0; i < vet2.length; i++) {
            vet3[vet1.length + i] = vet2[i];
        }

        //impressao
        System.out.println("vetor 3: ");
        for(int i = 0; i < vet3.length; i++) {
            System.out.println(vet3[i]);
        }

    }
}
