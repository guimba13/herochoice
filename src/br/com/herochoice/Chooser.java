package br.com.herochoice;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		
		Helper.villains = new Team(Helper.characters, ids);
		Helper.villains.print();

		Double budget = Helper.calculateBudget(Helper.characters, Helper.villains);
		Team heroes = Helper.createFirstTeam();
		
//		Double solutionValue = Helper.calculateSolutionValue(firstChoice, Helper.villains);
//		System.out.println("Solution value = " + solutionValue);
//		System.out.println("Solution cost = " + firstChoice.getCost());
		
		Team otmSolution = new Team();
		Double otmSolValue = 0.0;
		Integer counter = 0;
		
		do{
			do{
				counter++;
				
				Team newSolution = Helper.getNeighborSolution(heroes);
				Double solValue = Helper.calculateSolutionValue(heroes);
				Double newSolValue = Helper.calculateSolutionValue(newSolution);
				Double delta = newSolValue - solValue;
				
				if(delta >= 0){
					heroes = newSolution;
					if(newSolValue > otmSolValue && Helper.isValidSolution(heroes)){
						otmSolution = newSolution;
						otmSolValue = newSolValue;
						counter = 0;
					}
				}else{
					Double np = Helper.newProbability(delta);
					Random rand = new Random();
					Double prob = rand.nextDouble();
					if(prob <= np){
						heroes = newSolution;
						if(newSolValue > otmSolValue && Helper.isValidSolution(heroes)){
							otmSolution = newSolution;
							otmSolValue = newSolValue;
							counter = 0;
						}
					}
				}
			}while(counter <= Helper.maxRepetitions);
			Helper.updateTemp();
		}while(Helper.temp < 1);
		
		otmSolution.print();
	}

	private static Character getCharacterById(int id) {
		return Helper.characters.get(id - 1);
	}

	private static Relation getRelationsById(int id1, int id2) {
		return Helper.relations[id1 - 1][id2 - 1];
	}

}
