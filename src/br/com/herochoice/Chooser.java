package br.com.herochoice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Chooser {

	private static BufferedReader br;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unused")
	public static Object readCsv() throws IOException {
		br = new BufferedReader(new FileReader("test.csv"));

		String line;
		String splitBy = ",";
		while ((line = br.readLine()) != null) {
			String[] b = line.split(splitBy);
			// TODO Percorrer a matriz setando os dados para cada heroi

		}
		return line;
	}

}
