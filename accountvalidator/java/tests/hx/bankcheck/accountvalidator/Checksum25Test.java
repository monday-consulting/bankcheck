package hx.bankcheck.accountvalidator;

import static org.junit.Assert.assertTrue;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum25;

import org.junit.Test;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum25Test {

	@Test
	public void validate() throws ValidationException {
		Checksum25 cs = new Checksum25();
		
		int[] n = { 0, 5, 2, 1, 3, 8, 2, 1, 8, 1 };
		
		assertTrue(cs.validate(n));
	}
	
}
