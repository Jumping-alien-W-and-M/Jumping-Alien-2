package jumpingalien.model;

import java.util.List;
import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import jumpingalien.util.Sprite;

/**
 * A class of shark, which are enemies that live in the water.
 */
public class Shark extends Enemy {
	
	/**
	 * Creates a new shark.
	 * 
	 * @param x
	 * 			The x-position of the new shark.
	 * @param y
	 * 			The y-position of the new shark.
	 * @param images
	 * 			The array of sprites of the new shark.
	 * @effect	...
	 * 			| super(x, y, images, 1.5, 0, 4, 1, 4, 100) 
	 */
	public Shark(double x, double y, Sprite[] images) {
		super(x, y, images, 1.5, 0, 4, 1, 4, 100);
	}
	
	/**
	 * Checks if ay is a valid vertical acceleration.
	 * 
	 * @param ay
	 * 			the value to be checked
	 * @result	...
	 * 			| result = ((ay == 0) || (ay == -10) || (Math.abs(ay) <= getMaxRiseAy()))
	 */
	@Override
	public boolean isValidAy(double ay) {
		return ((ay == 0) || (ay == -10) || (Math.abs(ay) <= getMaxRiseAy()));
	}
	
	/**
	 * Gets the number of periods this shark has not jumped.
	 */
	@Basic
	public int getNonJumpingPeriods() {
		return non_jumping_periods;
	}

	/**
	 * Sets the number of periods this shark has not jumped.
	 * 
	 * @param nonjumpingperiods
	 * 			the new number of periods this shark has not jumped.
	 * @pre		...
	 * 			| nonjumpinperiods >= 0
	 * @post	...
	 * 			| new.getNonJumpingPeriods() = nonjumpinperiods
	 */
	private void setNonJumpingPeriods(int nonjumpingperiods) {
		assert(nonjumpingperiods >= 0);
		this.non_jumping_periods = nonjumpingperiods;
	}

	private int non_jumping_periods = 5;
	
	/**
	 * Gets the absolute value of the maximum vertical acceleration this shark can rise or dive with.
	 */
	@Basic @Immutable
	public static double getMaxRiseAy() {
		return Shark.max_rise_ay;
	}
	
	private static final double max_rise_ay = 0.2;
	
	/**
	 * Gets the new vertical acceleration of this shark.
	 * 
	 * @result	...
	 * 			| if((canDive() && getAy() < 0 && getAy() > -getMaxRiseAy()))
	 *			|	result = getAy()
	 * @result 	...
	 * 			| if (canRise() && getAy() > 0 && getAy() < getMaxRiseAy())
	 * 			|	 result = getAy()
	 * @result	...
	 *			| List<List<List<Object>>> collisions = getCollisions()
	 *			| for(int i = 0; i < 2; i++) 
	 *			|	if (collisions.get(i).get(1).contains(Feature.air)
	 *			|	   || collisions.get(i).get(1).contains(Feature.magma)) 
	 *			|		result = -10
	 * @result	...
	 *			| else
	 *			| 	result = 0 
	 */
	@Model @Override
	protected double advanceAy() {
		List<List<List<Object>>> collisions = getCollisions();
		if (canDive() && getAy() < 0 && getAy() > -getMaxRiseAy()) return getAy();
		if (canRise() && getAy() > 0 && getAy() < getMaxRiseAy()) return getAy();
		for(int i = 0; i < 2; i++) {
			if (collisions.get(i).get(1).contains(Feature.air) || collisions.get(i).get(1).contains(Feature.magma)) return -10;
		}
		return 0;
	}
	
	/**
	 *  Gets the new vertical speed of this shark.
	 *  
	 * @param time
	 *  		The time to calculate the vertical speed of this shark with.  
	 * @result	...
	 *  		| if((getAy() > 0 && !canRise()) || (getAy() < 0 && getAy() > -getMaxRiseAy() && !canDive()))
	 *			|	result =  0
	 * @result	...
	 *			| result = super.advaceVy(time)
	 * @throws IllegArgumentException
	 * 			| ! isValidDt(time)	
	 */
	@Override
	protected double advanceVy(double time) throws IllegalArgumentException {
		if (!isValidDt(time))
			throw new IllegalArgumentException();
		if((getAy() > 0 && !canRise()) || (getAy() < 0 && getAy() > -getMaxRiseAy() && !canDive()))
			return 0;
		return(super.advanceVy(time));
	}
	
	/**
	 * Advances the y-position of this shark over a given time interval.
	 * 
	 * @effect 	...
	 * 			super.advanceY(time)
	 * @throws IllegArgumentException
	 * 			| ! isValidDt(time)	
	 */
	@Override
	protected void advanceY(double time) throws IllegalArgumentException{
		if (!isValidDt(time))
			throw new IllegalArgumentException();
		
		
		super.advanceY(time);
	}
	
	/**
	 * Starts a random vertical movement for this shark.
	 * 
	 * @effect	...
	 * 			| setVy(0)
	 * @effect 	...
	 * 			| if(getAy() != -10)
	 *			|	setAy(0);
	 * @effect	...			
	 *			| if(getNonJumpingPeriods() > 4 && canJump())
	 *			|	startJump()
	 *			| else 
	 *			|	startRiseOrDive()
	 */
	@Override
	protected void performRandomVerticalAction() {
		setVy(0);
		if(getAy() != -10)
			setAy(0);

		if(getNonJumpingPeriods() > 4 && canJump()){
			startJump();
		} else 
			startRiseOrDive();	
	}
	
	/**
	 * Handles a collision between this shark and a slime.
	 * 
	 * @param slime
	 * 			The slime this shark collides with.
	 * @pre		...
	 * 			| slime != null
	 * @effect	...
	 * 			| if (this.getDeathTime() == 0 && slime.getDeathTime() == 0 && getTimeInvincible() == 0 && slime.getTimeInvincible() == 0) 
	 *			|	if (direction != 1) 
	 *			|		slime.hit(50)
	 *			|		slime.setTimeInvincible(0.6)
	 *			| 	if (direction != 3) {
	 *			|		setHitpoints(getHitpoints() - 50);
	 *			|		setTimeInvincible(0.6)
	 */
	@Override
	protected void collisionHandleSlime(Slime slime, int direction) {
		assert(slime != null);
		
		if (this.getDeathTime() == 0 && slime.getDeathTime() == 0 && getTimeInvincible() == 0 && slime.getTimeInvincible() == 0) {
			if (direction != 1) {
				slime.hit(50);
				slime.setTimeInvincible(0.6);
			}
			
			if (direction != 3) {
				setHitpoints(getHitpoints() - 50);
				setTimeInvincible(0.6);
			}
		}
	}
	
	/**
	 * Handles a collision between this shark and the player.
	 * 
	 * @param player
	 * 			the player this shark collides with.
	 * @pre		...	
	 * 			| player != null
	 * @effect	...
	 * 			| player.collisionHandleShark(this, mirroDirection(direction))
	 */
	@Override
	protected void collisionHandleMazub(Mazub player, int direction) {
		assert(player != null);
		player.collisionHandleShark(this, mirrorDirection(direction));
	}
	
	/**
	 * Handles a collision between this shark and the air.
	 * 
	 * @param time
	 * 			The time to advance the time in air of this shark with.
	 * @effect	...
	 * 			| setTimeInAir(getTimeInAir() + time)
	 *			| while(getTimeInAir() >= 0.2)
	 *			|	setHitpoints(getHitpoints() - 6)
	 *			|	setTimeInAir(getTimeInAir() - 0.2)
	 * @throws IllegArgumentException
	 * 			| ! isValidDt(time)			
	 */
	@Override
	protected void collisionHandleAir(double time)throws IllegalArgumentException{
		if (! isValidDt(time))
			throw new IllegalArgumentException();
		
		setTimeInAir(getTimeInAir() + time);
		while(getTimeInAir() >= 0.2){
			setHitpoints(getHitpoints() - 6);
			setTimeInAir(getTimeInAir() - 0.2);
		}
	}
	
	/**
	 * Handles a collision between this shark and magma.
	 * 
	 * @param time
	 * 			The time to advance the time in magma of this shark with.
	 * @effect 	...
	 * 			| if(getTimeInMagma() == 0)
	 *			|	setHitpoints(getHitpoints() - 50)
	 *			| setTimeInMagma(getTimeInMagma() + time)
	 *			| while(getTimeInMagma() >= 0.2)
	 *			|	setHitpoints(getHitpoints() - 50)
	 *		    |	setTimeInMagma(getTimeInMagma() - 0.2)
	  * @throws IllegArgumentException
	 * 			| ! isValidDt(time)		
	 */
	@Override 
	protected void collisionHandleMagma(double time) throws IllegalArgumentException{
		if (! isValidDt(time))
			throw new IllegalArgumentException();
		
		if(getTimeInMagma() == 0)
			setHitpoints(getHitpoints() - 50);
		
		setTimeInMagma(getTimeInMagma() + time);	
		
		while(getTimeInMagma() >= 0.2){
			setHitpoints(getHitpoints() - 50);
			setTimeInMagma(getTimeInMagma() - 0.2);			
		}
	}
	
	/**
	 * Starts a jump for this shark.
	 * 
	 * @effect	...
	 * 			| setVy(2)
	 * @effect	...
	 * 			| setNonJumpingPeriods(0)
	 * @effect	...
	 * 			| List<List<List<Object>>> collisions = getCollisions()
	 *			| if (!(noObjectMovementBlocking(collisions.get(3).get(0))) 
	 *			|	&& (collisions.get(3).get(1).contains(Feature.ground)))
	 *			|		setJustJumped(true)
	 */
	@Model
	private void startJump(){
		setVy(2);

		setNonJumpingPeriods(0);

		List<List<List<Object>>> collisions = getCollisions();
		if (!(noObjectMovementBlocking(collisions.get(3).get(0))) && (collisions.get(3).get(1).contains(Feature.ground)))
			setJustJumped(true);
	}
	
	/**
	 * Checks whether or not this shark can dive.
	 * 
	 * @return	...
	 * 			| ArrayList<List<List<Object>>> collisions = getCollisions()
	 *			| for(Object feature : collisions.get(3).get(1)) 
	 *			|		if ((Feature) feature != Feature.water) result =  false
	 *			| result = true;
	 */
	private boolean canDive(){
		List<List<List<Object>>> collisions = getCollisions();
		for(Object feature : collisions.get(3).get(1)) {
			if ((Feature) feature != Feature.water) return false;
		}
		return true;
	}
	
	/**
	 * Checks whether or not this shark can rive.
	 * 
	 * @return	...
	 * 			| ArrayList<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0, getHeight() + 1)
	 *			| for(Object feature : collisions.get(1).get(1)) 
	 *			|		if ((Feature) feature != Feature.water) result =  false
	 *			| result = true;
	 */
	private boolean canRise() {
		List<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0, getHeight() + 1);
		for(Object feature : collisions.get(1).get(1)) {
			if ((Feature) feature != Feature.water) return false;
		}
		return true;
	}
	
	/**
	 * Start a dive or a rise for this shark.
	 * 
	 * @effect	...
	 * 			| setNonJumpingPeriods(getNonJumpingPeriods() + 1);
	 * @effect	...
	 * 			| Random rand = new Random()
	 * 			| double lowerbound = 0
	 *			| double upperbound = 0
	 *			| if (canDive()) lowerbound = -getMaxRiseAy()
	 *			| if (canRise()) upperbound = getMaxRiseAy()
	 *			| setAy(rand.nextDouble()*(upperbound - lowerbound) + lowerbound)
	 *			| if (getAy() > 0) setJustJumped(true)
	 *		
	 */
	@Model
	private void startRiseOrDive() {
		Random rand = new Random(); 
		setNonJumpingPeriods(getNonJumpingPeriods() + 1);
		double lowerbound = 0;
		double upperbound = 0;
		if (canDive()) lowerbound = -getMaxRiseAy();
		if (canRise()) upperbound = getMaxRiseAy();
		setAy(rand.nextDouble()*(upperbound - lowerbound) + lowerbound);
		if (getAy() > 0) setJustJumped(true);
	}
	
	/**
	 * Checks whether or not this shark can jump.
	 * 
	 * @effect 	...
	 * 			| ArrayList<List<List<Object>>> collisions = getcollisions()		
	 *			|	if(collisions.get(3).get(1).contains(Feature.ground) || collisions.get(3).get(1).contains(Feature.water) 
	 *			|	  || collisions.get(0).get(1).contains(Feature.ground) || collisions.get(0).get(1).contains(Feature.water)
	 *			|	  || collisions.get(3).get(0).size() != 0)
	 *			|		result =  true		
	 *			| result =  false
	 */
	@Override
	public boolean canJump(){
		List<List<List<Object>>> collisions = getCollisions();
		
		if(collisions.get(3).get(1).contains(Feature.ground) || collisions.get(3).get(1).contains(Feature.water)
				|| collisions.get(0).get(1).contains(Feature.ground) || collisions.get(0).get(1).contains(Feature.water)
				|| collisions.get(3).get(0).size() != 0)
			return true;
		
		return false;
	}
	
	
	/**
	 * Breaks the connection between this shark and it's world.
	 * 
	 * @pre		...
	 * 			| this.getWorld() != null
	 * @effect	...
	 * 			| this.getWorld().removeShark(this)
	 */
	@Override
	protected void terminate() {
		assert(this.getWorld() != null);
		this.getWorld().removeShark(this);
	}	
	

}
