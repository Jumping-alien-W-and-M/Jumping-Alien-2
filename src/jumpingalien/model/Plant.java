package jumpingalien.model;

import java.util.ArrayList;
import java.util.List;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;

/**
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
	 * @effect	This new plant will be initialized as a game object with the given position, 
	 * 			the given array of sprites, an initial horizontal accelaration of 0 
	 * 			and a initial horizontal velocity of 0.5.
	 * 			| super(x,y,images, 0, 0.5)
	 * @effect	This new plant's movement time will be set to zero.
	 * 			| setMovementTime(0)
	 * @effect	This new plant's horizontal movement is set to the initial horizontal velocity of this plant.
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
	protected void setMovementTime(double movement_time){
		this.movement_time = movement_time;
	}
	
	private double movement_time;
	
	/**
	 * Advances the time by a given time period.
	 * 
	 * @param dt
	 * 			The amount of seconds to advance.
	 * @effect	advances deatthime step by step 
	 * 			| double timestep = 1 / Math.abs(getVx()/100); 
	 *			| for(double time = timestep; timestep <= dt; time += timestep)
	 * 			|   advanceDeathtime(time).
	 * @effect	advances the x-position of this plant step by step 
	 * 			if this plant hits a wall or the player it will not move in that direction.
	 *  		| double timestep = 1 / Math.abs(getVx()/100); 
	 *			| for(double time = timestep; timestep <= dt; time += timestep)
	 * 			| 	ArrayList<List<List<Object>>> collisions = getWorld().collisionDetect(this,0);
	 *			| 	if (canmove(collisions)) 
	 *			|		advanceX(time);	
	 * @effect  step by step advances movement time 
	 *  		| double timestep = 1 / Math.abs(getVx()/100); 
	 *			| for(double time = timestep; timestep <= dt; time += timestep
	 * 			| 	advanceMovementTime(time)
	 * @effect  Checks and deals with collisions in each step.
	 * 			| double timestep = 1 / Math.abs(getVx()/100); 
	 *			| for(double time = timestep; timestep <= dt; time += timestep)
	 *			|		collisionhandle(collisions);
	 * @throws IllegalArgumentException
	 * 			If dt is not a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Override
	public void advanceTime(double dt)throws IllegalArgumentException{
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double timestep = 0.01/getVx();
		for(double time = timestep; timestep <= dt; time += timestep) {
			super.advanceTimeStep(time);
			
			advanceDeathTime(time);
			advanceMovementTime(time);
		}
		
		ArrayList<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0);
		collisionHandle(collisions, dt);
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
	private void advanceMovementTime(double time){
		setMovementTime(getMovementTime() + time);
		if(getMovementTime() > 0.5){
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
	 *			|			collisionhandleplayer((Mazub) collision_object);	
	 */
	protected void collisionHandle(ArrayList<List<List<Object>>> collisions){
		for(int i = 0; i < 4; i++) {
			ArrayList<Object> collision_objects = (ArrayList<Object>) collisions.get(i).get(0);
			for(Object collision_object: collision_objects){
				if(collision_object instanceof Mazub)
					collisionHandleMazub((Mazub) collision_object);				
			}
		}
	}
	
	/**
	 * Handles a collision with the player.
	 * 
	 * @param player
	 * 			The player this plant collides with.
	 * @effect	If this plant is not dying, 
	 * 			the hitpoints of player will be set to the current hitpoints plus 50.
	 * 			| player.setHitpoints(player.getHitpoints() + 50)
	 * @effect	This plant is killed if it wasn't already killed.
	 * 			| this.kill()
	 */
	@Override
	protected void collisionHandleMazub(Mazub player){
		player.collisionHandlePlant(this);
	}
	
	/**
	 * This plant will be removed from the game world.
	 * 
	 * @pre		...
	 * 			| getWorld() != null
	 * @effect	This plant will be removed from the game world.
	 * 			| getWorld().removePlant(this)
	 * @effect  The world this plant belongs to will be set to null.
	 * 			| setWorld(null)
	 */
	@Override
	protected void terminate() {
		assert(getWorld() != null);
		getWorld().removePlant(this);
		setWorld(null);
	}
	
	/**
	 * Changes the direction in which this plant is moving.
	 * 
	 * @post	the new horizontal velocity is equal to the negative of the old horizontal velocity
	 * 			| new.getVx() = -this.getVx()
	 */
	private void changeDirection(){
			setVx(-getVx());
	}
}
