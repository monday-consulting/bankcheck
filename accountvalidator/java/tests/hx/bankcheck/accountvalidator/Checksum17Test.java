package hx.bankcheck.accountvalidator;

import static org.junit.Assert.assertTrue;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum17;

import org.junit.Test;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum17Test {
	
	@Test
	public void checksum17() throws ValidationException {
		ChecksumValidator cs = new Checksum17();
		
		int[][] correctNumbers = {
				{ 0,4,4,6,7,8,6,0,4,0 }
		};
		
		assertTrue(cs.validate(correctNumbers[0],null));
	}
	
	
}