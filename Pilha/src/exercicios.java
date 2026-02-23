package src;
import java.util.Scanner;

public class exercicios{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

    /*
    1) Construa um programa que leia 10 valores e empilhe-os
    conforme forem pares ou impares na pilha1 e pilha2
    respectivamente. No final desempilhe os valores de cada pilha
    mostrando-os na tela.
    */
        Pilha pilha1 = new Pilha(10);
        Pilha pilha2 = new Pilha(10);
        
        for(int i = 0; i < 10; i++){
            System.out.print("Digite um valor: ");
            int valor = sc.nextInt();
            
            if(valor % 2 == 0){
                pilha1.empilhar(valor);
            } else {
                pilha2.empilhar(valor);
            }
        }

        for(int i = 0; i < 10; i++){
            while(!pilha1.pilhaVazia()){
                System.out.println("Pilha 1: " + pilha1.desempilhar());
            }
            while(!pilha2.pilhaVazia()){
                System.out.println("Pilha 2: " + pilha2.desempilhar());
            }
        }
    }
}