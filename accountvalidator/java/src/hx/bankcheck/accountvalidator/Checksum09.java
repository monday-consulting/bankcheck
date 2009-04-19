package hx.bankcheck.accountvalidator;

/**
 * Keine Prüfzifferberechnung
 * 
 * @author tma
 *
 */
public class Checksum09 implements IAccountChecksum {

	@Override
	public boolean validate(int[] accountNumber) throws ValidationException {
		return true;
	}

}
