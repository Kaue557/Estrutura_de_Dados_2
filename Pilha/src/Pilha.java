package src;
public class Pilha{
    private int topo;
    private int []itens;
    private int capacidade;

public boolean pilhaVazia(){
    return topo == -1;
}
public boolean pilhaCheia(){
    return topo == capacidade - 1;
}

public Pilha(int capacidade){
    this.capacidade = capacidade;
}

public void empilhar(int valor){
    if(this.pilhaCheia()){
        throw new RuntimeException("Pilha cheia");
    }
    itens[++topo] = valor;
}

public int desempilhar() {
    if(this.pilhaVazia()){
        throw new RuntimeException("Pilha vazia");
    }
    return itens[topo--];
}

public int mostrarTopo(){
    if(this.pilhaVazia()){
        throw new RuntimeException("Pilha vazia");
    }
    return itens[topo]; 
}


}