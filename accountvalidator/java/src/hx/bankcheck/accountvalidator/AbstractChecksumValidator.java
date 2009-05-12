/**
 * 
 */
package hx.bankcheck.accountvalidator;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

/**
 * Abstract class for algorithms needing no bank number for validation.
 * 
 * @author Sascha Dömer (sdo@lmis.de) - LM Internet Services AG
 * @version 1.0
 * 
 */
public abstract class AbstractChecksumValidator implements ChecksumValidator {

	private int alternative=0;
	private boolean exception=false;
	private int[] weights=null;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see hx.bankcheck.accountvalidator.ChecksumValidator#validate(int[],
	 * int[])
	 */
	@Override
	public boolean validate(int[] accountNumber, int[] bankNumber)
			throws ValidationException {
		return validate(accountNumber);
	}

	/**
	 * Checks an account number for correctness
	 * 
	 * @param accountNumber
	 *            The 10-Digit accountNumber, right aligned.
	 * @return
	 */
	public abstract boolean validate(int[] accountNumber)
			throws ValidationException;

	/**
	 * @param alternative the alternative to set
	 */
	protected void setAlternative(int alternative) {
		this.alternative = alternative;
	}

	/**
	 * @return the alternative
	 */
	public int getAlternative() {
		return alternative;
	}

	/**
	 * @param exception the exception to set
	 */
	protected void setException(boolean exception) {
		this.exception = exception;
	}

	/**
	 * @return the exception
	 */
	public boolean isException() {
		return exception;
	}

	/**
	 * @param weights the weights to set
	 */
	protected void setWeights(int[] weights) {
		this.weights = weights;
	}

	/**
	 * @return the weights
	 */
	public int[] getWeights() {
		return weights;
	}

}
