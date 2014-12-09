package br.com.herochoice;

import java.util.List;

public class Team {
	
	private List<Character> team;
	private Boolean isHero;
	
	private Integer intelligence;
	private Integer strength;
	private Integer speed;
	private Integer durability;
	private Integer energy;
	private Integer fightingSkills;
	private Integer pgMed;
	private Integer popMed;
	private Integer numberComics;
	private Integer size;
	private Integer cost;
	
	public Team(List<Character> characters, List<Integer> ids){
		for(Integer id : ids){
			team.add(characters.get(id-1));
		}
		
		this.size = team.size();
		if(size <= 0) return;
		
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

	public Integer getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(Integer intelligence) {
		this.intelligence = intelligence;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getDurability() {
		return durability;
	}

	public void setDurability(Integer durability) {
		this.durability = durability;
	}

	public Integer getEnergy() {
		return energy;
	}

	public void setEnergy(Integer energy) {
		this.energy = energy;
	}

	public Integer getFightingSkills() {
		return fightingSkills;
	}

	public void setFightingSkills(Integer fightingSkills) {
		this.fightingSkills = fightingSkills;
	}

	public Integer getPgMed() {
		return pgMed;
	}

	public void setPgMed(Integer pgMed) {
		this.pgMed = pgMed;
	}

	public Integer getNumberComics() {
		return numberComics;
	}

	public void setNumberComics(Integer numberComics) {
		this.numberComics = numberComics;
	}

	public Integer getPopMed() {
		return popMed;
	}

	public void setPopMed(Integer popMed) {
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
	
	
}
