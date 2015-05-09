package jumpingalien.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import jumpingalien.util.Sprite;

/**
 * All the game objects that can appear in the game.
 */

public abstract class GameObject {
	
	/**
	 * Creates a new game object.
	 * 
	 * @param x
	 * 			The x-position of the new game object.
	 * @param y
	 * 			The y-position of the new game object.
	 * @param images
	 * 			The array of sprite of the new game object.
	 * @param axi
	 * 			The initial horizontal acceleration of the new game object.
	 * @param vxi
	 * 			The initial horizontal velocity of the new game object.
	 * @param vxmax
	 * 			The maximum horizontal velocity of the new game object.
	 * @pre		...
	 * 			| isValidx(x)
	 * @pre		...
	 * 			| isValidy(y)
	 * @post	...
	 * 			| new.getImages() = images
	 * @post	...
	 * 			| new.getAxi() = axi
	 * @post	...
	 * 			| new.getVxi() = vxi
	 * @effect	...
	 * 			| setX(x)
	 * @effect	...
	 * 			| setY(y)
	 * @effect	...
	 * 			| setVxmax(vxmax)
	 * @effect	...
	 * 			| setVx(0)
	 * @effect	...
	 * 			| setVy(0)
	 * @effect	...
	 * 			| setAx(0)
	 * @effect	...
	 * 			| setAy(0)
	 * @effect	...
	 * 			| setHitpoints(100)
	 */
	protected GameObject(double x, double y, Sprite[] images, double axi, double vxi, double vxmax, Program program) {
		assert(isValidX(x));
		assert(isValidX(y));
		assert((program != null) || ((this instanceof Mazub) && !(this instanceof Buzam)));
		
		setX(x);
		setY(y);
		this.images = images;
		this.axi = axi;
		this.vxi = vxi;
		setVxmax(vxmax);
		this.program = program;
		
		setVx(0);
		setVy(0);
		setAx(0);
		setAy(0);
		setHitpoints(100);
	}
	
	/**
	 * Gets the x-position of this game object.
	 */
	@Basic
	public double getX() {
		return this.x;
	}
	
	/**
	 * Sets the x-position of this game object.
	 * 
	 * @param x
	 * 			This game object's new x-position in pixels.
	 * @post	This game object's new x-position will be equal to the given x.
	 * 			| new.getX() == x
	 * @throws	IllegalArgumentException
	 * 			Throws an exception if x is not a valid x-position.
	 * 			| !isValidX(x)
	 */
	public void setX(double x) throws IllegalArgumentException {
		if (!isValidX(x)) {
			throw new IllegalArgumentException();
		}
		this.x = x;
	}
	
	/**
	 * Checks if the given x is a valid x-position for this game object.
	 * 
	 * @param x
	 * 			The x-position in pixels which should be checked.
	 * @return	If this game object is in a world, whether or not the given x is within the game world.
	 * 			else it returns whether or not the given x is larger then or equal to zero.
	 * 			| if(getWorld() != null)
	 *			|	result = (x >= 0 && x < getWorld().getWorldWidth())
	 *			| else
	 *			| 	result =  (x >= 0)
	*/
	public boolean isValidX(double x) {
		if(getWorld() != null)
			return (x >= 0 && x < getWorld().getWorldWidth());
		return x >= 0;
	}
	
	private double x;
	
	/**
	 * Gets the y-position of this game object.
	 */
	@Basic
	public double getY() {
		return this.y;
	}
	
	/**
	 * Sets the y-position of this game object.
	 * 
	 * @param y
	 * 			This game object's new y-position in pixels.
	 * @post	This game object's new y-position will be equal to the given y.
	 * 			| new.getY() == y
	 * @throws	IllegalArgumentException
	 * 			If y is not a valid y-position.
	 * 			| !isValidY(y)
	 */
	public void setY(double y) throws IllegalArgumentException {
		if (!isValidY(y)) {
			throw new IllegalArgumentException();
		}
		this.y = y;
	}
	
	/**
	 * Checks if the given y is a valid y-position for this game object.
	 * 
	 * @param y
	 * 			The y-position in pixels which should be checked.
	 * @return	If this game object is in a world, whether or not the given y is within the game world.
	 * 			else it returns whether or not the given y is larger then or equal to zero.
	 * 			| if(getWorld() != null)
	 *			|	return (y >= 0 && x < getWorld().getWorldHeight());
	 *			| return y >= 0;
	 */
	public boolean isValidY(double y) {
		if (getWorld() != null)
			return (y >= 0 && y < getWorld().getWorldHeight()); 
		return y >= 0;
	}
	
	private double y;
	
	/**
	 * Gets the horizontal velocity of this game object.
	 */
	@Basic
	public double getVx() {
		return this.vx;
	}
	
	/**
	 * Sets the horizontal velocity of this game object.
	 * 
	 * @param vx
	 * 			The new horizontal velocity of this game object in meters per second (1m = 100 pixels).
	 * @pre		Vx is a valid horizontal velocity for this game object.
	 * 			| isValidVx(vx)
	 * @post	The new horizontal velocity of this game object is equal to vx.
	 * 			| new.getVx() = vx
	 */
	@Model
	protected void setVx(double vx) {
		assert isValidVx(vx);
		this.vx = vx;
	}
	
	/**
	 * Checks if the given vx is a valid horizontal velocity.
	 * 
	 * @param vx
	 * @return 	...
	 * 			| result = ((-vxmax <= vx && vx <= -vxi) || (vx == 0) || (vxi <= vx && vx <= vxmax))
	 */
	public boolean isValidVx(double vx) {
		return ((-getVxmax() <= vx && vx <= -getVxi())
				|| (vx == 0)
				|| (getVxi() <= vx && vx <= getVxmax()));
	}
	
	private double vx;	 
	
	/**
	 * Gets the magnitude of the initial horizontal velocity a game object starts with when they start moving.
	 */
	@Basic @Immutable
	public double getVxi() {
		return this.vxi;
	}

	private final double vxi;
	
	/**
	 * Gets the magnitude of the maximal horizontal velocity this game object can achieve when moving.
	 */
	@Basic
	public double getVxmax() {
		return this.vxmax;
	}
	
	/**
	 * Sets this game object's maximal horizontal velocity to a given vxmax.
	 * 
	 * @param vxmax
	 * 			The maximal horizontal velocity this game object should have.
	 * @post	The new game object's vxmax should be equal to the given max.
	 * 			| new.getVxmax() == vxmax
	 */
	protected void setVxmax(double vxmax) {
		this.vxmax = vxmax;
	}
	
	private double vxmax;
	
	/**
	 * Gets the vertical velocity of this game object.
	 */
	@Basic
	public double getVy() {
		return this.vy;
	}
	
	/**
	 * Sets the vertical velocity of this game object.
	 * 
	 * @param vy
	 * 			This game object's new vertical velocity in meters per second (1m = 100 pixels),
	 * @post	This game object's new vertical velocity will be equal to the given vy.
	 * 			| new.getVy() = vy
	 *
	 */
	protected void setVy(double vy) {
			this.vy = vy;
	}
	
	protected double vy;
	
	/**
	 * Gets the horizontal acceleration of this game object.
	 */
	@Basic
	public double getAx() {
		return this.ax;
	}
	
	/**
	 * Sets the horizontal acceleration of this game object to ax.
	 * 
	 * @param ax
	 * 			The new horizontal acceleration of this game object.
	 * @post	If ax is a valid horizontal acceleration,
	 * 			then the horizontal acceleration will be set to ax.
	 * 			| if (isVAlidAx(ax))
	 * 			|	then new.getAx() = ax
	 * @post	If ax is  not a valid horizontal acceleration,
	 * 			then the horizontal acceleration will be set to 0.
	 * 			| if (!isValidAx(ax))
	 * 			|	then new.getAx() = 0
	 */
	protected void setAx(double ax) {
		if (isValidAx(ax))
			this.ax = ax;		
		else 
			this.ax = 0;		
	}
	
	/**
	 * Checks if ax is a valid horizontal acceleration.
	 * 
	 * @param ax
	 * 			The value to be checked.
	 * @result
	 * 			True if and only if ax is equal to 0 or (min) axi.
	 * 			| result = ((ax == 0) || (ax == axi) || (ax = -axi))
	 */
	public boolean isValidAx(double ax) {
		return ((ax == 0) || (ax == getAxi()) || (ax == -getAxi()));
	}
	
	private double ax;
	
	/**
	 * Gets the horizontal initial acceleration.
	 */
	@Basic @Immutable
	protected double getAxi() {
		return this.axi;
	}
	
	protected final double axi;
	
	/**
	 * Gets the vertical acceleration of this game object.
	 */
	@Basic
	public double getAy() {
		return this.ay;
	}
	
	/**
	 * Sets the vertical acceleration of this game object to ay.
	 * 
	 * @param ay
	 * 			The new vertical acceleration of this game object.
	 * @post	If ay is a valid vertical acceleration,
	 * 			then the vertical acceleration will be set to ay.
	 * 			| if (isVAlidAy(ay)
	 * 			|	then new.getAy() = ay
	 * @post	If ay is  not a valid vertical acceleration,
	 * 			then the vertical acceleration will be set to 0.
	 * 			| if (!isValidAy(ay))
	 * 			|	then new.getAy() = 0
	 */
	protected void setAy(double ay) {
		if (isValidAy(ay))
			this.ay = ay;		
		else 
			this.ay = 0;		
	}
	
	/**
	 * Checks if ay is a valid vertical acceleration.
	 * 
	 * @param ay
	 * 			the value to be checked
	 * @result	True if and only if ay is equal to 0 or -10.
	 * 			| result = ((ay == 0) || (ay == -10))
	 */
	public boolean isValidAy(double ay) {
		return ((ay == 0) || (ay == -10));
	}
	
	private double ay;	
	
	/**
	 * Gets this game object's width.
	 * 
	 * @return	The current width in pixels of this game object.
	 * 			| result = getCurrentSprite().getWidth()
	 */
	@Basic
	public int getWidth() {
		return getCurrentSprite().getWidth();
	}
	
	/**
	 * Gets this Mazub's height.
	 * 
	 * @return	The current height in pixels of this game object.
	 * 			| result = getCurrentSprite().getHeight()
	 */
	@Basic
	public int getHeight() {
		return getCurrentSprite().getHeight();
	}
	
	/**
	 * Gets whether or not this game object just started a jump, without hitting ground above or to its sides.
	 */
	public boolean getJustJumped() {
		return this.just_jumped;
	}
	
	/**
	 * Sets whether or not this game object just started a jump, without hitting ground above or to its sides.
	 * 
	 * @param just_jumped
	 * 			Whether or or not this game object just started a jump without hitting ground above or to its sides.
	 * @post	The new just_jumped variable will be equal to the given just_jumped parameter.
	 * 			| ((new.getJustJumped()) == just_jumped)
	 */
	protected void setJustJumped(boolean just_jumped) {
		this.just_jumped = just_jumped;
	}
	
	private boolean just_jumped = false;
	
	/**
	 * Gets the image array of this game object.
	 */
	@Basic @Immutable
	public Sprite[] getImages() {
		return this.images;
	}
	
	private final Sprite[] images;
	
	/**
	 * Gets the world this game object is in.
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Sets the world this game object is in.
	 * 
	 * @param world
	 * 			The world this game object should be in.
	 * @post	This game object is in the given world.
	 * 			| (new.getWorld() == world)
	 */
	@Model
	protected void setWorld(World world) {
		this.world = world;
	}
	
	private World world;
	
	/**
	 * Gets this game object's hitpoints.	
	 */
	@Basic
	public int getHitpoints() {
		return this.hitpoints;
	}
	
	protected void setHitpoints(int hitpoints) { }
	
	protected int hitpoints;
	
	/**
	 * Gets 0.6 min the time this game object has been dead for or zero if this game object is alive.
	 */
	@Basic
	public double getDeathTime(){
		return this.death_time;
	}
	
	/**
	 * Sets the death time of this game object.
	 * 
	 * @param deathtime
	 * 			The new death time of this game object
	 * @post	The new time  deathtim of this game object is equal to the given death time.
	 * 			| new.getDeathTime() = deathtime 
	 */	
	protected void setDeathTime(double deathtime){
		this.death_time = deathtime;
	}
	
	/**
	 * Terminates this game object with a delay of 0.6 seconds.
	 * 
	 * @effect	The death time of this game object will we set to 0.6.
	 * 			| setDeathTime(0.6)
	 */
	protected void kill() {
		setDeathTime(0.6);
	}
	
	private double death_time = 0;
	
	/**
	 * Gets this game object's time in air.
	 */
	@Basic
	public double getTimeInAir(){
		return this.time_in_air;
	}
	
	/**
	 * Sets this game object's time in air.
	 * 
	 * @param timeinair
	 * 			The new time in air of this game object.
	 * @pre		The timeinair parameter must be larger then or equal to zero.
	 * 			| (timeinair >= 0)
	 * @post	The timeinair variable of this game object will be equal to the given timeinair.
	 * 			| (new.getTimeInAir() = timeinair)
	 */
	protected void setTimeInAir(double timeinair){
		assert(timeinair >= 0);
		this.time_in_air = timeinair;
	}
	
	private double time_in_air;
	
	/**
	 * Gets this game object's time in water.
	 */
	@Basic
	public double getTimeInWater(){
		return this.time_in_water;
	}
	
	/**
	 * Sets this game object's time in water.
	 * 
	 * @param timeinwater
	 * 			The new time in water of this game object.
	 * @pre		The timeinwater parameter must be larger than or equal to zero.
	 * 			| (timeinwater >= 0)
	 * @post	The timeinwater variable of this game object will be equal to the given timeinwater.
	 * 			| (new.getTimeinwater() = timeinwater)
	 */
	protected void setTimeInWater(double timeinwater) {
		assert(timeinwater >= 0);
		this.time_in_water = timeinwater;
	}
	
	private double time_in_water;
	
	/**
	 * Gets this game object's time in magma.
	 */
	@Basic
	public double getTimeInMagma(){
		return this.time_in_magma;
	}
	
	/**
	 * Sets this game object's time in magma.
	 * 
	 * @param timeinmagma
	 * 			The new time in magma of this game object.
	 * @pre		The timeinmagma parameter must be larger then or equal to zero.
	 * 			| (timeinmagma >= 0)
	 * @post	The timeinmagma variable of this game object will be equal to the given timeinmagma.
	 * 			| (new.getTimeinmagma() = timeinmagma)
	 */
	protected void setTimeInMagma(double timeinmagma){
		assert(timeinmagma >= 0);
		this.time_in_magma = timeinmagma;
	}
	
	private double time_in_magma;
	
	/**
	 * Gets the time this game object will be invincible for.
	 */
	@Basic
	public double getTimeInvincible(){
		return this.time_invincible;
	}
	
	/**
	 * Sets the time that this game object will stay invincible for to the given time.
	 * 
	 * @param time
	 * 			The new time that this game object is invincible for 
	 * @pre		The time parameter must be larger then or equal to zero.
	 * 			| (time >= 0)
	 * @post	This game object's new time he will be invincible for, will be equal to time
	 * 			| (new.getTimeInvincible() = time)
	 */
	protected void setTimeInvincible(double time){
		assert(time >= 0);
		this.time_invincible = time;
	}
	
	private double time_invincible = 0;
	
	public Program getProgram() {
		return this.program;
	}
	
	private final Program program;
	
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
	 * 			The previous move of this Mazub. This should be an empty string if only one movement is currently going on,
	 * 			and the first invoked movement otherwise.
	 * @pre		The given previous move is either an empty string, "left" or "right".
	 * 			| ((prev_move == "") || (prev_move == "left") || (prev_move == "right"))
	 * @post	This Mazub's previous move will be equal to the given prev_move.
	 * 			| (new.getPrevMove() == prev_move)
	 */
	@Model
	private void setPrevMove(String prev_move) {
		assert((prev_move == "") || (prev_move == "left") || (prev_move == "right"));
		
		this.prev_move = prev_move;
	}
	
	private String prev_move = "";
	
	protected abstract void terminate();
	
	protected abstract void advanceTime(double dt) throws IllegalArgumentException;
	
	/**
	 * Advances the time by a given timestep for this game object.
	 * 
	 * @param timestep
	 * 			The amount of seconds to be advanced.
	 * @effect	...
	 * 			| if (getDeathTime() > 0)
	 * 			|		return
	 * @effect	...
	 * 			| List<List<List<Object>>> collisions = getCollisions();
	 *			| if ((noObjectMovementBlocking(collisions.get(0).get(0)) && !(collisions.get(0).get(1).contains(Feature.ground)) && (getVx() < 0))
	 *			|	|| ((noObjectMovementBlocking(collisions.get(2).get(0)) && !(collisions.get(2).get(1).contains(Feature.ground)) && (getVx() > 0)))) 
	 *			|		then advanceX(timestep)
	 * @effect	...
	 * 			| setVx(advanceVx(timestep))
	 * @effect	...
	 * 			| collisions = getCollisions()
	 *			| if (noObjectMovementBlocking(collisions.get(3).get(0)) && !(collisions.get(3).get(1).contains(Feature.ground)) && (getVy() < 0))
	 *			|		advanceY(timestep);
	 *			| else if (getVy() > 0) 
	 *			|		boolean should_advance = true
	 *			|		for(int i = 0; i < 3; i++) 
	 *			|			if (!noObjectMovementBlocking(collisions.get(i).get(0)) || collisions.get(i).get(1).contains(Feature.ground))
	 *			|				should_advance = false
	 *			| 		if (should_advance) advanceY(timestep);
	 * @effect	...
	 * 			| setVy(advanceVy(timestep))
	 * @effect	...
	 * 			| setAy(advanceAy())
	 * @effect	...
	 * 			| collisions = getCollisions();
	 *			| if ((this instanceof Mazub) && !(getJustJumped()) &&
	 *  		|	((collisions.get(3).get(0).size() != 0) || (collisions.get(3).get(1).contains(Feature.ground)))) 
	 *			|		then ((Mazub) this).setJumping(false)
	 * @effect	...
	 * 			| setTimeInvincible(advanceTimeInvincible(timestep))
	 */
	public void advanceTimeStep(double timestep) {
		
		if (getDeathTime() > 0) return;
		
		List<List<List<Object>>> collisions = getCollisions();
		if ((noObjectMovementBlocking(collisions.get(0).get(0)) && !(collisions.get(0).get(1).contains(Feature.ground)) && (getVx() < 0))
			|| ((noObjectMovementBlocking(collisions.get(2).get(0)) && !(collisions.get(2).get(1).contains(Feature.ground)) && (getVx() > 0)))) 
				advanceX(timestep);
		
		setVx(advanceVx(timestep));
		
		collisions = getCollisions();
		if (noObjectMovementBlocking(collisions.get(3).get(0)) && !(collisions.get(3).get(1).contains(Feature.ground)) && (getVy() < 0))
			advanceY(timestep);
		else if (getVy() > 0) {
			boolean should_advance = true;
			for(int i = 0; i < 3; i++) {
				if (!noObjectMovementBlocking(collisions.get(i).get(0)) || collisions.get(i).get(1).contains(Feature.ground))
					should_advance = false;
			}
			if (should_advance) advanceY(timestep);
		}

		advanceVy(timestep);
		setAy(advanceAy());

		collisions = getCollisions();
		if ((this instanceof Mazub) && !(getJustJumped()) &&
				((collisions.get(3).get(0).size() != 0) || (collisions.get(3).get(1).contains(Feature.ground)))) {
			((Mazub) this).setJumping(false);
		}
		
		setTimeInvincible(advanceTimeInvincible(timestep));
	}
	
	/**
	 * Gets the collisions of this with other game objects and features.
	 * 
	 * @return 	...
	 *			| if(getWorld() != null)
	 *			| 		result =  getWorld().collisionDetect(this, 0, 0);
	 * @return 	...
	 * 			| if(getWorld() == null)
	 * 			| result =  new ArrayList<List<List<Object>>>(
	 *			|				Collections.nCopies(4, Collections.nCopies(2, new ArrayList<Object>())))
	 */
	@Model
	protected List<List<List<Object>>> getCollisions() {
		if(getWorld() != null)
			return getWorld().collisionDetect(this, 0, 0);
		else
			return new ArrayList<List<List<Object>>>(
							Collections.nCopies(4, Collections.nCopies(2, new ArrayList<Object>())));
	}
	
	/**
	 * Checks whether or not the given (array)list (of collisions) blocks the movement of this game object.
	 * 
	 * @param list
	 * 			The (array)list which should be checked.
	 * @return	...
	 * 			| if (this instanceof Plant) result = true
	 * 			| else
	 * 			| 	for(Object element : list)
	 * 			|		if (!(element instanceof Plant))
	 * 			|			then result = false
	 * 			| 	result = true
	 */
	@Model
	protected boolean noObjectMovementBlocking(List<Object> list) {
		if (this instanceof Plant) return true;
		
		for(Object element : list) {
			if (!(element instanceof Plant)) {
				return false;
			}
		}
		return true;
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
	 * Gets the timestep necessary to move this game object pixel by pixel.
	 * 
	 * @return	...
	 * 			| if ((getVx() == 0) && (getVy() == 0) && (getAx() == 0) && (getAy() == 0))
	 *			|	then result = dt - time_passed;
	 *			| else
	 * 			| 	then result =  Math.min(0.01/(Math.sqrt(Math.pow(getVx(), 2) + Math.pow(getVy(), 2)) +
	 *			|				Math.sqrt(Math.pow(getAx(), 2) + Math.pow(getAy(), 2))*dt), dt - time_passed)
	 */
	public double getTimestep(double dt, double time_passed) {
		if ((getVx() == 0) && (getVy() == 0) && (getAx() == 0) && (getAy() == 0))
			return dt - time_passed;
		return Math.min(0.01/(Math.sqrt(Math.pow(getVx(), 2) + Math.pow(getVy(), 2)) +
				Math.sqrt(Math.pow(getAx(), 2) + Math.pow(getAy(), 2))*dt), dt - time_passed);
	}
	
	/**
	 * Advnaces the x-position of this game object over a given time interval.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @effect	...
	 * 			| max_s = maxAdvanceX(dt)
	 * 			| actual_s = maxAdvanceX(dt)
	 * 			| double newx = getX() + Math.min(max_s, actual_s)
	 *			| setXWithinBounds(newx)
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Model
	protected void advanceX(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double max_s = maxAdvanceX(dt);
		// This second formula can be changed, the first one is static.
		double actual_s = maxAdvanceX(dt);
		double newx = getX() + Math.min(max_s, actual_s);
		
		setXWithinBounds(newx);
	}
	
	/**
	 * Sets this game objects x-position to x after x is converted to a valid x-position.
	 * 
	 * @param x
	 * 			The new x-position which is first converted to a valid one.
	 * @effect	...
	 * 			| if (getWorld() != null && (x < 0 || x >= getWorld().getWorldWidth()))
	 *			|		terminate()
	 * @effect	...
	 * 			| setX(Math.max(x,0))
	 */
	protected void setXWithinBounds(double x) {
		if (getWorld() != null && (x < 0 || x >= getWorld().getWorldWidth()))
				terminate();
		setX(Math.max(x, 0));
	}

	/**
	 * Calculates the maximal change in x-position within a given period of time.
	 * 
	 * @param dt
	 * 			The period of time which should be advanced.
	 * @return	The maximal change in x-position using dt, in pixels.
	 * 			| result = 100*(getVx()*dt + getAx()*Math.pow(dt, 2)/2)
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Model
	private double maxAdvanceX(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		return 100*(getVx()*dt + getAx()*Math.pow(dt, 2)/2);
	}
	
	/**
	 * Returns the new horizontal velocity after a given time has passed.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @return	The new horizontal velocity after dt time has passed in meters per second.
	 * 			| result = Math.max(-vxmax, Math.min(vxmax, getVx() + getAx()*dt))
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Model
	protected double advanceVx(double dt) throws IllegalArgumentException, IllegalStateException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double newvx = getVx() + getAx()*dt;
		newvx = Math.max(-vxmax, Math.min(vxmax, newvx));
		
		return newvx;
	}
	
	/**
	 * Advances this game objects y-position over a given time interval.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @effect	...
	 * 			| max_s = maxAdvanceX(dt)
	 * 			| actual_s = maxAdvanceX(dt)
	 * 			| double newy = getY() + Math.min(max_s, actual_s)		
	 *			| setYWithinBounds(newy)
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Model
	protected void advanceY(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		double max_s = maxAdvanceY(dt);
		// This second formula can be changed, the first one is static.
		double actual_s = maxAdvanceY(dt);
		double newy = getY() + Math.min(max_s, actual_s);
		
		setYWithinBounds(newy);
	}
	
	/**
	 * Sets this game objects y-position to y after y is converted to a valid y-position.
	 * 
	 * @param y
	 * 			The new y-position which is first converted to a valid one.
	 * @effect	...
	 * 			| if (getWorld() != null && (y < 0 || y >= getWorld().getWorldHeight()))
	 * 			| terminate()
	 * @effect	...
	 * 			| setY(Math.max(y, 0))
	 */
	protected void setYWithinBounds(double y) {
		if (getWorld() != null && (y < 0 || y >= getWorld().getWorldHeight()))
				terminate();
		setY(Math.max(y, 0));
	}
	
	/**
	 * Calculates the maximal change in y-position within a given period of time.
	 * 
	 * @param dt
	 * 			The period of time which should be advanced.
	 * @return	The maximal change in y-position using dt, in pixels.
	 * 			| result = 100*(getVy()*dt + getAy()*Math.pow(dt, 2)/2)
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
	@Model
	private double maxAdvanceY(double dt) throws IllegalArgumentException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		return 100*(getVy()*dt + getAy()*Math.pow(dt, 2)/2);
	}
	
	/**
	 * Returns the new vertical velocity after a given time has passed.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @effect	...	
	 * 			| if(getY() <= 0) setVy(0)
	 * @effect	...
	 * 			| List<List<List<Object>>> collisions = getCollisions()
	 *			| if (getVy() > 0) {
	 *			|	for(int i = 0; i < 3; i++) {
	 *			| 		if (!noObjectMovementBlocking(collisions.get(i).get(0)) || (collisions.get(i).get(1).contains(Feature.ground))) 
	 *			|			setJustJumped(false);
	 *			|			setVy(0)
	 *			|			return
	 *			| if (!noObjectMovementBlocking(collisions.get(3).get(0)) || (collisions.get(3).get(1).contains(Feature.ground))) 
	 *			|	if (!(getJustJumped())) {
	 *			|		setVy(0)
	 *			|		return
	 *			|	}
	 *			| else
	 *			|	setJustJumped(false)
	 * @effect	...
	 * 			| setVy(getVy() + getAy()*dt)
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 * @throws IllegalStateException
	 * 			...
	 * 			| newvy == Double.NEGATIVE_INFINITY
	 */
	@Model
	protected void advanceVy(double dt) throws IllegalArgumentException, IllegalStateException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		if (getY() <= 0) {
			setVy(0);
			return;
		}

		List<List<List<Object>>> collisions = getCollisions();
		if (getVy() > 0) {
			for(int i = 0; i < 3; i++) {
				if (!noObjectMovementBlocking(collisions.get(i).get(0)) || (collisions.get(i).get(1).contains(Feature.ground))) {
					setJustJumped(false);
					setVy(0);
					return;
				}
			}
		}
		if (!noObjectMovementBlocking(collisions.get(3).get(0)) || (collisions.get(3).get(1).contains(Feature.ground))) {
			if (!(getJustJumped())) {
				setVy(0);
				return;
			}
		} else {
			setJustJumped(false);
		}
		
		double newvy = getVy() + getAy()*dt;
		if (newvy == Double.NEGATIVE_INFINITY) {
			throw new IllegalStateException();
		}
		setVy(newvy);
	}
	
	/**
	 * Returns the new vertical acceleration of this game object.
	 * 
	 * @return	...
	 * 			| List<List<List<Object>>> collisions = getCollisions()
	 *			| if ((!noObjectMovementBlocking(collisions.get(3).get(0)) || collisions.get(3).get(1).contains(Feature.ground)
	 *			|	|| (int) getY() <= 0) && !getJustJumped()) 
	 *			|		result =  0
	 *			| else 
	 *			|	result = -10
	 */
	@Model
	protected double advanceAy() {
		
		List<List<List<Object>>> collisions = getCollisions();
		if ((!noObjectMovementBlocking(collisions.get(3).get(0)) || collisions.get(3).get(1).contains(Feature.ground)
				|| (int) getY() <= 0) && !getJustJumped()) {
			return 0;
		} else {
			return -10;
		}
	}
	
	/**
	 * Return the new time this mazub will be invincible for.
	 * 
	 * @param dt
	 * 			The period of time wich should be advanced, in seconds.
	 * @return	The current time this mazub will be invinceble for min the given dt 
	 * 			if this is bigger then 0 else 0 is returned.
	 * 			| result = Math.max(0, getTimeInvincible()-dt)
	 */
	@Model
	protected double advanceTimeInvincible(double dt){
		return Math.max(0, getTimeInvincible() - dt);
	}
	
	/**
	 * Handles the collisions
	 * 
	 * @param collisions
	 * 			The result of the collisionDetect mehotd of World.
	 * @param time
	 * 			The time to (if necessary) advance this game objects time in a feature with.
	 * @effect	...
	 * 			| for(int i = 0; i < collisions.size(); i++) 
	 *			|	ArrayList<Object> collision_objects = (ArrayList<Object>) collisions.get(i).get(0)
	 *			|	for(Object object : collision_objects) 
	 *			|		if (object instanceof Mazub) collisionHandleMazub((Mazub) object, i)
	 *			|		if (object instanceof Shark) collisionHandleShark((Shark) object, i)
	 *			|		if (object instanceof Plant) collisionHandlePlant((Plant) object, i)
	 *			|		if (object instanceof Slime) collisionHandleSlime((Slime) object, i)
	 *			|		if (getWorld() == null) return
	 * @effect	...
	 * 			| collisionHandleFeature(collisions, time)
	 */
	@Model
	protected void collisionHandle(List<List<List<Object>>> collisions, double time) {
	
		for(int i = 0; i < collisions.size(); i++) {
			ArrayList<Object> collision_objects = (ArrayList<Object>) collisions.get(i).get(0);
			
			for(Object object : collision_objects) {
				if (object instanceof Mazub) collisionHandleMazub((Mazub) object, i);
				if (object instanceof Shark) collisionHandleShark((Shark) object, i);
				if (object instanceof Plant) collisionHandlePlant((Plant) object, i);
				if (object instanceof Slime) collisionHandleSlime((Slime) object, i);
				if (getWorld() == null) return;
			}
		}
		
		collisionHandleFeature(collisions, time);

	}
	
	/**
	 * Handles collisions between this game object and feature.
	 * 
	 * @param collisions
	 * 			The result of the collisionDetect method of World.
	 * @param time
	 * 			The time to (if necessary) advance this game object's time in a feature with.
	 * @effect	...
	 * 			| boolean touched_air = false
	 *			| boolean touched_water = false
	 * 			| boolean touched_magma = false		
	 *			| for(int i = 0; i < collisions.size(); i++) 
	 *			|		ArrayList<Object> collision_features = (ArrayList<Object>) collisions.get(i).get(1)
	 *			|		if ((collision_features.contains(Feature.air)) && !(touched_air)) 
	 *			|			collisionHandleAir(time)
	 *			|			if (getWorld() == null) return
	 *			|			touched_air = true
	 *			|		if ((collision_features.contains(Feature.water)) && !(touched_water)) 
	 *			|			collisionHandleWater(time)
	 *			|			if (getWorld() == null) return
	 *			|			touched_water = true
	 *			|		if ((collision_features.contains(Feature.magma)) && !(touched_magma)) 
	 *			|			collisionHandleMagma(time)
	 *			|			if (getWorld() == null) return
	 *			|			touched_magma = true
	 *			| if (!(touched_air)) setTimeInAir(0)
	 *			| if (!(touched_water)) setTimeInWater(0)
	 *			| if (!(touched_magma)) setTimeInMagma(0)		
	 */
	protected void collisionHandleFeature(List<List<List<Object>>> collisions, double time) {
		
		boolean touched_air = false;
		boolean touched_water = false;
		boolean touched_magma = false;
		
		for(int i = 0; i < collisions.size(); i++) {
			ArrayList<Object> collision_features = (ArrayList<Object>) collisions.get(i).get(1);
		
			if ((collision_features.contains(Feature.air)) && !(touched_air)) {
				collisionHandleAir(time);
				if (getWorld() == null) return;
				touched_air = true;
			}
			if ((collision_features.contains(Feature.water)) && !(touched_water)) {
				collisionHandleWater(time);
				if (getWorld() == null) return;
				touched_water = true;
			}
			if ((collision_features.contains(Feature.magma)) && !(touched_magma)) {
				collisionHandleMagma(time);
				if (getWorld() == null) return;
				touched_magma = true;
			}
		}
		
		if (!(touched_air)) setTimeInAir(0);
		if (!(touched_water)) setTimeInWater(0);
		if (!(touched_magma)) setTimeInMagma(0);
		
	}
	
	protected void collisionHandleMazub(Mazub player, int direction) { }
	protected void collisionHandleShark(Shark shark, int direction) { }
	protected void collisionHandlePlant(Plant plant, int direction) { }
	protected void collisionHandleSlime(Slime slime, int direction) { }

	protected void collisionHandleAir(double time) { }
	protected void collisionHandleWater(double time) { }
	protected void collisionHandleMagma(double time) { }
	
	protected void startMove(String direction) {
		assert(direction == "left" || direction == "right");
		
		if (getPrevMove() == "") {
			if (getAx() < 0) {
				setPrevMove("left");
			} else if (getAx() > 0) {
				setPrevMove("right");
			}
		}
			
		if (direction == "left") {
			setVx(-getVxi());
			setAx(-getAxi());
		} else {
			setVx(getVxi());
			setAx(getAxi());
		}
	}
	
	/**
	 * Stops the horizontal movement of this Mazub.
	 * 
	 * @effect	If there is no previous movement, this Mazub's horizontal velocity and acceleration will be set to zero.
	 * 			| if (getPrevMove() == "") {
	 * 			|	setVx(0)
	 * 			|	setAx(0)
	 * 			| }
	 * @effect	If there is previous movement, this Mazub's new movement will be this previous movement and the previous
	 * 			movement will be removed.
	 * 			| if (getPrevMove() != "") {
	 * 			|	if (getPrevMove() != direction) {
	 * 			|		if (direction == "left")
	 * 			|			then startMove("right")
	 * 			|		else
	 * 			|			then startMove("left")
	 * 			|	}
	 * 			|	setPrevMove("")
	 * 			| }
	 */
	public void endMove(String direction) {
		
		if (getPrevMove() == "") {
			setVx(0);
			setAx(0);
		} else {
			if (getPrevMove() != direction) {
				if (direction == "left")
					startMove("right");
				else
					startMove("left");
			}
			setPrevMove("");
		}
	}
	
	protected void startJump() {
		
	}

	/**
	 * Ends a jump for this Mazub.
	 * 
	 * @effect	This Mazub's vertical velocity will be set to 0 if it's positive.
	 * 			| setVy(Math.min(0, getVy()))
	 */
	public void endJump() {
		setVy(Math.min(0, getVy()));
	}
	
	protected void startDuck() {
		
	}
	
	protected void endDuck() {
		
	}
	
	protected void executeProgram(double time) {
		
	}
	
	/**
	 * Checks whether or not this game object can jump.
	 * 
	 * @return	False (by default for any game object).
	 * 			| result = false
	 */
	public boolean canJump() {
		return false;
	}
	
	/**
	 * Gets the current sprite of this game object.
	 * 
	 * @return	The first image from this game object's image array if it's moving to the left or standing still, else
	 * 			the second image.
	 * 			| if (this.getVx() <= 0)
	 * 			| 	then result = getImages()[0]
	 * 			| else
	 * 			| 	then result = getImages()[1]
	 */
	public Sprite getCurrentSprite() {
		if (this.getVx() <= 0) {
			return getImages()[0];
		}
		return getImages()[1];
	}
	
	/**
	 * Returns the opposite direction of the given direction.
	 * 
	 * @param direction
	 * 			The direction that should ber mirrored.
	 * @return	...
	 * 			| if (direction < 4) 
	 *			|	then result (direction + 2)%4
	 *			| else
	 *			| 	then result = (direction + 2)%4 + 4
	 */
	protected int mirrorDirection(int direction) {
		if (direction < 4) {
			return (direction + 2)%4;
		} else {
			return (direction + 2)%4 + 4;
		}
	}
}
