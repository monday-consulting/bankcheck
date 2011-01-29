package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;
import hx.bankcheck.accountvalidator.exceptions.ValidatorUnknownException;
import hx.bankcheck.accountvalidator.utils.ChecksumUtils;
import hx.bankcheck.blz.BankInfo;
import hx.bankcheck.blz.BlzManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BankAccountValidator {

	private BlzManager blzManager;
	private ChecksumValidatorFactory factory;

	public BankAccountValidator() throws FileNotFoundException, IOException {
		blzManager = new BlzManager();
		factory = new ChecksumValidatorFactory();
	}

	public boolean isValid(String blzStr, String kontoStr) throws ValidatorUnknownException, ValidationException {
		BankInfo bankData = blzManager.findBank(blzStr).get(0);

		if (bankData == null)
			return false;
		
		long number = Long.parseLong(kontoStr);
		int[] accountNumber = ChecksumUtils.parseAccountNumber(number);
		
		int[] blz = ChecksumUtils.parseBlz(Integer.parseInt(blzStr));
		
		ChecksumValidator validator = factory.createValidatorFor(bankData.getChecksumAlgo());
		return validator.validate(accountNumber, blz);
	}
}
