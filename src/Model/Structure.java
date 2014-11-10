package Model;

public abstract class Structure {
	
	protected int hitpoints;
	private int production;
	private int range;			//Range of tower (in tiles, manhattan distance)
	private int damage;
	private int splashRadius;	//tile radius (tiles in manhattan distance)
	private int rateOfFire;  	//in milliseconds
	private int buildCost;
	private SpecialAttack special;
	
	public Structure(int hp, int prod, int rng, int dmg, int splash, int rate, int cost, SpecialAttack sp) {
		
		hitpoints = hp;
		production = prod;
		range = rng;
		damage = dmg;
		splashRadius = splash;
		rateOfFire = rate;
		special = sp;
		buildCost = cost;
		
	}
	
	public abstract void shoot(Attacker a);
	public abstract void takeDamage(int dmg);
	public abstract void explode();
	
	public int getHP() {
		return hitpoints;
	}
	public int getProd() {
		return production;
	}
	public int getRange() {
		return range;
	}
	public int getDamage() {
		return damage;
	}
	public int getSplash() {
		return splashRadius;
	}
	public int getROF() {
		return rateOfFire;
	}
	public SpecialAttack getSpecial() {
		return special;
	}
}
