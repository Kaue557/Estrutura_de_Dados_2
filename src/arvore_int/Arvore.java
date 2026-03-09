package arvore_int;

public class Arvore {

    private No raiz;

    public Arvore() {
        this.raiz = null;
    }

    public void adiciona(int valor) {
        No novoNo = new No(valor);

        if (this.raiz == null) {
            this.raiz = novoNo;
        } else {
            No atual = this.raiz;

            while (true) {
                if (valor < atual.getValor()) {
                    if (atual.getEsquerda() != null) {
                        atual = atual.getEsquerda();
                    } else {
                        atual.setEsquerda(novoNo);
                        System.out.println("folha: " + valor + " a esquerda de " + atual.getValor());
                        break;
                    }
                } else { // maior ou igual vai para a direita
                    if (atual.getDireita() != null) {
                        atual = atual.getDireita();
                    } else {
                        atual.setDireita(novoNo);
                        System.out.println("folha: " + valor + " a direita de " + atual.getValor());
                        break;
                    }
                }
            }
        }
    }

    public No getRaiz() {
        return raiz;
    }

    public No busca(No p, int chave) {
        if (p != null) {
            if (chave < p.getValor()) {
                p = busca(p.getEsquerda(), chave);
            } else if (chave > p.getValor()) {
                p = busca(p.getDireita(), chave);
            }
        }
        return p;
    }

    // Percursos -------------------------------------

    public void emOrdem(No atual) { // esquerda - raiz - direita
        if (atual != null) {
            emOrdem(atual.getEsquerda());
            System.out.println(atual.getValor());
            emOrdem(atual.getDireita());
        }
    }

    public void preOrdem(No atual) { // raiz - esquerda - direita
        if (atual != null) {
            System.out.println(atual.getValor());
            preOrdem(atual.getEsquerda());
            preOrdem(atual.getDireita());
        }
    }

    public void posOrdem(No atual) { // esquerda - direita - raiz
        if (atual != null) {
            posOrdem(atual.getEsquerda());
            posOrdem(atual.getDireita());
            System.out.println(atual.getValor());
        }
    }
}