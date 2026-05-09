package arvore_B;

public class ArvoreB {
    private NoB raiz;
    private int ordem;

    public ArvoreB(int ordem) {
        this.ordem = ordem;
        this.raiz = null;
    }

    // inicia a busca a partir da raiz
    public NoB buscar(int x) {
        if (raiz == null) {
            return null;
        }
        return buscarNo(raiz, x);
    }

    // Metodo recursivo baseado no Algoritmo do pdf
    private NoB buscarNo(NoB no, int x) {
        int i = 0;

        // Procura a primeira chave maior ou igual a x dentro do nó
        while (i < no.n && x > no.chaves[i]) {
            i++;
        }

        // Se encontrou a chave neste nó, retorna o nó
        if (i < no.n && x == no.chaves[i]) {
            return no; // Chave encontrada
        }

        // Se chegamos em um nó folha e não encontramos, a chave não existe
        if (no.folha) {
            return null; // Falha na busca
        }

        // Caso contrário, desce para o filho apropriado (mudança de página)
        return buscarNo(no.filhos[i], x);
    }

    public void inserir(int valor){
        int i = 0; // tipo pAnda; pra achar a chave

        if(this.raiz == null){ // arvore vazia
            this.raiz = new NoB(this.ordem, true); // cria novo no
            this.raiz.chaves[0] = valor; // coloca o valor a inserir na raiz
            this.raiz.n = 1; // atualiza o tamanho do no (n chaves no nó)
        }
        else { // se a árvore já tiver pelo menos a raiz

            // 1. Verificar se a raiz está cheia.
            // Supondo que a capacidade máxima de chaves seja o tamanho do array.
            if (this.raiz.n == this.raiz.chaves.length) {

                // Cria a nova raiz, que nao eh folha, por isso o false
                NoB nova_raiz = new NoB(this.ordem, false);

                // Pendura a antiga raiz cheia como o primeiro filho (indice 0)
                NoB raiz_antiga = this.raiz;
                nova_raiz.filhos[0] = raiz_antiga;

                // Atualiza o ponteiro principal da árvore. Agora a nova_raiz eh a raiz de fato.
                this.raiz = nova_raiz;

                // Chama o metodo que vai cortar a raiz_antiga ao meio
                // e jogar a chave do meio para cima (para dentro da nova_raiz)
                dividirFilho(nova_raiz, 0, raiz_antiga);
            }

            // 2. Chamar um metodo auxiliar recursivo para descer na árvore buscando a folha certa.
            // Perceba que usamos `this.raiz`. Se ela estava cheia, agora aponta para a nova.
            // Se não estava, aponta para a mesma de sempre.
            inserirAuxiliar(this.raiz, valor);
        }

    }

    // O 'pai' (nova raiz) é o nó que vai receber a chave do meio
    // O 'i' é a posição (índice) onde o 'filhoCheio' está conectado no 'pai'.
    // O 'filhoCheio' é a página que estourou o limite e precisa ser quebrada.
    private void dividirFilho(NoB pai, int i, NoB filhoCheio) {

        // 1. O novo nó: Nasce o "irmão da direita" do filhoCheio.
        // Ele terá a mesma natureza do irmão (se o irmão é folha, ele também é).
        NoB novoFilho = new NoB(this.ordem, filhoCheio.folha);

        // Descobrimos onde é o exato meio do array de chaves.
        int meio = filhoCheio.n / 2;

        // Calculamos quantas chaves vão ser transferidas para o irmão da direita.
        // Basicamente, tudo que está DEPOIS do 'meio'.
        int chavesParaMover = filhoCheio.n - meio - 1;

        // 2. Transferência de Chaves: Copiando a metade da direita para o novoFilho
        for (int j = 0; j < chavesParaMover; j++) {
            novoFilho.chaves[j] = filhoCheio.chaves[meio + 1 + j];
        }

        // 3. Transferência de Filhos:
        // Se o filhoCheio NÃO for folha, ele tem filhos pendurados sob essas chaves.
        // Precisamos levar esses filhos para a casa nova também.
        if (!filhoCheio.folha) {
            for (int j = 0; j <= chavesParaMover; j++) {
                novoFilho.filhos[j] = filhoCheio.filhos[meio + 1 + j];
            }
        }

        // Atualizamos a quantidade de chaves de cada um.
        novoFilho.n = chavesParaMover; // O novo nó ganha as chaves movidas
        filhoCheio.n = meio;           // O nó antigo "encolhe" cortando a direita

        // 4. Preparando o PAI: Precisamos abrir espaço na página do pai para receber
        // a chave promovida e o novoFilho

        // Arredando os PONTEIROS (filhos) do pai uma casa para a direita
        for (int j = pai.n; j >= i + 1; j--) {
            pai.filhos[j + 1] = pai.filhos[j];
        }
        // Conectando o irmão novo no pai
        pai.filhos[i + 1] = novoFilho;

        // Arredando as CHAVES do pai uma casa para a direita
        for (int j = pai.n - 1; j >= i; j--) {
            pai.chaves[j + 1] = pai.chaves[j];
        }

        // 5. A Promoção: A chave do meio sobe para o espaço que abrimos no pai
        pai.chaves[i] = filhoCheio.chaves[meio];
        pai.n++; // O pai acaba de ganhar uma chave nova
    }


    private void inserirAuxiliar(NoB no, int valor) {
        // Começamos olhando da última chave para a primeira
        int i = no.n - 1;

        // CASO 1: Chegamos numa folha. É aqui que o valor entra de fato.
        if (no.folha) {
            // Empurra as chaves maiores para a direita para abrir espaço
            while (i >= 0 && valor < no.chaves[i]) {
                no.chaves[i + 1] = no.chaves[i];
                i--;
            }

            // Insere o novo valor e atualiza a contagem
            no.chaves[i + 1] = valor;
            no.n++;
        }
        // CASO 2: É um nó interno. Precisamos descobrir por qual filho descer.
        else {
            // Encontra a posição do filho correto
            while (i >= 0 && valor < no.chaves[i]) {
                i--;
            }
            i++; // O índice do filho é sempre +1 em relação à última chave menor que o valor

            // Verifica preventivamente se o filho para onde vamos descer está cheio
            if (no.filhos[i].n == no.chaves.length) {

                // Corta o filho ao meio. A chave central subirá para o 'no' atual.
                dividirFilho(no, i, no.filhos[i]);

                // Como uma chave subiu, o 'no' ganhou um novo filho.
                // Precisamos checar se o valor vai para o filho da esquerda (i) ou da direita (i+1)
                if (valor > no.chaves[i]) {
                    i++;
                }
            }

            // Agora temos certeza de que o filho[i] não está cheio.
            // Podemos descer a recursão com segurança.
            inserirAuxiliar(no.filhos[i], valor);
        }
    }

    // ------------ metodos para visualizacao da arvore ------------
    private void imprimirEstruturado(NoB no, String prefixo, boolean ultimo) {
        if (no == null) return;

        // Desenha o "galho"
        System.out.print(prefixo);
        System.out.print(ultimo ? "└── " : "├── ");

        // Tipo do nó
        System.out.print(no.folha ? "[Folha] " : "[Interno] ");

        // Chaves
        System.out.print("Chaves: [ ");
        for (int i = 0; i < no.n; i++) {
            System.out.print(no.chaves[i] + " ");
        }
        System.out.println("]");

        // Novo prefixo para os filhos
        String novoPrefixo = prefixo + (ultimo ? "    " : "│   ");

        // Imprime filhos com índice
        if (!no.folha) {
            for (int i = 0; i <= no.n; i++) {
                if (no.filhos[i] != null) {
                    System.out.print(novoPrefixo);
                    System.out.println("Filho " + i + ":");

                    imprimirEstruturado(
                            no.filhos[i],
                            novoPrefixo,
                            (i == no.n)
                    );
                }
            }
        }
    }

    // Metodo principal
    public void imprimir() {
        if (this.raiz == null) {
            System.out.println("Árvore vazia.");
            return;
        }

        System.out.println("\n===== ÁRVORE B =====");
        imprimirEstruturado(this.raiz, "", true);
        System.out.println("====================\n");
    }

    private int encontrarIndice(NoB no, int valor) {
        int i = 0;
        while (i < no.n && valor > no.chaves[i]) {
            i++;
        }
        return i;
    }

    private int getPredecessor(NoB no, int idx) {
        NoB atual = no.filhos[idx];

        // desce até a folha mais à direita
        while (!atual.folha) {
            atual = atual.filhos[atual.n];
        }

        return atual.chaves[atual.n - 1];
    }

    private int getSucessor(NoB no, int idx) {

        NoB atual = no.filhos[idx + 1];

        while (!atual.folha) {
            atual = atual.filhos[0];
        }

        return atual.chaves[0];
    }

    private int minimoChaves() {
        return (this.ordem - 1) / 2;
    }

    // EMPRESTAR DA ESQUERDA
    private void emprestarDaEsquerda(NoB pai, int idx) {

        NoB filho = pai.filhos[idx];
        NoB irmao = pai.filhos[idx - 1];

        // abre espaço no filho
        for (int i = filho.n - 1; i >= 0; i--) {
            filho.chaves[i + 1] = filho.chaves[i];
        }

        // se não for folha, desloca filhos
        if (!filho.folha) {
            for (int i = filho.n; i >= 0; i--) {
                filho.filhos[i + 1] = filho.filhos[i];
            }
        }

        // chave do pai desce
        filho.chaves[0] = pai.chaves[idx - 1];

        // último filho do irmão vem junto
        if (!irmao.folha) {
            filho.filhos[0] = irmao.filhos[irmao.n];
        }



        // maior chave do irmão sobe
        pai.chaves[idx - 1] = irmao.chaves[irmao.n - 1];

        filho.n++;
        irmao.n--;
    }

    // EMPRESTAR DA DIREITA
    private void emprestarDaDireita(NoB pai, int idx) {

        NoB filho = pai.filhos[idx];
        NoB irmao = pai.filhos[idx + 1];

        // chave do pai desce
        filho.chaves[filho.n] = pai.chaves[idx];

        // filho do irmão vem junto
        if (!filho.folha) {
            filho.filhos[filho.n + 1] = irmao.filhos[0];
        }

        // primeira chave do irmão sobe
        pai.chaves[idx] = irmao.chaves[0];

        // shift esquerda no irmão
        for (int i = 0; i < irmao.n - 1; i++) {
            irmao.chaves[i] = irmao.chaves[i + 1];
        }

        // shift filhos
        if (!irmao.folha) {
            for (int i = 0; i < irmao.n; i++) {
                irmao.filhos[i] = irmao.filhos[i + 1];
            }
        }

        filho.n++;
        irmao.n--;
    }

    private void fundir(NoB pai, int idx) {

        NoB esquerdo = pai.filhos[idx];
        NoB direito = pai.filhos[idx + 1];

        // chave do pai desce
        esquerdo.chaves[esquerdo.n] = pai.chaves[idx];

        // copia chaves do direito
        for (int i = 0; i < direito.n; i++) {
            esquerdo.chaves[esquerdo.n + 1 + i] = direito.chaves[i];
        }

        // copia filhos
        if (!esquerdo.folha) {
            for (int i = 0; i <= direito.n; i++) {
                esquerdo.filhos[esquerdo.n + 1 + i] = direito.filhos[i];
            }
        }

        esquerdo.n += direito.n + 1;

        // remove chave do pai
        for (int i = idx; i < pai.n - 1; i++) {
            pai.chaves[i] = pai.chaves[i + 1];
        }

        // remove ponteiro do irmão direito
        for (int i = idx + 1; i < pai.n; i++) {
            pai.filhos[i] = pai.filhos[i + 1];
        }

        pai.n--;
    }

    private void removerDeFolha(NoB no, int idx) {
        // desloca tudo para a esquerda
        for (int i = idx; i < no.n - 1; i++) {
            no.chaves[i] = no.chaves[i + 1];
        }
        // limpar último valor
        no.chaves[no.n - 1] = 0;

        no.n--;
    }

    private void removerInterno(NoB no, int idx) {

        int valor = no.chaves[idx];

        // pega predecessor
        int pred = getPredecessor(no, idx);

        // substitui no nó interno
        no.chaves[idx] = pred;

        // agora remove o predecessor na subárvore
        removerRec(no.filhos[idx], pred);
    }

    private void removerRec(NoB no, int valor) {

        int idx = encontrarIndice(no, valor);
        int minimo = minimoChaves();

        // --------------------------------------------------
        // CASO 1: chave encontrada neste nó
        // --------------------------------------------------

        if (idx < no.n && no.chaves[idx] == valor) {

            // ---------------- folha ----------------
            if (no.folha) {
                removerDeFolha(no, idx);
            }

            // ---------------- interno ----------------
            else {

                NoB filhoEsq = no.filhos[idx];
                NoB filhoDir = no.filhos[idx + 1];

                // predecessor
                if (filhoEsq.n > minimo) {

                    int pred = getPredecessor(no, idx);

                    no.chaves[idx] = pred;

                    removerRec(filhoEsq, pred);
                }

                // sucessor
                else if (filhoDir.n > minimo) {

                    int succ = getSucessor(no, idx);

                    no.chaves[idx] = succ;

                    removerRec(filhoDir, succ);
                }

                // merge
                else {

                    fundir(no, idx);

                    removerRec(filhoEsq, valor);
                }
            }
        }

        // --------------------------------------------------
        // CASO 2: chave NÃO está neste nó
        // --------------------------------------------------

        else {

            // chegou em folha -> não existe
            if (no.folha) {
                return;
            }

            boolean ultimoFilho = (idx == no.n);

            NoB filho = no.filhos[idx];

            // garante mínimo antes de descer
            if (filho.n == minimo) {

                // tenta esquerda
                if (idx > 0 && no.filhos[idx - 1].n > minimo) {

                    emprestarDaEsquerda(no, idx);
                }

                // tenta direita
                else if (idx < no.n && no.filhos[idx + 1].n > minimo) {

                    emprestarDaDireita(no, idx);
                }

                // merge
                else {

                    if (idx < no.n) {
                        fundir(no, idx);
                    } else {
                        fundir(no, idx - 1);
                        idx--;
                    }
                }
            }

            // após merge pode mudar
            if (ultimoFilho && idx > no.n) {
                removerRec(no.filhos[idx - 1], valor);
            } else {
                removerRec(no.filhos[idx], valor);
            }
        }
    }

    public String remover(int valor) {

        if (this.raiz == null) {
            return "Árvore vazia!";
        }

        removerRec(this.raiz, valor);

        // raiz vazia
        if (this.raiz.n == 0) {

            if (this.raiz.folha) {
                this.raiz = null;
            } else {
                this.raiz = this.raiz.filhos[0];
            }
        }

        return "Remoção executada.";
    }
}