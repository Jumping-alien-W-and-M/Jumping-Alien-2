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
	 * 			the given array of sprites, an initial horizontal accelaration of 0.9 
	 * 			and a initial horizontal velocity of 1.
	 * 			| super(x, y, images, 0.9, 1)
	 * @effect	This Mazub's maximal horizontal velocity will be equal to 3.
	 * 			| setVxmax(3)
	 * @effect	This Mazub's last movement will be "standing still".
	 * 			| setLastMove(0)
	 */
	public Mazub(double x, double y, Sprite[] images) {
		super(x, y, images, 0.9, 1, 3);
		
		setLastMove(0);
	}
	
	/**
	 * Returns whether or not this Mazub is currently ducking.
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
	 * @post	If ducking is true, this Mazub should be ducking, else it shouldn't be.
	 * 			| new.getDucking() == ducking
	 */
	protected void setDucking(boolean ducking) {
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
	 * 			| new.getTryStand() = try_stand
	 */
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
	 * @post	The last move variable will be equal to last_move.
	 * 			| new.getLastMove() == last_move
	 */
	protected void setLastMove(double last_move) {
		assert(last_move >= -1 && last_move <= 1);
		this.last_move = last_move;
	}
	
	private double last_move;
	
	/**
	 * Gets the previous move of this Mazub. This is an empty string if only one movement is currently going on,
	 * and the first invoked movement otherwise.
	 */
	@Basic
	public String getPrevMove() {
		return prev_move;
	}
	
	/**
	 * Sets the previous move of this Mazub.
	 * 
	 * @param prev_move
	 * 			The previous move of this Mazub.This should be an empty string if only one movement is currently going on,
	 * 			and the first invoked movement otherwise.
	 * @pre		The given previous move is either an empty string, "left" or "right".
	 * 			| ((prev_move == "") || (prev_move == "left") || (prev_move == "right"))
	 * @post	This Mazub's previous move will be equal to the given previous move.
	 * 			| (getPrevMove() == prev_move)
	 */
	public void setPrevMove(String prev_move) {
		assert((prev_move == "") || (prev_move == "left") || (prev_move == "right"));
		
		this.prev_move = prev_move;
	}
	
	private String prev_move;
	
	/**
	 * Gets the amount of frames in this Mazub's running left/right animation.
	 * 
	 * @return The number of frames in both the animation of running left and running right.
	 * 			easily to the frame time.
	 * 			| result = (images.length - 8)/2
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
	 * 			| new.getAnimationTime() == getAnimationTime()%(getFramesAmount()*getFrameTime())
	 */
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
	 * Sets the hitpoints of this Mazub.
	 * 
	 * @param hitpoints
	 * 			The new number of hitpoints of this Mazub
	 * @post	If hitpoints is larger then or equal to 500 this Mazub's hitpoints will be set to 500.
	 * 			If hitpoints is smaller then or equal to 0 this Mazub's hitpoints will be set to 0 
	 * 				and this Mazub will be terminated.
	 * 			Else this Mazub's hitpoints will be set to hitpoints.
	 * 			| if (hitpoints <= 0)
	 *			|	this.hitpoints = 0
	 *			| 	this.terminate()
	 *			| else if (hitpoints >= 500)
	 *			|	this.hitpoints = 500
	 *			| else
	 *			|	this.hitpoints = hitpoints;		
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
	 * 			| (new.(this.getWorld()).getPlayer == null)
	 * @post	This Mazub will no longer be part of a world.
	 * 			| (new.getWorld() == null)
	 */
	@Override
	protected void terminate() {
		assert(getWorld() != null);
		
		getWorld().setMazub(null);
		setWorld(null);
	}
	
	/**
	 * Advances the time by a given time.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @effect	Each step advanceTimeStep of the super class will be executed.
	 * 			Step by step increases the last movement time of This Mazub.
	 * 			Step by step advances the animation time of this Mazub.
	 * 			If this mazub tried to stand before but couldn't, endduck() will be executed.
	 * 			| double timestep = getTimestep()
	 * 			| for(double time = getTimestep(); time <= dt; time += timestep) 
	 *			| 	super.advanceTimeStep(time);
	 *			|	setLastMove(advanceLastMove(dt))
	 *			|	setAnimationTime(getAnimationTime() + dt))
	 *			| 	if (getTryStand()) 
	 *			|		endDuck();
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
		
			setLastMove(advanceLastMove(timestep));
			setAnimationTime(advanceAnimationTime(timestep));
			
			if (getTryStand()) {
				endDuck();
			}			
		}

		if(getWorld() != null){
			List<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0);
			collisionHandle(collisions, dt);
		}
	}
	
	/**
	 * Handles a collision of this Mazub with a given shark.
	 * 
	 * @param shark
	 * 			The shark this Mazub collides with.
	 * @pre		shark is not null
	 * 			| shark != null
	 * @effect	If shark is not dying and Mazub is not invincible,
	 * 			this Mazub hitpoints will be decreased by 50.
	 * 			| if(shark.getDeathTime() != 0 && getTimeInvincible() == 0)
	 *			|	this.setHitpoints(this.getHitpoints() - 50)
	 * @effect 	If shark is not dying and Mazub is not invincible,
	 * 			shark's hitpoints will be decreased by 50.
	 * 			| if(shark.getDeathTime() != 0 && getTimeInvincible() == 0)
	 * 			|  	shark.setHitpoints(shark.getHitpoints() - 50)
	 * @effect 	If shark is not dying and Mazub is not invincible,
	 * 			this Mazub will be invincible for the next 0.6 seconds.
	 * 			| if(shark.getDeathTime() != 0 && getTimeInvincible() == 0)
	 * 			|	this.setTimeInvincible(0.6);
	 */
	@Override 
	protected void collisionHandleShark(Shark shark, int direction) {
		assert(shark != null);
		
		if (shark.getDeathTime() != 0 && getTimeInvincible() == 0 && shark.getTimeInvincible() == 0) {
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
	 * Handles a collision of this mazub with a given plant.
	 * 
	 * @param plant	
	 * 			The plant this mazub collides with.
	 * @pre		plant is not null
	 * 			| plant != null
	 * @effect 	If plant is not dying and this mazub's hitpoints are less then 500,
	 * 			plant will be killed
	 * 			| if( !(plant.getDeathTime() > 0) && (getHitpoints() < 500))
	 * 			| 	plant.kill()
	 * @effect	If this plant is not dying and this mazub's hitpoints are less then 500, 
	 * 			this mazub's hitpoints will be increased by 50.
	 * 			| if( !(plant.getDeathTime() > 0) && (getHitpoints() < 500))
	 * 			|	setHitpoints(getHitpoints() + 50)
	 */
	@Override
	protected void collisionHandlePlant(Plant plant, int direction) {
		assert(plant != null);
		if( !(plant.getDeathTime() > 0) && (getHitpoints() < 500)){
			plant.kill();
			setHitpoints(getHitpoints() + 50);
		}
	}
	
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
	 * Handles a collision between the player and water.
	 * 
	 * @param time
	 * 			The time to increase the time in water of this mazub with.
	 * @effect	Reduces the hitpoints of this mazub with 2 for each 0.2 seconds this mazub is in water
	 * 			and sets this mazub's time in water to the current time plus time, modulo 0.2.
	 * 			| setTimeInWater(getTimeInWater() + time)
	 *			| while(getTimeInWater() > 0.2)
	 *			|	setHitpoints(getHitpoints() - 2)
	 *			|	setTimeInWater(getTimeInWater() - 0.2)
	 *@throws IllegalArgumentException
	 *			If the given time is not a valid time period.
	 *			| ! isValidDt(time)
	 */
	@Override
	protected void collisionHandleWater(double time) throws IllegalArgumentException{
		if (! isValidDt(time))
			throw new IllegalArgumentException();
		setTimeInWater(getTimeInWater() + time);
		while(getTimeInWater() >= 0.2){
			setHitpoints(getHitpoints() - 2);
			setTimeInWater(getTimeInWater() - 0.2);
		}
	}
	
	/**
	 * Handles a collision between the player and magma.
	 * 
	 * @param time
	 * 			The time to increase the time in magma of this mazub with.
	 * @effect	If the current time in magma is equal to 0, 
	 * 			the hitpoints of this mazub will be decreased with 50.
	 * 			| if(getTimeInMagma() == 0)
	 *			|	setHitpoints(getHitpoints() - 50)
	 * @effect	Reduces the hitpoints of this mazub with 50 for each 0.2 seconds this mazub is in magma
	 * 			and sets this mazub's time in magma to the current time plus time, modulo 0.2.
	 * 			| setTimeInMagma(getTimeInMagma() + time)
	 *			| while(getTimeInMagma() > 0.2)
	 *			|	setHitpoints(getHitpoints() - 2)
	 *			|	setTimeInMagma(getTimeInMagma() - 0.2)
	 */
	@Override
	protected void collisionHandleMagma(double time){
		if(getTimeInMagma() == 0)
			setHitpoints(getHitpoints() - 50);
		
		setTimeInMagma(getTimeInMagma() + time);	
		
		while(getTimeInMagma() >= 0.2){
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
	 * 			it will be set to zero.
	 * 			If the last move variable was already 0 to begin with and this Mazub isn't moving, this method will simply return 0.
	 * 			| if (getVx() > 0)
	 * 			| 	then result = 1
	 * 			| else if (getVx() > 0)
	 * 			| 	then result = -1
	 * 			| else if (getLastMove() > 0)
	 * 			| 	then result = Math.max(0, getLastMove() - dt)
	 * 			| else if (getLastMove() < 0)
	 * 			| 	then result = Math.min(0, getLastMove() + dt)
	 * 			| else then result = 0
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
	 * 			the direction in which this Mazub starts moving.
	 * @pre		The given direction is equal to left or direction is equal to right.
	 * 			| direction == "left" || direction == "right"
	 * @post 	If direction is equal to right vx will be set to vxi and ax will be set to axi.
	 * 			| if (direction == "right")
	 * 			|	then (new.getVx() = getVxi()) && (new.getAx() = getAxi())
	 * @post 	If direction is equal to left vx will be set to -vxi and ax will be set to -axi.
	 * 			| if (direction == "left")
	 * 			|	then (new.getVx() = -getVxi()) && (new.getAx() = -getAxi())
	 */
	public void startMove(String direction){
		assert(direction == "left" || direction == "right");
		
		if ((getAx() < 0) && (direction == "right")) {
			setPrevMove("left");
		} else if ((getAx() > 0) && (direction == "left")) {
			setPrevMove("right");
		}
		
		if (direction == "left") {
			setVx(-getVxi());
			setAx(-getAxi());
		} else {
			setVx(getVxi());
			setAx(getAxi());
		}
		
		setAnimationTime(0);
	}
	
	/**
	 * Stops the horizontal movement of this Mazub.
	 * 
	 * @post	vx and ax are set to 0.
	 * 			| new.getVx() == 0 && new.getAx()  == 0
	 */
	public void endMove() {
		
		if (getPrevMove() == "") {
			setVx(0);
			setAx(0);
		} else {
			String prev_move = getPrevMove();
			setPrevMove("");
			startMove(prev_move);
		}
	}
	
	/**
	 * Starts a jump for this Mazub.
	 * 
	 * @post	This Mazub's vertical velocity will be equal to 8 if this mazub can 0jump.
	 * 			| if(canjump())
	 * 			|	new.getVy() = 8
	 */
	public void startJump() {
		if (canJump()) 
			setVy(8);
	}
	
	/**
	 * Ends a jump for this Mazub.
	 * 
	 * @post	This Mazub's vertical velocity will be equal to or smaller than 0.
	 * 			| new.getVy() <= 0
	 */
	public void endJump() {
		setVy(Math.min(0, getVy()));
	}
	
	/**
	 * Checks whether or not this Mazub is (partially) on solid ground.
	 * 
	 * @return 	True if this Mazub. is (partially) on solid ground true, else false.
	 * 			| for(int x = (int) getX(); x < (int) getX() + getWidth(); x++)
	 *			| 	if(getWorld().getFeature(getWorld().getTilePos(x),getWorld().getTilePos((int) Math.round(getY())))
	 *			|		== Feature.ground)
	 *			|		result =  true;
	 *			| result = false
	 */
	@Override
	public boolean canJump() {
		if(getWorld() == null)
			return true;
		List<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0);
		if(collisions.get(3).get(0).size() != 0 || collisions.get(3).get(1).contains(Feature.ground))
			return true;
		
		return false;
	}
	
	/**
	 * Sets this Mazub's ducking state to true.
	 * 
	 * @post	The new Mazub's ducking state will be true.
	 * 			| new.getDucking() == true
	 * @post	The new Mazub's magnitude of maximal horizontal velocity will be equal to 1.
	 * 			| new.getVxmax() == 1
	 */
	public void startDuck() {
		setDucking(true);
		setVxmax(1);
	}

	/**
	 * Sets this Mazub's ducking state to false and his maximum horizontal velocity to 3, if possible.
	 * 
	 * @effect	If this Mazub can stand, the new Mazub's ducking state will be false.
	 * 			| if (canstand())
	 * 			|	then setDucking(false)
	 * @effect	If this Mazub can stand, the new Mazub's magnitude of maximal horizontal velocity will be equal to 3.
	 * 			| if (canstand())
	 * 			|	then setVxmax(3)
	 * @effect 	If this Mazub can stand, the new mazub will not try to stand.
	 * 			| if( canstand())
	 * 			|	then setTryStand(false)
	 * @effect	If this Mazub can't stand, this will be saved.
	 * 			| if (canstand())
	 * 			|	then setTryStand(true)
	 */
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
	 * @return	True if there are no collisions between this mazub and other game objects or the ground
	 * 			when this mazub rises step by step to a standing position.
	 * 			| int currentheigth = getCurrentSprite().getHeight()
	 *			| int standingheigth = getHeightWhenNotDucking()
	 *			| for(int heigth = currentheigth; heigth <=  standingheigth; heigth ++) 			
	 *			|	ArrayList<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0)			
	 *			|	if (collisions.get(1).get(0).size() > 0 || collisions.get(1).get(1).contains(Feature.ground)) 
	 *			|		return false
	 *			| return true
	 */
	private boolean canstand(){
		int currentheigth = getCurrentSprite().getHeight();
		int standingheigth = getHeightWhenNotDucking();
		for(int heigth = currentheigth; heigth <=  standingheigth; heigth ++) {
			
			List<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0);
			
			if (collisions.get(1).get(0).size() > 0 || collisions.get(1).get(1).contains(Feature.ground)) 
				return false;			
		}
		return true;
	}
	
	/**
	 * Gets the height of this Mazub if it wasn't ducking.
	 * 
	 * @return	The height this Mazub has while not ducking, in its current state.
	 * 			| setDucking(false)
	 * 			| result = height
	 * 			| setDucking(true);
	 */
	private int getHeightWhenNotDucking(){
		
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
			
			if (getLastMove() == 0) {
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
				if (getY() > 0) {
					return getImages()[4];
				} else {
					return getImages()[8 + getCurrentFrameIndex()];
				}
			}
			
		} else {
			
			// Everything which happens if Mazub's moving left.
			
			if (!getDucking()) {
				if (getY() > 0) {
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
