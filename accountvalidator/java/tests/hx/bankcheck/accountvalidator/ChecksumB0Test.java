package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.ChecksumB0;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm B0.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB0Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		ChecksumB0 checksum = new ChecksumB0();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 1, 1, 9, 7, 4, 2, 3, 1, 6, 2 };
		int[] validAccountNumberAlternative1_2 = { 1, 0, 0, 0, 0, 0, 0, 6, 0, 6 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 8, 1, 3, 7, 4, 2, 3, 2, 6,
				0 };
		int[] invalidAccountNumberAlternative1_2 = { 0, 6, 0, 0, 0, 0, 0, 6, 0,
				6 };
		int[] invalidAccountNumberAlternative1_3 = { 0, 0, 5, 1, 2, 3, 4, 3, 0,
				9 };

		// Valid account numbers for alternative 2
		int[] validAccountNumberAlternative2_1 = { 1,0,0,0,0,0,0,4,0,6 };
		int[] validAccountNumberAlternative2_2 = { 1,0,0,0,0,0,0,4,0,6 };
		int[] validAccountNumberAlternative2_3 = { 1,1,2,6,9,3,9,7,2,4 };
		int[] validAccountNumberAlternative2_4 = { 1,1,9,7,4,2,3,4,6,0 };

		// Invalid account numbers for alternative 2
		int[] invalidAccountNumberAlternative2_1 = { 1,0,0,0,0,0,0,4,0,5 };
		int[] invalidAccountNumberAlternative2_2 = { 1,0,3,5,7,9,1,5,3,9 };
		int[] invalidAccountNumberAlternative2_3 = { 8,0,3,5,7,9,1,5,3,2 };
		int[] invalidAccountNumberAlternative2_4 = { 0,5,3,5,7,9,1,8,3,0 };
		int[] invalidAccountNumberAlternative2_5 = { 0,0,5,1,2,3,4,9,0,1 };

		// Should be valid using alternative 1
		assertTrue((checksum.validate(validAccountNumberAlternative1_1))
				&& (checksum.getAlternative() == 0));
		assertTrue((checksum.validate(validAccountNumberAlternative1_2))
				&& (checksum.getAlternative() == 0));

		// Should be invalid using alternative 1
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_1))
				&& (checksum.getAlternative() == 0));
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_2))
				&& (checksum.getAlternative() == 0));
		assertFalse((checksum.validate(invalidAccountNumberAlternative1_3))
				&& (checksum.getAlternative() == 0));

		// Should be valid using alternative 2
		assertTrue((checksum.validate(validAccountNumberAlternative2_1))
				&& (checksum.getAlternative() == 1));
		assertTrue((checksum.validate(validAccountNumberAlternative2_2))
				&& (checksum.getAlternative() == 1));
		assertTrue((checksum.validate(validAccountNumberAlternative2_3))
				&& (checksum.getAlternative() == 1));
		assertTrue((checksum.validate(validAccountNumberAlternative2_4))
				&& (checksum.getAlternative() == 1));

		// Should be invalid using alternative 2
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_1))
				&& (checksum.getAlternative() == 1));
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_2))
				&& (checksum.getAlternative() == 1));
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_3))
				&& (checksum.getAlternative() == 1));
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_4))
				&& (checksum.getAlternative() == 1));
		assertFalse((checksum.validate(invalidAccountNumberAlternative2_5))
				&& (checksum.getAlternative() == 1));

	}
}
