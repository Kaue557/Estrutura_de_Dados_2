package arvore_Bmais;

public class No {
    int ordem;           // Ordem da árvore (d)
    int[] chaves;        // Array que armazena as chaves do nó
    No[] filhos;         // Array de ponteiros para os filhos (usado APENAS se for página interna)
    No proximo;          // Ponteiro para a próxima página (usado APENAS se for página folha)
    int numChaves;       // Quantidade de chaves atualmente armazenadas no nó
    boolean ehFolha;     // Flag que define se a estrutura atua como Folha (true) ou Interna (false)

    /**
     * Construtor da classe No
     * * @param ordem A ordem 'd' da árvore B+ (teremos entre d e 2d chaves)
     * @param ehFolha Define se o nó será instanciado como folha ou interno
     */
    public No(int ordem, boolean ehFolha) {
        this.ordem = ordem;
        this.ehFolha = ehFolha;
        this.numChaves = 0;

        // Alocamos 2*d chaves. Adicionamos +1 de espaço extra para
        // facilitar a lógica de "estouro" antes de fazer a divisão (split) da página.
        this.chaves = new int[2 * ordem + 1];

        if (this.ehFolha) {
            // Se for folha: inicializa o ponteiro para a próxima folha como nulo.
            this.proximo = null;
            this.filhos = null; // Folhas não possuem filhos
        } else {
            // Se for página interna: m chaves terão m+1 filhos.
            // Novamente, alocamos +1 espaço extra para facilitar o split.
            this.filhos = new No[2 * ordem + 2];
            this.proximo = null; // Páginas internas não apontam para vizinhos horizontais
        }
    }
}
