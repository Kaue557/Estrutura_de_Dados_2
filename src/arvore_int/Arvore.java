package arvore_int;
import java.util.LinkedList;
import java.util.Queue;

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

    /*
    -------------------------------------------------------------
    1. Escreva uma funçao recursiva para contar:
        (a) o número total de nós;
        (b) o número de folhas;
        (c) o número de nós internos (com pelo menos um filho).
    */
    public int contaNo(No atual){
        if(atual == null) return 0;

        else{
            int cont = 0;
            return cont = 1 + contaNo(atual.getEsquerda()) + contaNo(atual.getDireita()); // atual +  lado esq + lado dir
        }
    }

    public int contaFolha(No atual){
        if(atual == null) return 0; // se no for nulo

        else if(atual.getEsquerda() == null && atual.getDireita() == null){ // se no for folha
            return 1;
        }
        else{
            return contaFolha(atual.getEsquerda()) + contaFolha(atual.getDireita()); // se no for interno
        }
    }

    public int contaInterno(No atual){
        if(atual == null) return 0; // se no for nulo

        else if(atual.getEsquerda() != null || atual.getDireita() != null){ // se no for interno
            return 1 + contaInterno(atual.getEsquerda()) + contaInterno(atual.getDireita());
        }
        else{
            return contaInterno(atual.getEsquerda()) + contaInterno(atual.getDireita()); // se no for folha
        }
    }
    /*
    -------------------------------------------------------------
    2. Escreva um metodo que devolve o clone da arvore
    */

    public No cloneArvore(No atual){
        if(atual == null){
            return null;
        }

        No novo = new No(atual.getValor());

        novo.setEsquerda(cloneArvore(atual.getEsquerda()));
        novo.setDireita(cloneArvore(atual.getDireita()));

        return novo;
    }

    /*
    -------------------------------------------------------------
    3. Escreva um metodo que devolve o espelho da árvore
    (o que está a esquerda da árvore original, estara a direita no espelho e vice-versa
    */

    public void inverte(No atual){
        if(atual == null) return;

        No temp = atual.getEsquerda();
        atual.setEsquerda(atual.getDireita());
        atual.setDireita(temp);

        inverte(atual.getEsquerda());
        inverte(atual.getDireita());
    }

    /*
    -------------------------------------------------------------
    4. Dada uma arvore binaria qualquer, implemente uma funçao que determine se ela é uma BST valida.
    */

    public boolean ehBST(No atual, int min, int max){
        if(atual == null){
            return true;
        }

        if(atual.getValor() <= min || atual.getValor() >= max){
            return false;
        }

        return ehBST(atual.getEsquerda(), min, atual.getValor()) &&
                ehBST(atual.getDireita(), atual.getValor(), max);
    }

    public boolean ehBST(){
        return ehBST(this.raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    /*
    -------------------------------------------------------------
    5. Escreva um metodo que remova todas as folhas da arvore.
    */
    public No removeFolhas(No atual){

        if(atual == null){
            return null;
        }

        // se for folha
        if(atual.getEsquerda() == null && atual.getDireita() == null){
            if(atual == raiz){
                raiz = null;
            }
            return null;
        }

        atual.setEsquerda(removeFolhas(atual.getEsquerda()));
        atual.setDireita(removeFolhas(atual.getDireita()));
        return atual;
    }

    /*
    -------------------------------------------------------------
    (c) Qual é a altura da árvore? - LAB 2
    */
    public int altura(No atual){
        if(atual == null){
            return 0;
        }

        int alturaEsq = altura(atual.getEsquerda());
        int alturaDir = altura(atual.getDireita());

        return 1 + Math.max(alturaEsq, alturaDir);
    }

    public int altura(){
        return altura(this.raiz);
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

    /*
    -------------------------------------------------------------
    EX2 - em nivel - LAB 2
    */
    public void emNivel(){

        if(raiz == null) return;

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while(!fila.isEmpty()){

            No atual = fila.poll();
            System.out.println(atual.getValor());

            if(atual.getEsquerda() != null){
                fila.add(atual.getEsquerda());
            }

            if(atual.getDireita() != null){
                fila.add(atual.getDireita());
            }
        }
    }
}