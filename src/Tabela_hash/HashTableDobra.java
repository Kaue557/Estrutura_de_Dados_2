package Tabela_hash;

public class HashTable {

    // O tamanho foi alterado para 100, já que o método da dobra 
    // implementado gera endereços de 2 dígitos (00 a 99)
    private static final int SIZE = 100; 
    private Data[] table;

    public HashTable() {
        table = new Data[SIZE];
    }

    // NOVA FUNÇÃO HASH: Método da Dobra
    private int hash(int key) {
        // Garante que trabalharemos com uma chave de até 6 dígitos absolutos
        key = Math.abs(key) % 1000000;
        
        // Extrai os 6 dígitos separadamente (c1 a c6)
        int c1 = key / 100000;
        int c2 = (key / 10000) % 10;
        int c3 = (key / 1000) % 10;
        int c4 = (key / 100) % 10;
        int c5 = (key / 10) % 10;
        int c6 = key % 10;
        
        // PRIMEIRA DOBRA: o par (c1, c2) é dobrado sobre (c3, c4)
        // O c1 alinha com o c4, e o c2 alinha com o c3.
        // A soma descarta o "vai-um" (usando % 10)
        int r1_tens = (c2 + c3) % 10;
        int r1_ones = (c1 + c4) % 10;
        
        // SEGUNDA DOBRA: o resultado anterior (r1_tens, r1_ones) é dobrado sobre (c5, c6)
        // r1_tens alinha com c6, e r1_ones alinha com c5.
        int final_tens = (r1_ones + c5) % 10;
        int final_ones = (r1_tens + c6) % 10;
        
        // Monta o endereço final de 2 dígitos
        int address = (final_tens * 10) + final_ones;
        
        // Garante que o endereço caiba no array (redundante se SIZE = 100, mas seguro)
        return address % SIZE;
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