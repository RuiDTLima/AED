package series.serie2;

import series.serie2.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

public class ListUtilTest {

	/*
	 * For circular lists with sentinel
	 */

	public static <E> Node<E> emptyListWithSentinel() {
		Node<E> empty = new Node<E>();
		empty.next = empty.previous = empty;
		return empty;
	}

	public static <E> boolean isEmptyListWithSentinel(Node<E> list) {
		return list.next == list && list.previous == list;
	}

	public static Node<String> getListFromFile(String fileName)
			throws IOException {
		Node<String> list = ListUtilTest.<String> emptyListWithSentinel();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = null, sentence = null;
		int k = 0;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			int pos = line.indexOf('.', k);
			while (pos != -1) {
				sentence = line.substring(k, pos);
				k = pos + 1;
				pos = line.indexOf('.', k);
				sentence = sentence.trim();
				addWordsToList(list, sentence);
				list.previous.next = newNode(".", list.previous, list);
				list.previous = list.previous.next;
			}
			if (k < line.length())
				addWordsToList(list, line.substring(k, line.length()));
			k = 0;
		}
		br.close();
		return list;
	}

	private static void addWordsToList(Node<String> list, String sentence) {
		if (sentence.length() > 0) {
			String[] words = sentence.split("\\s+");
			for (int j = 0; j < words.length; j++) {
				if (words[j].length() > 0) {
					list.previous.next = newNode(words[j], list.previous, list);
					list.previous = list.previous.next;
				}
			}
		}
	}

	public static boolean onlyDots(Node<String> list) {
		Node<String> curr = list.next;
		while (curr != list) {
			if (!curr.value.equals("."))
				return false;
			curr = curr.next;
		}
		return true;
	}

	public static <E> int countElements(Node<Node<E>> list) {
		Node<Node<E>> head = list.next;
		int count = 0;
		while (head != list) {
			Node<E> subHead = head.value;
			while (subHead != null) {
				count++;
				subHead = subHead.next;
			}
			head = head.next;
		}
		return count;
	}

	public static int countDots(Node<String> list) {
		Node<String> head = list.next;
		int count = 0;
		while (head != list) {
			if (head.value.equals("."))
				count++;
			head = head.next;
		}
		return count;
	}

	public static boolean withoutDots(Node<String> list) {
		while (list != null) {
			if (list.value.equals("."))
				return false;
			list = list.next;
		}
		return true;
	}

	/*
	 * For non_circular lists with no sentinel
	 */



	public static <E> Node<E> emptyListWithoutSentinel() {
		return null;
	}

	public static Node<Integer> getListWithoutSentinel(int begin, int end,
			int step) {
		if (end < begin)
			return null;
		Node<Integer> list = new Node<Integer>();
		Node<Integer> cur = list;
		cur.value = begin;
		begin += step;
		for (; begin < end; begin += step) {
			Node<Integer> next = new Node<Integer>();
			cur.next = next;
			next.previous = cur;
			next.value = begin;
			cur = cur.next;
		}
		return list;
	}

	public static Node<Integer> getRandomList(int dimension) {
		Random r = new Random();
		Node<Integer> list = ListUtilTest.<Integer> emptyListWithoutSentinel();
		for (int i = 0; i < dimension; i++) {
			Node<Integer> novo = newNode(r.nextInt() % 40);
			novo.next = list;
			if (list != null)
				list.previous = novo;
			list = novo;
		}
		return list;
	}

	public static Node<Integer> getListWithoutSentinel(ArrayList<Integer> array) {
		if (array.size() == 0)
			return null;
		Node<Integer> list = new Node<Integer>();
		Node<Integer> cur = list;
		cur.value = array.get(0);
		for (int i = 1; i < array.size(); i++) {
			Node<Integer> next = new Node<Integer>();
			cur.next = next;
			next.previous = cur;
			next.value = array.get(i);
			cur = cur.next;
		}
		return list;

	}

	public static <E> Node<E> getNNode(int N, Node<E> list) {
		for (int i = 0; i < N; i++) {
			if (list == null)
				return null;
			list = list.next;
		}
		return list;

	}

	public static <E> boolean isSorted(Node<E> list, Node<E> last,
			Comparator<E> cmp) {
		Node<E> curr = list;
		if (curr == null || curr == last)
			return true;
		while (curr.next != last) {
			if (cmp.compare(curr.value, curr.next.value) > 0)
				return false;
			curr = curr.next;
		}
		return true;
	}
	public static <E> void print(Node<E> list, Node<E> last) {
		Node<E> curr = list;
		if (curr == null || curr == last)
			return ;
		while (curr != last) {
			System.out.print(curr.value + " ");
			curr = curr.next;
		}
	}

	/*
	 * 
	 * Generic Methods
	 */

	public static <E> Node<E> newNode(E v) {
		Node<E> result = new Node<E>();
		result.value = v;
		return result;
	}

	public static <E> Node<E> newNode(E v, Node<E> p, Node<E> n) {
		Node<E> result = newNode(v);
		result.previous = p;
		result.next = n;
		return result;
	}
}
