package hx.bankcheck.accountvalidator;

import static org.junit.Assert.assertTrue;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum19;

import org.junit.Test;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum19Test {
	
	@Test
	public void checksum19() throws ValidationException {
		ChecksumValidator cs = new Checksum19();
		
		int[][] correctNumbers = {
				{0,2,4,0,3,3,4,0,0,0}, 
				{0,2,0,0,5,2,0,0,1,6}
		};
		
		assertTrue(cs.validate(correctNumbers[0],null));
		assertTrue(cs.validate(correctNumbers[1],null));
	}
	
}