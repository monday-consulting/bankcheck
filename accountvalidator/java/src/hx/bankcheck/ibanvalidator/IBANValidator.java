package hx.bankcheck.ibanvalidator;

import hx.bankcheck.accountvalidator.exceptions.ValidationException;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IBANValidator {

	private static final String IBAN_PATTERNS_FILENAME = "hx/bankcheck/ibanvalidator/europeanIbanPatterns.properties";
	
	private HashMap<String, Pattern> europeanIbanPatterns;

	public IBANValidator() {
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(IBAN_PATTERNS_FILENAME);

			if (inputStream == null) {
				throw new FileNotFoundException("Property file '" + IBAN_PATTERNS_FILENAME + "' not found in the classpath.");
			}
			Properties properties = new Properties();
			properties.load(inputStream);
			
			europeanIbanPatterns = new HashMap<String, Pattern>();
			
			for (Entry<Object, Object> entry : properties.entrySet()) {
				europeanIbanPatterns.put((String) entry.getKey(), Pattern.compile((String) entry.getValue()));
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public boolean isValid(String iban, String country)	throws ValidationException {
		
		if (iban == null || iban.length() < 4) {
			return false;
		}
		
		String countryCode = iban.substring(0, 2).toUpperCase();
		
		if (country != null && !country.toUpperCase().equals(countryCode)) {
			return false;
		}
		
		Pattern ibanPattern = europeanIbanPatterns.get(countryCode);
		if (ibanPattern == null) {
			return false;
		}
		
		Matcher ibanMatcher = ibanPattern.matcher(iban.toUpperCase());
		if (!ibanMatcher.matches()) {
			return false;
		}
		
		ChecksumIBAN checksumIBAN =  new ChecksumIBAN();
		return checksumIBAN.validate(iban);
	}
}
