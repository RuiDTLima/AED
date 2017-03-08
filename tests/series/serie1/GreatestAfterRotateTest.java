package series.serie1;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static series.serie1.Arrays.greatestAfterRotate;

public class GreatestAfterRotateTest {

	@Test
	public void test_getMaximumIncreasingSubsequence_with_one() {
		int[] array = new int[]{ 55};
		assertEquals(55,greatestAfterRotate(array, 0, 0));}

	@Test
	public void test_getMaximumIncreasingSubsequence_with_two() {
		int[] array = new int[]{40, 55};
		assertEquals(55,greatestAfterRotate(array, 0, 1));
		array = new int[]{60, 55};
		assertEquals(60,greatestAfterRotate(array, 0, 1));
	}

	@Test
	public void test_getMaximumIncreasingSubsequence_with_three(){
		int[] array = new int[]{57, 60, 55};
		assertEquals(60,greatestAfterRotate(array, 0, 2));
		array = new int[]{60, 51, 54};
		assertEquals(60,greatestAfterRotate(array, 0, 2));
		array = new int[]{60, 65, 70};
		assertEquals(70,greatestAfterRotate(array, 0, 2));
	}

	@Test
	public void test_getMaximumIncreasingSubsequence_without_shifted_positions() {
		int[] array = {7, 22, 35, 42, 47, 55};
		assertEquals(55, greatestAfterRotate(array, 0, 5));
		array = new int[]{55, 0, 1, 2, 3, 7, 22, 35, 42, 47, 48, 60, 63};
		assertEquals(48, greatestAfterRotate(array, 1, 10));
	}

	@Test
	public void test_getMaximumIncreasingSubsequence_with_one_shifted_positions(){
		int[] array = { 55, 7, 22, 35, 42, 47 };
		assertEquals(55,greatestAfterRotate(array, 0, 5));
		array = new int[]{ 55, 0, 1, 2, 3, 7, 22, 35, 42, 47, 48, 60, 63};
		assertEquals(55,greatestAfterRotate(array, 0, 10));
	}

	@Test
	public void test_getMaximumIncreasingSubsequence_with_two_shifted_positions(){
		int[] array = { 60, 35, 42, 5, 15, 27, 29 };
		assertEquals(42,greatestAfterRotate(array, 1, 6));
	}
	
	@Test
	public void test_getMaximumIncreasingSubsequence_with_four_shifted_positions(){
		int[] array = { 27, 29, 35, 42, 5, 15, 18 };
		assertEquals(42,greatestAfterRotate(array, 0, 6));
	}
	
	@Test
	public void test_getMaximumIncreasingSubsequence_with_also_four_shifted_positions(){
		int[] array = { 55, 57, 59, 60, 1, 7, 22, 35, 42, 47, 48, 50,53};
		assertEquals(60,greatestAfterRotate(array, 0, 12));
		
	}
}
