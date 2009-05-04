package hx.bankcheck.accountvalidator;

import static org.junit.Assert.assertTrue;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum10;

import org.junit.Test;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum10Test {
	
	@Test
	public void checksum10() throws ValidationException {
		ChecksumValidator cs = new Checksum10();
		
		int[][] correctNumbers = {
				{0,0,1,2,3,4,5,0,0,8},
				{0,0,8,7,6,5,4,0,0,8}
		};
		
		assertTrue(cs.validate(correctNumbers[0]));
		assertTrue(cs.validate(correctNumbers[1]));
	}
	
}