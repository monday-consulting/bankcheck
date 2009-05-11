package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum26;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum26Test extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws ValidationException {
		Checksum26 cs = new Checksum26();
		
		int[] n1 = ChecksumUtils.parseAccountNumber(520309001l);
		int[] n2 = ChecksumUtils.parseAccountNumber(1111118111l);
		int[] n3 = ChecksumUtils.parseAccountNumber(5501024l);
		
		assertTrue(cs.validate(n1));
		assertTrue(cs.validate(n2));
		assertTrue(cs.validate(n3));
	}
	
}
