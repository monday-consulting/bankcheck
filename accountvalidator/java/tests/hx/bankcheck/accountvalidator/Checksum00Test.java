package hx.bankcheck.accountvalidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hx.bankcheck.accountvalidator.impl.Checksum00;

import org.junit.Test;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 * $Id$
 */
public class Checksum00Test {
	
	@Test
	public void checksum00() throws Exception {
		ChecksumValidator cs = new Checksum00();

		int[][] correctNumbers = new int[][] {
				{0,0,0,9,2,9,0,7,0,1},
				{0,5,3,9,2,9,0,8,5,8},
				{0,0,0,1,5,0,1,8,2,4},
				{0,0,0,1,5,0,1,8,3,2}
		};
		
		assertTrue(cs.validate(correctNumbers[0],null));
		assertTrue(cs.validate(correctNumbers[1],null));
		assertTrue(cs.validate(correctNumbers[2],null));
		assertTrue(cs.validate(correctNumbers[3],null));
		
		int[][] wrongNumbers = new int[][] {
				{1,2,3,4,5,6,7,8,9,0}
		};
		assertFalse(cs.validate(wrongNumbers[0],null));
	}
	
}