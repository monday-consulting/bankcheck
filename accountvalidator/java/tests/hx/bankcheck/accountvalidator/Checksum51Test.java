package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.impl.Checksum51;
import junit.framework.TestCase;

import org.junit.Test;

/**
 * Testclass for testing algorithm 51.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class Checksum51Test extends TestCase {

	@Test
	public void testValidate() throws Throwable {

		Checksum51 checksum = new Checksum51();

		// Valid account numbers for alternative A
		int[] validAccountNumberAlternativeA_1 = { 0, 0, 0, 1, 1, 5, 6, 0, 7, 1 };
		int[] validAccountNumberAlternativeA_2 = { 0, 0, 0, 1, 1, 5, 6, 0, 7, 1 };

		// Valid account numbers for alternative B
		int[] validAccountNumberAlternativeB_1 = { 0, 0, 0, 0, 1, 5, 6, 0, 7, 8 };

		// Valid account numbers for alternative C
		int[] validAccountNumberAlternativeC_1 = { 0, 0, 0, 0, 1, 5, 6, 0, 7, 1 };

		// Valid exceptional account numbers for alternative 1
		int[] validAccountNumberExceptionAlternative1_1 = { 0, 1, 9, 9, 1, 0,
				0, 0, 0, 2 };
		int[] validAccountNumberExceptionAlternative1_2 = { 0, 0, 9, 9, 1, 0,
				0, 0, 1, 0 };
		int[] validAccountNumberExceptionAlternative1_3 = { 2, 5, 9, 9, 1, 0,
				0, 0, 0, 2 };

		// Invalid exceptional account numbers for alternative 1
		int[] invalidAccountNumberExceptionAlternative1_1 = { 0, 1, 9, 9, 1, 0,
				0, 0, 0, 4 };
		int[] invalidAccountNumberExceptionAlternative1_2 = { 2, 5, 9, 9, 1, 0,
				0, 0, 0, 3 };
		int[] invalidAccountNumberExceptionAlternative1_3 = { 0, 0, 9, 9, 3, 4,
				5, 6, 7, 8 };

		// Valid exceptional account numbers for alternative 1
		int[] validAccountNumberExceptionAlternative2_1 = { 0, 1, 9, 9, 1, 0,
				0, 0, 0, 4 };
		int[] validAccountNumberExceptionAlternative2_2 = { 2, 5, 9, 9, 1, 0,
				0, 0, 0, 3 };
		int[] validAccountNumberExceptionAlternative2_3 = { 3, 1, 9, 9, 2, 0,
				4, 0, 9, 0 };

		// Invalid exceptional account numbers for alternative 1
		int[] invalidAccountNumberExceptionAlternative2_1 = { 0, 0, 9, 9, 3, 4,
				5, 6, 7, 8 };
		int[] invalidAccountNumberExceptionAlternative2_2 = { 0, 0, 9, 9, 1, 0,
				0, 1, 1, 0 };
		int[] invalidAccountNumberExceptionAlternative2_3 = { 0, 1, 9, 9, 1, 0,
				0, 0, 4, 0 };

		// Should be valid for alternative A
		assertTrue(checksum.validate(validAccountNumberAlternativeA_1)
				&& (checksum.getAlternative() == 0));
		assertTrue(checksum.validate(validAccountNumberAlternativeA_2)
				&& (checksum.getAlternative() == 0));

		// Should be valid for alternative B (also valid for alternative A)
		assertTrue(checksum.validate(validAccountNumberAlternativeB_1)
				&& (checksum.getAlternative() <= 1));

		// Should be valid for alternative C
		assertTrue(checksum.validate(validAccountNumberAlternativeC_1)
				&& (checksum.getAlternative() == 2));

		// Should be valid for alternative 1
		assertTrue(checksum.validate(validAccountNumberExceptionAlternative1_1)
				&& (checksum.getAlternative() == 0));
		assertTrue(checksum.validate(validAccountNumberExceptionAlternative1_2)
				&& (checksum.getAlternative() == 0));
		assertTrue(checksum.validate(validAccountNumberExceptionAlternative1_3)
				&& (checksum.getAlternative() == 0));

		// Should be valid for alternative 1
		assertFalse(checksum
				.validate(invalidAccountNumberExceptionAlternative1_1)
				&& (checksum.getAlternative() == 0));
		assertFalse(checksum
				.validate(invalidAccountNumberExceptionAlternative1_2)
				&& (checksum.getAlternative() == 0));
		assertFalse(checksum
				.validate(invalidAccountNumberExceptionAlternative1_3)
				&& (checksum.getAlternative() == 0));

		// Should be valid for alternative 2
		assertTrue(checksum.validate(validAccountNumberExceptionAlternative2_1)
				&& (checksum.getAlternative() == 1));
		assertTrue(checksum.validate(validAccountNumberExceptionAlternative2_2)
				&& (checksum.getAlternative() == 1));
		assertTrue(checksum.validate(validAccountNumberExceptionAlternative2_3)
				&& (checksum.getAlternative() == 1));

		// Should be valid for alternative 2
		assertFalse(checksum
				.validate(invalidAccountNumberExceptionAlternative2_1)
				&& (checksum.getAlternative() == 1));
		assertFalse(checksum
				.validate(invalidAccountNumberExceptionAlternative2_2)
				&& (checksum.getAlternative() == 1));
		assertFalse(checksum
				.validate(invalidAccountNumberExceptionAlternative2_3)
				&& (checksum.getAlternative() == 1));

	}
}
