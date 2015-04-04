package jumpingalien.model;

import java.util.Random;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;

public class Slime extends Enemy {
	
	public Slime(double x, double y, Sprite[] images) {
		super(x, y, images, 0.7, 0, 2, 6);
		setHitpoints(100);

		setVxmax(2.5);
	}
	
	/**
	 * Sets the world this game object is in.
	 * 
	 * @param world
	 * 			The world this game object should be in.
	 * @pre		...
	 * 			| (getSchool() != null)
	 * @pre		...
	 * 			| (getSchool().getWorld() == world)
	 * @effect	...
	 * 			| super.setWorld(world)
	 */
	@Override
	protected void setWorld(World world) {
		assert(getSchool() != null);
		assert(getSchool().getWorld() == world);
		
		super.setWorld(world);
	}
	
	/**
	 * Gets the school this slime is a part of.
	 */
	@Basic
	public School getSchool() {
		return this.school;
	}
	
	/**
	 * Sets the school of this slime.
	 * 
	 * @param school
	 * 			The new school this slime should be linked to.
	 * @post	The slime's old school shall no longer contain this slime, if this slime had an old school.
	 * 			| if (getSchool() != null)
	 * 			|	then (new.(this.getSchool()).hasAsSlime(this)) == false)
	 */
	public void setSchool(School school) {
		this.school = school;
	}
	
	private School school;
	
	/**
	 * Performs a random action for this slime.
	 * 
	 * @effect	...
	 * 			| setVx(0)
	 * @effect	...
	 * 			| rand = new Random()
	 * 			| if (rand.nextInt(2) == 0) setAx(getAxi())
	 * 			| else setAx(-getAxi())
	 */
	@Override
	protected void performRandomAction() {
		setVx(0);
		Random rand = new Random();
		if (rand.nextInt(2) == 0) setAx(getAxi());
		else setAx(-getAxi());
	}
	
	protected void collisionHandleMazub(Mazub player) { }
	protected void collisionHandleShark(Shark shark) { }
	protected void collisionHandlePlant(Plant plant) { }
	protected void collisionHandleSlime(Slime slime) { }

	protected void collisionHandleAir(double time) { }
	protected void collisionHandleWater(double time) { }
	protected void collisionHandleMagma(double time) { }
	
	/**
	 * Terminates this slime.
	 * 
	 * @effect	...
	 * 			| getSchool().removeSlime(this)
	 */
	@Override
	protected void terminate() {
		getSchool().removeSlime(this);
	}
	
}
