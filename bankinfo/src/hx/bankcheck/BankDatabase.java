package hx.bankcheck;

import hx.dtaus.SimpleDTAusReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BankDatabase {
	private final static int[] BLZ_FIELDSIZE = {
		8,  // Bankleitzahl
		1,  // Merkmal, ob bankleitzahlführendes Kreditinstitut(„1“) oder nicht („2“)
		58, // Bezeichnung des Kreditinstitutes (ohne Rechtsform)
		5,  // Postleitzahl
		35, // Ort
		27, // Kurzbezeichnung des Kreditinstitutes mit Ort (ohne Rechtsform)
		5,  // Institutsnummer für PAN
		11, // Bank Identifier Code – BIC
		2,  // Kennzeichen für Prüfzifferberechnungsmethode
		6,  // Nummer des Datensatzes
		1,  // Änderungskennzeichen
		1,  // Hinweis auf eine beabsichtigte Bankleitzahllöschung
		8  // Hinweis auf Nachfolge-Bankleitzahl
	};
	
	private List<BankInfo> banken;
	public BankDatabase() {
	}
	
	public void init(InputStream blz) throws IOException {
		SimpleDTAusReader reader = new SimpleDTAusReader(new InputStreamReader(blz, "latin1"), BLZ_FIELDSIZE);
		banken = new ArrayList<BankInfo>();
		try {
			String[] parts;
			while((parts = reader.readLine()) != null) {
				BankInfo bank = createBank(parts);
				banken.add(bank);
			}
		} finally {
			reader.close();
		}
	}

	private BankInfo createBank(String[] parts) {
		String blz = parts[0];
		boolean hasBLZ = "1".equals(parts[1]);
		String longDesc = parts[2];
		String plz = parts[3];
		String ort = parts[4];
		String shortDesc = parts[5];
		String panNumber = parts[6];
		String bic = parts[7];
		String checksumMethod = parts[8];
		int entryNumber = Integer.parseInt(parts[9]);
		BankInfo.CHANGE_FLAG changeFlag = 
			BankInfo.CHANGE_FLAG.getFlagForSign(parts[10]);
		boolean remarkedForDelete = "1".equals(parts[11]);
		String newBlz = parts[12];
		
		return new BankInfo(blz, hasBLZ, longDesc, plz, ort, shortDesc, 
				panNumber, bic, checksumMethod, entryNumber, changeFlag,
				remarkedForDelete, newBlz);
	}
	
	public List<BankInfo> getBanken() {
		return banken;
	}
	
	
	public static void main(String[] args) throws IOException {
		BankDatabase manager = new BankDatabase();
		manager.init(new FileInputStream("datafiles/blz_20090309.txt"));
		
		List<BankInfo> banken = manager.getBanken();
		
		System.out.println(banken.size() + " Banken in Datei");
		
		int counter = 0;
		for(BankInfo bank : banken) {
			if (bank.getChecksumMethod().equals("09") && bank.isHasBLZ() && bank.getChangeFlag() != BankInfo.CHANGE_FLAG.DELETED) {
				System.out.println(bank.getBlz() + ": " + bank.getLongDescription());
				counter++;
			}
		}
		System.out.println(counter + " Banken ohne Kontoprüfsumme");
	}
}
