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
                        break; // se adicionou, para
                    }
                }else{ // se for maior ou igual, vai pra direita
                    if(atual.getDireita() != null){
                        atual = atual.getDireita();
                    }else{
                        atual.setDireita(novoElemento);
                        break; // se adicionou, para
                    }
                }
            }
        }
    }
}