package hx.bankcheck.accountvalidator;

import static org.junit.Assert.assertTrue;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum13;

import org.junit.Test;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum13Test {
	
	@Test
	public void checksum13() throws ValidationException {
		ChecksumValidator cs = new Checksum13();
		
		int[][] correctNumbers = {
				{0,0,0,6,0,0,0,4,0,0},
				{0,0,0,0,0,6,0,0,0,4}	
		};
		
		assertTrue(cs.validate(correctNumbers[0]));
		assertTrue(cs.validate(correctNumbers[1]));
	}
	
}