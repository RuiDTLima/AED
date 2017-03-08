package series.serie2;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class IterablesTest {

	protected static <T> List<T> singleton( T v ) {
		return Collections.singletonList( v );
	}

	protected static <T> List<T> unmodifiable( List<T> s ) {
		return Collections.unmodifiableList(s);
	}
	
	protected static <T> void assertIterableEquals( Iterable<T> expected, Iterable<T>  result) {
		Iterator<T> itE= expected.iterator(), 
		            itR= result.iterator();
		while ( itE.hasNext() ) {
			assertTrue( itR.hasNext() );
			assertEquals( itE.next(), itR.next() );
		}
		assertFalse( itR.hasNext() );
	}
	
	protected static <T> void assertIterableEmpty( Iterable<T> result ) {
		assertFalse( result.iterator().hasNext() );
	}
}
