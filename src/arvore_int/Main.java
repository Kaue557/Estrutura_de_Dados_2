package arvore_int;

public class Main {

    public static void main(String[] args) {

        Arvore arvore = new Arvore();

        arvore.adiciona(30); // raiz
        arvore.adiciona(15);
        arvore.adiciona(45);
        arvore.adiciona(51);
        arvore.adiciona(19);
        System.out.println();

        /*
        System.out.println("Em ordem:");
        arvore.emOrdem(arvore.getRaiz());

        System.out.println("\nPre-ordem:");
        arvore.preOrdem(arvore.getRaiz());

        System.out.println("\nPos-ordem:");
        arvore.posOrdem(arvore.getRaiz());
        */

        // EXERCICIO 1
        System.out.println("EXERCICIO 1");
        int total_nos = arvore.contaNo(arvore.getRaiz());
        System.out.println("total de nos: " + total_nos);

        int total_folhas = arvore.contaFolha(arvore.getRaiz());
        System.out.println("total de folhas: " + total_folhas);

        int total_internos = arvore.contaInterno(arvore.getRaiz());
        System.out.println("total de Nos internos: " + total_internos);
        System.out.println();

        // EXERCICIO 2
        System.out.println("EXERCICIO 2");
        No raizClone = arvore.cloneArvore(arvore.getRaiz());

        System.out.println("Arvore original:");
        arvore.emOrdem(arvore.getRaiz());

        System.out.println();

        System.out.println("Arvore clone:");
        arvore.emOrdem(raizClone);
        System.out.println();

        // EXERCICIO 3
        System.out.println("EXERCICIO 3");
        arvore.inverte(arvore.getRaiz());
        arvore.emOrdem(arvore.getRaiz());
        System.out.println();

        // EXERCICIO 4
        System.out.println("EXERCICIO 4");
        if(arvore.ehBST()){
            System.out.println("A arvore é uma BST");
        }else{
            System.out.println("A arvore NÃO é uma BST");
        }
        System.out.println();

        // EXERCICIO 5
        System.out.println("EXERCICIO 5");
        arvore.removeFolhas(arvore.getRaiz());
        arvore.emOrdem(arvore.getRaiz());
    }
}