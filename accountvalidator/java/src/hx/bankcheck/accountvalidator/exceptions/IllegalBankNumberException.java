package hx.bankcheck.accountvalidator.exceptions;

public class IllegalBankNumberException extends ValidationException {
	private static final long serialVersionUID = 4201977648958334207L;

	public IllegalBankNumberException() {
		super();
	}

	public IllegalBankNumberException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalBankNumberException(String message) {
		super(message);
	}

	public IllegalBankNumberException(Throwable cause) {
		super(cause);
	}

}
