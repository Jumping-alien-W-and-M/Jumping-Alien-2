package jumpingalien.model;

import java.util.ArrayList;
import java.util.List;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;

/**
 * An alien plant which can heal Mazub.
 * 
 * @invar 	The amount of sprites is equal to two.
 * 			| images.length == 2 
 *
 */
public class Plant extends GameObject {
	
	/**
	 * Creates a new plant.
	 * 
	 * @param x
	 * 			the x-position of the new plant
	 * @param y
	 * 			the y-position of the new plant
	 * @param images
	 * 			the new plant's array of sprites
	 * @effect	...
	 * 			| super(x,y,images, 0, 0.5)
	 * @effect	...
	 * 			| setMovementTime(0)
	 * @effect	...
	 * 			| setVx(getVxi())
	 */
	public Plant(double x, double y, Sprite[] images){
		super(x, y, images, 0, 0.5, 0.5);
		
		setMovementTime(0);
		setVx(getVxi());
	}
	
	/**
	 * Gets the time this plant is moving in one direction.
	 */
	@Basic
	public double getMovementTime(){
		return this.movement_time;
	}
	
	/**
	 * Sets the time this plant is moving in one direction.
	 * 
	 * @param movement_time
	 * 				the new movement time of this plant
	 * @post 	The new movement time of this plant is equal to movement_time
	 * 			| new.getMovementTime() = movement_time
	 */
	@Model
	protected void setMovementTime(double movement_time){
		this.movement_time = movement_time;
	}
	
	private double movement_time;
	
	/**
	 * Advances the time by a given time period.
	 * 
	 * @param dt
	 * 			The amount of seconds to advance.
	 * @effect	...
	 * 			| double timestep = 0		
	 *			| for(double time_passed = 0; time_passed < dt; time_passed += timestep) 
	 *			|	if (getVx() != 0) timestep = Math.min(0.01/Math.abs(getVx()), dt - time_passed)
	 *			|	else timestep = dt - time_passed
	 *			|	super.advanceTimeStep(timestep)
	 *			|	if (getWorld() == null) return
	 *			|	advanceMovementTime(timestep)
	 *			|	advanceDeathTime(timestep)
	 *			| 	if (getWorld() == null) return
	 * @effect	...
	 * 			| List<List<List<Object>>> collisions = getCollisions()
	 *			| collisionHandle(collisions, dt)
	 * @throws IllegalArgumentException
	 * 			If dt is not a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Model @Override
	protected void advanceTime(double dt)throws IllegalArgumentException{
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double timestep = 0;
		
		for(double time_passed = 0; time_passed < dt; time_passed += timestep) {
			if (getVx() != 0) timestep = Math.min(0.01/Math.abs(getVx()), dt - time_passed);
			else timestep = dt - time_passed;
			super.advanceTimeStep(timestep);
			if (getWorld() == null) return;
			
			advanceMovementTime(timestep);
			advanceDeathTime(timestep);
			if (getWorld() == null) return;
		}
		List<List<List<Object>>> collisions = getCollisions();
		collisionHandle(collisions, dt);
	}
	
	/**
	 * Returns the new vertical acceleration of this plant.
	 * 
	 * @result	...
	 * 			| result = 0
	 */
	@Model @Override
	protected double advanceAy() {
		return 0;
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
	@Model
	private void advanceDeathTime(double time){
		if(! (getDeathTime() == 0)){
			this.setDeathTime(getDeathTime() - time);
			if(getDeathTime() <= 0)
				terminate();
		}
	}
	
	/**
	 * Advances the time this plant has been moving in the same direction.
	 * 
	 * @param time
	 * 			The time to increase the movement time of this plant with.
	 * @effect	Sets the movement time of this plant to the current movement time plus time.
	 * 			| setMovementTime(getMovementTime() + time)
	 * @effect 	If the new movement time is bigger then 0.5 it will be decreased by 0.5 
	 * 			and the direction of this plant will be changed.
	 * 			| if(new.getMovementTime() > 0.5)
	 *			| 	setMovementTime(getMovementTime() - 0.5);
	 *			| 	changeDirection();			
	 */
	@Model
	private void advanceMovementTime(double time){
		setMovementTime(getMovementTime() + time);
		while (getMovementTime() > 0.5) {
			setMovementTime(getMovementTime() - 0.5);
			changeDirection();			
		}
	}
	
	/**
	 * Checks and deals with collisions.
	 * 
	 * @param collisions
	 * 			The result of the collisiondetect method in World.
	 * @effect	loops over the elements of collisions 
	 * 			and if there is a player in it, the method calls collisionhandleplayer() 
	 * 			|for(int i = 0; i <= 4; i++) {
	 *			| 	ArrayList<Object> collision_objects = (ArrayList<Object>) collisions.get(i).get(0);
	 *			| 	for(Object collision_object: collision_objects){
	 *			|		if(collision_object instanceof Mazub)
	 *			|			collisionhandleplayer((Mazub) collision_object, i);	
	 */
	@Override @Model
	protected void collisionHandle(List<List<List<Object>>> collisions, double time) {
		for(int i = 0; i < 4; i++) {
			ArrayList<Object> collision_objects = (ArrayList<Object>) collisions.get(i).get(0);
			for(Object collision_object: collision_objects){
				if(collision_object instanceof Mazub)
					collisionHandleMazub((Mazub) collision_object, i);				
			}
		}
	}
	
	/**
	 * Handles a collision with the player.
	 * 
	 * @param player
	 * 			The player this plant collides with.
	 * @effect	...
	 * 			| player.collisionHandlePlant(this, mirrorDirection(direction))
	 */
	@Override
	protected void collisionHandleMazub(Mazub player, int direction){
		player.collisionHandlePlant(this, mirrorDirection(direction));
	}
	
	/**
	 * This plant will be removed from the game world.
	 * 
	 * @pre		...
	 * 			| getWorld() != null
	 * @effect	This plant will be removed from the game world.
	 * 			| getWorld().removePlant(this)
	 */
	@Override
	protected void terminate() {
		assert(getWorld() != null);
		getWorld().removePlant(this);
	}
	
	/**
	 * Changes the direction in which this plant is moving.
	 * 
	 * @effect	...
	 * 			| setVx(-getVx))
	 */
	private void changeDirection(){
			setVx(-getVx());
	}
}
