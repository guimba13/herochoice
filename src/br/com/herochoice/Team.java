package br.com.herochoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team {
	
	private List<Character> team;
	private Boolean isHero;
	
	private Double intelligence;
	private Double strength;
	private Double speed;
	private Double durability;
	private Double energy;
	private Double fightingSkills;
	private Double pgMed;
	private Double popMed;
	private Integer size;
	private Double cost;
	
	public Team(){
		team = new ArrayList<Character>();
		
		this.intelligence = 0.0;
		this.strength = 0.0;
		this.speed = 0.0;
		this.durability = 0.0;
		this.energy = 0.0;
		this.fightingSkills = 0.0;
		this.popMed = 0.0;
		this.cost = 0.0;
		this.pgMed = 0.0;
		this.size = 0;
	}
	
	public Team(List<Character> characters, List<Integer> ids){
		team = new ArrayList<Character>();
		
		for(Integer id : ids){
			Character c = characters.get(id-1);
			team.add(c);
		}
		
		this.size = team.size();
		if(size <= 0) return;
		
		calculateAvgs();
	}

	public void calculateAvgs() {
		this.intelligence = 0.0;
		this.strength = 0.0;
		this.speed = 0.0;
		this.durability = 0.0;
		this.energy = 0.0;
		this.fightingSkills = 0.0;
		this.popMed = 0.0;
		this.cost = 0.0;

		for(int i=0; i < team.size(); i++){
			this.intelligence += (Double)(team.get(i).getIntelligence() / team.size());
			this.strength += (Double)team.get(i).getStrength() / team.size();
			this.speed += (Double)team.get(i).getSpeed() / team.size();
			this.durability += (Double)team.get(i).getDurability() / team.size();
			this.energy += (Double)team.get(i).getEnergy() / team.size();
			this.fightingSkills += (Double)team.get(i).getFightingSkills() / team.size();
			this.popMed += (Double)team.get(i).getNumberComics() / team.size();
			this.cost += (Double)(team.get(i).getPgMed() * team.get(i).getNumberComics());
		}
		this.pgMed = (Double)(intelligence + strength + speed + durability + energy + fightingSkills) / 6;
	}

	public List<Character> getTeam() {
		return team;
	}

	public void setTeam(List<Character> team) {
		this.team = team;
	}

	public Boolean getIsHero() {
		return isHero;
	}

	public void setIsHero(Boolean isHero) {
		this.isHero = isHero;
	}

	public Double getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(Double intelligence) {
		this.intelligence = intelligence;
	}

	public Double getStrength() {
		return strength;
	}

	public void setStrength(Double strength) {
		this.strength = strength;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Double getDurability() {
		return durability;
	}

	public void setDurability(Double durability) {
		this.durability = durability;
	}

	public Double getEnergy() {
		return energy;
	}

	public void setEnergy(Double energy) {
		this.energy = energy;
	}

	public Double getFightingSkills() {
		return fightingSkills;
	}

	public void setFightingSkills(Double fightingSkills) {
		this.fightingSkills = fightingSkills;
	}

	public Double getPgMed() {
		return pgMed;
	}

	public void setPgMed(Double pgMed) {
		this.pgMed = pgMed;
	}

	public Double getPopMed() {
		return popMed;
	}

	public void setPopMed(Double popMed) {
		this.popMed = popMed;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public static void copyTeam(Team team1,Team team2 ){
		team1.setCost(team2.getCost());
		team1.setDurability(team2.getDurability());
		team1.setEnergy(team2.getEnergy());
		team1.setFightingSkills(team2.getFightingSkills());
		team1.setIntelligence(team2.getIntelligence());
		team1.setIsHero(team2.getIsHero());
		team1.setPgMed(team2.getPgMed());
		team1.setPopMed(team2.getPopMed());
		team1.setSize(team2.getSize());
		team1.setSpeed(team2.getSpeed());
		team1.setStrength(team2.getStrength());
		List<Character> cs = new ArrayList<Character>();
		for(Character c : team2.getTeam())
			cs.add(c);
		team1.setTeam(cs);
	}
	
	private Boolean IsInTeam(List<Character> team, Character c){
		for(Character cs : team){
			if(c.getId()==cs.getId()){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
	public Team addCharacter(){
		Random rand = new Random();
		Character newChar;
		do{
			int newHero = rand.nextInt(381) + 1;
			newChar = Helper.characters.get(newHero-1);
		}while(IsInTeam(team, newChar));
		
		Team newTeam = new Team();
		copyTeam(newTeam, this);
		
		newTeam.getTeam().add(newChar);
		newTeam.setSize(newTeam.getSize()+1);
		newTeam.calculateAvgs();
		return newTeam;
	}
	
	public Team addCharacterFirstTeam(Team villains, Relation relations[][]){
		Random rand = new Random();
		Character newChar;
		Boolean hasRelation = Boolean.FALSE;
		
		do{
			int newHero = rand.nextInt(381) + 1;
			newChar = Helper.characters.get(newHero-1);
			
			hasRelation = Boolean.FALSE;
			for(Character c : villains.getTeam()){
				Relation rel = relations[newHero-1][c.getId()-1];
				if(rel != null) hasRelation = Boolean.TRUE;
			}
		}while(IsInTeam(team, newChar) && !hasRelation);
		
		Team newTeam = new Team();
		copyTeam(newTeam, this);
		
		newTeam.getTeam().add(newChar);
		newTeam.setSize(newTeam.getSize()+1);
		newTeam.calculateAvgs();
		return newTeam;
	}
	
	public Team removeCharacter(){
		Random rand = new Random();
		int newHero = chooseRemoveCharacter(this);
		Team newTeam = new Team();
		copyTeam(newTeam, this);
		
		newTeam.getTeam().remove(newHero);
		newTeam.setSize(newTeam.getSize()-1);
		newTeam.calculateAvgs();
		return newTeam;
	}
	
	public Team changeCharacter(){
		Random rand = new Random();
		int heroOut = chooseRemoveCharacter(this); 
		Character newChar;
		do{
			int heroIn = rand.nextInt(381) + 1; 
			newChar = Helper.characters.get(heroIn-1);
		}while(IsInTeam(team, newChar));
		Team newTeam = new Team();
		copyTeam(newTeam, this);
		
		newTeam.getTeam().remove(heroOut);
	
		newTeam.getTeam().add(newChar);
		newTeam.calculateAvgs();
		return newTeam;
	}
	
	public void print(){
		if(team.size() > 0){
			for(Character c : team){
				System.out.print(c.getName() + "|");
			}
			System.out.println("");
		}else{
			System.out.println("Empty Team");
		}
	}
	
	public void printPg(){
		System.out.println("Team: " + intelligence + "|" + strength + "|" + speed + "|" + durability + "|" + energy + "|" + fightingSkills + "|" + pgMed + "|" + popMed + "|" + size);
	}
	
	private Integer chooseRemoveCharacter(Team heroes){
		Double min = 1000000.0;
		Double colaborattion = 0.0;
		Double fightingExperience = 0.0;
		
		Integer indiceMin = 0;
		
		for(int i = 0; i < heroes.getTeam().size(); i++){
			for(int j = 0; j < heroes.getTeam().size(); j++){
				Relation rel = Helper.relations[heroes.getTeam().get(i).getId() - 1][heroes.getTeam().get(j).getId() - 1];
				if(rel != null) colaborattion += rel.getNumberComics();
			}
			for(Character v : Helper.villains.getTeam()){
				Relation rel = Helper.relations[heroes.getTeam().get(i).getId() - 1][v.getId() - 1];
				if(rel != null) fightingExperience += rel.getNumberComics();
			}
			
			Double sum = colaborattion + fightingExperience;
			if(sum < min){
				indiceMin = i;
				min = sum;
			}
			
			colaborattion = 0.0;
			fightingExperience = 0.0;
		}
		
		return indiceMin;
	}

}
