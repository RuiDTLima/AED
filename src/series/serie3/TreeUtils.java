package series.serie3;

import java.util.Comparator;

public class TreeUtils {

    /**
     * verifica se o valor de algum elemento da arvore está contido entre min e max
     * @param root // raiz da arvore que queremos verificar
     * @param min  // valor minimo que se vai verificar
     * @param max  // valor máximo que se vai verificar
     * @param cmp  // método de comparação, comparador
     * @param <E>
     * @return  // true se a arvore contém um elemento entre min e max, false caso contrário
     */
    public static <E> boolean contains(Node<E> root, E min, E max, Comparator<E> cmp){
        if (root == null || cmp.compare(min,max) > 0) return false;
        if (cmp.compare(root.value,min) >= 0 && cmp.compare(root.value,max)<=0) return true;
        return contains(root.left,min,max,cmp) || contains(root.right,min,max,cmp);
    }

    public static Node<Integer> createBSTFromRange(int start, int end){
        if(end<start) return null;
        int mid = (start+end)/2;
        Node<Integer> bst = new Node<>(mid);
        if(mid-1 >= start) bst.left = createBSTFromRange(start,mid-1);
        if(mid+1 <= end) bst.right = createBSTFromRange(mid+1,end);
        return bst;
    }

    public static <E> boolean isBST(Node<E> root, Comparator<E> cmp){
        if (root == null) return true;
        //analisa o lado esquerdo da árvore
        if (root.left != null && cmp.compare(root.value, root.left.value) < 0 ) return false;
        //analisa o lado direito da árvore
        if (root.right != null && cmp.compare(root.value, root.right.value) > 0)return false;
        return isBST(root.left, cmp) && isBST(root.right, cmp);
    }
}