package hx.bankcheck.blz;

import hx.bankcheck.utils.files.DtaReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BlzManager {

	class IllegalDataException extends Exception {
		private static final long serialVersionUID = 1403145711243717144L;

		public IllegalDataException(String message) {
			super(message);
		}
		
	}
	
	enum Column {
		BLZ(8), DienstleisterMitBlz(1), Bezeichnung(58), PLZ(5), Ort(35), Kurzbezeichnung(
				27), InstNumPAN(5), BIC(11), Checksum(2), Datensatznr(6), Aenderungskennz(
				1), WirdGeloescht(1), NachfolgeBLZ(8);

		private final int length;

		public int getLength() {
			return length;
		}

		private Column(int length) {
			this.length = length;
		}
	}

	private DtaReader reader;
	private List<BankInfo> banks;

	public BlzManager() throws FileNotFoundException, IOException {
		banks = new ArrayList<BankInfo>();
	}

	public void loadFile(File file) throws FileNotFoundException, IOException, IllegalDataException {
		InputStream is = new FileInputStream(file);
		try {
			load(is);
		} finally {
			is.close();
		}
	}
	
	private void load(InputStream input) throws FileNotFoundException, IOException, IllegalDataException {
		int[] columns = new int[Column.values().length];
		int i=0;
		for(Column c: Column.values()) {
			columns[i++] = c.getLength();
		}
		
		reader = new DtaReader(input, columns);
		try {
			String[] data = null;
			while((data = reader.next()) != null) {
				BankInfo b = new BankInfo();
				
				b.setBlz(data[Column.BLZ.ordinal()].trim());
				
				String d = data[Column.DienstleisterMitBlz.ordinal()].trim();
				if ("1".equals(d)) {
					b.setDienstleisterMitBlz(true);
				} else if ("2".equals(d)) {
					b.setDienstleisterMitBlz(false);
				} else {
					throw new IllegalDataException("Ungültiger Wert " + d + " für Feld " + Column.DienstleisterMitBlz.toString());
				}
				
				d = data[Column.Bezeichnung.ordinal()].trim();
				b.setBezeichnung(d);
				
				d = data[Column.PLZ.ordinal()].trim();
				b.setPlz(Integer.parseInt(d));
				
				d = data[Column.Ort.ordinal()].trim();
				b.setOrt(d);
				
				d = data[Column.Kurzbezeichnung.ordinal()].trim();
				b.setKurzBezeichnung(d);
				
				d = data[Column.InstNumPAN.ordinal()].trim();
				if (d.trim().length() > 0)
					b.setInstNumPAN(Integer.parseInt(d));
				
				d = data[Column.BIC.ordinal()].trim();
				b.setBIC(d);
				
				d = data[Column.Checksum.ordinal()].trim();
				b.setChecksumAlgo(d);
				
				d = data[Column.Datensatznr.ordinal()].trim();
				b.setDatensatzNr(Integer.parseInt(d));
				
				d = data[Column.Aenderungskennz.ordinal()].trim();
				if ("A".equals(d))
					b.setNeu(true);
				else if ("D".equals(d)) 
					b.setGeloescht(true);
				else if ("U".equals(d))
					b.setGeaendert(false);
				else if ("M".equals(d))
					b.setGeaendert(true);
				else
					throw new IllegalDataException("Ungültiger Wert " + d + " für Feld " + Column.Aenderungskennz.toString());
				
				d = data[Column.WirdGeloescht.ordinal()].trim();
				if ("0".equals(d))
					b.setWirdGeloescht(false);
				else if ("1".equals(d))
					b.setWirdGeloescht(true);
				else 
					throw new IllegalDataException("Ungültiger Wert " + d + " für Feld " + Column.WirdGeloescht.toString());
				
				banks.add(b);
			}
		} finally {
			reader.close();
		}
	}

	public List<BankInfo> findBank(String blz) {
		List<BankInfo> r = new ArrayList<BankInfo>();
		for (BankInfo b: banks) {
			if (blz.equals(b.getBlz()))
				r.add(b);
		}
		return r;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, IllegalDataException {
		BlzManager r = new BlzManager();
		
		File f = new File("data/blz_20101206.txt");
		r.loadFile(f);
		
		List<BankInfo> banks = r.findBank("79063060");
		for(BankInfo b: banks)
			System.out.println(b);
	}
	
}
