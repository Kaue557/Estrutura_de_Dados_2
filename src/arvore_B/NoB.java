package arvore_B;

public class NoB {
    int n;               // Número atual de chaves no nó
    int[] chaves;        // Array de chaves
    NoB[] filhos;        // Array de ponteiros para os nós filhos
    boolean folha;       // Verdadeiro se o nó for uma folha

    // m = ordem da Árvore B
    public NoB(int m, boolean folha) {
        this.n = 0;
        this.folha = folha;
        // Um nó pode ter no máximo m-1 chaves e m filhos
        this.chaves = new int[m - 1];
        this.filhos = new NoB[m];
    }
}