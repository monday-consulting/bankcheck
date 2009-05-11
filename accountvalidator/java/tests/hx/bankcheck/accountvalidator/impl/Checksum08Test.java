package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.impl.Checksum08;

import java.util.Arrays;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum08Test extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws Exception {
		ChecksumValidator cs = new Checksum08();
		
		int[][] correctNumbers = new int[][] {
				{0,0,0,9,2,9,0,7,0,1},
				{0,5,3,9,2,9,0,8,5,8},
				{0,0,0,1,5,0,1,8,2,4},
				{0,0,0,1,5,0,1,8,3,2},
				{0,0,0,0,0,6,0,0,0,4}
		};
		assertTrue(cs.validate(correctNumbers[0],null));
		assertTrue(cs.validate(correctNumbers[1],null));
		assertTrue(cs.validate(correctNumbers[2],null));
		assertTrue(cs.validate(correctNumbers[3],null));
		assertTrue(cs.validate(correctNumbers[4],null));
		
		int[][] wrongNumbers = new int[][] {
				{1,2,3,4,5,6,7,8,9,0}
		};
		assertFalse(cs.validate(wrongNumbers[0],null));

		// Check all numbers < 60000
		for(int i=0; i<60000; i++) {
			int[] n = new int[10];
			int x = i;
			for(int c=0; c<5; c++) {
				n[9-c] = x % 10;
				x = x / 10;
			}
			assertFalse("Check failed for " + Arrays.toString(n), cs.validate(n,null));
		}
	}
	
}