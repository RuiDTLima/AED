package series.serie1;

import static org.junit.Assert.*;
import static series.serie1.Arrays.changeValueInMaxHeap;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ChangeValueInMaxHeapTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test 
	public void changeValueInMaxHeap_onEmptyHeap(){
		exception.expect(IllegalArgumentException.class);
		changeValueInMaxHeap(new int[0],0,0 ,3);	
	}
	
	@Test 
	public void changeValueInMaxHeap_withIllegalArguments(){
		exception.expect(IllegalArgumentException.class);
		for(int i =0;i<10;i++)
			changeValueInMaxHeap(new int[i],i, i,3);	
	}
	
	@Test 
	public void changeValueInMaxHeap_onHeapWithOneElement() {
		int[] maxHeap = { 1 };
		changeValueInMaxHeap( maxHeap, 1,0 ,3);	
	    assertTrue(isMaxHeap(maxHeap,1));
	}
	

	@Test 
	public void changeValueInMaxHeap_onHeap1() {
		int[] maxHeap= {8, 7, 6, 5, 4, 3, 2 };
		 changeValueInMaxHeap( maxHeap, 7,0,3 );	
		 assertTrue(isMaxHeap(maxHeap,7));
		 
	}
	
	@Test 
	public void changeValueInMaxHeap_onHeap2() {
		int[] maxHeap = {18, 15, 16, 10, 9, 12, 14, 3, 4, 5, 2 };
		changeValueInMaxHeap( maxHeap, 11, 3,20);	
		 assertTrue(isMaxHeap(maxHeap,11));
		 
	}
	
	@Test 
	public void changeValueInMaxHeap_onHeap3() {
		int[] maxHeap = {18, 15, 16, 10, 9, 12, 14, 3, 4, 5, 2 };
		changeValueInMaxHeap( maxHeap, 11, 1,1);	
		assertTrue(isMaxHeap(maxHeap,11));
		 
		 
	}
	
	public static boolean isMaxHeap(int[] v, int count){
		for(int i=count-1; i>=0;i--)
			if(v[i]>v[(i-1)/2]) return false;
		return true;	
	}
	
	

}
