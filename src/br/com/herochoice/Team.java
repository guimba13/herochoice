package br.com.herochoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sun.rmi.runtime.NewThreadAction;

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
	private Integer cost;
	
	public Team(){
		
	}
	
	public Team(List<Character> characters, List<Integer> ids){
		for(Integer id : ids){
			team.add(characters.get(id-1));
		}
		
		this.size = team.size();
		if(size <= 0) return;
		
		calculateAvgs();
	}

	public void calculateAvgs() {
		for(int i=0; i < size; i++){
			this.intelligence += team.get(i).getIntelligence() / size;
			this.strength += team.get(i).getStrength() / size;
			this.speed += team.get(i).getSpeed() / size;
			this.durability += team.get(i).getDurability() / size;
			this.energy += team.get(i).getEnergy() / size;
			this.fightingSkills += team.get(i).getFightingSkills() / size;
			this.popMed += team.get(i).getNumberComics() / size;
			this.cost += (team.get(i).getPgMed() * team.get(i).getNumberComics());
		}
		this.pgMed = (intelligence + strength + speed + durability + energy + fightingSkills) / 6;
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

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
	private void copyTeam(Team team1,Team team2 ){
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
		team1.setTeam(team2.getTeam());
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
		int newHero = rand.nextInt(381) + 1;
		Character newChar;
		do{
			newChar = Helper.characters.get(newHero-1);
		}while(!IsInTeam(team, newChar));
		Team newTeam = new Team();
		copyTeam(newTeam, this);
		
		newTeam.getTeam().add(newChar);
		newTeam.setSize(newTeam.getSize()+1);
		newTeam.calculateAvgs();
		return newTeam;
	}
	
	public Team removeCharacter(){
		Random rand = new Random();
		int newHero = rand.nextInt(this.size) + 1; 
		Team newTeam = new Team();
		copyTeam(newTeam, this);
		
		newTeam.getTeam().remove(newHero-1);
		newTeam.setSize(newTeam.getSize()-1);
		newTeam.calculateAvgs();
		return newTeam;
	}
	
	public Team changeCharacter(){
		Random rand = new Random();
		int heroOut = rand.nextInt(this.size) + 1; 
		int heroIn = rand.nextInt(381) + 1; 
		Character newChar;
		do{
			newChar = Helper.characters.get(heroIn-1);
		}while(!IsInTeam(team, newChar));
		Team newTeam = new Team();
		copyTeam(newTeam, this);
		
		newTeam.getTeam().remove(heroOut-1);
		newTeam.getTeam().add(newChar);
		newTeam.calculateAvgs();
		return newTeam;
	}
	

}
