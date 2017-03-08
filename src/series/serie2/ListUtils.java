package series.serie2;

import java.util.Comparator;

public class ListUtils {

	public static <E> void quicksort(Node<E> first, Node<E> last, Comparator<E> cmp){
		if(first == null || last == null || first == last) return;
		Node<E> middle = partition(first,last,cmp);
		if(middle.previous!= null && first!=middle) quicksort(first,middle.previous,cmp);
		if(middle.next != null && middle != last) quicksort(middle.next,last,cmp);
	}

	//Método Auxiliar
	private static <E> Node partition(Node<E> left,Node<E> right, Comparator<E> cmp){
		Node<E> pivot = right;
		Node<E> i = left, j = right.previous;
		for ( ; ; ){
			while(cmp.compare(i.value,pivot.value)<= 0){
				if(i==j) break;
				i = i.next;
			}
			while(cmp.compare(pivot.value,j.value) <= 0){
				if(i==j)break;
				j=j.previous;
			}
			if(i==j) break;
			exchangeValue(i, j);
			i = i.next;
			if (i == j) break;
			j = j.previous;
		}
		if(cmp.compare(pivot.value,i.value)< 0) exchangeValue(i,right);
		else {
			i = i.next;
			exchangeValue(i,right);
		}
		return i;
	}

	//Método Auxiliar
	private static <E> void exchangeValue(Node<E> i, Node<E> j) {
		E valuetemp = i.value;
		i.value = j.value;
		j.value = valuetemp;
	}




	public static Node<Node<String>> splitBySentence(Node<String> list) {
		Node<Node<String>> split = new Node<>();
		Node<Node<String>> headSplit = split;
		Node<String> aux = list.next;
		Node<String> headList = list;
		Node<String> firstWord = null;
		boolean first = true;
		while(aux!=headList){
			while(aux != headList && !aux.value.equals(".")){
				if(first){
					split.next = new Node<>();
					split.next.previous = split;
					split = split.next;
					firstWord = split.value = aux;
					first = false;
				}
				else split.value = split.value.next;
				aux = aux.next;
			}
			if(aux.next.value != "." || aux== headList) {
				// tornar a lista de palavras circular
				split.value.next = firstWord;
				split.value.next.previous = split.value;
				split.value = split.value.next;
				// tornar a lista de palavras nao circular
				split.value.previous.next = null;
				split.value.previous = null;
			}
			first = true;
			list.next = aux;
			if(aux!= headList) aux = aux.next;
			list = list.next;
		}
		split.next = headSplit;
		split.next.previous = split;
		split = split.next;
		return split;
	}
}
