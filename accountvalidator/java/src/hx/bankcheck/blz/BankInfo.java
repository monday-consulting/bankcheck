package hx.bankcheck.blz;

/**
 * Enthält Informationen zu einer Bank
 * 
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 * 
 *         $Id$
 */
public class BankInfo {

	private String bezeichnung;

	private String bic;

	private String blz;

	private String checksumAlgo;

	private int datensatzNr;

	private int instNumPAN;

	private boolean isGeaendert;

	private boolean isGeloescht;

	private boolean isNeu;

	private String kurzBezeichnung;

	private String ort;

	private int plz;

	private boolean wirdGeaendert;

	public String getBezeichnung() {
		return bezeichnung;
	}

	public String getBic() {
		return bic;
	}
	public String getBlz() {
		return blz;
	}
	public String getChecksumAlgo() {
		return checksumAlgo;
	}
	public int getDatensatzNr() {
		return datensatzNr;
	}
	public int getInstNumPAN() {
		return instNumPAN;
	}
	public String getKurzBezeichnung() {
		return kurzBezeichnung;
	}
	public String getOrt() {
		return ort;
	}
	public int getPlz() {
		return plz;
	}
	public boolean isGeaendert() {
		return isGeaendert;
	}
	public boolean isGeloescht() {
		return isGeloescht;
	}
	public boolean isNeu() {
		return isNeu;
	}
	public boolean isWirdGeaendert() {
		return wirdGeaendert;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public void setBIC(String bic) {
		this.bic = bic;
	}

	public void setBlz(String blz) {
		this.blz = blz;
	}

	public void setChecksumAlgo(String checksumAlgo) {
		this.checksumAlgo = checksumAlgo;
	}

	public void setDatensatzNr(int datensatzNr) {
		this.datensatzNr = datensatzNr;
	}

	public void setDienstleisterMitBlz(boolean isDienstleisterMitBlz) {

	}

	public void setGeaendert(boolean isGeaendert) {
		this.isGeaendert = isGeaendert;
	}

	public void setGeloescht(boolean isGeloescht) {
		this.isGeloescht = isGeloescht;
	}

	public void setInstNumPAN(int instNumPAN) {
		this.instNumPAN = instNumPAN;
	}

	public void setKurzBezeichnung(String kurzBezeichnung) {
		this.kurzBezeichnung = kurzBezeichnung;
	}

	public void setNeu(boolean isNeu) {
		this.isNeu = isNeu;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public void setWirdGeloescht(boolean wirdGeaendert) {
		this.wirdGeaendert = wirdGeaendert;
	}

	@Override
	public String toString() {
		return "BankInfo [bezeichnung=" + bezeichnung + ", bic=" + bic
				+ ", blz=" + blz + ", checksumAlgo=" + checksumAlgo
				+ ", datensatzNr=" + datensatzNr + ", instNumPAN=" + instNumPAN
				+ ", isGeaendert=" + isGeaendert + ", isGeloescht="
				+ isGeloescht + ", isNeu=" + isNeu + ", kurzBezeichnung="
				+ kurzBezeichnung + ", ort=" + ort + ", plz=" + plz
				+ ", wirdGeaendert=" + wirdGeaendert + "]";
	}

	
}
