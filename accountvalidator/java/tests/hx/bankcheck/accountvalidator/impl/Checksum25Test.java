package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum25;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum25Test extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {
		Checksum25 cs = new Checksum25();
		
		int[] n = { 0, 5, 2, 1, 3, 8, 2, 1, 8, 1 };
		
		assertTrue(cs.validate(n));
	}
	
}
