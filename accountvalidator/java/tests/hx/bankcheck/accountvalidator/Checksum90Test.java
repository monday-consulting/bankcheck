package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.Checksum90;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm 90.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum90Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		Checksum90 checksum = new Checksum90();

		// Valid account numbers for testing method A
		int[] validAccountNumberA1 = { 0, 0, 0, 1, 9, 7, 5, 6, 4, 1 };
		int[] validAccountNumberA2 = { 0, 0, 0, 1, 9, 8, 8, 6, 5, 4 };
		int[] validAccountNumberNotUsingA = { 0,0, 0, 0, 6, 5, 4, 3, 2, 1 };

		// Valid account numbers for testing method B
		int[] validAccountNumberB1 = { 0, 0, 0, 0, 8, 6, 3, 5, 3, 0 };
		int[] validAccountNumberB2 = { 0, 0, 0, 0, 7, 8, 4, 4, 5, 1 };
		int[] validAccountNumberNotUsingB1 = { 0, 0, 0, 0, 9, 9, 7, 6, 6, 4 };
		int[] validAccountNumberNotUsingB2 = { 0, 0, 0, 0, 8, 6, 3, 5, 3, 6 };

		// Valid account numbers for testing method C
		int[] validAccountNumberC1 = { 0, 0, 0, 0, 6, 5, 4, 3, 2, 1 };
		int[] validAccountNumberC2 = { 0, 0, 0, 0, 8, 2, 4, 4, 9, 1 };
		int[] validAccountNumberNotUsingC1 = { 0, 0, 0, 0, 8, 2, 0, 4, 8, 4 };
		int[] validAccountNumberNotUsingC2 = { 0, 0, 0, 0, 6, 5, 4, 3, 2, 8 };

		// Valid account numbers for testing method D
		int[] validAccountNumberD1 = { 0, 0, 0, 0, 6, 7, 7, 7, 4, 7 };
		int[] validAccountNumberD2 = { 0, 0, 0, 0, 8, 4, 0, 5, 0, 7 };
		int[] validAccountNumberNotUsingD1 = { 0, 0, 0, 0, 6, 7, 7, 7, 4, 2 };
		int[] validAccountNumberNotUsingD2 = { 0, 0, 0, 0, 7, 2, 6, 3, 9, 1 };

		// Valid account numbers for testing method E
		int[] validAccountNumberE1 = { 0, 0, 0, 0, 9, 9, 6, 6, 6, 3 };
		int[] validAccountNumberE2 = { 0, 0, 0, 0, 6, 6, 6, 0, 3, 4 };

		// Valid account numbers for testing method F
		int[] validAccountNumberF1 = { 0, 0, 9, 9, 1, 0, 0, 0, 0, 2 };
		int[] validAccountNumberF2 = { 0, 0, 9, 9, 1, 0, 0, 0, 0, 2 };

		// Invalid AccoutNumbers for all Methods
		int[] invalidAccountNumber1 = { 0, 0, 0, 1, 9, 2, 4, 5, 9, 2 };
		int[] invalidAccountNumber2 = { 0, 0, 0, 0, 9, 0, 1, 5, 6, 8 };
		int[] invalidAccountNumber3 = { 0, 0, 0, 0, 8, 2, 0, 4, 8, 7 };
		int[] invalidAccountNumber4 = { 0, 0, 0, 0, 7, 2, 6, 3, 9, 3 };
		int[] invalidAccountNumber5 = { 0, 0, 0, 0, 9, 2, 4, 5, 9, 1 };

		// Should be valid using method A
		assertTrue(checksum.validate(validAccountNumberA1)
				&& (checksum.getAlternative() <= 0));
		assertTrue(checksum.validate(validAccountNumberA2)
				&& (checksum.getAlternative() <= 0));
		// Should be valid but not using method A
		assertTrue(checksum.validate(validAccountNumberNotUsingA)
				&& (checksum.getAlternative() == 2));

		// Should be valid using method B
		assertTrue(checksum.validate(validAccountNumberB1)
				&& (checksum.getAlternative() <= 1));
		assertTrue(checksum.validate(validAccountNumberB2)
				&& (checksum.getAlternative() <= 1));
		// Should be valid but not using method B
		assertTrue(checksum.validate(validAccountNumberNotUsingB1)
				&& (checksum.getAlternative() == 2));
		assertTrue(checksum.validate(validAccountNumberNotUsingB2)
				&& (checksum.getAlternative() == 3));

		// Should be valid using method C
		assertTrue(checksum.validate(validAccountNumberC1)
				&& (checksum.getAlternative() <= 2));
		assertTrue(checksum.validate(validAccountNumberC2)
				&& (checksum.getAlternative() <= 2));
		// Should be valid but not using method C
		assertTrue(checksum.validate(validAccountNumberNotUsingC1)
				&& (checksum.getAlternative() == 3));
		assertTrue(checksum.validate(validAccountNumberNotUsingC2)
				&& (checksum.getAlternative() == 4));

		// Should be valid using method D
		assertTrue(checksum.validate(validAccountNumberD1)
				&& (checksum.getAlternative() <= 3));
		assertTrue(checksum.validate(validAccountNumberD2)
				&& (checksum.getAlternative() <= 3));
		// Should be valid but not using method D
		assertTrue(checksum.validate(validAccountNumberNotUsingD1)
				&& (checksum.getAlternative() == 4));
		assertTrue(checksum.validate(validAccountNumberNotUsingD2)
				&& (checksum.getAlternative() == 4));

		// Should be valid using method E
		assertTrue(checksum.validate(validAccountNumberE1)
				&& (checksum.getAlternative() <= 4));
		assertTrue(checksum.validate(validAccountNumberE2)
				&& (checksum.getAlternative() <= 4));

		// Should be valid using method F
		assertTrue(checksum.validate(validAccountNumberF1)
				&& (checksum.getAlternative() == 5));
		assertTrue(checksum.validate(validAccountNumberF2)
				&& (checksum.getAlternative() == 5));

		// Should be invalid in any case
		assertFalse(checksum.validate(invalidAccountNumber1));
		assertFalse(checksum.validate(invalidAccountNumber2));
		assertFalse(checksum.validate(invalidAccountNumber3));
		assertFalse(checksum.validate(invalidAccountNumber4));
		assertFalse(checksum.validate(invalidAccountNumber5));
	}
}
