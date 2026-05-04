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
    public void imprimir() {
        if (this.raiz == null) {
            System.out.println("A árvore está vazia.");
        } else {
            System.out.println("--- Estrutura da Árvore B ---");
            imprimirAuxiliar(this.raiz, 0);
            System.out.println("-----------------------------");
        }
    }

    // Metodo recursivo que imprime os andares (níveis)
    private void imprimirAuxiliar(NoB no, int nivel) {
        if (no != null) {
            // Imprime os recuos (espaços) para cada nível, para dar efeito de escada
            for (int i = 0; i < nivel; i++) {
                System.out.print("    ");
            }

            // Imprime as chaves do nó atual
            System.out.print("Nível " + nivel + " [ ");
            for (int i = 0; i < no.n; i++) {
                System.out.print(no.chaves[i] + " ");
            }
            System.out.println("]");

            // Se não for folha, desce para imprimir os filhos
            if (!no.folha) {
                for (int i = 0; i <= no.n; i++) {
                    imprimirAuxiliar(no.filhos[i], nivel + 1);
                }
            }
        }
    }
}