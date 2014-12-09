package br.com.herochoice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Helper {

	private static BufferedReader br;
	private static Boolean useBudget;
	public static Double budget;
	public static List<Character> characters;
	public static Relation relations[][];

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
	
	public static Double calculateBudget(List<Character> chars, Team villains){
		Double avgPgHeroes = 0.0;
		Double avgPopHeroes = 0.0;
		Double avgPgVillains = 0.0;
		
		int i = 0;
		int j = 0;
		for(Character c : chars){
			if(c.isHero()){
				avgPgHeroes += c.getPgMed();
				avgPopHeroes += c.getNumberComics();
				i++;
			}else{
				avgPgVillains += c.getPgMed();
				j++;
			}
		}
		avgPgHeroes = avgPgHeroes/i;
		avgPopHeroes = avgPopHeroes/i;
		avgPgVillains = avgPgVillains/j;
		
		Double radiopg = avgPgHeroes / villains.getPgMed();
		Double radiopop = avgPopHeroes / villains.getPopMed();
		Double factor = villains.getPgMed() / avgPgVillains;
		
		Double exp1 = radiopg * radiopop * villains.getCost();
		Double exp2 = factor * avgPgHeroes * avgPopHeroes * villains.getSize();
		
		budget = Math.max(exp1, exp2);
		
		return budget;
	}
	
	public static Team createFirstTeam(List<Character> chars){
		Team heroes = null;
		if(useBudget){
			while(true){
				Team newTeam = heroes.addCharacter();
				if(newTeam.getCost() < budget){
					heroes = newTeam;
				}else if(newTeam.getCost() == budget){
					heroes = newTeam;
					break;
				}else{
					break;
				}
			}
		}else{
			// TODO
		}
		return heroes;
	}
	
	public static Team getNeighborSolution(Team heroes, List<Character> chars){
		Team neighbor = null;
		Random rand = new Random();
		
		if(useBudget){
			while(true){
				int n = 0;
				if(heroes.getSize() > 1){
					n = rand.nextInt(3) + 1;
				}else{
					n = rand.nextInt(2) + 1;
				}
				
				if(n == 1){
					neighbor = heroes.addCharacter();
					if(neighbor.getCost() <= budget){
						return neighbor;
					}else{
						continue;
					}
				}else if(n == 2){
					neighbor = heroes.changeCharacter();
					if(neighbor.getCost() <= budget){
						return neighbor;
					}else{
						continue;
					}
				}else if(n == 3){
					neighbor.removeCharacter();
					return neighbor;
				}else{
					continue;
				}
			}
		}else{
			// TODO
		}
		
		return neighbor;
	}
	
	public static Integer calculateSolutionValue(Team heroes, Team villains, List<Relation> relations){
		Integer sol = 0;
		
		return sol;
	}
	
	public static Boolean isValidSolution(Team heroes){
		return true;
	}
	
	public static Double updateTemp(Double temp){
		Double newTemp = 0.0;
		return newTemp;
	}
	
	public static void setUseBudget(Boolean bg){
		useBudget = bg;
	}
}
