package jumpingalien.model;

import java.util.List;

import jumpingalien.util.*;
import jumpingalien.model.GameObject;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;

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
 * 
 * @version 2.0
 */
public class Mazub extends GameObject {
	
	/**
	 * Creates a new Mazub at a given position with a series of given sprites.
	 * 
	 * @param x
	 * 			The new Mazub's x-position.
	 * @param y
	 * 			The new Mazub's y-position.
	 * @param images
	 * 			The new Mazub's array of sprites.
	 * @effect	This new Mazub will be initialized as a game object with the given position, 
	 * 			the given array of sprites, an initial horizontal acceleration of 0.9, an initial horizontal velocity of 1
	 * 			and a maximal horizontal velocity of 3.
	 * 			| super(x, y, images, 0.9, 1, 3)
	 * @effect	This Mazub's last movement will be "standing still".
	 * 			| setLastMove(0)
	 */
	public Mazub(double x, double y, Sprite[] images, Program program) {
		super(x, y, images, 0.9, 1, 3, program);
		
		setLastMove(0);
	}
	
	/**
	 * Checks whether or not this Mazub is currently ducking.
	 */
	@Basic
	public boolean getDucking() {
		return this.ducking;
	}
	
	/**
	 * Sets this Mazub's state of ducking to the given state.
	 * 
	 * @param ducking
	 * 			Whether or not this Mazub should be ducking.
	 * @post	If ducking is true, this Mazub will be ducking, else it won't be.
	 * 			| (new.getDucking() == ducking)
	 */
	@Model
	private void setDucking(boolean ducking) {
		this.ducking = ducking;
	}
	
	private boolean ducking;
	
	/**
	 * Checks whether or not this Mazub has tried to stand up, but couldn't at the time.
	 */
	@Basic
	public boolean getTryStand() {
		return try_stand;
	}
	
	/**
	 * Sets whether or not this Mazub has tried to stand up, but couldn't at the time.
	 * 
	 * @param try_stand
	 * 			Whether or not this Mazub has tried to stand up, but couldn't at the time.
	 * @post	If try_stand is true, this Mazub will have tried to stand up, but couldn't at the time. False if otherwise.
	 * 			| (new.getTryStand() = try_stand)
	 */
	@Model
	private void setTryStand(boolean try_stand) {
		this.try_stand = try_stand;
	}
	
	private boolean try_stand = false;
	
	/**
	 * Gets the variable specifying the last move. If it's smaller than 0, this Mazub's has moved to the left during the last second,
	 * if it's larger than 0, this Mazub's has moved to the right during the last second. If it's zero this Mazub's hasn't moved
	 * horizontally at all during the last second.
	 */
	@Basic
	public double getLastMove() {
		return this.last_move;
	}
	
	/**
	 * Sets the variable specifying the last move.
	 * 
	 * @param last_move
	 * 		The new value for the last move variable.
	 * @pre		The given last_move must be in between -1 and 1, inclusive.
	 * 			| (last_move >= -1 && last_move <= 1)
	 * @post	The last move variable will be equal to the given last_move.
	 * 			| (new.getLastMove() == last_move)
	 */
	@Model
	protected void setLastMove(double last_move) {
		assert(last_move >= -1 && last_move <= 1);
		this.last_move = last_move;
	}
	
	private double last_move;
	
	/**
	 * Gets the amount of frames in this Mazub's running left/right animation.
	 * 
	 * @return The number of frames in both the animation of running left and running right.
	 * 			| result = (getImages().length - 8)/2
	 */
	public int getFramesAmount() {
		return (getImages().length - 8)/2;
	}

	/**
	 * Gets the animation time in seconds of this Mazub.
	 */
	@Basic
	public double getAnimationTime() {
		return this.animation_time;
	}
	
	/**
	 * Sets this Mazub's time within the animation.
	 * 
	 * @param animation_time
	 * 			This Mazub's new time within its running animation in seconds. Starts at 0, and goes up to the maximal animation time
	 * 			until it resets.
	 * @post	This Mazub's new animation time will be equal to the given animation time, possibly shifted by the maximal
	 * 			animation time to make sure it's in the right interval.
	 * 			| (new.getAnimationTime() == getAnimationTime()%(getFramesAmount()*getFrameTime()))
	 */
	@Model
	protected void setAnimationTime(double animation_time) {
		this.animation_time = animation_time;
		while (getAnimationTime() >= getFramesAmount()*getFrameTime()) {
			this.animation_time -= getFramesAmount()*getFrameTime();
		}
	}
	
	private double animation_time;
	
	/**
	 * Returns the time in between two frames.
	 */
	@Basic @Immutable
	public static double getFrameTime() {
		return Mazub.frame_time;
	}
	
	private static final double frame_time = 0.075;
	
	/**
	 * Gets the index of the current frame in the running animation.
	 * 
	 * @return	The index of the current frame in the running animation.
	 * 			| result = (int) Math.floor(getAnimationTime()/getFrameTime())
	 */
	public int getCurrentFrameIndex() {
		return (int) Math.floor(getAnimationTime()/getFrameTime());
	}
	
	/**
	 * Checks whether or not this Mazub is currently jumping.
	 */
	@Basic
	public boolean getJumping() {
		return this.jumping;
	}
	
	/**
	 * Sets whether or not this Mazub is currently jumping.
	 * 
	 * @param jumping
	 * 			Whether or not this Mazub should be jumping.
	 * @post	If jumping is true, this Mazub will be jumping, otherwise it won't.
	 * 			| (new.getJumping() == jumping)
	 */
	@Model
	protected void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	private boolean jumping = false;
	
	/**
	 * Sets the hitpoints of this Mazub.
	 * 
	 * @param hitpoints
	 * 			The new number of hitpoints of this Mazub
	 * @post	If hitpoints is larger than or equal to 500, this Mazub's hitpoints will be set to 500.
	 * 			If hitpoints is smaller than or equal to 0, this Mazub's hitpoints will be set to 0 
	 * 				and this Mazub will be terminated.
	 * 			Else this Mazub's hitpoints will be set to hitpoints.
	 * 			| if (hitpoints <= 0) {
	 *			|	this.hitpoints = 0
	 *			| 	this.terminate()
	 *			| } else if (hitpoints >= 500) {
	 *			|	this.hitpoints = 500
	 *			| } else {
	 *			|	this.hitpoints = hitpoints;
	 *			| }	
	 */
	@Override
	protected void setHitpoints(int hitpoints) {
		if (hitpoints <= 0){
			this.hitpoints = 0;
			this.terminate();	
		}
		else if (hitpoints >= 500) {
			this.hitpoints = 500;
		} else {
			this.hitpoints = hitpoints;
		}
	}
	
	/**
	 * Breaks the connection between this Mazub and its world.
	 * 
	 * @pre		This Mazub is currently part of a world.
	 * 			| (getWorld() != null)
	 * @post	The old world will no longer have a player.
	 * 			| (new.(this.getWorld()).getMazub() == null)
	 * @post	This Mazub will no longer be part of a world.
	 * 			| (new.getWorld() == null)
	 */
	@Override
	protected void terminate() {
		assert(getWorld() != null);
		
		getWorld().setMazub(null);
	}
	
	/**
	 * Advances the time by a given time for this Mazub.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @effect	The given dt shall be split up into small timesteps to ensure this Mazub never moves more than one pixel at a time.
	 * 			For every timestep, this Mazub shall call game object's advanceTimeStep method using this timestep,
	 * 			and if it is still part of a world, this Mazub shall also call its advance advanceLastMove and advanceAnimationTime
	 * 			methods, as well as endDuck if this Mazub has tried to stand up but couldn't at the time.
	 * 			Finally, after all timesteps have been executed, if this Mazub is still part of a world, it will call its
	 * 			collisionHandle method.
	 * 			| timestep = getTimestep(dt, 0)
	 * 			| for(time_passed = 0; time_passed < dt; time_passed += timestep) {
	 *			| 	timestep = getTimestep(dt, time_passed)
	 *			|	super.advanceTimeStep(timestep)
	 *			|	if (getWorld() != null) {
	 *			|		setLastMove(advanceLastMove(timestep))
	 *			|		setAnimationTime(advanceAnimationTime(timestep))
	 *			| 		if (getTryStand()) 
	 *			|			then endDuck();
	 *			|	}
	 *			| }
	 *			| if (getWorld() != null)
	 *			|	then collisionHandle(getCollisions(), dt)
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Override @Model
	protected void advanceTime(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double timestep = getTimestep(dt, 0);
		for(double time_passed = 0; time_passed < dt; time_passed += timestep) {
			timestep = getTimestep(dt, time_passed);
			super.advanceTimeStep(timestep);
			
			if (getWorld() == null) return;
			
			setLastMove(advanceLastMove(timestep));
			setAnimationTime(advanceAnimationTime(timestep));
			
			if (getTryStand()) {
				endDuck();
			}
		}
		
		if (getProgram() != null) executeProgram(dt);
		
		collisionHandle(getCollisions(), dt);
	}
	
	/**
	 * Handles a collision of this Mazub with a given shark.
	 * 
	 * @param shark
	 * 			The shark this Mazub collides with.
	 * @param direction
	 * 			The direction in which this Mazub collides with the given shark. 0, 1, 2 and 3 correspond to respectively left, up,
	 * 			right and down.
	 * @pre		The given shark exists.
	 * 			| (shark != null)
	 * @effect	If the given shark isn't dying and both this Mazub and the given shark are vulnerable,
	 * 			both sides will lose 50 hitpoints and be invincible for 0.6 seconds, unless one of them touches
	 * 			the other from the top side, in which case this game object remains unaffected.
	 * 			| if (shark.getDeathTime() == 0 && getTimeInvincible() == 0 && shark.getTimeInvincible() == 0) {
	 * 			|	if (direction != 1) {
	 * 			|		shark.setHitpoints(shark.getHitpoints() - 50)
	 * 			|		shark.setTimeInvincible(0.6)
	 * 			|	}
	 * 			|	if (direction != 3) {
	 * 			|		setHitpoints(getHitpoints() - 50)
	 * 			|		setTimeInvincible(0.6)
	 * 			|	}
	 * 			| }
	 */
	@Override 
	protected void collisionHandleMazub(Mazub alien, int direction) {
		assert(alien != null);
		
		if (this instanceof Buzam)
			alien.collisionHandleMazub(this, mirrorDirection(direction));
		
		if (getTimeInvincible() == 0 && alien.getTimeInvincible() == 0) {
			if (direction != 1) {
				alien.setHitpoints(alien.getHitpoints() - 50);
				alien.setTimeInvincible(0.6);
			}
			
			if (direction != 3) {
				setHitpoints(getHitpoints() - 50);
				setTimeInvincible(0.6);
			}
		}
	}
	
	/**
	 * Handles a collision of this Mazub with a given shark.
	 * 
	 * @param shark
	 * 			The shark this Mazub collides with.
	 * @param direction
	 * 			The direction in which this Mazub collides with the given shark. 0, 1, 2 and 3 correspond to respectively left, up,
	 * 			right and down.
	 * @pre		The given shark exists.
	 * 			| (shark != null)
	 * @effect	If the given shark isn't dying and both this Mazub and the given shark are vulnerable,
	 * 			both sides will lose 50 hitpoints and be invincible for 0.6 seconds, unless one of them touches
	 * 			the other from the top side, in which case this game object remains unaffected.
	 * 			| if (shark.getDeathTime() == 0 && getTimeInvincible() == 0 && shark.getTimeInvincible() == 0) {
	 * 			|	if (direction != 1) {
	 * 			|		shark.setHitpoints(shark.getHitpoints() - 50)
	 * 			|		shark.setTimeInvincible(0.6)
	 * 			|	}
	 * 			|	if (direction != 3) {
	 * 			|		setHitpoints(getHitpoints() - 50)
	 * 			|		setTimeInvincible(0.6)
	 * 			|	}
	 * 			| }
	 */
	@Override 
	protected void collisionHandleShark(Shark shark, int direction) {
		assert(shark != null);
		
		if (shark.getDeathTime() == 0 && getTimeInvincible() == 0 && shark.getTimeInvincible() == 0) {
			if (direction != 1) {
				shark.setHitpoints(shark.getHitpoints() - 50);
				shark.setTimeInvincible(0.6);
			}
			
			if (direction != 3) {
				setHitpoints(getHitpoints() - 50);
				setTimeInvincible(0.6);
			}
		}
	}
	
	/**
	 * Handles a collision of this Mazub with a given plant.
	 * 
	 * @param plant	
	 * 			The plant this mazub collides with.
	 * @param direction
	 * 			The direction in which this Mazub collides with the given plant. 0, 1, 2 and 3 correspond to respectively left, up,
	 * 			right and down.
	 * @pre		The given plant exists.
	 * 			| (plant != null)
	 * @effect 	If the given plant is not dying and this Mazub's amount hitpoints is less then 500,
	 * 			the given plant will be killed.
	 * 			| if(!(plant.getDeathTime() > 0) && (getHitpoints() < 500))
	 * 			| 	plant.kill()
	 * @effect	If the given plant is not dying and this Mazub's amount hitpoints is less then 500, 
	 * 			this Mazub's hitpoints will be increased by 50.
	 * 			| if(!(plant.getDeathTime() > 0) && (getHitpoints() < 500))
	 * 			|	setHitpoints(getHitpoints() + 50)
	 */
	@Override
	protected void collisionHandlePlant(Plant plant, int direction) {
		assert(plant != null);
		if(!(plant.getDeathTime() > 0) && (getHitpoints() < 500)){
			plant.kill();
			setHitpoints(getHitpoints() + 50);
		}
	}

	
	/**
	 * Handles a collision of this Mazub with a given slime.
	 * 
	 * @param slime
	 * 			The slime this Mazub collides with.
	 * @param direction
	 * 			The direction in which this Mazub collides with the given plant. 0, 1, 2 and 3 correspond to respectively left, up,
	 * 			right and down.
	 * @pre		The given slime exists.
	 * 			| (slime != null)
	 * @effect	If the given slime isn't dying and both this Mazub and the given slime are vulnerable,
	 * 			both sides will lose 50 hitpoints (this has side effects for slimes) and be invincible for 0.6 seconds,
	 * 			unless one of them touches the other from the top side, in which case this game object remains unaffected.
	 * 			| if (slime.getDeathTime() == 0 && getTimeInvincible() == 0 && slime.getTimeInvincible() == 0) {
	 * 			|	if (direction != 1) {
	 * 			|		slime.hit(50)
	 * 			|		slime.setTimeInvincible(0.6)
	 * 			|	}
	 * 			|	if (direction != 3) {
	 * 			|		setHitpoints(getHitpoints() - 50)
	 * 			|		setTimeInvincible(0.6)
	 * 			|	}
	 * 			| }
	 */
	@Override
	protected void collisionHandleSlime(Slime slime, int direction) {
		assert(slime != null);
		
		if (slime.getDeathTime() == 0 && getTimeInvincible() == 0 && slime.getTimeInvincible() == 0) {
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
	 * Handles a collision between the player and water.
	 * 
	 * @param time
	 * 			The time to increase the time in water of this Mazub with.
	 * @effect	Reduces the hitpoints of this Mazub with 2 for each 0.2 seconds this Mazub is in water, starting 0.2 seconds after
	 * 			it touches water and sets this Mazub's time in water to its current time in water plus time, modulo 0.2.
	 * 			| setTimeInWater(getTimeInWater() + time)
	 *			| while(getTimeInWater() > 0.2) {
	 *			|	setHitpoints(getHitpoints() - 2)
	 *			|	setTimeInWater(getTimeInWater() - 0.2)
	 *			| }
	 * @throws IllegalArgumentException
	 *			If the given time is not a valid time period.
	 *			| (!isValidDt(time))
	 */
	@Override
	protected void collisionHandleWater(double time) throws IllegalArgumentException {
		if (!isValidDt(time))
			throw new IllegalArgumentException();
		
		setTimeInWater(getTimeInWater() + time);
		
		while(getTimeInWater() >= 0.2) {
			setHitpoints(getHitpoints() - 2);
			setTimeInWater(getTimeInWater() - 0.2);
		}
	}
	
	/**
	 * Handles a collision between the player and magma.
	 * 
	 * @param time
	 * 			The time to increase the time in magma of this Mazub with.
	 * @effect	Reduces the hitpoints of this Mazub with 50 for each 0.2 seconds this Mazub is in magma, starting as soon as it
	 * 			touches magma and sets this Mazub's time in water to its current time in water plus time, modulo 0.2.
	 * 			| if(getTimeInMagma() == 0)
	 * 			|	then setHitpoints(getHitpoints() - 50)
	 * 			| setTimeInMagma(getTimeInMagma() + time)
	 *			| while(getTimeInMagma() > 0.2) {
	 *			|	setHitpoints(getHitpoints() - 50)
	 *			|	setTimeInWater(getTimeInMagma() - 0.2)
	 *			| }
	 * @throws IllegalArgumentException
	 *			If the given time is not a valid time period.
	 *			| (!isValidDt(time))
	 */
	@Override
	protected void collisionHandleMagma(double time){
		if (!isValidDt(time))
			throw new IllegalArgumentException();
		
		if(getTimeInMagma() == 0)
			setHitpoints(getHitpoints() - 50);
		
		setTimeInMagma(getTimeInMagma() + time);	
		
		while(getTimeInMagma() >= 0.2) {
			setHitpoints(getHitpoints() - 50);
			setTimeInMagma(getTimeInMagma() - 0.2);			
		}
	}
	
	/**
	 * Gets the last move variable using the given dt.
	 * 
	 * @param dt
	 * 			The period of time which has to be advanced, in seconds.
	 * @return	If this Mazub is moving right, 1, and if it's moving left, -1. If neither cases are true,
	 * 			it will add/subtract dt to/from the current last move variable in order to bring it closer to 0. If it surpasses 0,
	 * 			it will be set to 0.
	 * 			If the last move variable was already 0 to begin with and this Mazub isn't moving, this method will simply return 0.
	 * 			| if (getVx() > 0)
	 * 			| 	then result = 1
	 * 			| else if (getVx() < 0)
	 * 			| 	then result = -1
	 * 			| else if (getLastMove() > 0)
	 * 			| 	then result = Math.max(0, getLastMove() - dt)
	 * 			| else if (getLastMove() < 0)
	 * 			| 	then result = Math.min(0, getLastMove() + dt)
	 * 			| else
	 * 			|	then result = 0
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Model
	private double advanceLastMove(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		if (getVx() > 0) {
			return 1;
		} else if (getVx() < 0) {
			return -1;
		}
		
		if (getLastMove() > 0) {
			return Math.max(0, getLastMove() - dt);
		} else if (getLastMove() < 0) {
			return Math.min(0, getLastMove() + dt);
		}
		return 0;
	}
	
	/**
	 * Returns the new animation time for this Mazub time after a given period of time has passed.
	 * 
	 * @param dt
	 * 			The period of time which should be advanced, in seconds.
	 * @return	The current animation time plus the given dt.
	 * 			| result = getAnimationTime() + dt
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Model
	private double advanceAnimationTime(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		return getAnimationTime() + dt;
	}
	
	/**
	 * Initiates horizontal movement of this Mazub.
	 * 
	 * @param direction
	 * 			The direction in which this Mazub starts moving.
	 * @pre		The given direction is equal to left or right.
	 * 			| (direction == "left" || direction == "right")
	 * @effect	If there is no previous movement currently stored but there is another movement going on right now,
	 * 			this movement will be stored as the previous movement.
	 * 			| if (getPrevMove() == "") {
	 * 			|	if (getAx() < 0)
	 * 			|		then setPrevMove("left")
	 * 			|	else if (getAx() > 0)
	 * 			|		then setPrevMove("right")
	 * 			| }
	 * @effect 	If the given direction is equal to left, vx will be equal to -vxi and ax will be equal to -axi.
	 * 			| if (direction == "left") {
	 * 			|	setVx(-getVxi())
	 * 			|	setAx(-getAxi())
	 * 			| }
	 * @effect 	If the given direction is equal to right, vx will be equal to vxi and ax will be equal to axi.
	 * 			| if (direction == "right") {
	 * 			|	setVx(getVxi())
	 * 			|	setAx(getAxi())
	 * 			| }
	 * @effect	This Mazub's animation time will be set to 0.
	 * 			| setAnimationTime(0)
	 */
	@Override
	public void startMove(String direction) {
		super.startMove(direction);
		setAnimationTime(0);
	}
	
	/**
	 * Starts a jump for this Mazub.
	 * 
	 * @effect	If this Mazub can jump in its current situation, its vertical velocity will be set to 8.
	 * 			| if (canJump())
	 * 			|	then setVy(8)
	 * @effect	If this Mazub can jump in its current situation, its vertical acceleration will be set to -10.
	 * 			| if (canJump())
	 * 			|	then setAy(-10)
	 * @effect	If this Mazub can jump in its current situation, its jumping state will be set to true.
	 * 			| if (canJump())
	 * 			|	then setJumping(true)
	 * @effect	If this Mazub can jump in its current situation, its state of just having started a jump will be set to true.
	 * 			| if (canJump())
	 * 			|	then setJustJumped(true)
	 */
	@Override
	public void startJump() {
		if (canJump()) {
			setVy(8);
			setAy(-10);
			setJumping(true);
			setJustJumped(true);
		}
	}
	
	/**
	 * Checks whether or not this Mazub is (partially) on solid ground.
	 * 
	 * @return 	True if this Mazub is (partially) on solid ground or another impassable game object, false otherwise.
	 * 			| if (getWorld() == null)
	 * 			|	then result = true
	 * 			| collisions = getCollisions();
	 *			| if (!noObjectMovementBlocking(collisions.get(3).get(0)) || collisions.get(3).get(1).contains(Feature.ground))
	 *			|		result =  true;
	 *			| result = false
	 */
	@Override
	public boolean canJump() {
		if (getWorld() == null) return true;
		List<List<List<Object>>> collisions = getCollisions();
		if (!noObjectMovementBlocking(collisions.get(3).get(0)) || collisions.get(3).get(1).contains(Feature.ground))
			return true;
		
		return false;
	}
	
	/**
	 * Starts a duck for this Mazub.
	 * 
	 * @effect	This Mazub's ducking state will be set to true.
	 * 			| setDucking(true)
	 * @effect	This Mazub will not try to stand anymore.
	 * 			| setTryStand(false)
	 * @effect	This Mazub's maximal horizontal velocity will be set to 1.
	 * 			| setVxmax(1)
	 */
	@Override
	public void startDuck() {
		setDucking(true);
		setTryStand(false);
		setVxmax(1);
	}

	/**
	 * Ends a duck for this Mazub, if possible.
	 * 
	 * @effect	If this Mazub can stand, its ducking state will be set to false.
	 * 			| if (canstand())
	 * 			|	then setDucking(false)
	 * @effect	If this Mazub can stand, its maximal horizontal velocity will be set to 3.
	 * 			| if (canstand())
	 * 			|	then setVxmax(3)
	 * @effect 	If this Mazub can stand, it will not continue to try to stand anymore.
	 * 			| if (canstand())
	 * 			|	then setTryStand(false)
	 * @effect	If this Mazub can't stand, it will continue to try to stand.
	 * 			| if (!(canstand()))
	 * 			|	then setTryStand(true)
	 */
	@Override
	public void endDuck() {
		if (canstand()) {
			setDucking(false);
			setVxmax(3);
			setTryStand(false);
		} else {
			setTryStand(true);
		}
	}
	
	/**
	 * Checks if this Mazub can stand in its current situation.
	 * 
	 * @return	True if there are no collisions between this Mazub and other game objects or the ground
	 * 			when it first widens and then rises step by step to a standing position.
	 * 			| currentwidth = getCurrentSprite().getWidth()
	 * 			| futurewidth = getWidthWhenNotDucking()
	 * 			| for(width = currentwidth; width < futurewidth; width++) {
	 * 			| 	collisions = getWorld().collisionDetect(this, width, 0)
	 * 			| 	if (!noObjectMovementBlocking(collisions.get(2).get(0)) || collisions.get(2).get(1).contains(Feature.ground))
	 * 			|		then result = false
	 * 			| 
	 * 			| currentheight = getCurrentSprite().getWidth()
	 * 			| futureheight = getWidthWhenNotDucking()
	 * 			| for(height = currentheight; height < futureheight; height++) {
	 * 			| 	collisions = getWorld().collisionDetect(this, futurewidth, height)
	 * 			| 	if (!noObjectMovementBlocking(collisions.get(1).get(0)) || collisions.get(1).get(1).contains(Feature.ground))
	 * 			|		then result = false
	 * 			| result = true
	 */
	@Model
	private boolean canstand() {
		int currentwidth = getCurrentSprite().getWidth();
		int futurewidth = getWidthWhenNotDucking();
		for(int width = currentwidth; width < futurewidth; width++) {
			
			List<List<List<Object>>> collisions = getWorld().collisionDetect(this, width, 0);
			
			if (!noObjectMovementBlocking(collisions.get(2).get(0)) || collisions.get(2).get(1).contains(Feature.ground)) 
				return false;			
		}
		
		int currentheight = getCurrentSprite().getHeight();
		int futureheight = getHeightWhenNotDucking();
		for(int height = currentheight; height < futureheight; height++) {
			
			List<List<List<Object>>> collisions = getWorld().collisionDetect(this, futurewidth, height);
			
			if (!noObjectMovementBlocking(collisions.get(1).get(0)) || collisions.get(1).get(1).contains(Feature.ground)) 
				return false;			
		}
		
		return true;
	}
	
	/**
	 * Gets the width of this Mazub if it wasn't ducking.
	 * 
	 * @return	The width of this Mazub whilst not ducking, in its current state.
	 * 			| setDucking(false)
	 * 			| width = getWidth()
	 * 			| setDucking(true)
	 * 			| result = width
	 */
	private int getWidthWhenNotDucking(){
		
		setDucking(false);
		int width = getWidth();
		setDucking(true);
		return width;
		
	}
	
	/**
	 * Gets the height of this Mazub if it wasn't ducking.
	 * 
	 * @return	The height of this Mazub whilst not ducking, in its current state.
	 * 			| setDucking(false)
	 * 			| height = getHeight()
	 * 			| setDucking(true)
	 * 			| result = height
	 */
	private int getHeightWhenNotDucking() {
		
		setDucking(false);
		int height = getHeight();
		setDucking(true);
		return height;
		
	}
	
	/**
	 * Gets the current sprite object given this Mazub's past and current horizontal velocity, vertical position and ducking state.
	 * 
	 * @return	The current sprite object fitting this Mazub's current state, which follows these rules:
	 * 			
	 * 			CORRESPONDING INDEX			STATE
	 * 			0							Not moving horizontally, not moving horizontally during the last second, not ducking.
	 * 			1							Not moving horizontally, not moving horizontally during the last second, ducking.
	 * 			2							Not moving horizontally, was moving right during the last second, not ducking.
	 * 			3							Not moving horizontally, was moving left during the last second, not ducking.
	 * 			4							Moving right, jumping, not ducking
	 * 			5							Moving left, jumping, not ducking
	 * 			6							Moving right or was moving right during the last second, ducking.
	 * 			7							Moving left or was moving left during the last second, ducking.
	 * 			8 ... 8 + m					Moving right, neither jumping nor ducking.
	 * 			9 + m ... 9 + 2*m			Moving left, neither jumping nor ducking.
	 * 			
	 * 			The m used above corresponds to getFramesAmount(), which is the amount of frames in both the running left and
	 * 			running right animations.
	 */
	@Override
	public Sprite getCurrentSprite() {
		
		if (getVx() == 0) {
			
			// Everything which happens if Mazub's not moving horizontally.
			
			if (Math.abs(getLastMove()) < Util.DEFAULT_EPSILON) {
				// No last move
				if (getDucking()) {
					return getImages()[1];
				} else {
					return getImages()[0];
				}
			} else if (getLastMove() > 0) {
				// Last move was right.
				if (!getDucking()) {
					return getImages()[2];
				}
			} else {
				// Last move was left.
				if (!getDucking()) {
					return getImages()[3];
				}
			}
		} else if (getVx() > 0) {
			
			// Everything which happens if Mazub's moving right.
			
			if (!getDucking()) {
				if (getJumping()) {
					return getImages()[4];
				} else {
					return getImages()[8 + getCurrentFrameIndex()];
				}
			}
			
		} else {
			
			// Everything which happens if Mazub's moving left.
			
			if (!getDucking()) {
				if (getJumping()) {
					return getImages()[5];
				} else {
					return getImages()[8 + getFramesAmount() + getCurrentFrameIndex()];
				}
			}
		}
		
		if (getDucking()) {
			if (getVx() > 0 || getLastMove() > 0) {
				return getImages()[6];
			} else if (getVx() < 0 || getLastMove() < 0) {
				return getImages()[7];
			}
		}
		
		return getImages()[0];
	}
}
