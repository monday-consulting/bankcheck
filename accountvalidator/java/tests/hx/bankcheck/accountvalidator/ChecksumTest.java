package hx.bankcheck.accountvalidator;

import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;

public class ChecksumTest {

	@Test
	public void checksum00() throws Exception {
		IAccountChecksum cs = new Checksum00();

		int[][] correctNumbers = new int[][] {
				{0,0,0,9,2,9,0,7,0,1},
				{0,5,3,9,2,9,0,8,5,8},
				{0,0,0,1,5,0,1,8,2,4},
				{0,0,0,1,5,0,1,8,3,2}
		};
		checkNumbers(cs, correctNumbers, true);
		
		int[][] wrongNumbers = new int[][] {
				{1,2,3,4,5,6,7,8,9,0}
		};
		checkNumbers(cs, wrongNumbers, false);
	}
	
	@Test
	public void checksum01() throws Exception {
		//TODO
	}

	@Test
	public void checksum02() throws Exception {
		//TODO
	}
	
	@Test
	public void checksum03() throws Exception {
		// TODO should be ok when checksum01 is
	}

	@Test
	public void checksum04() throws Exception {
		// TODO should be ok when checksum02 is
	}

	@Test
	public void checksum05() throws Exception {
		// TODO should be ok when checksum01 is
	}
	
	@Test
	public void checksum06() throws Exception {
		IAccountChecksum cs = new Checksum06();
		
		int[][] correctNumbers = {
				{0,0,9,4,0,1,2,3,4,1},
				{5,0,7,3,3,2,1,0,1,0}
		};
		checkNumbers(cs, correctNumbers, true);
	}

	@Test
	public void checksum07() throws Exception {
		// TODO should be ok when checksum02 is
	}
	
	@Test
	public void checksum08() throws Exception {
		IAccountChecksum cs = new Checksum08();
		
		int[][] correctNumbers = new int[][] {
				{0,0,0,9,2,9,0,7,0,1},
				{0,5,3,9,2,9,0,8,5,8},
				{0,0,0,1,5,0,1,8,2,4},
				{0,0,0,1,5,0,1,8,3,2},
				{0,0,0,0,0,6,0,0,0,4}
		};
		checkNumbers(cs, correctNumbers, true);
		
		int[][] wrongNumbers = new int[][] {
				{1,2,3,4,5,6,7,8,9,0}
		};
		checkNumbers(cs, wrongNumbers, false);
		
		// Check all numbers < 60000
		for(int i=0; i<60000; i++) {
			int[] n = new int[10];
			int x = i;
			for(int c=0; c<5; c++) {
				n[9-c] = x % 10;
				x = x / 10;
			}
			assertFalse("Check failed for " + Arrays.toString(n), cs.validate(n));
		}
	}
	
	@Test
	public void checksum09() throws ValidationException {
		IAccountChecksum cs = new Checksum09();
		
		// Check all numbers < 100000, all would take too long.
		for(int i=0; i<100000l; i++) {
			int[] n = new int[10];
			int x = i;
			for(int c=0; c<10; c++) {
				n[9-c] = x % 10;
				x = x / 10;
			}
			assertTrue("Check failed for " + Arrays.toString(n), cs.validate(n));
		}
	}
	
	@Test
	public void checksum10() throws ValidationException {
		IAccountChecksum cs = new Checksum10();
		
		int[][] correctNumbers = {
				{0,0,1,2,3,4,5,0,0,8},
				{0,0,8,7,6,5,4,0,0,8}
		};
		
		checkNumbers(cs, correctNumbers, true);
	}
	
	@Test
	public void checksum11() {
		// TODO should be ok when checksum06 is
	}
	
	@Test
	public void checksum13() throws ValidationException {
		IAccountChecksum cs = new Checksum13();
		
		int[][] correctNumbers = {
				{0,0,0,6,0,0,0,4,0,0},
				{0,0,0,0,0,6,0,0,0,4}	
		};
		
		checkNumbers(cs, correctNumbers, true);
	}
	
	@Test
	public void checksum14() {
		// TODO should be ok when checksum02 is
	}
	
	@Test
	public void checksum15() {
		// TODO should be ok when checksum06 is
	}
	
	@Test
	public void checksum16() throws ValidationException {
		IAccountChecksum cs = new Checksum16();
		
		int[][] correctNumbers = {
				{0,0,9,4,0,1,2,3,4,1},
				{5,0,7,3,3,2,1,0,1,0},
				{0,0,9,4,0,1,2,3,4,4}
		};
		
		checkNumbers(cs, correctNumbers, true);
	}
	

	@Test
	public void checksum17() throws ValidationException {
		IAccountChecksum cs = new Checksum17();
		
		int[][] correctNumbers = {
				{ 0,4,4,6,7,8,6,0,4,0 }
		};
		
		checkNumbers(cs, correctNumbers, true);
	}
	
	
	private void checkNumbers(IAccountChecksum cs, int[][] numbers, boolean numbersAreValid) throws ValidationException {
		for(int[] number : numbers) {
			assertTrue(Arrays.toString(number)+" is not " + numbersAreValid, cs.validate(number) == numbersAreValid);
		}
	}
	
}
