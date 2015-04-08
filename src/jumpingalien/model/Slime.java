package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;

public class Slime extends Enemy {
	
	/**
	 * Creates a new slime at a given position, with a given series of sprites and part of a given school.
	 * 
	 * @param x
	 * 			The x-position of the new slime.
	 * @param y
	 * 			The y-position of the new slime.
	 * @param images
	 * 			The series of sprites of the new slime.
	 * @param school
	 * 			The school the new sprite should be part of.
	 * @effect	...
	 * 			| super(x, y, images, 0.7, 0, 2, 6)
	 * @effect	...
	 * 			| if (school != null)
	 * 			|	then school.addSlime(this)
	 */
	public Slime(double x, double y, Sprite[] images, School school) {
		super(x, y, images, 0.7, 0, 2.5, 2, 6, 100);
		
		if (school != null) school.addSlime(this);
	}
	
	/**
	 * Hits this slime, subtracting a given amount of hitpoints from this slime and one from all other slimes in its school.
	 * 
	 * @param hitpoints
	 * 			The amount of hitpoints which should be subtracted from this slime.
	 * @pre		...
	 * 			| (hitpoints >= 0)
	 * @effect	...
	 * 			| for (Slime slime : getSchool().getSlimes())
	 * 			| 	if (slime != this)
	 * 			|		then setHitpoints(getHitpoints() - 1)
	 * @effect	...
	 * 			| setHitpoints(getHitpoints() - hitpoints)
	 */
	protected void hit(int hitpoints) {
		assert(hitpoints >= 0);
		
		for (Slime slime : getSchool().getSlimes()) {
			if (slime != this) setHitpoints(getHitpoints() - 1);
		}
		
		setHitpoints(getHitpoints() - hitpoints);
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
	protected void setSchool(School school) {
		this.school = school;
	}
	
	/**
	 * Switches this slime from one school to another, first handing over one hitpoint to all slimes of its old school,
	 * then all slimes of the new school handing over one hitpoint to this slime.
	 * 
	 * @param school
	 * 			The new school this slime should be part of.
	 * @pre		...
	 * 			| (school != null)
	 * @effect	...
	 * 			| for(Slime slime : getSchool().getSlimes())
	 * 			| 	if (slime != this) {
	 * 			|		slime.setHitpoints(getHitpoints() + 1)
	 * 			|		this.setHitpoints(getHitpoints() - 1)
	 * 			|	}
	 * @effect	...
	 * 			| getSchool().removeSlime(this)
	 * @effect	...
	 * 			| school.addSlime(this)
	 * @effect	...
	 * 			| for(Slime slime : getSchool().getSlimes())
	 * 			| 	if (slime != this) {
	 * 			|		this.setHitpoints(getHitpoints() + 1)
	 * 			|		slime.setHitpoints(getHitpoints() - 1)
	 * 			|	}
	 */
	private void switchSchool(School school) {
		assert(school != null);
		
		// Hand over one hitpoint to all slimes of old school
		for(Slime slime : getSchool().getSlimes()) {
			if (slime != this) {
				slime.setHitpoints(getHitpoints() + 1);
				this.setHitpoints(getHitpoints() - 1);
			}
		}
		
		// Change school
		getSchool().removeSlime(this);
		school.addSlime(this);
		
		// Get one hitpoint from all slimes of new school
		for(Slime slime : getSchool().getSlimes()) {
			if (slime != this) {
				this.setHitpoints(getHitpoints() + 1);
				slime.setHitpoints(getHitpoints() - 1);
			}
		}
		
	}
	
	private School school;
	
	/**
	 * Handles a collision with a Mazub.
	 * 
	 * @param player
	 * 			The Mazub this slime has a collision with.
	 * @pre		...
	 * 			| (player != null)
	 * @effect	...
	 * 			| collisionHandleEnemy()
	 */
	@Override
	protected void collisionHandleMazub(Mazub player) { assert(player != null); player.collisionHandleSlime(this); }

	/**
	 * Handles a collision with a shark.
	 * 
	 * @param shark
	 * 			The shark this slime has a collision with.
	 * @pre		...
	 * 			| (shark != null)
	 * @effect	...
	 * 			| collisionHandleEnemy()
	 */
	@Override
	protected void collisionHandleShark(Shark shark) { assert(shark != null); shark.collisionHandleSlime(this); }
	
	/**
	 * Handles a collision with another slime
	 * 
	 * @param slime
	 * 			The slime this slime has a collision with.
	 * @pre		...
	 * 			| (slime != null)
	 * @effect	...
	 * 			| if (this.getSchool().getNbOfSlimes() < slime.getSchool().getNbOfSlimes())
	 * 			|	then this.switchSchool(slime.getSchool())
	 * @effect	...
	 * 			| if (this.getSchool().getNbOfSlimes() > slime.getSchool().getNbOfSlimes())
	 * 			|	then slime.switchSchool(this.getSchool())
	 * 
	 */
	protected void collisionHandleSlime(Slime slime) {
		assert(slime != null);
		
		int size1 = this.getSchool().getNbOfSlimes();
		int size2 = slime.getSchool().getNbOfSlimes();
		
		if (size1 < size2) {
			this.switchSchool(slime.getSchool());
		} else if (size1 > size2) {
			slime.switchSchool(this.getSchool());
		}
		
	}
	
	/**
	 * Handles a collision with water.
	 * 
	 * @param time
	 * 			The time this slime has spent in water since the last time this method was called.
	 * @pre		...
	 * 			| (time > 0)
	 * @effect	...
	 * 			| setTimeInWater(getTimeInWater() + time)
	 * @effect	...
	 * 			| while (getTimeInWater() >= 0.2) {
	 * 			|	hit(20)
	 * 			|	setTimeInWater(getTimeInWater() - 0.2)
	 * 			| }
	 */
	@Override
	protected void collisionHandleWater(double time) {
		assert(time > 0);
		
		setTimeInWater(getTimeInWater() + time);
		
		while (getTimeInWater() >= 0.2) {
			hit(2);
			setTimeInWater(getTimeInWater() - 0.2);
		}
	}
	
	/**
	 * Handles a collision with magma.
	 * 
	 * @param time
	 * 			The time this slime has spent in magma since the last time this method was called.
	 * @pre		...
	 * 			| (time > 0)
	 * @effect	...
	 * 			| if (getTimeInMagma() == 0)
	 * 			| 	then hit(20)
	 * @effect	...
	 * 			| setTimeInMagma(getTimeInMagma() + time)
	 * @effect	...
	 * 			| while (getTimeInMagma() >= 0.2) {
	 * 			|	hit(20)
	 * 			|	setTimeInMagma(getTimeInMagma() - 0.2)
	 * 			| }
	 */
	@Override
	protected void collisionHandleMagma(double time) {
		assert(time > 0);
		
		if (getTimeInMagma() == 0) {
			hit(50);
		}
		
		setTimeInMagma(getTimeInMagma() + time);
		
		while (getTimeInMagma() >= 0.2) {
			hit(50);
			setTimeInMagma(getTimeInMagma() - 0.2);
		}
	}
	
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
