package Fila;
import java.util.Scanner;

public class Exercicios{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char resp;

        do {
            System.out.println("\nEscolha o exercicio (1 ao 3):");

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
                case '0':
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        } while (resp != '0');

        sc.close();
    }

    public static int exercicio1(Scanner sc){
        Fila<Integer> fila = new Fila<>(10);

        for(int i = 0; i < 5; i++){
            System.out.print("Digite um valor: ");
            int valor = sc.nextInt();
            fila.inserir(valor);
        }
        
        fila.exibirElementos();
        fila.remover();
        fila.remover();
        fila.exibirElementos();

        return 0;
    }

    public static void exercicio2(Scanner sc){
        Fila<Integer> fila1 = new Fila(3);
        for(int i = 0; i < 3; i++){
            System.out.print("Digite um valor: ");
            int valor = sc.nextInt();
            fila1.inserir(valor);
        }
        fila1.exibirElementos();
        System.out.println();


        Fila<String> fila2 = new Fila(3);
        for(int i = 0; i < 3; i++){
            System.out.print("Digite uma palavra: ");
            String palavra = sc.next();
            fila2.inserir(palavra);
        }
        fila2.exibirElementos();
        System.out.println();


        Fila<Double> fila3= new Fila(3);
        for(int i = 0; i < 3; i++){
            System.out.print("Digite um double: ");
            Double valor = sc.nextDouble();
            fila3.inserir(valor);
        }
        fila3.exibirElementos();
    }

    public static void exercicio3(Scanner sc) {
        Fila<String> fila = new Fila<>(50);

        System.out.println("Digite os nomes dos clientes (digite 'fim' para encerrar):");

        while (true) {
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            if (nome.equalsIgnoreCase("fim")) {
                break;
            }

            fila.inserir(nome);
        }

        System.out.println("\n*Iniciando atendimento*");

        while (!fila.filaVazia()) {
            String cliente = fila.primeiro();
            fila.remover();
            System.out.println("Atendendo cliente: " + cliente);
        }

        System.out.println("Todos os clientes foram atendidos.");
    }
}
