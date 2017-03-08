package series.serie1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static series.serie1.Arrays.sortIPv4Addresses;

public class SortIPv4Test {

	@Test
	public void oneElement() {
		String[] IPs = generateRandomIPs(1); 
		sortIPv4Addresses(IPs, 0, 0);
		Assert.assertEquals(IPs[0], IPs[0]);
	}
	
	@Test
	public void twoRandomElements() {
		String[] IPs = generateRandomIPs(2);
		sortIPv4Addresses(IPs, 0, 1);
		Assert.assertTrue(lessOrEqualThan(IPs[0], IPs[1]));
	}

	@Test
	public void threeRandomElements() {
		String[] IPs = generateRandomIPs(3); 
		sortIPv4Addresses(IPs, 0, 2);
		Assert.assertTrue(lessOrEqualThan(IPs[0], IPs[1]));
		Assert.assertTrue(lessOrEqualThan(IPs[1], IPs[2]));
	}

	@Test
	public void threeLexicographicElements() {
		String[] IPs = {"9.7.8.50", "10.1.2.7", "11.0.0.2"};
		sortIPv4Addresses(IPs, 0, 2);
		Assert.assertTrue(lessOrEqualThan(IPs[0], IPs[1]));
		Assert.assertTrue(lessOrEqualThan(IPs[1], IPs[2]));
	}
	
	@Test
	public void manyElements() {
		String[] IPs = generateRandomIPs(2048); 
		sortIPv4Addresses(IPs, 0, IPs.length-1);
		for (int i=1; i < IPs.length; ++i)
			Assert.assertTrue(lessOrEqualThan(IPs[i-1], IPs[i]));
	}

	private boolean lessOrEqualThan(String ip1, String ip2) {
		String[] partsIP1 = ip1.split("[.]"); 
		String[] partsIP2 = ip2.split("[.]");
		long ip1Int = Long.parseLong(partsIP1[0]) << 24 | 
					  Long.parseLong(partsIP1[1]) << 16 | 
					  Long.parseLong(partsIP1[2]) << 8 | 
					  Long.parseLong(partsIP1[3]); 
		
		long ip2Int = Long.parseLong(partsIP2[0]) << 24 | 
				      Long.parseLong(partsIP2[1]) << 16 | 
				      Long.parseLong(partsIP2[2]) << 8 | 
				      Long.parseLong(partsIP2[3]); 
		return ip1Int <= ip2Int;
	}
	
	private String[] generateRandomIPs(int numberOfIPs) {
		String[] IPs = new String[numberOfIPs];
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<numberOfIPs; ++i) {
			for (int j=0; j<4; ++j) {
				sb.append(rnd.nextInt(256));
				if (j!=3) sb.append(".");
			}
			IPs[i] = sb.toString();
			sb.delete(0, sb.length());
		}
		return IPs;
	}

}

