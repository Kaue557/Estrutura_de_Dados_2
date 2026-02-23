public class Arvore<T extends Comparable>{

    private Elemento<T> raiz;

    public Arvore(){
        this.raiz = null;
    }

    public void adiciona(T valor){
        Elemento<T> novoElemento = new Elemento<T>(valor);
        if(this.raiz == null){
            this.raiz = novoElemento;
        }else{
            Elemento<T> atual = this.raiz;
            while(true){
                if(novoElemento.getValor().compareTo(atual.getValor()) < 0){
                    if(atual.getEsquerda() != null){
                        atual = atual.getEsquerda();
                    }else{
                        atual.setEsquerda(novoElemento);
                        System.out.println("folha: " + novoElemento.getValor() + " a esquerda de " + atual.getValor());
                        break; // se adicionou, para
                    }
                }else{ // se for maior ou igual, vai pra direita
                    if(atual.getDireita() != null){
                        atual = atual.getDireita();
                    }else{
                        atual.setDireita(novoElemento);
                        System.out.println("folha: " + novoElemento.getValor() + " a direita de " + atual.getValor());
                        break; // se adicionou, para
                    }
                }
            }
        }
    }

    public Elemento<T> getRaiz(){
        return raiz;
    }

    public void emOrdem(Elemento<T> atual){ // esq - cima - dir
        if(atual != null){
            emOrdem(atual.getEsquerda());
            System.out.println(atual.getValor());
            emOrdem(atual.getDireita());
        }
    }

    public void preOrdem(Elemento<T> atual){ // cima - esq - dir
        if(atual != null){
            System.out.println(atual.getValor());
            preOrdem(atual.getEsquerda());
            preOrdem(atual.getDireita());
        }
    }

    public void posOrdem(Elemento<T> atual){ // esq - dir - cima
        if(atual != null){
            posOrdem(atual.getEsquerda());
            posOrdem(atual.getDireita());
            System.out.println(atual.getValor());
        }
    }
}