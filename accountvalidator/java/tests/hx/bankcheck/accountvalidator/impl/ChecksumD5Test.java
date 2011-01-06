package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

import java.util.Arrays;

/**
 * Testclass for testing algorithm D5.
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class ChecksumD5Test extends AbstractChecksumTest {
	public void testValidate() throws Exception {
		int[][] correct = {
				{ 5,9,9,9,7,1,8,1,3,8 },
				{ 1,7,9,9,2,2,2,1,1,6 },
				{ 0,0,9,9,6,3,2,0,0,4 },
				{ 0,0,0,4,7,1,1,1,7,3 }, 
				{ 0,0,0,7,0,9,3,3,3,0 },
				{ 0,0,0,0,1,2,7,7,8,7 },
				{ 0,0,0,4,7,1,1,1,7,2 }, 
				{ 0,0,0,7,0,9,3,3,3,5 },
				{ 0,0,0,0,1,0,0,0,6,2 }, 
				{ 0,0,0,0,1,0,0,0,8,8 }
		};
		
		int[][] wrong = {
				{ 3,2,9,9,6,3,2,0,0,8 },
				{ 1,9,9,9,2,0,4,2,9,3 },
				{ 0,3,9,9,2,4,2,1,3,9 },
//				{ 0,0,0,4,7,1,1,1,7,2 }, // Richtig bei Variante 3
				{ 8,6,2,3,4,2,0,0,0,4 },
				{ 0,0,0,1,1,2,3,4,5,8 },
//				{ 8,6,2,3,4,1,0,0,0,0 }, // Richtig durch Variante 4
				{ 0,0,0,1,1,2,3,4,5,8 },
				{ 0,0,0,0,1,0,0,0,8,4 }, 
				{ 0,0,0,0,1,0,0,0,8,5 }
		};
		
		ChecksumValidator c = new ChecksumD5();
		
		for(int[] accountNumber : correct) {
			assertTrue(Arrays.toString(accountNumber), c.validate(accountNumber, null));
		}
		
		for(int[] accountNumber : wrong) {
			assertFalse(Arrays.toString(accountNumber), c.validate(accountNumber, null));
		}
	}
	
}
