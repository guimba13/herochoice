package br.com.herochoice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Chooser {

	private static List<Character> characters;
	private static Relation relations[][];

	public static void main(String[] args) {

		try {
			characters = Helper.readCharacter();
			relations = Helper.readRelations();

			List<String> villains = Files.readAllLines(Paths.get(args[0]),
					StandardCharsets.UTF_8);

			String villainsIds[] = villains.get(0).split(" ");

			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> ids = null;
		Team villains = new Team(characters, ids);
		Double budget = Helper.calculateBudget(characters, villains);
		
	}
	
	

	private static Character getCharacterById(int id) {
		return characters.get(id - 1);
	}

	private static Relation getRelationsById(int id1, int id2) {
		return relations[id1 - 1][id2 - 1];
	}

}
