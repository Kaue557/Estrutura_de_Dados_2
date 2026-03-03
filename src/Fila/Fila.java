package Fila;

public class Fila<T> {

    private int inicio;
    private int fim;
    private int capacidade;
    private int tamanho;
    private T[] itens;

    @SuppressWarnings("unchecked")
    public Fila(int capacidade){
        this.capacidade = capacidade;
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;

        // Java não permite new T[]
        this.itens = (T[]) new Object[capacidade];
    }

    public void inserir(T elemento) {
        if (filaCheia()) {
            throw new RuntimeException("Fila cheia");
        }
        itens[fim] = elemento;
        fim = (fim + 1) % capacidade;
        tamanho++;
    }

    public T remover() {
        if (filaVazia()) {
            throw new RuntimeException("Fila vazia");
        }
        T elemento = itens[inicio];
        inicio = (inicio + 1) % capacidade;
        tamanho--;
        return elemento;
    }

    public boolean filaCheia() {
        return tamanho == capacidade;
    }

    public boolean filaVazia() {
        return tamanho == 0;
    }

    public int tamanho() {
        return tamanho;
    }

    public T primeiro() {
        if (filaVazia()) {
            throw new RuntimeException("Fila vazia");
        }
        return itens[inicio];
    }

    public void exibirElementos(){
        if (filaVazia()){
            System.out.println("Fila vazia!");
            return;
        }

        System.out.print("Fila: ");
        int idx = inicio;

        for (int i = 0; i < tamanho; i++) {
            System.out.print(itens[idx] + " ");
            idx = (idx + 1) % capacidade;
        }

        System.out.println();
    }
}