/**
 * 
 */
package hx.bankcheck.accountvalidator.impl;

import hx.bankcheck.accountvalidator.ChecksumValidator;
import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 *
 */
public class ChecksumA6 implements ChecksumValidator {
	
	private int alternative=0;

	/* (non-Javadoc)
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[])
	 */
	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		if(accountNumber[1]==8){
			setAlternative(0);
			return new Checksum00().validate(accountNumber);
		}else{
			setAlternative(1);
			return new Checksum01().validate(accountNumber);
		}
	}

	/**
	 * @param alternative the alternative to set
	 */
	public void setAlternative(int alternative) {
		this.alternative = alternative;
	}

	/**
	 * @return the alternative
	 */
	public int getAlternative() {
		return alternative;
	}

}
