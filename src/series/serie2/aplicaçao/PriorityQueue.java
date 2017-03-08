package series.serie2.aplicaçao;


import java.util.ArrayList;


public class PriorityQueue<E,P extends Comparable<P>> {

    /**
     * campo para um KeyExtractor
     */
    private KeyExtractor<E> extract;

    /**
     * campo para guardar um heap num ArrayList
     */
    private ArrayList<Pair<E,P>> heap;

    /**
     * campo para guardar a HashTable
     */
    private HashTable<Integer,Pair<Customer,Integer>> hash; //first integer is the ticket, the second is the index in heap

    /**
     * indica o tamanho da PriorityQueue
     */
    private int size;

    /**
     * quantidade de elementos na Priority Queue
     */
    private int idx = 0;



    /**
     * construtor de uma PriorityQueue com um dado tamanho e com uma dada forma de extrair a chave
     * @param capacity   //tamanho da PriorityQueue
     * @param extract   //forma de extrair a chave
     */
    public PriorityQueue(int capacity, KeyExtractor<E> extract){
        heap = new ArrayList<>(capacity);
        hash = new HashTable<>(capacity);
        size = capacity;
        this.extract = extract;
    }

    //método auxiliar
    /**
     * verifica se um elemento é menor que o outro
     * @param v   //primeiro elemento
     * @param w   //segundo elemento
     * @param <P>
     * @return   //-1 se v é menor que w, 0 se são iguais e 1 se v é maior que w
     */
    public static <P extends Comparable<P>> boolean less(P v, P w) {
        return v.compareTo(w) < 0;
    }


    //método auxiliar
    /**
     * dado um array troca os elementos no indice i e j
     * @param array   //array onde estão guardados os elementos a serem trocados
     * @param i  // indice do primeiro elemento a ser trocado
     * @param j  // indice do segundo elemento a ser trocado
     * @param <E>
     * @param <P>
     */
    static <E,P> void exch(ArrayList<Pair<E,P>> array, int i, int j) {
        Pair<E,P> temp = array.get(i);
        array.set(i,array.get(j));
        array.set(j,temp);
    }


    /**
     * reordena o heap através do indice k
     * @param k
     * @return  // retorna o novo indice
     */
    private int increaseMinKey(int k) {
        int idxParent;
        while (k > 0) {
            // Get parent
            idxParent = (k-1) / 2;

            Pair<E, P> parentPair = heap.get(idxParent);
            Pair<E, P> kPair = heap.get(k);
            // Verify if parent has less priority than current key index k
            if(less(parentPair.getValue(), kPair.getValue()) ||
                parentPair.getValue().equals(kPair.getValue()) &&
                less(extract.getKey(parentPair.getKey()),extract.getKey(kPair.getKey()))
            )
                break;// The heap is in order

            // and exchange parent with current key index and update it on HashTable
            exch(heap, k, idxParent);
            updateHeapIdxInHashTable(k);
            updateHeapIdxInHashTable(idxParent);
            // Set current key index to parent index
            k = idxParent;
        }
        return k;
    }


    /**
     * reordena o heap a apartir do indice k e para o tamanho hSize
     * @param k  //indice do elemento fora do lugar
     * @param hSize  //tamanho do array a reordenar em heap
     * @return  //o novo indice do elemento inicialmente fora do sitio
     */
    private int minHeapify(int k, int hSize) {
        // Get index of left descendant
        int idxLeftChild = 2*k + 1;
        int idxRightChild;
        // If there exists a left descendant
        while (idxLeftChild < hSize) {
            // Get index of right descendant
            idxRightChild = idxLeftChild + 1;
            // Get the index with more priority of the left and right descendants
            if (idxRightChild < hSize &&
                (less(heap.get(idxRightChild).getValue(),heap.get(idxLeftChild).getValue()) ||
                heap.get(idxRightChild).getValue().equals(heap.get(idxLeftChild).getValue()) &&
                less(extract.getKey(heap.get(idxRightChild).getKey()),extract.getKey(heap.get(idxLeftChild).getKey()))
                )
            )
                idxLeftChild = idxRightChild;
            
            Pair<E, P> kPair = heap.get(k);
            Pair<E, P> childPair = heap.get(idxLeftChild);
            // If the descendant has not more priority than the parent, stop
            if(less(kPair.getValue(), childPair.getValue()) ||
                kPair.getValue().equals(childPair.getValue()) &&
                less(extract.getKey(kPair.getKey()),extract.getKey(childPair.getKey()))
            )
                break;
            // Else, exchange the largest descendant by its parent and update it on HashTable
            exch(heap, k, idxLeftChild);
            updateHeapIdxInHashTable(k);
            updateHeapIdxInHashTable(idxLeftChild);
            // Set the next parent to be the left descendant
            k = idxLeftChild;
            // Update left descendant
            idxLeftChild = 2*k + 1;
        }
        return k;
    }

    /**
     * altera o indice de um dado elemento na HashTable
     * @param idx  //novo indice para a HashTable
     */
    private void updateHeapIdxInHashTable(int idx) {
        hash.search(extract.getKey(heap.get(idx).getKey())).getValue().setValue(idx);
    }

    /**
     * adiciona um novo elemento à PriorityQueue
     * @param elem  // novo elemento a ser adicionado à PriorityQueue
     * @param prio  // prioridade do novo elemento da PriorityQueue
     */
    public void add(E elem, P prio){
        Pair<Customer,Integer> pair2 = new Pair<>((Customer)elem,idx);
        hash.put(extract.getKey(elem)%size,pair2);
        Pair<E,P> pair = new Pair<>(elem,prio);
        heap.add(idx,pair);
        increaseMinKey(idx++);
    }

    /**
     * retorna o próximo elemento da PriorityQueue
     * @return  // o elemento mais prioritário da PriorityQueue
     */
    public E pick(){
        return (heap.size()==0)? null : heap.get(0).getKey();
    }

    /**
     * retorna o elemento mais prioritário da PriorityQueue com recurso ao método pick(), e remove o da lista
     * @return  // o elemento mais prioritário da PriorityQueue
     */
    public E poll(){
        if(heap.size()==0) return null;
        E elem = this.pick();
        exch(heap,0,heap.size()-1);
        updateHeapIdxInHashTable(0);
        heap.remove(heap.size()-1);
        hash.remove(elem);
        minHeapify(0,--idx);
        return elem;
    }

    /**
     * dado o elemento identificado por key altera a sua prioridade
     * @param key  // chave que identifica o elemento a modificar
     * @param prio  // nova prioridade do elemento identificado por key
     */
    public void update(int key, P prio){
        int idx = hash.search(key).getValue().getValue();
        P prev = heap.get(idx).getValue();
        heap.get(idx).setValue(prio);
        if(less(prio,prev)) increaseMinKey(idx);
        else if(less(prev,prio)) minHeapify(idx,heap.size());
    }


    /**
     * remove o elemento identificado por key da PriorityQueue
     * @param key  // chave que identifica o elemento a ser removido da PriorityQueue
     */
    public void remove(int key){
        int i  = hash.search(key).getValue().getValue();
        exch(heap,i,heap.size()-1);
        updateHeapIdxInHashTable(i);
        heap.remove(heap.size()-1);
        hash.remove(key);
        idx--;
        if(i<heap.size()-1) minHeapify(i,heap.size());
    }

    /**
     * @return // retorna o heap
     */
    public ArrayList<Pair<E,P>> getHeap() {
        return heap;
    }

    /**
     * @return // retorna a tabela de dispersão
     */
    public HashTable<Integer,Pair<Customer,Integer>> getHash() {
        return hash;
    }

    /**
     * @return // retorna o extrator de uma chave
     */
    public KeyExtractor<E> getExtract() {
        return extract;
    }
}
