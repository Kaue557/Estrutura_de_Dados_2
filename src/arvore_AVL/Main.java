package arvore_AVL;

public class Main {
    public static void main(String[] args){
        Arvore arvore = new Arvore();

        // exemplo forçando desbalanceio da arvore*
        arvore.inserir(50);
        arvore.inserir(40);
        arvore.inserir(60);
        arvore.inserir(20);
        arvore.inserir(30);


        System.out.println("Em ordem:");
        arvore.emOrdem(arvore.getRaiz());

        System.out.println("\nPre-ordem:");
        arvore.preOrdem(arvore.getRaiz());

        System.out.println("\nPos-ordem:");
        arvore.posOrdem(arvore.getRaiz());

        // remoçao
        arvore.remover(20);
        System.out.println("Em ordem:");
        arvore.emOrdem(arvore.getRaiz());
    }
}
