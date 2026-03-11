package arvore_int;

// ****************************** LABORATÓRIO 2 ******************************

public class Lab2 {

    public static void main(String[] args) {

        Arvore arvore = new Arvore();

        arvore.adiciona(30); // raiz
        arvore.adiciona(15);
        arvore.adiciona(50);
        arvore.adiciona(10);
        arvore.adiciona(20);
        arvore.adiciona(40);
        arvore.adiciona(60);
        System.out.println();

        /*
        PERCURSOS:

        System.out.println("Em ordem:");
        arvore.emOrdem(arvore.getRaiz());

        System.out.println("\nPre-ordem:");
        arvore.preOrdem(arvore.getRaiz());

        System.out.println("\nPos-ordem:");
        arvore.posOrdem(arvore.getRaiz());
        */


        // EXERCICIO 1
        System.out.println("Em ordem:");
        arvore.emOrdem(arvore.getRaiz());
        System.out.println();

        System.out.println("Altura da arvore: " + arvore.altura());


        // EXERCICIO 2
        Arvore arvore2 = new Arvore(); // A insercao já segue a regra BST

        arvore2.adiciona(10);
        arvore2.adiciona(5);
        arvore2.adiciona(15);
        arvore2.adiciona(2);
        arvore2.adiciona(7);
        arvore2.adiciona(20);

        System.out.println("Pre-ordem: ");
        arvore2.preOrdem(arvore.getRaiz());
        System.out.println();
        System.out.println("Em ordem: ");
        arvore2.emOrdem(arvore.getRaiz());
        System.out.println();
        System.out.println("Pos-ordem: ");
        arvore2.posOrdem(arvore.getRaiz());
        System.out.println();

        System.out.println("Em nivel:");
        arvore2.emNivel();

    }
}