package jumpingalien.model;

import java.util.List;
import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import jumpingalien.util.Sprite;

public abstract class Enemy extends GameObject {
	
	/**
	 * Creates an enemy.
	 * 
	 * @param x
	 * 			The x-coordinate this enemy should be created at.
	 * @param y
	 * 			The y-coordinate this enemy should be created at.
	 * @param images
	 * 			The series of sprites which should be used to display this enemy.
	 * @param axi
	 * 			The magnitude of the horizontal acceleration this enemy will have when moving horizontally.
	 * @param vxi
	 * 			The magnitude of the horizontal velocity will initially have when it starts moving horizontally.
	 * @param vxmax
	 * 			The magnitude of the maximal horizontal velocity can have when moving horizontally.
	 * @param min_action_time
	 * 			The minimal time a random action for this enemy should take.
	 * @param max_action_time
	 * 			The maximal time a random action for this enemy should take.
	 * @param hitpoints
	 * 			The amount of hitpoints this enemy should start with.
	 * @post	...
	 * 			| (new.getMinActionTime() == min_action_time)
	 * @post	...
	 * 			| (new.getMaxActionTime() == max_action_time)
	 * @effect	...
	 * 			| super(x, y, images, axi, vxi, vxmax)
	 * @effect	...
	 * 			| setHitpoints(hitpoints)
	 */
	public Enemy(double x, double y, Sprite[] images, double axi, double vxi, double vxmax, double min_action_time,
			double max_action_time, int hitpoints) {
		super(x, y, images, axi, vxi, vxmax);
		this.min_action_time = min_action_time;
		this.max_action_time = max_action_time;
		setHitpoints(hitpoints);
	}
	
	/**
	 * Sets the hitpoints of this enemy.
	 * 
	 * @param hitpoints
	 * 			The amount of hitpoints this enemy should have.
	 * @post	The amount of hitpoints of this enemy is equal to the given hitpoints.
	 * 			| (new.getHitpoints() == hitpoints)
	 */
	@Override
	protected void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
		
		if ((getHitpoints() <= 0) && (getDeathTime() == 0)) {
			kill();
		}
	}
	
	/**
	 * Gets the time this enemy is moving in one direction.
	 */
	@Basic
	public double getActionTime() {
		return this.action_time;
	}
	
	/**
	 * Sets the time this enemy is moving in one direction.
	 * 
	 * @param action_time
	 * 			The new action time for this enemy.
	 * @post 	...
	 * 			| (new.getActionTime() = action_time)
	 */
	protected void setActionTime(double action_time) {
		this.action_time = action_time;
	}
	
	private double action_time = 0;
	
	/**
	 * Advances the time by a given time.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @effect	The x-position will be advanced using the given time.
	 * 			| setX(advanceX(dt))
	 * @effect	The horizontal velocity will be advanced using the given time.
	 * 			| setVx(advanceVx(dt))
	 * @effect	The y-position will be advanced using the given time.
	 * 			| setY(advanceY(dt))
	 * @effect	The vertical velocity will be advanced using the given time.
	 * 			| setVy(advanceVy(dt))
	 * @effect	The vertical acceleration will be equal to -10 if this Mazub is in mid-air, else it will be 0.
	 * 			| setAy(advanceAy())
	 * @effect	The last movement time will be equal to -1 if this Mazub is moving to the left,
	 * 			1 if this Mazub is moving to the right, and if this Mazub is standing still the absolute value of the
	 * 			previous last movement time will be decreased by dt, unless it hits 0, in which case it will stick to 0.
	 * 			| setLastMove(advanceLastMove(dt))
	 * @effect	The animation time will be increased by dt.
	 * 			| setAnimationTime(getAnimationTime() + dt))
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Override
	public void advanceTime(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double timestep = getTimestep(dt, 0);
		for(double time_passed = 0; time_passed < dt; time_passed += timestep) {
			timestep = getTimestep(dt, time_passed);
			super.advanceTimeStep(timestep);
			if (getWorld() == null) return;
			
			advanceActionTime(timestep);
			advanceDeathTime(timestep);
			if (getWorld() == null) return;
		}
		
		List<List<List<Object>>> collisions = getCollisions();
		collisionHandle(collisions, dt);
	}
	
	/**
	 * Advances the action time of this enemy by a given time.
	 * 
	 * @param time
	 * 			The time which should be advanced.
	 * @effect	...
	 * 			| setActionTime(getActionTime() - time)
	 * @effect	...
	 * 			| if (getActionTime() <= 0) {
	 * 			|	while (getActionTime() < min_action_time) {
	 * 			|		rand = new Random()
	 * 			|		new_value = min_action_time + (max_action_time - min_action_time)*rand.nextDouble()
	 * 			|		setActionTime(getActionTime() + new_value)
	 * 			|	}
	 * 			|	performRandomAction()
	 * 			| }
	 */
	protected void advanceActionTime(double time) {
		
		setActionTime(getActionTime() - time);
		
		if (getActionTime() > 0) {
			return;
		}
		
		while (getActionTime() < 0) {
			Random rand = new Random();
			double new_value = getMinActionTime() + (getMaxActionTime() - getMinActionTime())*rand.nextDouble();
			setActionTime(getActionTime() + new_value);
		}
		
		performRandomAction();
	}
	
	/**
	 * Advances the time this plant has been dead for with time.
	 * 
	 * @param time
	 * 			The time to decrease the death time of this plant with.
	 * @post	If deathtime is not equal to zero it will be set to the currendeathtime min time.
	 * 			| if(! (getDeathTime() == 0)){
	 *			|	this.setDeathTime(getDeathTime() - time);
	 * @effect	If the new deatthime is lower then or equal to zero this plant will be terminated.
	 * 			| if(new.getDeathTime() <= 0)
	 *			| 	terminate();
	 */
	private void advanceDeathTime(double time){
		if(getDeathTime() != 0) {
			this.setDeathTime(getDeathTime() - time);
			if(getDeathTime() <= 0) terminate();
		}
	}
	
	/**
	 * Gets the minimum time an action can take for this enemy.
	 */
	@Basic @Immutable
	public double getMinActionTime() {
		return this.min_action_time;
	}
	
	protected final double min_action_time;

	/**
	 * Gets the maximal time an action can take for this enemy.
	 */
	@Basic @Immutable
	public double getMaxActionTime() {
		return this.max_action_time;
	}
	
	protected final double max_action_time;
	
	protected void performRandomAction() {
		performRandomHorizontalAction();
		performRandomVerticalAction();
	}

	/**
	 * Start a random horizontal movement for this enemy.
	 * 
	 * @effect	...
	 * 			| setVx(0)
	 * @effect 	...
	 * 			| Random rand = new Random()
	 *			| if (rand.nextInt(2) == 0) 
	 *			|	setAx(getAxi())
	 *			| else 
	 *			|	setAx(-getAxi())
	 */
	protected void performRandomHorizontalAction(){
		setVx(0);
		Random rand = new Random();
		if (rand.nextInt(2) == 0) 
			setAx(getAxi());
		else 
			setAx(-getAxi());	
	}
	
	protected void performRandomVerticalAction() { }
}