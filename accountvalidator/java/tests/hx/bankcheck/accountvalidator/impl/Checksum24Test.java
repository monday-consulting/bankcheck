package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum24;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum24Test extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {
		Checksum24 cs = new Checksum24();
		
		int[] n1 = ChecksumUtils.parseAccountNumber(138301);
		int[] n2 = ChecksumUtils.parseAccountNumber(1306118605);
		int[] n3 = ChecksumUtils.parseAccountNumber(3307118608l);
		int[] n4 = ChecksumUtils.parseAccountNumber(9307118603l);
		
		assertTrue(cs.validate(n1));
		assertTrue(cs.validate(n2));
		assertTrue(cs.validate(n3));
		assertTrue(cs.validate(n4));
	}

}
