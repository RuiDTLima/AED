package series.serie3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Comparator;


/**
 * Created by rui_l on 13/12/2016.
 */
public class TreeUtilsTest {

    static final Comparator<Integer> CMP_NATURAL_ORDER = new Comparator<Integer>() {
        public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
        }
    };
    private final int MIN = 5;
    private final int MAX = 11;
    private final int NEGATIVE_MIN = -11;
    private final int NEGATIVE_MAX = -5;


    /* ====================Teste para o método contains=========================*/

    @Test
    public void contains_emptyTree() throws Exception {
        Node<Integer> empty = null;
        boolean boo = TreeUtils.contains(empty, MIN,MAX,CMP_NATURAL_ORDER);
        assertFalse(boo);
    }

    @Test
    public void contains_oneElement() throws Exception {
        Node<Integer> one = new Node<>(7);
        boolean boo = TreeUtils.contains(one, MIN, MAX,CMP_NATURAL_ORDER);
        assertTrue(boo);
    }

    @Test
    public void contains_multipleElements() throws Exception {
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(8);
        root.right = new Node<>(13);
        root.left.left = new Node<>(5);
        root.left.right = new Node<>(9);
        root.right.left = new Node<>(11);
        root.right.right = new Node<>(17);
        boolean boo = TreeUtils.contains(root, MIN, MAX,CMP_NATURAL_ORDER);
        assertTrue(boo);
    }

    @Test
    public void notcontains_suchElement() throws Exception {
        Node<Integer> root = new Node<>(14);
        root.left = new Node<>(4);
        root.left.left = new Node<>(1);
        root.left.right = new Node<>(12);
        root.right = new Node<>(16);
        root.right.left = new Node<>(15);
        root.right.right = new Node<>(17);
        boolean boo = TreeUtils.contains(root, MIN, MAX,CMP_NATURAL_ORDER);
        assertFalse(boo);
    }

    @Test
    public void contains_onlyMaxElement() throws Exception {
        Node<Integer> root = new Node<>(21);
        root.left = new Node<>(16);
        root.right = new Node<>(33);
        root.left.left = new Node<>(11);
        root.left.right = new Node<>(18);
        root.right.left = new Node<>(30);
        root.right.right = new Node<>(40);
        boolean boo = TreeUtils.contains(root, MIN, MAX,CMP_NATURAL_ORDER);
        assertTrue(boo);
    }

    @Test
    public void contains_onlyMinElement() throws Exception {
        Node<Integer> root = new Node<>(19);
        root.left = new Node<>(12);
        root.left.left = new Node<>(5);
        root.left.right = new Node<>(13);
        root.right = new Node<>(21);
        root.right.left = new Node<>(20);
        root.right.right = new Node<>(22);
        boolean boo = TreeUtils.contains(root, MIN, MAX,CMP_NATURAL_ORDER);
        assertTrue(boo);
    }

    @Test
    public void notcontains_negativeElements() throws Exception {
        Node<Integer> root = new Node<>(0);
        root.left = new Node<>(-4);
        root.left.left = new Node<>(-13);
        root.left.right = new Node<>(-2);
        root.right = new Node<>(7);
        root.right.left = new Node<>(5);
        root.right.right = new Node<>(11);
        boolean boo = TreeUtils.contains(root, NEGATIVE_MIN, NEGATIVE_MAX,CMP_NATURAL_ORDER);
        assertFalse(boo);
    }

    @Test
    public void contains_negativeElements() throws Exception {
        Node<Integer> root = new Node<>(0);
        root.left = new Node<>(-12);
        root.left.left = new Node<>(-17);
        root.left.right = new Node<>(-8);
        root.right = new Node<>(4);
        root.right.left = new Node<>(1);
        root.right.right = new Node<>(5);
        boolean boo = TreeUtils.contains(root, NEGATIVE_MIN, NEGATIVE_MAX,CMP_NATURAL_ORDER);
        assertTrue(boo);
    }

    @Test
    public void contains_wrongLength() throws Exception {
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(8);
        root.right = new Node<>(13);
        root.left.left = new Node<>(5);
        root.left.right = new Node<>(9);
        root.right.left = new Node<>(11);
        root.right.right = new Node<>(17);
        boolean boo = TreeUtils.contains(root, MAX,MIN,CMP_NATURAL_ORDER);
        assertFalse(boo);
    }


     /* ====================Teste para o método createBSTFromRange=========================*/

    @Test
    public void emptyRange() throws Exception {
        assertEquals(null,TreeUtils.createBSTFromRange(10,9));
    }

    @Test
    public void createBSTWithOneElement() throws Exception {
        assertEquals((Integer)10,TreeUtils.createBSTFromRange(10,10).value);
    }

    @Test
    public void createBSTWithEvenNumberOfElements() throws Exception {
        Node<Integer> node = new Node<>(7);
        node.left = new Node<>(5);
        node.left.right = new Node<>(6);
        node.right = new Node<>(9);
        node.right.left = new Node<>(8);
        node.right.right = new Node<>(10);
        assertTrue(compareBSTs(node,TreeUtils.createBSTFromRange(5,10),CMP_NATURAL_ORDER));
    }

    @Test
    public void createBSTWithOddNumberOfElements() throws Exception {
        Node<Integer> node = new Node<>(8);
        node.left = new Node<>(6);
        node.left.right = new Node<>(7);
        node.left.left = new Node<>(5);
        node.right = new Node<>(10);
        node.right.left = new Node<>(9);
        node.right.right = new Node<>(11);
        assertTrue(compareBSTs(node,TreeUtils.createBSTFromRange(5,11),CMP_NATURAL_ORDER));
    }

    //Auxiliar method
    private boolean compareBSTs(Node<Integer> node, Node<Integer> bstFromRange, Comparator<Integer> cmp) {
        if(node == null) return true;
        if(cmp.compare(node.value,bstFromRange.value)!=0) return false;
        return compareBSTs(node.left,bstFromRange.left,CMP_NATURAL_ORDER)
                && compareBSTs(node.right,bstFromRange.right,CMP_NATURAL_ORDER);
    }


    /* ====================Teste para o método isBST=========================*/

    @Test
    public void isEmptyA_BST() throws Exception {
        assertTrue(TreeUtils.isBST(null,CMP_NATURAL_ORDER));
    }

    @Test
    public void oneElementTree() throws Exception {
        Node<Integer> one = new Node<>(7);
        assertTrue(TreeUtils.isBST(one,CMP_NATURAL_ORDER));
    }

    @Test
    public void notBST() throws Exception {
        Node<Integer> node = new Node<>(7);
        node.left = new Node<>(5);
        node.left.right = new Node<>(6);
        node.right = new Node<>(9);
        node.right.left = new Node<>(10);
        node.right.right = new Node<>(8);
        assertFalse(TreeUtils.isBST(node,CMP_NATURAL_ORDER));
    }

    @Test
    public void isBST() throws Exception {
        Node<Integer> node = new Node<>(8);
        node.left = new Node<>(6);
        node.left.right = new Node<>(7);
        node.left.left = new Node<>(5);
        node.right = new Node<>(10);
        node.right.left = new Node<>(9);
        node.right.right = new Node<>(11);
        assertTrue(TreeUtils.isBST(node,CMP_NATURAL_ORDER));
    }
}