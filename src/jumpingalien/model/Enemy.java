package jumpingalien.model;

import java.util.List;
import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import jumpingalien.util.Sprite;

/**
 * An enemy, which hits Mazub on contact.
 * 
 * @invar 	The amount of sprites is equal to two.
 * 			| images.length == 2 
 *
 */
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
	 * @pre		...
	 * 			| (max_action_time >= min_action_time)
	 * @pre		...
	 * 			| (hitpoints > 0)
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
			double max_action_time, int hitpoints, Program program) {
		super(x, y, images, axi, vxi, vxmax, program);
		
		assert(max_action_time >= min_action_time);
		assert(hitpoints > 0);
		this.min_action_time = min_action_time;
		this.max_action_time = max_action_time;
		setHitpoints(hitpoints);
	}
	
	/**
	 * Sets the hitpoints of this enemy.
	 * 
	 * @param hitpoints
	 * 			The amount of hitpoints this enemy should have.
	 * @post	...
	 * 			| (new.getHitpoints() == hitpoints)
	 * @effect	...
	 * 			| if ((getHitpoints() <= 0) && (getDeathTime() == 0))
	 * 			| 	then kkill()
	 */
	@Model @Override
	public void setHitpoints(int hitpoints) {
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
	 * Advances the time by a given time for this enemy.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @effect	...
	 * 			| timestep = getTimestep(dt, 0)
	 * 			| for(time_passed = 0; time_passed < dt; time_passed += timestep) {
	 * 			|	if (getWorld() != null) {
	 * 			|		timestep = getTimestep(dt, time_passed)
	 * 			| 		super.advanceTimeStep(timestep)
	 * 			|	}
	 * 			|	if (getWorld() != null) {
	 * 			|		advanceActionTime(timestep)
	 * 			| 		advanceDeathTime(timestep)
	 * 			|	}
	 * 			| }
	 * 			| collisionHandle(getCollisions(), dt)
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Model @Override
	protected void advanceTime(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double timestep = getTimestep(dt, 0);
		for(double time_passed = 0; time_passed < dt; time_passed += timestep) {
			timestep = getTimestep(dt, time_passed);
			super.advanceTimeStep(timestep);
			if (getWorld() == null) return;
			
			if (getProgram() == null) advanceActionTime(timestep);
			advanceDeathTime(timestep);
			if (getWorld() == null) return;
		}
		
		List<List<List<Object>>> collisions = getCollisions();
		collisionHandle(collisions, dt);
		
		if (getProgram() != null) executeProgram(dt);
	}
	
	/**
	 * Advances the action time of this enemy by a given time.
	 * 
	 * @param time
	 * 			The time which should be advanced.
	 * @effect	...
	 * 			| setActionTime(getActionTime() - time)
	 * @effect	...
	 * 			| while (getActionTime() < 0) {
	 * 			|	rand = new Random();
	 * 			|	new_value = getMinActionTime() + (getMaxActionTime() - getMinActionTime())*rand.nextDouble()
	 * 			|	setActionTime(getActionTime() + new_value)
	 * 			| }
	 * 			| performRandomAction()
	 */
	@Model
	private void advanceActionTime(double time) {
		
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
	 * Advances the time this enemy has been dead for by a given time.
	 * 
	 * @param time
	 * 			The time to decrease the death time of this enemy with.
	 * @effect	...
	 * 			| if((getDeathTime() != 0)){
	 *			|	this.setDeathTime(getDeathTime() - time);
	 * @effect	...
	 * 			| if(new.getDeathTime() <= 0)
	 *			| 	terminate()
	 */
	@Model
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
	
	/**
	 * Starts a random action for this enemy.
	 * 
	 * @effect	...
	 * 			| performRandomHorizontalAction()
	 * @effect	...
	 * 			| performRandomVerticalAction()
	 */
	protected void performRandomAction() {
		performRandomHorizontalAction();
		performRandomVerticalAction();
	}

	/**
	 * Starts a random horizontal action for this enemy.
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
	
	/**
	 * Starts a random vertical action for this enemy.
	 */
	protected void performRandomVerticalAction() { }
}