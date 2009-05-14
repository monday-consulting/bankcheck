package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.AbstractChecksumTest;
import hx.bankcheck.accountvalidator.ChecksumValidator;

/**
 * Testclass for testing algorithm B7.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public class ChecksumB7Test  extends AbstractChecksumTest {
	
	@Override
	public void testValidate() throws Throwable {

		ChecksumValidator validator = new ChecksumB7();

		// Valid account numbers for alternative 1
		int[] validAccountNumberAlternative1_1 = { 0, 7, 0, 0, 0, 0, 1, 5, 2, 9 };
		int[] validAccountNumberAlternative1_2 = { 0, 7, 3, 0, 0, 0, 0, 0, 1, 9 };
		int[] validAccountNumberAlternative1_3 = { 0, 0, 0, 1, 0, 0, 1, 0, 0, 8 };
		int[] validAccountNumberAlternative1_4 = { 0, 0, 0, 1, 0, 5, 7, 8, 8, 7 };
		int[] validAccountNumberAlternative1_5 = { 0, 0, 0, 1, 0, 0, 7, 2, 2, 2 };
		int[] validAccountNumberAlternative1_6 = { 0, 8, 1, 0, 0, 1, 1, 8, 2, 5 };
		int[] validAccountNumberAlternative1_7 = { 0, 8, 0, 0, 1, 0, 7, 6, 5, 3 };
		int[] validAccountNumberAlternative1_8 = { 0, 0, 0, 5, 9, 2, 2, 3, 7, 2 };

		// Invalid account numbers for alternative 1
		int[] invalidAccountNumberAlternative1_1 = { 0, 0, 0, 1, 0, 5, 7, 8, 8,
				6 };
		int[] invalidAccountNumberAlternative1_2 = { 0, 0, 0, 3, 8, 1, 5, 5, 7,
				0 };
		int[] invalidAccountNumberAlternative1_3 = { 0, 0, 0, 5, 6, 2, 0, 5, 1,
				6 };
		int[] invalidAccountNumberAlternative1_4 = { 0, 7, 4, 0, 9, 1, 2, 2, 4,
				3 };
		int[] invalidAccountNumberAlternative1_5 = { 0, 8, 9, 3, 5, 2, 4, 4, 7,
				9 };

		// Should be valid using alternative 1
		assertTrue((validator.validate(validAccountNumberAlternative1_1,null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_2,null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_3,null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_4,null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_5,null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_6,null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_7,null))
				&& (validator.getAlternative() == 0));
		assertTrue((validator.validate(validAccountNumberAlternative1_8,null))
				&& (validator.getAlternative() == 0));

		// Should be invalid using alternative 1
		assertFalse((validator.validate(invalidAccountNumberAlternative1_1,null))
				&& (validator.getAlternative() == 0));
		assertFalse((validator.validate(invalidAccountNumberAlternative1_2,null))
				&& (validator.getAlternative() == 0));
		assertFalse((validator.validate(invalidAccountNumberAlternative1_3,null))
				&& (validator.getAlternative() == 0));
		assertFalse((validator.validate(invalidAccountNumberAlternative1_4,null))
				&& (validator.getAlternative() == 0));
		assertFalse((validator.validate(invalidAccountNumberAlternative1_5,null))
				&& (validator.getAlternative() == 0));

		// Should be valid using alternative 2
		checkRangeOfAccountNumbers(1, 1000000, null, 1, false, validator, 10000);
		checkRangeOfAccountNumbers(6000000, 700000000, null, 1, false, validator, 10000);

	}
}
