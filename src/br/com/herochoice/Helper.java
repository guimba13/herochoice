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
	public static Team villains;
	public static Double alpha = 0.7;
	public static Double temp = 25.0;
	

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
			character.setIntelligence(Double.parseDouble(b[3]));
			character.setStrength(Double.parseDouble(b[4]));
			character.setSpeed(Double.parseDouble(b[5]));
			character.setDurability(Double.parseDouble(b[6]));
			character.setEnergy(Double.parseDouble(b[7]));
			character.setFightingSkills(Double.parseDouble(b[8]));
			character.setNumberComics(Double.parseDouble(b[9]));

			characteres.add(character);
		}

		return characteres;
	}

	public static Relation[][] readRelations() throws IOException {
		br = new BufferedReader(new FileReader("relations.csv"));

		String line;
		String splitBy = ";";
		relations = new Relation[767][767];
		
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
	
	public static Team createFirstTeam(){
		Team heroes = new Team();
		Team newTeam = new Team();
		do{
			newTeam = heroes.addCharacter();
			if(useBudget){
				if(newTeam.getCost() == budget){
					heroes = newTeam;
					break;
				}else if(newTeam.getCost() < budget){
					heroes = newTeam;
				}else{
					break;
				}
			}
		}while(newTeam.getSize() < villains.getSize() );
		return heroes;
	}
	
	public static Team getNeighborSolution(Team heroes){
		Team neighbor = null;
		Random rand = new Random();
		
		while(true){
			int n = 0;
			if(heroes.getSize() > 1){
				if(heroes.getSize() < villains.getSize())
					n = rand.nextInt(3) + 1;
				else
					n = rand.nextInt(2) + 2;
			}else{
				n = rand.nextInt(2) + 1;
			}
			
			if(n == 1){
				neighbor = heroes.addCharacter();
				if(!useBudget || (useBudget && neighbor.getCost() <= budget)){
					return neighbor;
				}else{
					continue;
				}
			}else if(n == 2){
				neighbor = heroes.changeCharacter();
				if(!useBudget || (useBudget && neighbor.getCost() <= budget)){
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
	}
	
	public static Double calculateSolutionValue(Team heroes, Team villains){
		Double sol = 0.0;
		Double colaborattion = 0.0;
		Double fightingExperience = 0.0;
		Team aux = heroes;
		
		while(aux.getTeam().size() > 0){
			// colaboracao entre o time de herois
			for(int j = 1; j < aux.getTeam().size(); j++){
				Relation rel = relations[aux.getTeam().get(0).getId() - 1][aux.getTeam().get(j).getId() - 1];
				if(rel != null) colaborattion += rel.getNumberComics();
			}
			
			for(int k = 0; k < villains.getTeam().size(); k++){
				Relation rel = relations[aux.getTeam().get(0).getId() - 1][villains.getTeam().get(k).getId() - 1];
				if(rel != null) fightingExperience += rel.getNumberComics();
			}
			
			aux.getTeam().remove(0);
		}
		
		sol = colaborattion + fightingExperience;
		return sol;
	}
	
	public static boolean isValidSolution(Team heroes){
		if(heroes.getCost() > budget){
			return false;
		}
		if(heroes.getDurability() < Helper.villains.getDurability())
			return false;
		if(heroes.getEnergy() < Helper.villains.getEnergy())
			return false;
		if(heroes.getFightingSkills() < Helper.villains.getFightingSkills())
			return false;
		if(heroes.getIntelligence() < Helper.villains.getIntelligence())
			return false;
		if(heroes.getSpeed() < Helper.villains.getSpeed())
			return false;
		if(heroes.getStrength() < Helper.villains.getStrength())
			return false;
			
		return true;
	}
	
	public static void updateTemp(){
		temp = temp * alpha;
	}
	
	public static void setUseBudget(Boolean bg){
		useBudget = bg;
	}
}
