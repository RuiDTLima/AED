package series.serie2.aplicaçao;


public class Pair <K,E>{
    /**
     * campo que contém o elemento identificador do par
     */
    private K key;
    /**
     * campo que contém o elemento com o valor do par
     */
    private E value;

    /**
     * construtor de um par de elementos
     * @param key  //elemento que identifica o par
     * @param value  //elemento que contem o valor do par
     */
    public Pair(K key,E value){
        this.key = key;
        this.value = value;
    }

    /**
     *
     * @return   //o elemento que contém o valor do par
     */
    public E getValue() {
        return value;
    }

    /**
     *
     * @return  //o elemento identificado de um par
     */
    public K getKey() {
        return key;
    }

    /**
     * altera o elemento identificado de um par
     * @param key
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * altera o elemento com valor do par
     * @param value
     */
    public void setValue(E value) {
        this.value = value;
    }
}
