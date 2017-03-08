package series.serie2;


public class Utils {
	
	public static <E> Node<E>[] shrink(Node<E>[] hashTable){
		int newd = hashTable.length/2;
		Node <E> [] newTable = (Node<E>[]) new Node<?>[newd];
        int j;
        for (j = 0; j < newd; j++ )
            newTable[j] = hashTable[j];
        for (; j < hashTable.length; ++j){
            if (hashTable[j].previous.value != null){
                int idx = hashTable[j].previous.value.hashCode() % newd;
                newTable[idx].previous.next = hashTable[j].next;
                hashTable[j].previous.next = newTable[idx];
                hashTable[j].next.previous = newTable[idx].previous;
                newTable[idx].previous = hashTable[j].previous;
            }
        }
        return newTable;
	}
}
