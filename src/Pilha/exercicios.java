package Pilha;

import java.util.Scanner;
import java.util.Stack;

public class exercicios {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char resp;

        do {
            System.out.println("\nEscolha o exercicio (1 ao 4):");

            System.out.print("Opcao: ");
            resp = sc.next().charAt(0);
            sc.nextLine(); // limpa o buffer

            switch (resp) {
                case '1':
                    exercicio1(sc);
                    break;
                case '2':
                    exercicio2(sc);
                    break;
                case '3':
                    exercicio3(sc);
                    break;
                case '4':
                    exercicio4(sc);
                    break;
                case '0':
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        } while (resp != '0');

        sc.close();
    }


    public static void exercicio1(Scanner sc) {
        Pilha pilha1 = new Pilha(10);
        Pilha pilha2 = new Pilha(10);

        for (int i = 0; i < 10; i++) {
            System.out.print("Digite um valor: ");
            int valor = sc.nextInt();

            if (valor % 2 == 0) {
                pilha1.empilhar(valor);
            } else {
                pilha2.empilhar(valor);
            }
        }

        while (!pilha1.pilhaVazia()) {
            System.out.println("Pilha 1: " + pilha1.desempilhar());
        }

        while (!pilha2.pilhaVazia()) {
            System.out.println("Pilha 2: " + pilha2.desempilhar());
        }
    }

    public static void exercicio2(Scanner sc) {
        System.out.print("Digite a palavra: ");
        String palavra = sc.nextLine();

        Pilha pilha = new Pilha(palavra.length());

        //empilha do comeco da palavra ate o final
        for (int i = 0; i < palavra.length(); i++) {
            pilha.empilhar(palavra.charAt(i));
        }

        boolean ehPalindromo = true;

        //compara desempilhando
        for (int i = 0; i < palavra.length(); i++) {
            char topo = (char) pilha.desempilhar();

            if (palavra.charAt(i) != topo) {
                ehPalindromo = false;
                break;
            }
        }

        if (ehPalindromo) {
            System.out.println("É palíndromo.");
        } else {
            System.out.println("Não é palíndromo.");
        }

    }

    public static void exercicio3(Scanner sc) {
        /*
        3)Faca um programa que leia uma frase, terminada por ‘.’, e
        inverta a ordem das palavras na sequencia.
                Ex: OLA ESTE EH UM EXEMPLO SEM GRACA.
        a saida deve ser:
                    GRACA SEM EXEMPLO UM EH ESTE OLA.
        */
        System.out.print("Digite a frase (terminada com ponto): ");
        String frase = sc.nextLine();

        frase = frase.replace(".", ""); // remove ponto final

        String[] palavras = frase.split(" ");

        Stack<String> pilha = new Stack<>();

        // joga na pilha
        for (String palavra : palavras) {
            pilha.push(palavra);
        }

        System.out.println("Invertida:");

        while (!(pilha.isEmpty())) {
            System.out.print(pilha.pop() + " ");
        }

        System.out.println(".");
    }

    public static int exercicio4(Scanner sc) {
        Stack<Character> pilha = new Stack<>();

        System.out.print("Expressao: ");
        String exp = sc.nextLine();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == '(') {
                pilha.push(c);
            } else if (c == ')') {
                if (pilha.isEmpty()) {
                    return 0; // fecha sem abrir
                }
                pilha.pop(); // remove o correspondente
            }
        }

        // se sobrou algo na pilha, ta mal formada
        if (pilha.isEmpty()) {
            System.out.println(1);
            return 1;
        } else {
            System.out.println(0);
            return 0;
        }
    }
}