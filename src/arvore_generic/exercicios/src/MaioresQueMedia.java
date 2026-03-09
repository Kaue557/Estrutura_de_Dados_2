package Arvore.exercicios.src;

import java.util.Scanner;

public class MaioresQueMedia{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] vetor = new int[N];

        int soma = 0;

        //leitura do vetor e soma dos elementos
        for(int i = 0; i < N; i++){
            vetor[i] = sc.nextInt();
            soma += vetor[i];
        }

        //calculo da media
        double media = (double) soma/N;

        int contador = 0;

        //conta quantos sao maiores que a media
        for(int i = 0; i < N; i++){
            if(vetor[i] > media){
                contador++;
            }
        }

        //saida
        System.out.println(contador + " sao maiores que a media");

        sc.close();
    }
}
