package arvore_Bmais;

public class ArvoreBMais {
    private No raiz;
    private int ordem;

    public ArvoreBMais(int ordem) {
        this.ordem = ordem;
        this.raiz = new No(ordem, true); // A árvore nasce com a raiz sendo uma folha vazia
    }

    /**
     * Classe interna auxiliar.
     * Ela "transporta" a chave promovida e o novo nó gerado durante um split de volta para o nó pai.
     */
    private class RetornoSplit {
        int chavePromovida;
        No novoNoDir;

        public RetornoSplit(int chave, No no) {
            this.chavePromovida = chave;
            this.novoNoDir = no;
        }
    }

    // ==========================================
    // METODO PÚBLICO DE INSERÇÃO
    // ==========================================
    public void inserir(int chave) {
        RetornoSplit split = inserirRecursivo(raiz, chave);

        // Se a raiz original sofreu split, a árvore cresce em altura
        if (split != null) {
            No novaRaiz = new No(ordem, false); // Nova raiz sempre é nó interno
            novaRaiz.chaves[0] = split.chavePromovida;
            novaRaiz.filhos[0] = raiz;          // Filho à esquerda é a raiz antiga
            novaRaiz.filhos[1] = split.novoNoDir; // Filho à direita é o novo nó do split
            novaRaiz.numChaves = 1;

            raiz = novaRaiz; // Atualiza o ponteiro principal
        }
    }

    // ==========================================
    // LÓGICA RECURSIVA DE INSERÇÃO
    // ==========================================
    private RetornoSplit inserirRecursivo(No atual, int chave) {
        int i = atual.numChaves - 1;

        if (atual.ehFolha) {
            // 1. É FOLHA: Encontra a posição e insere a chave ordenadamente
            while (i >= 0 && atual.chaves[i] > chave) {
                atual.chaves[i + 1] = atual.chaves[i];
                i--;
            }
            atual.chaves[i + 1] = chave;
            atual.numChaves++;

            // Verifica se o nó folha estourou o limite máximo de 2d chaves
            if (atual.numChaves > 2 * ordem) {
                return dividirFolha(atual);
            }
            return null; // Não houve split, apenas retorna null

        } else {
            // 2. É PÁGINA INTERNA: Encontra o ponteiro do filho correto para descer
            while (i >= 0 && atual.chaves[i] > chave) {
                i--;
            }
            i++; // O índice i agora aponta para o ponteiro do filho correto

            // Desce recursivamente
            RetornoSplit splitFilho = inserirRecursivo(atual.filhos[i], chave);

            // Se o filho não estourou, o trabalho acabou
            if (splitFilho == null) return null;

            // 3. RETORNO DO FILHO: Se o filho dividiu, precisamos acomodar a chave promovida neste nó
            int j = atual.numChaves - 1;
            while (j >= i) {
                atual.chaves[j + 1] = atual.chaves[j];
                atual.filhos[j + 2] = atual.filhos[j + 1];
                j--;
            }
            atual.chaves[i] = splitFilho.chavePromovida;
            atual.filhos[i + 1] = splitFilho.novoNoDir;
            atual.numChaves++;

            // Verifica se este nó interno também estourou o limite máximo de 2d chaves
            if (atual.numChaves > 2 * ordem) {
                return dividirInterno(atual);
            }
            return null;
        }
    }

    // ==========================================
    // METODOS DE DIVISÃO (SPLIT)
    // ==========================================

    /**
     * Divide uma página FOLHA.
     * a chave deve ser mantida na folha e copiada para cima
     */
    private RetornoSplit dividirFolha(No folha) {
        No novaFolha = new No(ordem, true);
        int meio = ordem;

        // Move metade das chaves para a nova folha
        int j = 0;
        for (int i = meio; i < folha.numChaves; i++) {
            novaFolha.chaves[j++] = folha.chaves[i];
        }
        novaFolha.numChaves = folha.numChaves - meio;
        folha.numChaves = meio; // A folha original perde metade dos elementos

        // MANTÉM A LISTA ENCADEADA (Slide 4 e 7)
        novaFolha.proximo = folha.proximo;
        folha.proximo = novaFolha;

        // Cópia para cima: Retorna a primeira chave da nova folha, ela não é removida da folha!
        return new RetornoSplit(novaFolha.chaves[0], novaFolha);
    }

    /**
     * Divide uma página INTERNA (Índice).
     * A chave central não fica retida, ela é jogada inteiramente para o pai.
     */
    private RetornoSplit dividirInterno(No interno) {
        No novoInterno = new No(ordem, false);
        int meio = ordem;

        // A chave do meio SOBE (não é copiada, ela é movida)
        int chavePromovida = interno.chaves[meio];

        int j = 0;
        // Move as chaves e filhos à direita do meio para o novo nó interno
        for (int i = meio + 1; i < interno.numChaves; i++) {
            novoInterno.chaves[j] = interno.chaves[i];
            j++;
        }
        j = 0;

        for (int i = meio + 1; i <= interno.numChaves; i++) {
            novoInterno.filhos[j] = interno.filhos[i];
            j++;
        }
        // Move o último ponteiro filho
        novoInterno.filhos[j] = interno.filhos[interno.numChaves];
        novoInterno.numChaves = interno.numChaves - meio - 1;

        interno.numChaves = meio;

        return new RetornoSplit(chavePromovida, novoInterno);
    }
}