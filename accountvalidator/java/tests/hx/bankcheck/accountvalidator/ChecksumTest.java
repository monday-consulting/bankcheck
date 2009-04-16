package hx.bankcheck.accountvalidator;

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
		// TODO should be fine when checksum01 is
	}

	@Test
	public void checksum04() throws Exception {
		// TODO should be fine when checksum02 is
	}

	@Test
	public void checksum05() throws Exception {
		// TODO should be fine when checksum01 is
	}
	
	private void checkNumbers(IAccountChecksum cs, int[][] numbers, boolean numbersAreValid) throws ValidationException {
		for(int[] number : numbers) {
			assertTrue(cs.validate(number) == numbersAreValid);
		}
	}
	
}
