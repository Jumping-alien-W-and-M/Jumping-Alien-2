package jumpingalien.model;

import java.util.List;
import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.Sprite;

public class Shark extends Enemy {

	public Shark(double x, double y, Sprite[] images) {
		super(x, y, images, 1.5, 0, 4, 1, 4, 100);
	}
	
	/**
	 * Checks if ay is a valid vertical acceleration.
	 * 
	 * @param ay
	 * 			the value to be checked
	 * @result	True if and only if ay is equal to 0 or -10.
	 * 			| result = ((ay == 0) || (ay == -10))
	 */
	@Override
	public boolean isValidAy(double ay) {
		return ((ay == 0) || (ay == -10) || (Math.abs(ay) <= 0.2));
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
		this.non_jumping_periods = nonjumpingperiods;
	}

	private int non_jumping_periods;
	
	/**
	 * Gets the new vertical acceleration of this shark.
	 * 
	 * @result	...
	 * 			| if(getAy() >= -0.2 && getAy() <= 0.2 && canDiveOrRise())
	 *			|	result = getAy()
	 * @result	...
	 *			| else if(getAy() == -10 && (! canJump() && ! submerged(getWorld().collisionDetect(this,0))))
	 *			|	result = getAy()
	 * @result	...
	 *			| else
	 *			| 	result = 0 
	 */
	@Override
	protected double advanceAy() {
		if(getAy() >= -0.2 && getAy() <= 0.2 && canDiveOrRise())
			return(getAy());
		else if(getAy() == -10 && ! canJump() && ! submerged(getWorld().collisionDetect(this,0)))
			return(getAy());
		return 0;			
	}
	
	/**
	 *  Gets the new vertical speed of this shark.
	 *  
	 * @param time
	 *  		The time to calculate the vertical speed of this shark with.
	 *  
	 * @result	...
	 *  		| if((! canDiveOrRise()) && (getAy() != -10))
	 *			|	result =  0
	 * @result	...
	 *			| result = super.advaceVy(time)
	 * @throws IllegArgumentException
	 * 			| ! isValidDt(time)	
	 */
	@Override
	protected double advanceVy(double time) throws IllegalArgumentException{
		if (!isValidDt(time))
			throw new IllegalArgumentException();
		if((! canDiveOrRise()) && (getAy() != -10))
			return 0;
		return(super.advanceVy(time));
	}
	
	/**
	 * Advances the y-position of this shark over a given time interval.
	 * 
	 * @effect	...
	 * 			| if((! canDiveOrRise()) && (getAy() != -10))
	 *			|	setY(getY())
	 * @effect 	...
	 * 			super.advanceY(time)
	 * @throws IllegArgumentException
	 * 			| ! isValidDt(time)	
	 */
	@Override
	protected void advanceY(double time) throws IllegalArgumentException{
		if (!isValidDt(time))
			throw new IllegalArgumentException();
		
		if((! canDiveOrRise()) && (getAy() != -10))
			setY(getY());
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
	 *			| Random rand = new Random()
	 *			| int nextaction;
	 *			| if(getNonJumpingPeriods() > 4 && canJump())
	 *			|	nextaction = rand.nextInt(2)
	 *			| else
	 *			| 	nextaction = rand.nextInt(1)
	 *	
	 *			| if(nextaction == 1)
	 *			|	startJump()						
	 * 			| else if(canDiveOrRise())
	 *			|	startRiseOrDive()	
	 */
	@Override
	protected void performRandomVerticalAction() {
		setVy(0);
		if(getAy() != -10)
			setAy(0);
		Random rand = new Random();
		int nextaction;
		if(getNonJumpingPeriods() > 4 && canJump())
			nextaction = rand.nextInt(2);
		else
			nextaction = rand.nextInt(1);
		
		if(nextaction == 1){
			startJump();
		}						
		else if(canDiveOrRise())
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
	 * 			| this.setHitpoints(getHitpoints() - 50)
	 * @effect 	...
	 * 			| slime.setHitpoints(getHitpoints() - 50)
	 */
	@Override
	protected void collisionHandleSlime(Slime slime, int direction) {
		assert(slime != null);
		
		if (slime.getDeathTime() != 0 && getTimeInvincible() == 0 && slime.getTimeInvincible() == 0) {
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
	 * 			| player.collisionHandleShark(this)
	 */
	@Override
	protected void collisionHandleMazub(Mazub player, int direction) {
		assert(player != null);
		direction += 2;
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
	 * 			| setAy(-10)
	 * @effect	...
	 * 			| setNonJumpingPeriods(0)
	 */
	private void startJump(){
		setVy(2);
		setAy(-10);
		setNonJumpingPeriods(0);

		List<List<List<Object>>> collisions = getCollisions();
		if (!(listEmptyOrPlants(collisions.get(3).get(0))) && (collisions.get(3).get(1).contains(Feature.ground)))
			setJustJumped(true);
	}
	
	/**
	 * Checks whether or not this shark can dive or rise.
	 * 
	 * @return	...
	 * 			| ArrayList<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0)
	 *			| if(collisions.get(3).get(1).contains(Feature.water) || submerged(collisions))
	 *			|	return true
	 *			| return false
	 */
	private boolean canDiveOrRise(){
		List<List<List<Object>>> collisions = getCollisions();
		for(Object feature : collisions.get(3).get(1)) {
			if ((Feature) feature != Feature.water) return false;
		}
		return submerged(collisions);
	}
	
	/**
	 * Checks whether or not this shark is submerged in water.
	 * 
	 * @param collisions
	 * 			The result of the collisiondetect method in the class World.
	 * @return	...
	 * 			| if( collisions.get(1).get(1).contains(Feature.water))
	 *			|	return true
	 *			| return false
	 */
	private boolean submerged(List<List<List<Object>>> collisions){
		for(Object feature : collisions.get(1).get(1)) {
			if ((Feature) feature != Feature.water) return false;
		}
		return true;
	}
	
	/**
	 * Start a dive or a rise for this shark.
	 * 
	 * @effect	...
	 * 			| Random rand = new Random(); 
	 *			| setNonJumpingPeriods(getNonJumpingPeriods() + 1);
	 *			| double abs_value_ax = getAxi()*rand.nextDouble();
	 *			| if(rand.nextInt(2) == 0)
	 *			|	setAx(-abs_value_ax);
	 *			| else
	 *			|	setAx(abs_value_ax);
	 */
	private void startRiseOrDive(){
		Random rand = new Random(); 
		setNonJumpingPeriods(getNonJumpingPeriods() + 1);
		setAx(2*getAxi()*rand.nextDouble() - getAxi());
	}
	
	/**
	 * Checks whether or not this shark can jump.
	 * 
	 * @effect 	...
	 * 			| ArrayList<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0)		
	 *			|	if(collisions.get(3).get(1).contains(Feature.ground) || 
	 *			|		collisions.get(3).get(1).contains(Feature.water) || collisions.get(3).get(0).size() != 0)
	 *			|		return true		
	 *			| return false
	 */
	@Override
	public boolean canJump(){
		List<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0);
		
		if(collisions.get(3).get(1).contains(Feature.ground) || collisions.get(3).get(1).contains(Feature.water)
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
	 * @effect	...
	 * 			| this.setWorld(null)
	 */
	@Override
	protected void terminate() {
		assert(this.getWorld() != null);
		this.getWorld().removeShark(this);
		this.setWorld(null);		
	}	
	

}
