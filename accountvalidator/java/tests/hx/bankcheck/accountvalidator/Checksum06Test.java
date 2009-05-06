package hx.bankcheck.accountvalidator;

import static org.junit.Assert.assertTrue;
import hx.bankcheck.accountvalidator.impl.Checksum06;

import org.junit.Test;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum06Test {
	
	@Test
	public void checksum06() throws Exception {
		ChecksumValidator cs = new Checksum06();
		
		int[][] correctNumbers = {
				{0,0,9,4,0,1,2,3,4,1},
				{5,0,7,3,3,2,1,0,1,0}
		};
		
		assertTrue(cs.validate(correctNumbers[0],null));
		assertTrue(cs.validate(correctNumbers[1],null));
	}
	
}