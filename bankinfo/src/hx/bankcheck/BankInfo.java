package hx.bankcheck;

/**
 * Enthält die Informationen aus der BLZ-Datei
 * @author tma
 */
public class BankInfo {
	enum CHANGE_FLAG { ADDED, MODIFIED, UNCHANGED, DELETED;
		public static CHANGE_FLAG getFlagForSign(String sign) {
			if ("A".equals(sign))
				return ADDED;
			if ("M".equals(sign))
				return MODIFIED;
			if ("U".equals(sign))
				return UNCHANGED;
			if ("D".equals(sign))
				return DELETED;
			
			throw new IllegalArgumentException("Sign is not in [A, M, U, D]");
		}
	};
	
	private String blz;
	private boolean hasBLZ; // Hat eigene PLZ oder ist es eine Filiale
	private String longDescription;
	private String plz;
	private String ort;
	private String shortDescription;
	private String panNumber;
	private String bic; // Bank Identifier Code
	private String checksumMethod;
	private int entryNumber; // Nummer des Datensatzes
	private CHANGE_FLAG changeFlag; // Änderungskennzeichen
	private boolean remarkedForDelete; // Hinweis auf eine beabsichtigte Bankleitzahllöschung
	private String newBlz; // Hinweis auf Nachfolge-Bankleitzahl
	
	/**
	 * Liefert die Bankleitzahl
	 * @return
	 */
	public String getBlz() {
		return blz;
	}

	/**
	 * Merkmal, ob bankleitzahlführendes Kreditinstitut
	 * @return true wenn diese Bank eine eigene BLZ besitzt, sonst false (Zweigstelle/Filiale)
	 */
	public boolean isHasBLZ() {
		return hasBLZ;
	}

	/**
	 * Lange Bezeichnung (max. 58 Zeichen)
	 * @return
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * Postleitzahl
	 * @return
	 */
	public String getPlz() {
		return plz;
	}

	/**
	 * Ort
	 * @return
	 */
	public String getOrt() {
		return ort;
	}

	/**
	 * Kurzbezeichnung (max. 27 Zeichen)
	 * @return
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * Institutsnummer für PAN
	 * @return
	 */
	public String getPanNumber() {
		return panNumber;
	}

	/**
	 * Bank Identifier Code – BIC
	 * @return
	 */
	public String getBic() {
		return bic;
	}

	/**
	 * Kennzeichen für Prüfzifferberechnungsmethode
	 * @return
	 */
	public String getChecksumMethod() {
		return checksumMethod;
	}

	/**
	 * Nummer des Datensatzes
	 * @return
	 */
	public int getEntryNumber() {
		return entryNumber;
	}

	/**
	 * Änderungskennzeichen
	 * @return
	 */
	public CHANGE_FLAG getChangeFlag() {
		return changeFlag;
	}

	/**
	 * Hinweis auf eine beabsichtigte 
	 * Bankleitzahllöschung
	 * @return
	 */
	public boolean isRemarkedForDelete() {
		return remarkedForDelete;
	}

	/**
	 * Hinweis auf Nachfolge-Bankleitzahl
	 * @return
	 */
	public String getNewBlz() {
		return newBlz;
	}

	public BankInfo(String blz, boolean hasBLZ, String longDescription,
			String plz, String ort, String shortDescription, String panNumber,
			String bic, String checksumMethod, int entryNumber,
			CHANGE_FLAG changeFlag, boolean remarkedForDelete, String newBlz) {
		super();
		
		this.blz = blz;
		this.hasBLZ = hasBLZ;
		this.longDescription = longDescription;
		this.plz = plz;
		this.ort = ort;
		this.shortDescription = shortDescription;
		this.panNumber = panNumber;
		this.bic = bic;
		this.checksumMethod = checksumMethod;
		this.entryNumber = entryNumber;
		this.changeFlag = changeFlag;
		this.remarkedForDelete = remarkedForDelete;
		this.newBlz = newBlz;
	}

}
