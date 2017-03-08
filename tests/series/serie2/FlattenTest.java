package series.serie2;

import org.junit.Test;

import static series.serie2.Iterables.flatten;
import java.util.*;

public class FlattenTest extends IterablesTest {

	static Iterable<Integer> se = Collections.<Integer>emptyList();

	@Test 
	public void concat_OnEmptySequence(){
		// One sequence empty
		List< Iterable< Integer >> seq = new ArrayList< Iterable<Integer>>();
		assertIterableEmpty( flatten( unmodifiable ( seq ) ) );	
		
		// Four sequences empty
		for ( int i= 0; i < 4; ++ i )
			seq.add( se );
		assertIterableEmpty( flatten( unmodifiable(seq) ) );	
	}

	@Test 
	public void concat_OneSequenceOfOneElementSequence(){
		List< Iterable< Integer >> seq = new ArrayList< Iterable<Integer>>();
		// One sequence of one element with value 5
		Iterable<Integer> s1= singleton( 5 );
		seq.add( s1 );
		assertIterableEquals( s1, flatten( unmodifiable(seq) ) );
	}

	@Test 
	public void concat_TwoSequenceOfOneElement(){
		List< Iterable< Integer >> seq = new ArrayList< Iterable<Integer>>();
		Collection<Integer> expected = new ArrayList<Integer>();
		// Two sequence of one element with value [ 1, 2 ]
		for (int v = 1; v <= 2; ++v) {
			seq.add( singleton( v ));
			expected.add( v );
		}
		assertIterableEquals( expected, flatten( unmodifiable( seq ) ) );	
	}

	@Test 
	public void concat_TwoSequenceButOneIsEmpty(){
		LinkedList< Iterable< Integer >> seq = new LinkedList< Iterable<Integer>>();
		Iterable<Integer> seq2 = singleton( 4 );
		// One empty sequence followed by one sequence of one element
		seq.add( se ); seq.add( seq2 );
		assertIterableEquals( seq2, flatten( unmodifiable ( seq ) ) );
		// One sequence of one element followed by one empty sequence		
		seq.removeFirst();	seq.add( se );
		assertIterableEquals( seq2, flatten( unmodifiable( seq ) ) );
	}
	
	@Test 
	public void concat_NSequenceOfNElements(){
		List< Iterable< Integer >> seq;
		List< Integer > seq2, expected;
		for ( int n= 0; n <= 3; ++n ) {
			// Five sequences of same size (0, 1, 2 and 3 elements)
			seq = new ArrayList< Iterable<Integer>>();
			expected = new ArrayList<Integer>();
			int v= 7;
			for (int i= 0; i < 5; ++i) {
				seq2 = new ArrayList< Integer>();
				for( int j= 0; j < n; ++ j, v+=3 ) {
					seq2.add( v );
					expected.add( v );
				}
				seq.add( unmodifiable( seq2 ) );
			}
			assertIterableEquals( expected, flatten( unmodifiable ( seq ) ) );
		}
	}

	@Test 
	public void concat_NSequenceOfNElementsInterleaveEmptySequences(){
		List< Iterable< Integer >> seq;
		List<Integer> seq2, expected;
		for ( int n= 1; n <= 3; ++n ) {
			seq = new ArrayList< Iterable<Integer>>();
			expected = new ArrayList<Integer>();
			int v= 150;
			// Five sequences of same size (1, 2 and 3 elements) interleave of empty sequences 
			for( int j= 0; j < n; ++ j)	seq.add( se );
			for (int i= 0; i < 5; ++i) {
				seq2 = new ArrayList< Integer>();
				for( int j= 0; j < n; ++ j) {
					seq2.add( v-=3 );
					expected.add( v );
				}
				seq.add( unmodifiable( seq2 ) );
			}
			for( int j= 0; j < n; ++ j)	seq.add( se );
			assertIterableEquals( expected, flatten( unmodifiable ( seq ) ) );
		}
	}

	@Test 
	public void concat_NSequenceOfVariableDimension(){
		int [] values = { 23, 3, 17, 1, 25 };
		List< Iterable< Integer >> seq = new ArrayList< Iterable<Integer> >();
		List<Integer> seq2, expected = new ArrayList<Integer>();;
		// Five sequences of 1, 2, 3, 4, and 5 elements
		for (int i= 0; i < values.length; ++i) {
			seq2 = new ArrayList< Integer>();
			for( int j= 0; j <= i; ++ j) {
				seq2.add( values[j] );
				expected.add( values[j] );
			}
			seq.add( unmodifiable( seq2) );
		}
		assertIterableEquals( expected, flatten( unmodifiable( seq ) ) );
	}

}
