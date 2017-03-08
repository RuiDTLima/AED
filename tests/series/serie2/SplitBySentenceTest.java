package series.serie2;


import java.io.IOException;

import series.serie2.Node;
import org.junit.Test;

import static org.junit.Assert.*;
import static series.serie2.ListUtils.splitBySentence;

public class SplitBySentenceTest {

	@Test
	public void splitBySentence_emptyList() {
		Node<String> list = ListUtilTest.emptyListWithSentinel();
		Node<Node<String>> result = splitBySentence(list);
		assertTrue(ListUtilTest.isEmptyListWithSentinel(result));
		assertEquals(0, ListUtilTest.countElements(result));
	}

	@Test
	public void splitBySentence_withOneWord() throws IOException {
		Node<String> list = ListUtilTest.getListFromFile("OneWord.txt");
		Node<Node<String>> result = splitBySentence(list);
		assertTrue(ListUtilTest.withoutDots(result.next.value));
		assertEquals(1, ListUtilTest.countElements(result));
	}

	@Test
	public void splitBySentence_withOneWordAndOneDot() throws IOException {
		Node<String> list = ListUtilTest.getListFromFile("OneWordAndDot.txt");
		Node<Node<String>> result = splitBySentence(list);
		assertTrue(ListUtilTest.withoutDots(result.next.value));
		assertTrue(ListUtilTest.onlyDots(list));
		assertEquals(1, ListUtilTest.countElements(result));
	}

	@Test
	public void splitByUnit_oneSingleElementInEachList() throws IOException {
		Node<String> list = ListUtilTest
				.getListFromFile("OneWordBySentence.txt");
		Node<Node<String>> result = splitBySentence(list);
		assertTrue(ListUtilTest.onlyDots(list));
		Node<Node<String>> curr = result.next;
		while (curr != result) {
			assertTrue(ListUtilTest.withoutDots(curr.next.value));
			curr = curr.next;
		}
		assertEquals(3, ListUtilTest.countElements(result));

	}
	
	@Test
	public void splitByUnit_WithMoreDots() throws IOException {
		Node<String> list = ListUtilTest.getListFromFile("WithMoreDots.txt");
		Node<Node<String>> result = splitBySentence(list);
		assertTrue(ListUtilTest.onlyDots(list));
		Node<Node<String>> curr = result.next;
		while (curr != result) {
			assertTrue(ListUtilTest.withoutDots(curr.next.value));
			curr = curr.next;
		}
		assertEquals(3, ListUtilTest.countElements(result));
		assertEquals(6, ListUtilTest.countDots(list));

	}


	@Test
	public void splitByUnit_MoreThanOneElementInEachList() throws IOException {
		Node<String> list = ListUtilTest.getListFromFile("carochinhaShort.txt");
		Node<Node<String>> result = splitBySentence(list);
		assertTrue(ListUtilTest.onlyDots(list));
		Node<Node<String>> curr = result.next;
		while (curr != result) {
			assertTrue(ListUtilTest.withoutDots(curr.next.value));
			curr = curr.next;
		}
		assertEquals(62, ListUtilTest.countElements(result));

	}

	@Test
	public void splitByUnit_MoreThanOneElementInEachListAndWithOtherPonctuation()
			throws IOException {
		Node<String> list = ListUtilTest.getListFromFile("carochinha.txt");
		Node<Node<String>> result = splitBySentence(list);	
		assertTrue(ListUtilTest.onlyDots(list));
		Node<Node<String>> curr = result.next;
		while (curr != result) {
			assertTrue(ListUtilTest.withoutDots(curr.next.value));
			curr = curr.next;
		}
		assertEquals(1137, ListUtilTest.countElements(result));

	}
	
	
	
}
