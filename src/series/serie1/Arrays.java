package series.serie1;

/**
 * Created by rui_l on 12/10/2016.
 */
public class Arrays {
    /**
     * Retorna o maior elemento de um array ordenado de forma crescente
     * cujos elementos foram deslocados circularmente para a direita
     * Tem complexidade O(log n)
     * @param v    //array ordenado de forma crescente
     * @param left  //indice pelo qual se começa à procura do maior elemento
     * @param right //indice até ao qual se procura o maior elemento
     * @return         //o maior elemento
     */
    public static int greatestAfterRotate(int[] v, int left, int right) {
        if (left>right) return -1;
        if (left==right) return v[left];
        if (right-left == 1) return (v[left]>v[right])? v[left] : v[right];
        int mid = (left + right)/2;
        if (v[left]>v[mid]) return greatestAfterRotate(v,left,mid);
        return greatestAfterRotate(v,mid,right);
    }

    /**
     * Método que testa se um dado sub-array é permutação de outro sub-array
     * @param a1  //primeiro sub-array
     * @param l1  //indice do primeiro sub-array pelo qual se começa a verificar se houve permutação
     * @param r1  //indice do segundo sub-array até ao qual se verifica se houve permutação
     * @param a2  //segundo sub-array
     * @param l2  //indice do segundo sub-array pelo qual se começa a verificar se houve permutação
     * @param r2  //indice do segundo sub-array até ao qual se verifica se houve permutação
     * @return    //True se a1 é permutação de a2, false caso contrário.
     */
    public static boolean isPermutation(int[] a1, int l1, int r1, int[] a2, int l2, int r2) {
        if ((r2-l2) != (r1-l1)) return false;
        boolean permutation = false;
        for (int i = l2, j = l1; i<r2; ++i, ++j)
            if (a1[j] != a2[i]){
               permutation = true;
            }
        if(!permutation) return false;
        mergeSort(a1,l1,r1);
        mergeSort(a2,l2,r2);
        for (int i = l1, j= l2; i < r1; i++, j++) {
            if (a1[i] != a2[j]) return false;
        }
        return true;
    }

    /*=====================Auxiliar method===========================*/

    /**
     * este método auxiliar ordena o array entre os indices l e r
     * @param a // array a ser ordenado
     * @param l // indice inicial a ser ordenado
     * @param r // indice final a ser ordenado
     */
    public static void mergeSort(int[] a, int l, int r) {
        if (r <= l)
            return;
        int mid = (r+l)/2;
        // Sort the left part recursively
        mergeSort(a, l, mid);
        // Sort the right part recursively
        mergeSort(a, mid+1, r);
        // Merge the sorted left and right parts
        merge(a, l, mid, r);
    }

    /*=====================Auxiliar method===========================*/
    /**
     * este método auxiliar junta de forma ordenada duas partes do array consecutivas previamente ordenadas
     * @param array // array a ser ordenado
     * @param left // indice inicial a ser ordenado
     * @param mid // indice médio do array
     * @param right // indice final a ser ordenado
     */
    static void merge(int[] array, int left, int mid, int right) {
        int i, j;
        // Auxiliary array
        int[] aux = new int[array.length];
        // Copy original array to auxiliary array,
        // but invert the right part (see Figure 8.1),
        // in order to eliminate the need for limit tests
        for (i = mid + 1; i > left; i--)
            aux[i - 1] = array[i - 1];
        for (j = mid; j < right; j++)
            aux[right + mid - j] = array[j + 1];

        for (int k = left; k <= right; k++) {
            if (less(aux[j], aux[i]))
                array[k] = aux[j--];
            else
                array[k] = aux[i++];
        }
    }

    /*=====================Auxiliar method===========================*/
    /**
     * verifica se v é menor que w
     * @param v
     * @param w
     * @return
     */
    static boolean less(int v, int w) {
        return v < w;
    }

    /**
     * Altera o valor do elemento presente no indice ix para newvalue, mantendo o array como max-heap nos primeiros count elementos
     * @param v //array em max-heap nos primeiros count elementos ao qual se vai alterar um dos valores
     * @param count //quantidade de elementos do array que estão em max-heap
     * @param ix  //indice ao qual se vai alterar o valor do seu elemento
     * @param newValue  //novo valor do indice que vai ser alterado
     */
    public static void changeValueInMaxHeap(int[] v, int count, int ix, int newValue){
        if (ix >= count) throw new IllegalArgumentException();
        int temp = v[ix];
        v[ix] = newValue;
        if (temp < v[ix])
            increaseKey(ix,v);
        else
            maxHeapify(v,count-ix,ix);
        }

    /*=====================Auxiliar method===========================*/
    /**
     * despromove um elemento inserido no heap de forma a ser superior aos filhos e menor ao pai
     * @param array // heap a reordenar
     * @param hSize // tamanho do heap
     * @param k // indice do elemento a reordenar
     */
    private static void maxHeapify(int[] array, int hSize, int k) {
        // Get index of left descendant
        int idxLeftChild = (2 * k) + 1;
        int idxRightChild;
        // If there exists a left descendant
        while (idxLeftChild < hSize) {
            // Get index of right descendant
            idxRightChild = idxLeftChild + 1;
            // Get the largest index of the left and right descendants
            if (idxRightChild < hSize && less(array[idxLeftChild], array[idxRightChild]))
                idxLeftChild = idxRightChild;
            // If the largest descendant is not greater than the parent, stop (the heap is ordered)
            if (!less(array[k], array[idxLeftChild]))
                break;
            // Else, exchange the largest descendant by its parent
            exch(array, k, idxLeftChild);
            // Set the next parent to be the left descendant
            k = idxLeftChild;
            // Update left descendant
            idxLeftChild = (2 * k) + 1;
        }
    }
 /*=====================Auxiliar method===========================*/
    /**
     * promove um elemento inserido no heap de forma a ser superior aos filhos e menor ao pai
     * @param k // indice do elemento a reordenar
     * @param v // heap a reordenar
     */
    private static void increaseKey(int k, int[] v){
        if (k == 0) return;
        if (v[k] > v[(k-1)/2]){
            exch(v,k,k/2);
            increaseKey(k/2,v);
        }
    }

    /*=====================Auxiliar method===========================*/
    /**
     * troca os elementos do array a indice i e j
     * @param a // array dos elementos a trocar
     * @param i // indice a trocar
     * @param j // indice a trocar
     */
    static void exch(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    /**
     * este método ordena um array de strings em que cada elemento é um ip da seguinte forma "xxx.xxx.xxx.xxx" de forma crescente,
     * sendo a ordenação feita da direita para a esquerda, do octeto da direita para o octeto da esquerda.
     * @param v // array de IPs
     * @param l // indice inicial
     * @param r // indice final
     */
    public static void sortIPv4Addresses(String[] v, int l, int r) {
        for (int i = 3; i >= 0; --i)
            countingSort(v, i,l,r);
    }

    /*=====================Auxiliar method===========================*/
    /**
     * método auxiliar que conta o número de ocorrências de cada valor de forma a saber o índice em que um determinado elemento deve
     * ser colocado de modo a estar na ordem correta
     * @param v // array de IPs
     * @param i // nº do octeto a extrair
     * @param l // indice inicial
     * @param r // indice final
     */
    private static void countingSort(String[] v, int i, int l, int r) {
        int len = r-l+1;
        int octet;
        String[] aux = new String[len];
        int[] count = new int[256];
        for (int j = 0; j < len; ++j) {
            octet = getOctect(v[j], i);
            ++count[octet];
        }
        for (int j = 1; j < 256; ++j)
            count[j] += count[j - 1];
        for (int j = len-1; j >= 0; --j) {
            int octect = getOctect(v[j], i);
            aux[--count[octect]] = v[j];
        }
        for (int j = 0; j < len; ++j) {
            v[j] = aux[j];
        }
    }

    /*=====================Auxiliar method===========================*/
    /**
     * este método elimina os 3 octetos não desejados num dado momento, de forma a retornar apenas o valor do octeto pretendido
     * @param s // IP do qual será extraido o octeto
     * @param i // indice do octeto a ser extraído
     * @return // valor do octeto pretendido
     */
    private static int getOctect(String s, int i) {
        for(int j=0 ; j!=i ; ++j)
            s = s.substring(s.indexOf(".")+1);
        if(s.contains(".")) s = s.substring(0,s.indexOf("."));
        return Integer.parseInt(s);
    }
}
