package hx.bankcheck.ibanvalidator;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

import org.junit.Test;

public class IBANValidatorTest {
	
	@Test
	public void testValidate() throws ValidationException {
		
		IBANValidator ibanValidator = new IBANValidator();

		String[] validIBANs = { "CH10002300A1023502601", "GB29NWBK60161331926819", "DE57250100300436032307"};
		String[] invalidIBANs = { "AT611904300235473201"};
		
		for (String iban : validIBANs) {
			assertTrue(ibanValidator.isValid(iban, null));
		}
		
		for (String iban : invalidIBANs) {
			assertFalse(ibanValidator.isValid(iban, null));
		}
	}

}
