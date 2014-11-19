package TowerFSM;

/**
 * This interface is for all tower state classes.
 * @author Team Something
 *
 */
public interface TowerState {
	
	public int getCurrentHP();
	
	public void takeDamage(int dmg);
}
