package hx.bankcheck.accountvalidator;

import static org.junit.Assert.assertTrue;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.impl.Checksum26;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;

import org.junit.Test;

/**
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class Checksum26Test {

	@Test
	public void validate() throws ValidationException {
		Checksum26 cs = new Checksum26();
		
		int[] n1 = ChecksumUtils.parseAccountNumber(520309001l);
		int[] n2 = ChecksumUtils.parseAccountNumber(1111118111l);
		int[] n3 = ChecksumUtils.parseAccountNumber(5501024l);
		
		assertTrue(cs.validate(n1));
		assertTrue(cs.validate(n2));
		assertTrue(cs.validate(n3));
	}
	
}
