package br.com.herochoice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Chooser {

	public static void main(String[] args) {

		List<Integer> ids = new ArrayList<Integer>();
		try {
			Helper.characters = Helper.readCharacter();
			Helper.relations = Helper.readRelations();

			List<String> villains = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8);
			String useBudget = args[1];
			Helper.setUseBudget((useBudget != null && useBudget.equals("1")) ? true : false);

			String villainsIds[] = villains.get(0).split(" ");
			
			for(String villain : villainsIds){
				ids.add(Integer.parseInt(villain));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Team villains = new Team(Helper.characters, ids);
		villains.print();

		Double budget = Helper.calculateBudget(Helper.characters, villains);
		Double temp = 0.0;// temp inicial
		
		Team firstChoice = Helper.createFirstTeam();
		firstChoice.print();
		firstChoice.printPg();
		
		Double solutionValue = Helper.calculateSolutionValue(firstChoice, villains);
		System.out.println("Solution value = " + solutionValue);
		System.out.println("Solution cost = " + firstChoice.getCost());
		
	}

	private static Character getCharacterById(int id) {
		return Helper.characters.get(id - 1);
	}

	private static Relation getRelationsById(int id1, int id2) {
		return Helper.relations[id1 - 1][id2 - 1];
	}

}
