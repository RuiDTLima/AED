package series.serie2;

import static series.serie2.Iterables.alternateEvenOdd;

import java.util.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AlternateEvenOddTest extends IterablesTest {
	static final Iterable<Integer> empty = Collections.<Integer>emptyList();
	static final List<Integer> 	seq = unmodifiable(Arrays.asList(1, 2, 3, 4, 5, 6, 7)),
								mix = unmodifiable(Arrays.asList(2, 8, 3, 5, 4, 6, 7)),
								odds = unmodifiable(Arrays.asList(1, 3, 5, 7, 9)),
								evens = unmodifiable(Arrays.asList(2, 4, 6, 8)),
								eqOdd = unmodifiable(Arrays.asList(1, 1, 1, 1)),
								eqEven = unmodifiable(Arrays.asList(2, 2, 2, 2));
	
	@Test 
	public void alternateEvenOdd_withEmptySequences(){
		assertIterableEmpty(alternateEvenOdd(empty));	
	}

	@Test 
	public void alternateEvenOdd_withOneElementSequence(){
		List<Integer> s1 = Collections.singletonList(1);
		assertIterableEquals(s1, alternateEvenOdd(s1));
		List<Integer> s2 = Collections.singletonList(2);
		assertIterableEmpty(alternateEvenOdd(s2));
	}
	
	@Test 
	public void alternateEvenOdd_allElementsInSequences(){
		assertIterableEquals(seq, alternateEvenOdd(seq));	
	}
	
	@Test 
	public void alternateEvenOdd_someElementsInSequences(){
		List<Integer> res = new ArrayList<Integer>();
		for (int i= 0; i < 3; ++i) 
			res.add(i);
		res.set(0, 3); res.set(1, 4); res.set(2, 7);
		assertIterableEquals(res, alternateEvenOdd(mix));
	}

	@Test 
	public void alternateEvenOdd_allElementsOdd(){
		List<Integer> s1 = Collections.singletonList(1);
		assertIterableEquals(s1, alternateEvenOdd(s1));	
	}

	@Test 
	public void alternateEvenOdd_allElementsEven(){
		List<Integer> s2 = Collections.singletonList(2);
		assertIterableEmpty(alternateEvenOdd(s2));	
	}
	
	@Test 
	public void alternateEvenOdd_allElementsEqualOdd(){
		List<Integer> s1 = Collections.singletonList(1);
		assertIterableEquals(s1, alternateEvenOdd(s1));	
	}

	@Test 
	public void alternateEvenOdd_allElementsEqualEven(){
		List<Integer> s2 = Collections.singletonList(2);
		assertIterableEmpty(alternateEvenOdd(s2));	
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test 
	public void alternateEvenOdd_RemoveElements(){
		Iterator<Integer> it = alternateEvenOdd(seq).iterator();	
		it.next();
		exception.expect(UnsupportedOperationException.class);
		exception.expectMessage("alternateEvenoOdd: remove not supported");
		it.remove();
	}
	
	@Test 
	public void alternateEvenOdd_withEmptySequencesObtainFirst(){
		Iterator<Integer> it = alternateEvenOdd(empty).iterator();	
		exception.expect(NoSuchElementException.class);
		exception.expectMessage("alternateEvenoOdd: no more elements");
		it.next();
		
	}

}
