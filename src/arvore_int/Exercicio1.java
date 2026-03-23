package arvore_int;

public class Exercicio1{
    // 1. Escreva uma funcao iterativa que devolva o menor valor de uma arvore binaria de busca
    // E SE FOR UMA ARVORE COM ELEMENTOS SO A DIREITA??
    int devolveMenor(No atual){
        int menor = atual.getValor();

        while(atual.getEsquerda() != null){
            menor = atual.getEsquerda().getValor();
            atual = atual.getEsquerda();
        }
        return menor;
    }

    //2. Escreva uma funcao que exclui todos os nós de uma arvore binaria de busca com ID par.
    /*
    public int contaNo(No atual){
        if(atual == null) return 0;

        else{
            int cont = 0;
            return cont = 1 + contaNo(atual.getEsquerda()) + contaNo(atual.getDireita()); // atual +  lado esq + lado dir
        }
    }
    */
    void removePar(No atual){

    }

    public void main(String[] args){

        Arvore arvore = new Arvore();

        arvore.adiciona(30); // raiz
        arvore.adiciona(15);
        arvore.adiciona(45);
        arvore.adiciona(51);
        arvore.adiciona(19);
        arvore.adiciona(10);
        arvore.adiciona(5);


        int menor_valor = devolveMenor(arvore.getRaiz());
        System.out.println("menor valor = " + menor_valor);

    }
}
