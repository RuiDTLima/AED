package series.serie2.aplicaçao;

import javafx.util.Pair;


public class HashTable<K,V> {
    /**
     * declaração de um array de Pair que servirá como hashTable
     */
    private Pair<K,V> hash[];
    /**
     * variável que contém o número total de elementos no array
     */
    private int count;
    /**
     * tamanho inicial do array hash caso seja usado o construtor sem parametros
     */
    private static int defaultInitialCapacity = 100;

    /**
     *dado um elemento calcula o seu indice no array dado o seu hashCode.
     * @param e   //elemento que se quer introduzir no array
     * @param <E>
     * @return   //o indice do array aonde o elemento deve ser inserido
     */
    private <E> int idx(E e){
        int m = hash.length;
        int n = e.hashCode();
        return n % m;
    }

    /**
     * construtor sempre usado de um HashTable de um dado tamanho e que coloca o numero de elemento atuais no array a zero
     * @param initialCapacity   //tamanho inicial do HashTable
     */
    public HashTable(int initialCapacity){
        if (initialCapacity<=0)
            initialCapacity = defaultInitialCapacity;
        count = 0;
        hash = new Pair[initialCapacity];
    }

    /**
     * construtor sem parametros do HashTable que chama o construtor com parametros, com o tamanho definido no campo defaultInitialCapacity
     */
    public HashTable(){
        this(defaultInitialCapacity);
    }

    /**
     * adiciona o Pair<key,value> ao HashTable na posição calculada com recurso ao método idx(), passando o key como parametro
     * e aumenta o número total de elemntos presentes no HashTable
     * @param key   //primeiro elemento do par a adicionar ao HashTable, é usado tambem para calcular o indice do pair
     * @param value   //segundo elemento do par a adicionar ao HashTable
     */
    public void put(K key,V value){
        if (value == null){
            throw new NullPointerException();
        }
        int idx = idx(key);
        Pair<K,V> pair = new Pair<>(key,value);
        hash[idx] = pair;
        count ++;
    }

    /**
     * remove um dado elemento do HashTable, passando todos os elementos com um indice no HashTable superior ao do elemento
     * diminuindo o indice em que se encontram por um valor
     * @param elem   // elemento a ser removido
     * @param <E>
     * @return   // true caso a remoção tenha sido bem sucedida, caso o elemento nao exista no HashTable retorna false
     */
    public <E> boolean remove(E elem){
        if (elem == null) return false;
        int idx = idx(elem);
        for (;idx < count; idx++){
            hash[idx] = hash[idx+1];
        }
        return true;
    }

    /**
     * procura um dado elemento identificado por key, calculando o seu indice atraves do key
     * @param key   //e lemento identificador do par, usado para descobrir o indice do HashTable em que se encontra
     * @return   // retorna o elemento identificado por key
     */
    public Pair<K, V> search(K key){
        int idx = idx(key);
        return hash[idx];
    }
}