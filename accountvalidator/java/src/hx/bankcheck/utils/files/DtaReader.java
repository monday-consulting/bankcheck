package hx.bankcheck.utils.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Liest DTA-Dateien in ein Stringarray
 * @author Tobias Mayer (bankcheck@tobiasm.de)
 *
 *
 * $Id$
 */
public class DtaReader {

	/**
	 * Wird geworfen, wenn eine ungültige Zeile in der DTA-Datei erkannt wird
	 * @author Tobias Mayer (bankcheck@tobiasm.de)
	 *
	 *
	 * $Id$
	 */
	class InvalidLineException extends IOException {
		private static final long serialVersionUID = 8241612516551119895L;

		public InvalidLineException(String message) {
			super(message);
		}
	}

	private InputStream in;
	private final int[] columnLengths;
	private BufferedReader r;
	private int lineLength;

	/**
	 * Erzeugt einen DtaReader
	 * @param inStream Quelle der DTA-Daten
	 * @param columnLengths Array welches die Zeichenlänge der einzelnen Spalten enthält
	 * @throws IOException wird geworfen, falls der Stream nicht gelesen werden kann
	 */
	public DtaReader(InputStream inStream, int[] columnLengths)
			throws IOException {
		in = inStream;
		this.columnLengths = columnLengths;
		
		// Summe der Spaltenlängen berechnen
		lineLength = 0;
		for (int fieldLength : columnLengths) {
			lineLength += fieldLength;
		}

		// Reader initialisieren
		try {
			init("iso-8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new IOException("Can't read stream!", e);
		}
	}

	/**
	 * Initialisiert den DTA-Reader
	 * @param charset Zeichensatz der DTA-Datei
	 * @throws UnsupportedEncodingException
	 */
	private void init(String charset) throws UnsupportedEncodingException {
		r = new BufferedReader(new InputStreamReader(in, charset));
	}

	/**
	 * Liefert die Werte der nächsten Zeile
	 * @return Array mit den Spaltenwerten der nächsten Zeile. Ist kein weiterer Datensatz vorhanden wird null zurückgegeben.
	 * @throws IOException wenn ein Fehler beim Lesen des Stream auftritt
	 * @throws InvalidLineException wenn die Zeile nicht geparst werden kann
	 */
	public String[] next() throws IOException {
		String line = r.readLine();
		if (line == null)
			return null;

		if (line.length() < lineLength)
			throw new InvalidLineException("Line is to short: " + line.length()
					+ " < " + lineLength);

		String[] dataset = new String[columnLengths.length];

		int row = 0;
		int fieldStart = 0;
		for (int fieldLength : columnLengths) {
			String field = line.substring(fieldStart, fieldStart + fieldLength);
			dataset[row++] = field;
			fieldStart += fieldLength;
		}
		
		return dataset;
	}

	/**
	 * Schließt den Reader
	 * @throws IOException
	 */
	public void close() throws IOException {
		r.close();
	}

}
