package series.serie1.grupo3;

import java.io.*;


public class maiorNrOcorrencias {
    private static int size; // tamanho do top de palavras com mais ocorrencias
    private static Word[] repeteadWords; // array que guarda as palavras que mais aparecem
    private static File_Words[] nextWords; //guardar primeiro elemento de cada ficheiro
    private static String currentWord; //
    private static int index = 0; // nº de palavras já inseridas no top

    /**
     * esta aplicação determina as size palavras que se repetem mais vezes em um ou mais ficheiros de texto ordenados
     * lexicograficamente de forma crescente
     * @param args // argumentos passados pelo utilizador, contêm informação
     */
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        String [] fileNames = new String[args.length-2];
        size = Integer.parseInt(args[0]);
        String outputName = args[1];
        repeteadWords = new Word[size];
        nextWords = new File_Words[fileNames.length];
        for (int i = 2, j = 0; i < args.length ; i++, j++)
            fileNames[j] = args[i];
        BufferedReader[] b = new BufferedReader[fileNames.length];
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputName))){
            for (int idx = 0; idx < nextWords.length; idx++){
                b[idx] = new BufferedReader(new FileReader(fileNames[idx]));
                nextWords[idx] = new File_Words(b[idx].readLine(),idx);
            }
            buildHeapFile(nextWords,nextWords.length);
            while (nextWords[0].getString() != null) {
                Word w = new Word(nextWords[0].getString());
                countEquals(b[nextWords[0].getFileOrder()], w);
                rebuildHeap();
                while (w.getName().equals(nextWords[0].getString())) {
                    w.increaseAmount();
                    countEquals(b[nextWords[0].getFileOrder()], w);
                    rebuildHeap();
                }
                putWordOnTop(w);
            }
            mergeSort(repeteadWords,0,size-1);
            writeTop(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                for(int i=0 ; i<b.length ; ++i)
                    if(b[i] != null) b[i].close();
                System.out.println(System.currentTimeMillis()-time);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    /**
     * escreve num ficheiro de texto as palavras mais repetidas
     * @param writer // ficheiro de escrita
     * @throws IOException
     */
    private static void writeTop(BufferedWriter writer) throws IOException {
        for(int i = size-1 ; i>=0 ; --i) {
            writer.write(repeteadWords[i].getAmount()+" "+repeteadWords[i].getName());
            writer.newLine();
        }
    }


    /**
     * verifica se uma determinada palavra se repete vezes suficientes para entrar no top de ocorrencia de palavras
     * @param w // palavra
     */
    private static void putWordOnTop(Word w) {
        if (index != size){
            repeteadWords[index++] = w;
            if (index == size) buildHeapWord(repeteadWords);
        }
        else
            if (w.getAmount()>repeteadWords[0].getAmount()){
                repeteadWords[0] = w;
                minumHeapifyAmount(repeteadWords,repeteadWords.length,0);
            }
    }


    /**
     * reordena o heap com um elemento fora do sitio
     */
    private static void rebuildHeap() {
        nextWords[0].setString(currentWord);
        minumHeapifyWords(nextWords,nextWords.length,0);
    }


    /**
     * este método auxiliar ordena o array entre os indices l e r
     * @param a // array a ser ordenado
     * @param l // indice inicial a ser ordenado
     * @param r // indice final a ser ordenado
     */
    private static void mergeSort(Word[] a, int l, int r) {
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
        static void merge(Word[] array, int left, int mid, int right) {
            int i, j;
            // Auxiliary array
            Word[] aux = new Word[array.length];
            // Copy original array to auxiliary array,
            // but invert the right part (see Figure 8.1),
            // in order to eliminate the need for limit tests
            for (i = mid + 1; i > left; i--)
                aux[i - 1] = array[i - 1];
            for (j = mid; j < right; j++)
                aux[right + mid - j] = array[j + 1];

            for (int k = left; k <= right; k++) {
                if (lessNumber(aux[j].getAmount(), aux[i].getAmount()))
                    array[k] = aux[j--];
                else
                    array[k] = aux[i++];
            }
        }


    /**
     * constroi um heap de forma a que o pai seja maior que os filhos
     * @param array // heap a ser ordenado
     */
    private static void buildHeapWord(Word[] array) {
        for (int idxParent = (array.length / 2) - 1; idxParent >= 0; --idxParent) {
            minumHeapifyAmount(array, array.length, idxParent);
        }
    }


    /**
     * ordena um heap de forma a que o pai seja menor que os filhos
     * @param array // heap em que cada elemento corresponde ao nº de vez que uma dada palavra se repetiu
     * @param length // nº de palavras
     * @param ind // indice do elemento desordenado
     */
    private static void minumHeapifyAmount(Word[] array, int length, int ind) {
        int idxLeftChild = (2 * ind) + 1;
        int idxRightChild;
        // If there exists a left descendant
        while (idxLeftChild < length) {
            // Get index of right descendant
            idxRightChild = idxLeftChild + 1;
            // Get the largest index of the left and right descendants
            if (idxRightChild < length && lessNumber(array[idxRightChild].getAmount(),array[idxLeftChild].getAmount()))
                idxLeftChild = idxRightChild;
            // If the largest descendant is not greater than the parent, stop (the heap is ordered)
            if (lessNumber(array[ind].getAmount(),array[idxLeftChild].getAmount()))
                break;
            // Else, exchange the largest descendant by its parent
            exchNumber(array, ind, idxLeftChild);
            // Set the next parent to be the left descendant
            ind = idxLeftChild;
            // Update left descendant
            idxLeftChild = (2 * ind) + 1;
        }
    }


    /**
     * troca dois indices do array
     * @param array // array a ser alterado
     * @param i // indice do elemento a ser trocado
     * @param idx // indice do elemento a ser trocado
     */
    private static void exchNumber(Word[] array, int i, int idx) {
        Word temp = array[idx];
        array[idx] = array[i];
        array[i] = temp;
    }


    /**
     * verifica se firstamount é menor que secondamount
     * @param firstamount // valor a ser comparado
     * @param secondamount // valor a ser comparado
     * @return retorna true se firstamount menor que secondamount
     */
    private static boolean lessNumber(int firstamount, int secondamount) {
        return firstamount < secondamount;
    }


    /**
     * conta o nº de repetições de uma palavra num determinado ficheiro
     * @param b // leitor de ficheiro
     * @param w // palavra a ser comparada
     * @throws IOException
     */
    private static void countEquals(BufferedReader b, Word w) throws IOException {
        currentWord = b.readLine();
        while( currentWord !=null && w.getName().equals (currentWord)){
            w.increaseAmount();
            currentWord = b.readLine();
        }
    }


    /**
     * faz um heap em que o pai é menor que os filhos
     * @param array // heap em que cada elemento corresponde à palavra a ser analisada de um ficheiro
     * @param length // nº de ficheiros
     */
    private static void buildHeapFile(File_Words[] array, int length) {
        for (int idxParent = (length / 2) - 1; idxParent >= 0; --idxParent) {
            minumHeapifyWords(array, length, idxParent);
        }
    }


    /**
     * ordena um heap de forma a que o pai seja menor que os filhos
     * @param array // heap em que cada elemento corresponde à palavra a ser analisada de um ficheiro
     * @param length // nº de ficheiros
     * @param ind // indice do elemento desordenado
     */
    private static void minumHeapifyWords(File_Words[] array, int length, int ind) {
        int idxLeftChild = (2 * ind) + 1;
        int idxRightChild;
        // If there exists a left descendant
        while (idxLeftChild < length) {
            // Get index of right descendant
            idxRightChild = idxLeftChild + 1;
            // Get the largest index of the left and right descendants
            if (idxRightChild < length && lessWord(array[idxRightChild].getString(),array[idxLeftChild].getString()))
                idxLeftChild = idxRightChild;
            // If the largest descendant is not greater than the parent, stop (the heap is ordered)
            if (lessWord(array[ind].getString(),array[idxLeftChild].getString()))
                break;
            // Else, exchange the largest descendant by its parent
            exchWord(array, ind, idxLeftChild);
            // Set the next parent to be the left descendant
            ind = idxLeftChild;
            // Update left descendant
            idxLeftChild = (2 * ind) + 1;
        }
    }


    /**
     * @param first // string a ser comparada
     * @param second // string a ser comparada
     * @return // retorna a menor String lexicograficamente
     */
    private static boolean lessWord(String first, String second){
        if (first == null || first.equals(second))
            return false;
        if (second == null) return true;
        for (int i = 0; i < first.length() && i < second.length(); i++){
            if (first.charAt(i)< second.charAt(i))
                return true;
            if (second.charAt(i) < first.charAt(i))
                return false;
        }
        return false;
    }


    /**
     * troca dois indices do array
     * @param array // array a ser alterado
     * @param idx // indice do elemento a ser trocado
     * @param i // indice do elemento a ser trocado
     */
    private static void exchWord(File_Words[]array, int idx, int i){
        File_Words temp = array[idx];
        array[idx] = array[i];
        array[i] = temp;
    }
}
