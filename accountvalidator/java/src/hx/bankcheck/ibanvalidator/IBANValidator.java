package hx.bankcheck.ibanvalidator;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Pattern;

public class IBANValidator {

	private static final String IBAN_PATTERNS_FILENAME = "hx/bankcheck/ibanvalidator/ibanPatterns.properties";
	
	private HashMap<String, Pattern> ibanPatterns;
	
	private Pattern ALL_IBANS;

	public IBANValidator() {
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(IBAN_PATTERNS_FILENAME);

			if (inputStream == null) {
				throw new FileNotFoundException("Property file '" + IBAN_PATTERNS_FILENAME + "' not found in the classpath.");
			}
			Properties properties = new Properties();
			properties.load(inputStream);
			
			ibanPatterns = new HashMap<String, Pattern>();
			
			for (Entry<Object, Object> entry : properties.entrySet()) {
				ibanPatterns.put((String) entry.getKey(), Pattern.compile((String) entry.getValue()));
			}
			
			ALL_IBANS = ibanPatterns.get("ALL");
			
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public boolean isValid(String iban)	throws ValidationException {
		return isValid(iban, null);
	}

	public boolean isValid(String iban, String country)	throws ValidationException {
		
		// validate format 
		if (!ALL_IBANS.matcher(iban).matches()) {
			return false;
		}
		
		// validate checksum
		ChecksumIBAN checksumIBAN =  new ChecksumIBAN();
		if (!checksumIBAN.validate(iban)) {
			return false;
		}
		
		// validate country code
		String countryCode = iban.substring(0, 2).toUpperCase();
		if (country != null && !country.toUpperCase().equals(countryCode)) {
			return false;
		}
		
		// validate against country-specific format 
		Pattern ibanPattern = ibanPatterns.get(countryCode);
		if (ibanPattern != null && !ibanPattern.matcher(iban.toUpperCase()).matches()) {
			return false;
		}
		
		return true;
	}
}
