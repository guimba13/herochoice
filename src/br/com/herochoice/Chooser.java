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
			String useBudget = args.length > 1 ? args[1] : null;
			Helper.setUseBudget((useBudget != null && useBudget.equals("-b")) ? true : false);

			String villainsIds[] = villains.get(0).split(" ");
			
			for(String villain : villainsIds){
				ids.add(Integer.parseInt(villain));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Helper.villains = new Team(Helper.characters, ids);
		System.out.println("Time de viloes: ");
		Helper.villains.print();
		
		// ajusta as variaveis de controle a partir do tamanho do problema
		Integer size = Helper.villains.getTeam().size(); 
		if(size <= 8){
			Helper.temp = 80.0;
			Helper.k = 80.0;
		}else if(size <= 14){
			Helper.temp = 150.0;
			Helper.k = 150.0;
		}else{
			Helper.temp = 200.0;
			Helper.k = 200.0;
		}

		Double budget = Helper.calculateBudget(Helper.characters, Helper.villains);
		Team heroes = Helper.createFirstTeam();
		
		Team otmSolution = new Team();
		Double otmSolValue = 0.0;
		Integer counter = 0;
		
		do{
			counter++;
			
			Team newSolution = Helper.getNeighborSolution(heroes);
			Double solValue = Helper.calculateSolutionValue(heroes, false);
			Double newSolValue = Helper.calculateSolutionValue(newSolution, false);
			Double delta = newSolValue - solValue;
			
			if(delta >= 0){
				Team.copyTeam(heroes, newSolution);
				if(newSolValue > otmSolValue && Helper.isValidSolution(heroes)){
					Team.copyTeam(otmSolution, newSolution);
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
						Team.copyTeam(otmSolution, newSolution);
						otmSolValue = newSolValue;
						counter = 0;
					}
				}
			}
			Helper.updateTemp();
		}while(Helper.temp > 0.1 && counter <= Helper.maxRepetitions);
		
		if(Helper.print && counter > Helper.maxRepetitions) System.out.println("MAX REPETITIONS");
		if(Helper.print && Helper.temp < 0.01) System.out.println("TIME");
		
		System.out.println("Time de herois: ");
		otmSolution.print();
		System.out.println("budget: " + otmSolution.getCost());
		Double endValue = Helper.calculateSolutionValue(otmSolution, true);
		System.out.println("Total: " + otmSolValue);
	}

	private static Character getCharacterById(int id) {
		return Helper.characters.get(id - 1);
	}

	private static Relation getRelationsById(int id1, int id2) {
		return Helper.relations[id1 - 1][id2 - 1];
	}

}
