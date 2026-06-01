package Tabela_hash;

public class HashTable {

    private static final int SIZE = 11; // numero primo melhora distribuicao (m = SIZE)
    private Data[] table;

    public HashTable() {
        table = new Data[SIZE];
    }

    private int hash(int key) {
        return key % SIZE; // metodo da divisão
    }

    public void insert(int key, int value) {
        int index = hash(key);
        Data current = table[index];

        // Verifica duplicidade
        while (current != null) {
            if (current.key == key) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Data newNode = new Data(key, value);
        newNode.next = table[index];
        table[index] = newNode;
    }

    public Integer search(int key) {
        int index = hash(key);
        Data current = table[index];

        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }

        return null;
    }

    public boolean remove(int key) {
        int index = hash(key);
        Data current = table[index];
        Data prev = null;

        while (current != null) {
            if (current.key == key) {

                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }

                return true;
            }

            prev = current;
            current = current.next;
        }

        return false;
    }
}