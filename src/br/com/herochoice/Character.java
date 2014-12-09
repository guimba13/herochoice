package br.com.herochoice;

public class Character {

	private int id;
	private String name;
	private boolean hero;
	private Double intelligence;
	private Double strength;
	private Double speed;
	private Double durability;
	private Double energy;
	private Double fightingSkills;
	private Double numberComics;

	public Double getPgMed(){
		return (intelligence + strength + speed + durability + energy + fightingSkills) / 6;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHero() {
		return hero;
	}

	public void setHero(boolean hero) {
		this.hero = hero;
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

	public Double getNumberComics() {
		return numberComics;
	}

	public void setNumberComics(Double numberComics) {
		this.numberComics = numberComics;
	}
	
	public void print(){
		System.out.println("Character: " + id + "|" + name + "|" + intelligence + "|" + strength + "|" + speed + "|" + durability + "|" + energy + "|" + fightingSkills + "|" + numberComics  + "|");
	}
}
