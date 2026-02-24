package Pilha;

import java.util.Scanner;

public class exercicios {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //fazer do while para mostrar os exercicios****************

        //par/impar
        exercicio1(sc);

        //eh palindromo
        exercicio2(sc);

        // exercicio3(sc);
    }

    public static void exercicio1(Scanner sc) {
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

        while(!pilha1.pilhaVazia()){
            System.out.println("Pilha 1: " + pilha1.desempilhar());
        }

        while(!pilha2.pilhaVazia()){
            System.out.println("Pilha 2: " + pilha2.desempilhar());
        }
    }
    public static void exercicio2(Scanner sc){
        System.out.print("Digite a palavra: ");
        String palavra = sc.nextLine();

        Pilha pilha = new Pilha(palavra.length());

        //empilha do comeco da palavra ate o final
        for(int i = 0; i < palavra.length(); i++){
            pilha.empilhar(palavra.charAt(i));
        }

        boolean ehPalindromo = true;

        //compara desempilhando
        for(int i = 0; i < palavra.length(); i++){
            char topo = (char) pilha.desempilhar();

            if(palavra.charAt(i) != topo){
                ehPalindromo = false;
                break;
            }
        }

        if(ehPalindromo){
            System.out.println("É palíndromo.");
        } else {
            System.out.println("Não é palíndromo.");
        }

    }

    public static void exercicio3(Scanner sc){
        /*
        3)Faca um programa que leia uma frase, terminada por ‘.’, e
        inverta a ordem das palavras na sequencia.
                Ex: OLA ESTE EH UM EXEMPLO SEM GRACA.
        a saida deve ser:
                    GRACA SEM EXEMPLO UM EH ESTE OLA.
        */
    }
}
