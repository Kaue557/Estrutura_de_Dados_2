import java.util.Scanner;

public class Iguais {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.print("x: ");
        int x = sc.nextInt();

        System.out.println("valores do vetor: ");
        int []A = new int[5];

        for(int i = 0; i < A.length; i++){
            A[i] = sc.nextInt();
        }

        int cont = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i] == x){
                cont++;
            }
        }

        System.out.println(cont + " sao iguais a " + x);
    }
}
