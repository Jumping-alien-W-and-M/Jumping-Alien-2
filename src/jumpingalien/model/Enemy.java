package jumpingalien.model;

import java.util.List;
import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import jumpingalien.util.Sprite;

public abstract class Enemy extends GameObject {

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
		
		if (getHitpoints() <= 0) {
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
			
			advanceActionTime(timestep);
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
		
		while (getActionTime() < min_action_time) {
			Random rand = new Random();
			double new_value = min_action_time + (max_action_time - min_action_time)*rand.nextDouble();
			setActionTime(getActionTime() + new_value);
		}
		
		performRandomAction();
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