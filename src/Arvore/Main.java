package Arvore;

public class Main {

    void main(){
        Arvore<Integer> arvore = new Arvore<>();
        arvore.adiciona(30); // raiz
        arvore.adiciona(15);
        arvore.adiciona(45);
        arvore.adiciona(51);
        arvore.adiciona(19);

        System.out.println("Em ordem:");
        arvore.emOrdem(arvore.getRaiz());

        System.out.println("\n\nPre-ordem:");
        arvore.preOrdem(arvore.getRaiz());

        System.out.println("\n\nPos-ordem:");
        arvore.posOrdem(arvore.getRaiz());

    }
}
