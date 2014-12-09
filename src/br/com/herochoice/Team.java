package br.com.herochoice;

import java.util.List;

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
	

}
