package jumpingalien.model;

import java.util.ArrayList;
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
	 * @pre		The length of images must be 10 or larger and even.
	 * 			| ((images.length >= 10) && (images.length%2 == 0))
	 * @post	This Mazub's x-position will be equal to the given x.
	 * 			| new.getX() = x
	 * @post	This Mazub's y-position will be equal to the given y.
	 * 			| new.getY() = y
	 * @post	This Mazub's images array will be equal to the given images array.
	 * 			| new.getImages() = images
	 * @post	This Mazub's horizontal velocity will be equal to 0.
	 * 			| new.getVx() = 0
	 * @post	This Mazub's maximal horizontal velocity will be equal to 3.
	 * 			| new.getVxmax() = 3
	 * @post	This Mazub's vertical velocity will be equal to 0.
	 * 			| new.getVy() = 0
	 * @post	This Mazub's horizontal acceleration will be equal to 0.
	 * 			| new.getAx() = 0
	 * @post	This Mazub's vertical acceleration will be equal to 0.
	 * 			| new.getAy() = 0
	 * @post	This Mazub's last movement will be "standing still".
	 * 			| new.getLastMove() = 0
	 */
	public Mazub(double x, double y, Sprite[] images) {
		super(x, y, images);
		
		assert(images.length >= 10 && images.length%2 == 0);
		
		setVxmax(3);
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
	 * 		Whether or not this Mazub should be ducking.
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
	 * 			| ew.getTryStand() = try_stand
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
	 * Gets the world this Mazub is in.
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Sets the world this Mazub is in.
	 * 
	 * @param world
	 * 			The world this Mazub should be in.
	 * @post	This Mazub should be in the given world.
	 * 			| (getWorld() == world)
	 */
	protected void setWorld(World world) {
		this.world = world;
	}
	
	private World world;
	
	/**
	 * Gets hits mazubs hitpoints.	
	 */
	@Basic
	public int getHitpoints(){
		return this.hitpoints;
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
	 *			|	this.hitpoints = 0;
	 *			| 	this.terminate()
	 *			| else if (hitpoints >= 500)
	 *			|	this.hitpoints = 500;
	 *			| else
	 *			|	this.hitpoints = hitpoints;		
	 */
	private void setHitpoints(int hitpoints) {
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
	
	private int hitpoints = 100;
	
	protected void terminate(){
		getWorld().setMazub(null);
		setWorld(null);
	}
	
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
	public void advanceTime(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		for(double timestep = getTimesstep(); timestep <= dt; timestep += timestep) {
			super.advanceTimeStep(timestep);
		
			setLastMove(advanceLastMove(timestep));
			setAnimationTime(advanceAnimationTime(timestep));
			
			if (getTryStand()) {
				endDuck();
				setTryStand(false);
			}
			
		}
	}

	/**
	 * Checks if a given time is a valid time interval to advance the time with.
	 * 
	 * @param dt
	 * 			The amount of seconds which should be checked.
	 * @return	Whether or not dt is in between 0 and 0.2 seconds, exclusively.
	 * 			| result = ((0 < dt) && (dt < 0.2))
	 */
	public static boolean isValidDt(double dt) {
		return ((0 < dt) && (dt < 0.2));
	}
	
	/**
	 * Gets the timestep necessary to move this Mazub's pixel by pixel.
	 * 
	 * @return	If this Mazub's ax and ay is equal to zero, the result will be the minimum of 4 formulas.
	 * 			1) 1 divided by the absolute value of, this Mazub's horizontal velocity divided by 100.
	 * 			2) 1 divided by the absolute value of, this Mazub's vertical velocity divide by 100.
	 * 			3) The result of computeformula(v,a) with as parameters 
	 * 				this Mazub's horizontal acceleration and velocity
	 * 			4) The result of computeformula(v,a) with as parameters 
	 * 				this Mazub's vertical acceleration and velocity
	 * 			| result =  Math.min(computeformula(getVx,getAx)
	 * 			|				Math.min(computeformula(getVy,getAy),
	 * 			|					Math.min(1/Math.abs(getVx()/100), 1/Math.abs(getVy()/100)))
	 */
	private double getTimesstep() {
		double firstparameter = Math.min(1/Math.abs(getVx()/100), 1/Math.abs(getVy()/100));		
		double secondparameter = computeformula(getVx(),getAx());		
		double thirdparameter = computeformula(getVy(),getAy());
		
		return Math.min(firstparameter,Math.min(secondparameter,thirdparameter));
	}
	
	/**
	 * Computes a formula for the calculation of timesteps().
	 * 
	 * @param v
	 * 			the velocity to compute the formula with
	 * @param a
	 * 			the acceleration to compute the formula with	 * 
	 * @return	if a is not equal to zero
	 * 			The square root of two times the absolute value of, a divided by 100, 
	 * 			plus ,the square of v divide by 100, min the absolute value of, v divided by 100. 
	 * 			This square root divide by the absolute value of, a divide by 100.
	 * 			| if a != 0
	 * 			|	then result = Math.sqrt(2*Math.abs(a / 100) + Math.pow(v,2)/100 - Math.abs(v/100))
	 *			| 			/Math.abs(a / 100)
	 *@return 	if a is equal to zero 
	 *			Double.POSITIVE_INFINITY
	 *			| if a == 0
	 *			|  then result = Double.POSITIVE_INFINITY 
	 */
	private double computeformula(double v, double a){
		if(a == 0)
			return Double.POSITIVE_INFINITY;
		double helpparameter = Math.abs(a / 100);
		return Math.sqrt(2*helpparameter + Math.pow(v,2)/100 - Math.abs(v/100))
				/helpparameter;
	}
	
	protected void collisionhandle(ArrayList<List<List<Object>>> collisions){
		for(int i = 0; i <= 4; i++) {
			ArrayList<Object> collision_objects = (ArrayList<Object>) collisions.get(i).get(0);
			for(int j = 0; j < collision_objects.size(); j ++){
				if(collision_objects.get(j).getClass().toString() == "Shark")
					collisionhandleshark((Shark) collision_objects.get(j));
				if(collision_objects.get(j).getClass().toString() == "Plant")
					collisionhandleplant((Plant) collision_objects.get(j));
				if(collision_objects.get(j).getClass().toString() == "Slime")
					collisionhandleslime((Slime) collision_objects.get(j));
			}
		}
	}
	
	private void collisionhandleshark(Shark shark) {
		
	}
	
	private void collisionhandleplant(Plant plant) {
		
	}
	
	private void collisionhandleslime(Slime slime) {
		
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
		if (direction == "left"){
			if (getVx() <= 0)
				setVx(-getVxi());
			setAx(-getAxi());
		} else {
			if (getVx() >= 0)
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
	public void endMove(){
		setVx(0);
		setAx(0);
	}
	
	/**
	 * Starts a jump for this Mazub.
	 * 
	 * @post	This Mazub's vertical velocity will be equal to 8 if this mazub can 0jump.
	 * 			| if(canjump())
	 * 			|	new.getVy() = 8
	 */
	public void startJump() {
		if(canjump())
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
	public boolean canjump(){
		for(int x = (int) getX(); x < (int) getX() + getWidth(); x++) {
			if (getWorld().getFeature(getWorld().getTilePos(x), getWorld().getTilePos((int) Math.round(getY())))
					== Feature.ground)
				return true;
		}
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
	 * 			| if (canstand)
	 * 			|	then setDucking(false)
	 * @effect	If this Mazub can stand, the new Mazub's magnitude of maximal horizontal velocity will be equal to 3.
	 * 			| if (canstand)
	 * 			|	then setVxmax(3)
	 * @effect	If this Mazub can't stand, this will be saved.
	 * 			| if (canstand)
	 * 			|	then setTryStand(true)
	 */
	public void endDuck() {
		if (canstand()) {
			setDucking(false);
			setVxmax(3);
		} else {
			setTryStand(true);
		}
	}
	
	/**
	 * Checks if this Mazub can stand in its current situation.
	 * 
	 * @return
	 */
	private boolean canstand(){
		int currentheigth = getCurrentSprite().getHeight();
		int standingheigth = getHeightWhenNotDucking();
		for(int heigth = currentheigth; heigth <=  standingheigth; heigth ++) {
			
			ArrayList<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0);
			
			if (collisions.get(1).get(0).size() > 0 || collisions.get(1).get(1).contains(Feature.ground)) {
				return false;
			}
			
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