package br.com.herochoice;

public class Character {

	private int id;
	private String name;
	private boolean hero;
	private int intelligence;
	private int strength;
	private int speedy;
	private int durability;
	private int energy;
	private int fightingSkills;
	private int numberComics;

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

	public int getNumberComics() {
		return numberComics;
	}

	public void setNumberComics(int numberComics) {
		this.numberComics = numberComics;
	}

	public boolean isHero() {
		return hero;
	}

	public void setHero(boolean hero) {
		this.hero = hero;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getFightingSkills() {
		return fightingSkills;
	}

	public void setFightingSkills(int fightingSkills) {
		this.fightingSkills = fightingSkills;
	}

	public int getSpeedy() {
		return speedy;
	}

	public void setSpeedy(int speedy) {
		this.speedy = speedy;
	}
}
