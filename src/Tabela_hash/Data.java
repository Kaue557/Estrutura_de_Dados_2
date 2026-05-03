package Tabela_hash;

public class Data {
    int key;
    int value;
    Data next;

    public Data(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}