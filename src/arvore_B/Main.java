package arvore_B;

public class Main {
    public static void main(String[] args) {

        ArvoreB arvore = new ArvoreB(4); // coerente com seu NoB (m-1 chaves)

        int[] sequenciaPDF = {20, 40, 10, 30, 15, 50, 60, 70, 80};

        System.out.println("=== INSERÇÕES ===");

        for (int valor : sequenciaPDF) {
            System.out.println("\n>>> Inserindo: " + valor);
            arvore.inserir(valor);
            arvore.imprimir();
        }

        System.out.println("=== BUSCA ===");

        int[] testes = {30, 99};

        for (int alvo : testes) {
            NoB resultado = arvore.buscar(alvo);
            if (resultado != null) {
                System.out.println("Valor " + alvo + " encontrado.");
            } else {
                System.out.println("Valor " + alvo + " NÃO encontrado.");
            }
        }
    }
}