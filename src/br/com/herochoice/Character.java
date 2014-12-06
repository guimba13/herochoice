package br.com.herochoice;

public class Character {

	private int id;
	private String name;
	private boolean hero;
	private Integer intelligence;
	private Integer strength;
	private Integer speed;
	private Integer durability;
	private Integer energy;
	private Integer fightingSkills;
	private Integer numberComics;

	public Integer getPgMed(){
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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
