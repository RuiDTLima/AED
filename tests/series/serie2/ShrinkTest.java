package series.serie2;

import series.serie2.Node;
import org.junit.Test;
import static org.junit.Assert.*;
import static series.serie2.Utils.shrink;
import static series.serie2.HashTableTest.*;

	public class ShrinkTest {
				
		private static final int LENGTH = 64;
		private static final int HALF_LENGTH = 32;
		Node<Integer>[] empty;
		Node<Integer>[] hashTable;
		Node<Integer>[] hashTableCopy;
		Node<Integer>[] halfHashTable;
		
		@Test
		public void shrink_with_empty_table(){
			empty = emptyHashTable(LENGTH);
			assertTrue(isEmptyHastTable(shrink(empty)));
			assertEquals(HALF_LENGTH, shrink(empty).length);
		}
		
		@Test
		public void shrink_test_dim_new_table(){
			int dim = LENGTH;
			for (int i = dim; i >= 2; i/=2) {
				hashTable = getHashTable(1, 1, LENGTH, i);
				assertEquals(i/2, shrink(hashTable).length);
			}
		}
		
		@Test
		public void shrink_collision_on_same_position(){
			hashTable = getHashTable(LENGTH, LENGTH, LENGTH*10, LENGTH);
			hashTableCopy = getHashTable(LENGTH, LENGTH, LENGTH*10, LENGTH);
			int idx = index(hashTable, LENGTH);
			halfHashTable = shrink(hashTable);
			int jdx = index(halfHashTable, LENGTH);
			assertTrue(sameElements(hashTableCopy[idx], halfHashTable[jdx]));
		}

		@Test
		public void shrink_collision_on_all_positions_serial_keys(){
			int dim = LENGTH;
			for (int i = dim; i >= 2; i/=2) {
				hashTable = getHashTable(1, 1, LENGTH, i);
				hashTableCopy = getHashTable(1, 1, LENGTH, i);
				halfHashTable = shrink(hashTable);
				for (int j = 0; j < i/2; j++)
					assertTrue(sameElements(hashTableCopy[j], hashTableCopy[j+i/2], halfHashTable[j]));
			}
		}
		
		@Test
		public void shrink_collision_on_all_positions_alternate_odd_keys(){
			int dim = LENGTH;
			for (int i = dim; i >= 2; i/=2) {
				hashTable = getHashTable(1, 2, LENGTH, i);
				hashTableCopy = getHashTable(1, 2, LENGTH, i);
				halfHashTable = shrink(hashTable);
				for (int j = 0; j < i/2; j++)
					assertTrue(sameElements(hashTableCopy[j], hashTableCopy[j+i/2], halfHashTable[j]));
			}
		}
		
		@Test
		public void shrink_collision_on_all_positions_alternate_even_keys(){
			int dim = LENGTH;
			for (int i = dim; i >= 2; i/=2) {
				hashTable = getHashTable(2, 2, LENGTH, i);
				hashTableCopy = getHashTable(2, 2, LENGTH, i);
				halfHashTable = shrink(hashTable);
				for (int j = 0; j < i/2; j++)
					assertTrue(sameElements(hashTableCopy[j], hashTableCopy[j+i/2], halfHashTable[j]));
			}
		}
	}

