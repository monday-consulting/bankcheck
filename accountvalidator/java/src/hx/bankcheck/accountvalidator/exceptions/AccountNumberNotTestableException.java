package hx.bankcheck.accountvalidator.exceptions;

public class AccountNumberNotTestableException extends ValidationException {

	private static final long serialVersionUID = 8282225762733355433L;

	public AccountNumberNotTestableException() {
		super();
	}

	public AccountNumberNotTestableException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountNumberNotTestableException(String message) {
		super(message);
	}

	public AccountNumberNotTestableException(Throwable cause) {
		super(cause);
	}

}
