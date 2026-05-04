package arvore_B;

public class Main {
    public static void main(String[] args) {

        // Criando uma Árvore B de ordem 2
        // (Máximo de 4 chaves por nó, min de 2, exceto a raiz)
        ArvoreB arvore = new ArvoreB(2);

        // A exata sequência de testes do seu PDF:
        int[] sequenciaPDF = {20, 40, 10, 30, 15, 50, 60, 70, 80};

        System.out.println("Iniciando inserções...");
        for (int valor : sequenciaPDF) {
            System.out.println("\nInserindo o valor: " + valor);
            arvore.inserir(valor);

            // Imprimimos a árvore a cada passo para ver as cisões acontecendo
            arvore.imprimir();
        }

        System.out.println("\n--- TESTANDO A BUSCA ---");

        // Testando um valor que EXISTE (ex: 30)
        int alvoSucesso = 30;
        NoB resultado1 = arvore.buscar(alvoSucesso);
        if (resultado1 != null) {
            System.out.println("SUCESSO: Valor " + alvoSucesso + " encontrado na árvore!");
        } else {
            System.out.println("FALHA: Valor " + alvoSucesso + " não encontrado.");
        }

        // Testando um valor que NÃO EXISTE
        int alvoFalha = 99;
        NoB resultado2 = arvore.buscar(alvoFalha);
        if (resultado2 != null) {
            System.out.println("SUCESSO: Valor " + alvoFalha + " encontrado na árvore!");
        } else {
            System.out.println("FALHA: Valor " + alvoFalha + " não encontrado.");
        }
    }
}