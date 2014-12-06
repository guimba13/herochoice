package br.com.herochoice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Helper {

	private static BufferedReader br;

	public static List<Character> readCharacter() throws IOException {
		br = new BufferedReader(new FileReader("character.csv"));

		String line;
		String splitBy = ";";
		List<Character> characteres = new ArrayList<Character>();
		boolean firstLine = true;
		while ((line = br.readLine()) != null) {
			if (firstLine) {
				firstLine = false;
				continue;
			}

			String[] b = line.split(splitBy);

			Character character = new Character();
			character.setId(Integer.parseInt(b[0]));
			character.setName(b[1]);
			character.setHero(b[2].equals("hero") ? true : false);
			character.setIntelligence(Integer.parseInt(b[3]));
			character.setStrength(Integer.parseInt(b[4]));
			character.setSpeed(Integer.parseInt(b[5]));
			character.setDurability(Integer.parseInt(b[6]));
			character.setEnergy(Integer.parseInt(b[7]));
			character.setFightingSkills(Integer.parseInt(b[8]));
			character.setNumberComics(Integer.parseInt(b[9]));

			characteres.add(character);
		}

		return characteres;
	}

	public static Relation[][] readRelations() throws IOException {
		br = new BufferedReader(new FileReader("relations.csv"));

		String line;
		String splitBy = ";";
		Relation relations[][] = new Relation[767][767];

		boolean firstLine = true;
		while ((line = br.readLine()) != null) {
			if (firstLine) {
				firstLine = false;
				continue;
			}

			String[] b = line.split(splitBy);

			Relation relation = new Relation();
			relation.setNumberComics(Integer.parseInt(b[2]));
			relations[Integer.parseInt(b[0])-1][Integer.parseInt(b[1])-1] = relation;
		}

		return relations;
	}
}
