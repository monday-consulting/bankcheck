package hx.bankcheck.accountvalidator;

import static org.junit.Assert.assertTrue;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum16;

import org.junit.Test;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum16Test {
	
	@Test
	public void checksum16() throws ValidationException {
		ChecksumValidator cs = new Checksum16();
		
		int[][] correctNumbers = {
				{0,0,9,4,0,1,2,3,4,1},
				{5,0,7,3,3,2,1,0,1,0},
				{0,0,9,4,0,1,2,3,4,4}
		};

		assertTrue(cs.validate(correctNumbers[0]));
		assertTrue(cs.validate(correctNumbers[1]));
		assertTrue(cs.validate(correctNumbers[2]));
	}
	
}