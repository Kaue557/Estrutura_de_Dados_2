public class Main {

    void main(){
        Arvore<Integer> arvore = new Arvore<>();
        arvore.adiciona(10);
        arvore.adiciona(5);
        arvore.adiciona(7);
        arvore.adiciona(6);

        System.out.println("Em ordem:");
        arvore.emOrdem(arvore.getRaiz());

        System.out.println("\n\nPre-ordem:");
        arvore.preOrdem(arvore.getRaiz());

        System.out.println("\n\nPos-ordem:");
        arvore.posOrdem(arvore.getRaiz());

    }
}
