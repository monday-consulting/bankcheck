package hx.dtaus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class SimpleDTAusReader {

	private BufferedReader input;
	private int[] fieldSize;

	public SimpleDTAusReader(Reader reader, int[] fieldSize) {
		input = new BufferedReader(reader);
		this.fieldSize = fieldSize;
	}
	
	public String[] readLine() throws IOException {
		String[] result = new String[fieldSize.length];
		
		String line;
		while((line = input.readLine())!=null && line.length() == 0) {}; // Leere Zeilen überspringen
		
		if (line == null)
			return null;
		
		int start = 0;
		for(int i=0; i<fieldSize.length; i++) {
			int length = fieldSize[i];
			result[i] = line.substring(start, start+length);
			
			start += length;
		}
		
		return result;
	}
	
	public void close() throws IOException {
		input.close();
	}
	
}
