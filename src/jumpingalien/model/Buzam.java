package jumpingalien.model;

import jumpingalien.util.Sprite;


/**
 * The main character, which the players controls.
 * 
 * @invar	The x-position is valid.
 * 			| isValidX(getX())
 * @invar	The y-position is valid.
 * 			| isValidY(getY())
 * @invar	The horizontal velocity is valid.
 * 			| isValidVx(getVx())
 * @invar	The amount of sprites of this Mazub is larger than or equal to 10 and divisible by 2.
 * 			| ((images.length >= 10) && (images.length%2 == 0))
  */
public class Buzam extends Mazub {
	
	/**
	 * Creates a new Buzam at a given position with a series of given sprites and a given program
	 * or no program if there is non given.
	 * 
	 * @param x
	 * 			The new Buzam x-position.
	 * @param y
	 * 			The new Buzam's y-position.
	 * @param images
	 * 			The new Buzam's array of sprites.
	 * @param program
	 * 			The new Buzam's program, null if this buzam has no program.
	 * @effect	This new Buzam will be initialized as a Mazub with the given position, 
	 * 			the given array of sprites, an the given program.
	 * 			| super(x, y, images, program)
	 * @effect	This Buzam's hitpoints will be set to 500.
	 * 			| setHitpoints(500)
	 */
	public Buzam(double x, double y, Sprite[] images, Program program) {
		super(x, y, images, program);
		
		setHitpoints(500);
	}
	
	/**
	 * Breaks the connection between this Buzam and its world.
	 * 
	 * @pre		This Buzam is currently part of a world.
	 * 			| (getWorld() != null)
	 * @post	The old world will no longer have a buzam.
	 * 			| (new.(this.getWorld()).getBuzam() == null)
	 * @post	This Buzam will no longer be part of a world.
	 * 			| (new.getWorld() == null)
	 */
	@Override
	protected void terminate() {
		assert(getWorld() != null);
		getWorld().setBuzam(null);
	}
	
	/**
	 * Handles a collision of this Buzam with a given Mazub, which in this game can only be the player.
	 * 
	 * @param alien
	 * 			The Mazub this Buzam collides with.
	 * @param direction
	 * 			The direction in which this Buzam collides with the given Mazub. 0, 1, 2 and 3 correspond to respectively left, up,
	 * 			right and down.
	 * @pre		The given Mazub exists.
	 * 			| (alien != null)
	 * @effect	...
	 * 			| alien.collisionHandleMazub(this, mirrorDirection(direction));  
	 */
	@Override 
	protected void collisionHandleMazub(Mazub alien, int direction) {
		assert(alien != null);
		alien.collisionHandleMazub(this, mirrorDirection(direction));
	}
	
}
