package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Buzam extends Mazub {
	
	public Buzam(double x, double y, Sprite[] images, Program program) {
		super(x, y, images, program);
		
		setHitpoints(500);
	}
	
	/**
	 * Breaks the connection between this Mazub and its world.
	 * 
	 * @pre		This Mazub is currently part of a world.
	 * 			| (getWorld() != null)
	 * @post	The old world will no longer have a player.
	 * 			| (new.(this.getWorld()).getMazub() == null)
	 * @post	This Mazub will no longer be part of a world.
	 * 			| (new.getWorld() == null)
	 */
	@Override
	protected void terminate() {
		assert(getWorld() != null);
		getWorld().setBuzam(null);
	}
	
	/**
	 * Handles a collision of this Mazub with a given shark.
	 * 
	 * @param shark
	 * 			The shark this Mazub collides with.
	 * @param direction
	 * 			The direction in which this Mazub collides with the given shark. 0, 1, 2 and 3 correspond to respectively left, up,
	 * 			right and down.
	 * @pre		The given shark exists.
	 * 			| (shark != null)
	 * @effect	If the given shark isn't dying and both this Mazub and the given shark are vulnerable,
	 * 			both sides will lose 50 hitpoints and be invincible for 0.6 seconds, unless one of them touches
	 * 			the other from the top side, in which case this game object remains unaffected.
	 * 			| if (shark.getDeathTime() == 0 && getTimeInvincible() == 0 && shark.getTimeInvincible() == 0) {
	 * 			|	if (direction != 1) {
	 * 			|		shark.setHitpoints(shark.getHitpoints() - 50)
	 * 			|		shark.setTimeInvincible(0.6)
	 * 			|	}
	 * 			|	if (direction != 3) {
	 * 			|		setHitpoints(getHitpoints() - 50)
	 * 			|		setTimeInvincible(0.6)
	 * 			|	}
	 * 			| }
	 */
	@Override 
	protected void collisionHandleMazub(Mazub alien, int direction) {
		assert(alien != null);
		alien.collisionHandleMazub(this, mirrorDirection(direction));
	}
	
}
