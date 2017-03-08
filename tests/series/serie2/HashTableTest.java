package series.serie2;

import series.serie2.Node;

import java.util.ArrayList;
import java.util.Random;

public class HashTableTest {
	
	/*
	 * for hash tables with separate chaining 
	 * using circular lists with sentinel
	 */
	
	public static <E> Node<E>[] emptyHashTable(int n) {
		Node<E>[] empty = (Node<E>[]) new Node[n];
		for (int i = 0; i < empty.length; i++) {
			Node<E>	x = new Node<E>();
			x.next = x.previous = x;
			empty[i] = x;
		}
		return empty;
	}

	public static <E> boolean isEmptyHastTable(Node<E>[] hashTable) {
		for (int i = 0; i < hashTable.length; i++) 
			if (hashTable[i].next != hashTable[i]) return false;
		return true;
	}
	
	public static <E> int index(Node<E>[] hashTable, E e){
		int m = hashTable.length;
		int h = e.hashCode() % m;
		return (h < 0) ? h + m : h;
	}
	
	public static <E> void insert(Node<E>[] hashTable, E e){
		int i = index(hashTable, e);
		Node<E> x = new Node<E>();
		x.value=e;
		x.next = hashTable[i].next;
		hashTable[i].next.previous = x;
		hashTable[i].next = x;
		x.previous = hashTable[i];
	}
	
	public static <E> void insertList(Node<E>[] hashTable, Node<E> list){
		int i = index(hashTable, list.next.value);
		
		list.previous.next = hashTable[i].next;
		hashTable[i].next.previous = list.previous;
		hashTable[i].next = list.next;
		list.next.previous = hashTable[i];
	}
	
	public static Node<Integer>[] getHashTable( int start, int step, int end, int length) {
		Node<Integer>[] hashTable = emptyHashTable(length);
		for (int i = start; i <= end; i += step)
			insert(hashTable, i);
		return hashTable;
	}

	public static boolean sameElements(Node<Integer> list, Node<Integer> newList){
		ArrayList<Integer> array = new ArrayList<Integer>();
		ArrayList<Integer> newArray = new ArrayList<Integer>();
		Node<Integer> curr = list.next;
		while (curr != list) {
			array.add(curr.value);
			curr = curr.next;
		}
		curr = newList.next; 
		while (curr != newList) {
			newArray.add(curr.value);
			curr = curr.next;
		}
		return array.containsAll(newArray) && newArray.containsAll(array);
	}
	
	public static boolean sameElements(Node<Integer> list1, Node<Integer> list2, Node<Integer> newList){
		ArrayList<Integer> array = new ArrayList<Integer>();
		ArrayList<Integer> newArray = new ArrayList<Integer>();
		Node<Integer> curr = list1.next;
		while (curr != list1) {
			array.add(curr.value);
			curr = curr.next;
		}
		curr = list2.next;
		while (curr != list2) {
			array.add(curr.value);
			curr = curr.next;
		}
		curr = newList.next; 
		while (curr != newList) {
			newArray.add(curr.value);
			curr = curr.next;
		}
		return array.containsAll(newArray) && newArray.containsAll(array);
	}
}
