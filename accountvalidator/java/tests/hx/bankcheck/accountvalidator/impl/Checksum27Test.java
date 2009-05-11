package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum27;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum27Test extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {
		Checksum27 cs = new Checksum27();
		
		int[] n1 = { 2,8,4,7,1,6,9,4,8,8 };
		
		assertTrue(cs.validate(n1));
	}
	
}
