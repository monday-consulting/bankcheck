package hx.bankcheck.accountvalidator;

import static org.junit.Assert.assertTrue;
import hx.bankcheck.accountvalidator.impl.Checksum32;

import org.junit.Test;

public class Checksum32Test {

	@Test
	public void testValidate() throws Throwable {

		Checksum32 checksum32 = new Checksum32();

		// Valid account numbers
		int[] validAccountNumber1 = { 0, 0, 0, 9, 1, 4, 1, 4, 0, 5 };
		int[] validAccountNumber2 = { 1, 7, 0, 9, 1, 0, 7, 9, 8, 3 };
		int[] validAccountNumber3 = { 0, 1, 2, 2, 1, 1, 6, 9, 7, 9 };
		int[] validAccountNumber4 = { 0, 1, 2, 1, 1, 1, 4, 8, 6, 7 };
		int[] validAccountNumber5 = { 9, 0, 3, 0, 1, 0, 1, 1, 9, 2 };
		int[] validAccountNumber6 = { 9, 2, 4, 5, 5, 0, 0, 4, 6, 0 };

		// Should be valid
		assertTrue(checksum32.validate(validAccountNumber1));
		assertTrue(checksum32.validate(validAccountNumber2));
		assertTrue(checksum32.validate(validAccountNumber3));
		assertTrue(checksum32.validate(validAccountNumber4));
		assertTrue(checksum32.validate(validAccountNumber5));
		assertTrue(checksum32.validate(validAccountNumber6));

	}
}
