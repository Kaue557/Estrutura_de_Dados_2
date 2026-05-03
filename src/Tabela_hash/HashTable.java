package Tabela_hash;

// ***************** tabela hash com tratamento de colisão por encadeamento (lista ligada).
class Data {
    int key;
    int value;
    Data next; // ponteiro para o próximo elemento (lista ligada)

    public Data(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

public class HashTable {

    private static final int SIZE = 10;
    private Data[] hashTable;

    public HashTable() {
        hashTable = new Data[SIZE];
    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void insertOnHash(int key, int value) {
        int index = hash(key);

        Data item = new Data(key, value);

        // Insere no início da lista (encadeamento)
        item.next = hashTable[index];
        hashTable[index] = item;
    }

    public Data search(int key) {
        int index = hash(key);

        Data current = hashTable[index];

        while (current != null) {
            if (current.key == key) {
                return current;
            }
            current = current.next;
        }

        return null;
    }
}
