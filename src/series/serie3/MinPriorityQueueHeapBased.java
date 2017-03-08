package series.serie3;

import java.lang.reflect.Array;

/**
 *
 * Priority Queue heap implementation
 */
public class MinPriorityQueueHeapBased<E extends Comparable<E>> {

    static <E extends Comparable<E>> boolean less(E v, E w) {
        return v.compareTo(w) < 0;
    }

    static <E> void exch(E[] array, int i, int j) {
        E t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    /**
     * Priority queue array
     */
    private E[] pq;
    /**
     * Priority queue size
     */
    private int size;

    /**
     * Constructor 1
     *
     * @param capacity
     */
    public MinPriorityQueueHeapBased(Class<E> elementType, int capacity) {
        //
        // Changed: For convenience, indices start at 1. The zero position is not used.
        //
        pq = (E[]) Array.newInstance(elementType, capacity + 1);
        size = 0;
    }

    /**
     * Constructor 2
     *
     * Build a heap given an array (in non-heap strucuture)
     *
     * @param array Non-empty array
     */
    public MinPriorityQueueHeapBased(E[] array, int size) {
        pq = (E[]) Array.newInstance(array[0].getClass(), size + 1); // In this implementation, the zero position is not used
        for (int i = 0; i < size; i++) {
            pq[i+1] = array[i];
        }
        // Set pq size
        this.size = size;
        // Build heap from pq array
        buildHeap();
    }


    public int getSize() {
        return size;
    }

    /**
     *
     * @return
     */
    public boolean empty() {
        return size == 0;
    }

    /**
     *
     * @param elem
     */
    public void insert(E elem) {
        /// Changed

        pq[size+1] = elem; // Indices start at 1
        // Increment size
        ++size;
        // Fix up the heap
        increaseKey(size);
    }

    /**
     *
     * @return
     */
    public E get() {

        return pq[1];
    }

    /**
     *
     * @return
     */
    public E remove() {
        /// Changed

        // Get min
        E min = pq[1];
        // Set the new root to be the last element
        pq[1] = pq[size];
        // Remake heap from the root
        minHeapify(1, size - 1);
        // Decrement size
        --size;
        // Return the maximum
        return min;
    }

    /////////////////////////////////////////////
    // Heap operations
    //

    

    /**
     * O(size)
     * Algorithm:
     *  1. Get last element's parent (index = size / 2)
     *  2. max-heapify (sink operation) this parent
     *  in order to maintain the heap condition
     *  3. Go the previous parent (--index) and heapify it.
     *  4. Continue in this fashion until reaching the root.
     */
    private void buildHeap() {
        // Get last element's parent
        int idxParent = size / 2;
        while (idxParent >= 1) {
            minHeapify(idxParent, size);
            // Go to previous parent
            --idxParent;
        }
    }

    /**
     * Bottom-up heapify (swim)
     * @param k
     */
    private void increaseKey(int k) {
        int idxParent;
        while (k > 1) {
            // Get parent
            idxParent = k / 2;
            // Verify if parent is greater than current key index k
            if (!less(pq[k], pq[idxParent]))
                break; // The heap is in order
            // and exchange parent with current key index
            exch(pq, k, idxParent);
            // Set current key index to parent index
            k = idxParent;

        }
    }

    /**
     * Top-down heapify (sink)
     * @param k
     * @param hSize
     */
    private void minHeapify(int k, int hSize) {
        // Get index of left descendant
        int idxLeftChild = 2 * k;
        int idxRightChild;
        // If there exists a left descendant
        while (idxLeftChild <= hSize) {
            // Get index of right descendant
            idxRightChild = idxLeftChild + 1;
            // Get the smallest index of the left and right descendants
            if (idxRightChild <= hSize && less(pq[idxRightChild], pq[idxLeftChild]))
                idxLeftChild = idxRightChild;
            // If the smallest descendant is not smaller than the parent, stop (the heap is ordered)
            if (!less(pq[idxLeftChild], pq[k]))
                break;
            // Else, exchange the smallest descendant by its parent
            exch(pq, k, idxLeftChild);
            // Set the next parent to be the left descendant
            k = idxLeftChild;
            // Update left descendant
            idxLeftChild = 2 * k;
        }
    }
    /////////////////////////////////////////////

    public void print() {
        /// Changed

        if (empty()) {
            System.out.println("Empty");
            return;
        }
        System.out.print("Size = " + size);
        System.out.print(" [");

        // Now, indices start at 1
        for (int i = 1; i < size; i++) {
            System.out.print(pq[i] + ", ");
        }
        System.out.print(pq[size]);
        System.out.println("]");
    }

    public static void main(String[] args) {
        //
        // MinPriorityQueueHeapBased
        //

        // 1. Build Priority queue by successive insertion
        MinPriorityQueueHeapBased<Character> pq = new MinPriorityQueueHeapBased<>(Character.class, 20);
        Character[] array = { 'A', 'S', 'O', 'R', 'T', 'I', 'N', 'G', 'E', 'X', 'A', 'M', 'P', 'L', 'E' };

        // Insert keys 'A', 'S', 'O', 'R', 'T', 'I', 'N', 'G'  - Figure 9.5
        int size = 8;
        for (int i = 0; i < size; ++i) {
            pq.insert(array[i]);
        }
        pq.print(); // Size = 8 [T, S, O, G, R, I, N, A]

        // Insert keys 'E', 'X', 'A', 'M', 'P', 'L', 'E' - Figure 9.6
        for (int i = size; i < array.length; ++i) {
            pq.insert(array[i]);
        }
        pq.print(); // Size = 15 [X, T, P, G, S, O, N, A, E, R, A, I, M, L, E]

        // 2. Build heap from initial (non-heap) array
        MinPriorityQueueHeapBased<Character> pq1 = new MinPriorityQueueHeapBased<>(array, array.length);
        pq.print(); // Size = 15 [X, T, P, G, S, O, N, A, E, R, A, I, M, L, E]

        // 3.
        Integer[] intArray = { 8, 10, 1, 9, 4, 7, 3, 5, 6, 2 };
        System.out.println("Original array: " + java.util.Arrays.toString(intArray));
        MinPriorityQueueHeapBased<Integer> pq2 = new MinPriorityQueueHeapBased<>(intArray, intArray.length);
        pq2.print();
        while (!pq2.empty()) {
            // Remove the maximum
            System.out.println("Min = " + pq2.remove());
            pq2.print();
        }


    }

}
