package series.serie1;


import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static series.serie1.Arrays.isPermutation;

public class IsPermutationTest {

	@Test
	public void test_isPermutationSequence_with_two() {
		int[] array1 = new int[]{40, 55};
		int[] array2 = new int[]{55, 40};
		assertTrue(isPermutation(array1, 0, 1, array2, 0, 1));
	}

	@Test
	public void test_isPermutationSequence_with_three(){
		int[] array1 = new int[]{40, 55, 77};
		int[] array2 = new int[]{77, 40, 55};
		assertTrue(isPermutation(array1, 0, 2, array2, 0, 2));
	}

	@Test
	public void test_notIsPermutationSequence_with_three(){
		int[] array1 = new int[]{40, 55, 77};
		int[] array2 = new int[]{40, 55, 70};
		assertFalse(isPermutation(array1, 0, 2, array2, 0, 2));
	}

	@Test
	public void test_notIsPermutationSequence_with_different_size(){
		int[] array1 = new int[]{40, 55, 77};
		int[] array2 = new int[]{40, 55, 77, 40};
		assertFalse(isPermutation(array1, 0, 2, array2, 0, 3));
	}
	@Test
	public void test_isPermutationSequence_with_repetitions() {
		int[] array1 = {7, 22, 35, 42, 22, 55, 47, 55};
		int[] array2 = {42, 47, 55, 22, 55, 7, 22, 35};
		assertTrue(isPermutation(array1, 0, 7, array2, 0, 7));
	}
	@Test
	public void test_notIsPermutationSequence_with_repetitions() {
		int[] array1 = {7, 22, 35, 42, 22, 55, 47, 55};
		int[] array2 = {42, 47, 42, 35, 55, 7, 22, 35};
		assertFalse(isPermutation(array1, 0, 7, array2, 0, 7));
	}

	@Test
	public void test_isPermutationSubsequence_with_repetitions() {
		int[] array1 = {7, 22, 35, 42, 22, 35, 47, 55};
		int[] array2 = {22, 42, 47, 42, 22, 35, 35, 35};
		assertTrue(isPermutation(array1, 2, 5, array2, 3, 6));
	}

	@Test
	public void test_notIsPermutationSubsequence_with_repetitions() {
		int[] array1 = {7, 22, 35, 42, 22, 35, 42, 55};
		int[] array2 = {7, 22, 42, 42, 22, 35, 35, 55};
		assertFalse(isPermutation(array1, 2, 5, array2, 2, 5));
	}
}
