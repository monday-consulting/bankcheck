package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;

import java.util.Arrays;

/**
 * Testclass for testing algorithm D5.
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class ChecksumD6Test extends AbstractChecksumTest {

	@Override
	public void testValidate() throws Throwable {
		ChecksumD6 c = new ChecksumD6();
		
		int[][] correct = {
				{ 0,0,0,0,0,0,3,4,0,9},
				{ 0,0,0,0,5,8,5,3,2,7},
				{ 0,0,0,1,6,5,0,5,1,3},
				
				{3,6,0,1,6,7,1,0,5,6},
				{4,4,0,2,0,0,1,0,4,6},
				{6,1,0,0,2,6,8,2,4,1},
				
				{7,0,0,1,0,0,0,6,8,1},
				{9,0,0,0,1,1,1,1,0,5},
				{9,0,0,1,2,9,1,0,0,5}
		};
		
		int[][] wrong = {
				{0,0,0,0,0,3,3,3,9,4},
				{0,0,0,0,5,9,5,7,9,5},
				{0,0,1,6,4,0,0,5,0,1},
				
				{3,6,1,5,0,7,1,2,3,7}, 
				{6,0,3,9,2,6,7,0,1,3},
				{6,0,3,9,3,1,6,0,1,4},

				{7,0,0,4,0,1,7,6,5,3},
				{9,0,0,2,7,2,0,0,0,7},
				{9,0,1,7,4,8,3,5,2,4}

		};
		
		for(int[] accountNumber : correct) {
			assertTrue(Arrays.toString(accountNumber), c.validate(accountNumber, null));
		}
		
		for(int[] accountNumber : wrong) {
			assertFalse(Arrays.toString(accountNumber), c.validate(accountNumber, null));
		}

	}
	
}
