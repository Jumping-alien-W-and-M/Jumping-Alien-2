package jumpingalien.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import jumpingalien.util.Sprite;

public abstract class GameObject {
	
	protected GameObject(double x, double y, Sprite[] images, double axi, double vxi, double vxmax){
		setX(x);
		setY(y);
		this.images = images;
		this.axi = axi;
		this.vxi = vxi;
		setVxmax(vxmax);
		
		setVx(0);
		setVy(0);
		setAx(0);
		setAy(0);
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
	 * 			Throws an exception if x is outside the game world.
	 * 			| !isValidX(x)
	 */
	protected void setX(double x) throws IllegalArgumentException {
		if (!isValidX(x)) {
			throw new IllegalArgumentException();
		}
		this.x = x;
	}
	
	/**
	 * Checks if the given x is a valid x-position for this game object in the game world.
	 * 
	 * @param x
	 * 			The x-position in pixels which should be checked.
	 * @return	If this game object is in a world, whether or not the given x is within the game world.
	 * 			else it returns whether or not the given x is larger then or equal to zero.
	 * 			| if(getWorld() != null)
	 *			|	return (x >= 0 && x < getWorld().getWorldWidth());
	 *			| return x >= 0;
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
	 * 			If y is outside the game world.
	 * 			| !isValidY(y)
	 */
	protected void setY(double y) throws IllegalArgumentException {
		if (!isValidY(y)) {
			throw new IllegalArgumentException();
		}
		this.y = y;
	}
	
	/**
	 * Checks if the given y is a valid y-position for this game object in the game world.
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
	 * @pre		The given vx must be in between -vxmax and vxmax.
	 * 			| isValidVx(vx)
	 * @post	The new horizontal velocity of this game object is equal to vx.
	 * 			| new.getVx() = vx
	 */
	protected void setVx(double vx) {
		assert isValidVx(vx);
		this.vx = vx;
	}
	
	/**
	 * Checks if the given vx is a valid horizontal velocity.
	 * 
	 * @param vx
	 * @return Whether or not the magnitude of vx is equal to 0 or not smaller than the initial horizontal velocity and not larger than
	 * 			the maximal horizontal velocity.
	 * 			| result = ((-vxmax <= vx && vx <= -vxi) || (vx == 0) || (vxi <= vx && vx <= vxmax))
	 */
	public boolean isValidVx(double vx) {
		return ((-getVxmax() <= vx && vx <= -getVxi())
				|| (vx == 0)
				|| (getVxi() <= vx && vx <= getVxmax()));
	}
	
	private double vx;
	

	 
	
	/**
	 * Gets the magnitude of the initial horizontal velocity any Mazub starts with when they start moving.
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
	 * 			which has to be smaller than or equal to 8 meters per second.
	 * @post	This game object's new vertical velocity will be equal to the given vy, unless it's larger than 8 meters per second,
	 * 			in which case the new vertical velocity will be equal to 8 meters per second.
	 * 			| if (vy <= 8)
	 * 			| 	then new.getVy() = vy
	 *			| else
	 *			|	then new.getVy() = 8
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
	@Basic
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
	 * 			| (getWorld() == world)
	 */
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
	 * Gets the time this game object has been dead for.
	 */
	@Basic
	public double getDeathTime(){
		return this.death_time;
	}
	
	/**
	 * Sets the time this game object has been dead for.
	 * 
	 * @param deathtime
	 * 			The new time this game object has been dead for.
	 * @post	The new time this game object has been dead for is equal to the given death time.
	 * 			| new.getDeathTime() = deathtime 
	 */	
	protected void setDeathTime(double deathtime){
		this.death_time = deathtime;
	}
	
	/**
	 * Terminates this plant with a delay of 0.6 seconds.
	 * 
	 * @effect	The death time of this plant will we set to 0.6.
	 * 			| setDeathTime(0.6)
	 */
	protected void kill(){
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
	 * @param timeinmagma
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
	 * @post	The timeinwater variable of this game object will be equal to the given timeinwater.
	 * 			| (new.getTimeinwater() = timeinwater)
	 * @pre		The timeinwater parameter must be larger than or equal to zero.
	 * 			| (timeinwater >= 0)
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
	 * @post	The timeinmagma variable of this game object will be equal to the given timeinmagma.
	 * 			| (new.getTimeinmagma() = timeinmagma)
	 * @pre		The timeinmagma parameter must be larger then or equal to zero.
	 * 			| (timeinmagma >= 0)
	 */
	protected void setTimeInMagma(double timeinmagma){
		assert(timeinmagma >= 0);
		this.time_in_magma = timeinmagma;
	}
	
	private double time_in_magma;
	
	/**
	 * Gets how long this Mazub will be invincible.
	 */
	@Basic
	public double getTimeInvincible(){
		return this.time_invincible;
	}
	
	/**
	 * Sets the time that this Mazub will stay invincible for to the given time.
	 * 
	 * @param time
	 * 			The new time that Mazub is invincible for 
	 * @post	This Mazub's new time he will be invincible for, will be equal to time
	 * 			| (new.getTimeInvincible() = time)
	 * @pre		The time parameter must be larger then or equal to zero.
	 * 			| (time >= 0)
	 */
	protected void setTimeInvincible(double time){
		assert(time >= 0);
		this.time_invincible = time;
	}
	
	private double time_invincible;
	 
	protected abstract void terminate();
	
	public abstract void advanceTime(double dt) throws IllegalArgumentException;
	
	/**
	 * Advances the time by a given timestep for this game object.
	 * 
	 * @param timestep
	 * 			The amount of seconds to be advanced.
	 * @effect	The x-position will be advanced using the given time.
	 * 			| setX(advanceX(timestep))
	 * @effect	The horizontal velocity will be advanced using the given time.
	 * 			| setVx(advanceVx(timestep))
	 * @effect	The y-position will be advanced using the given time.
	 * 			| setY(advanceY(timestep))
	 * @effect	The vertical velocity will be advanced using the given time.
	 * 			| setVy(advanceVy(timestep))
	 * @effect	The vertical acceleration will be equal to -10 if this game object is in mid-air, else it will be 0.
	 * 			| setAy(advanceAy())
	 * @effect	The last movement time will be equal to -1 if this game object is moving to the left,
	 * 			1 if this game object is moving to the right, and if this game object is standing still the absolute value of the
	 * 			previous last movement time will be decreased by timestep, unless it hits 0, in which case it will stick to 0.
	 * 			| setLastMove(advanceLastMove(timestep))
	 * @effect	The animation time will be increased by timestep.
	 * 			| setAnimationTime(getAnimationTime() + timestep))
	 */
	public void advanceTimeStep(double timestep) {
		
		List<List<List<Object>>> collisions;
		if(getWorld() != null)
			collisions = getWorld().collisionDetect(this, 0);
		else
			collisions = new ArrayList<List<List<Object>>>(
							Collections.nCopies(4, Collections.nCopies(2, new ArrayList<Object>())));
		
		if ((listEmptyOrPlants(collisions.get(0).get(0)) && !(collisions.get(0).get(1).contains(Feature.ground)) && (getVx() < 0))
			|| ((listEmptyOrPlants(collisions.get(2).get(0)) && !(collisions.get(2).get(1).contains(Feature.ground)) && (getVx() > 0)))) 
				advanceX(timestep);
		
		setVx(advanceVx(timestep));
		
		if(getWorld() != null)
			collisions = getWorld().collisionDetect(this, 0);
		else
			collisions = new ArrayList<List<List<Object>>>(
							Collections.nCopies(4, Collections.nCopies(2, new ArrayList<Object>())));
		
		if ((listEmptyOrPlants(collisions.get(1).get(0)) && !(collisions.get(1).get(1).contains(Feature.ground)) && (getVy() > 0))
				|| ((listEmptyOrPlants(collisions.get(3).get(0)) && !(collisions.get(3).get(1).contains(Feature.ground)) && (getVy() < 0)))) 
			advanceY(timestep);
		
		if (getVy() > 0) {
			for(int i = 0; i < 4; i++) {
				if ((collisions.get(i).get(0).size() != 0) || (collisions.get(i).get(1).contains(Feature.ground))) {
					setVy(0);
					break;
				}
			}
		}
		
		setVy(advanceVy(timestep));
		setAy(advanceAy());
		setTimeInvincible(advanceTimeInvincible(timestep));
	}
	
	/**
	 * Checks whether the given (array)list is empty or only contains plants.
	 * 
	 * @param list
	 * 			The (array)list which should be checked.
	 * @return	...
	 * 			| for(Object element : list)
	 * 			|	if (!(element instanceof Plant))
	 * 			|		then result = false
	 * 			| result = true
	 */
	protected boolean listEmptyOrPlants(List<Object> list) {
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
	 * @return	If this game object's ax and ay are equal to zero, the result will be the minimum of 4 formulas.
	 * 			1) 1 divided by the absolute value of, this Mazub's horizontal velocity divided by 100.
	 * 			2) 1 divided by the absolute value of, this Mazub's vertical velocity divide by 100.
	 * 			3) the result of computeformula(v,a) with as parameters 
	 * 				this game object's horizontal acceleration and velocity.
	 * 			4) the result of computeformula(v,a) with as parameters 
	 * 				this game object's vertical acceleration and velocity.
	 * 			| result =  Math.min(computeformula(getVx,getAx)
	 * 			|				Math.min(computeformula(getVy,getAy),
	 * 			|					Math.min( 1/Math.abs(getVx()/100),1/Math.abs(getVy()/100)))
	 */
	public double getTimestep(double dt, double time_passed) {
		if ((getVx() == 0) && (getVy() == 0) && (getAx() == 0) && (getAy() == 0))
			return dt - time_passed;
		return Math.min(0.01/(Math.sqrt(Math.pow(getVx(), 2) + Math.pow(getVy(), 2)) +
				Math.sqrt(Math.pow(getAx(), 2) + Math.pow(getAy(), 2))*dt), dt - time_passed);
	}
	
	/**
	 * Returns the new x-position after a given time has passed.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @return	The new x-position after dt time has passed.
	 * 			| max_s = maxAdvanceX(dt)
	 * 			| actual_s = maxAdvanceX(dt)
	 * 			| result = getXWithinBounds(getX() + (int) Math.round(Math.min(max_s, actual_s)))
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
	 * Gets an x-position within the bounds of the game world.
	 * 
	 * @param x
	 * 			The x-position which should be converted to a valid one.
	 * @return	The new x-position. If it's already within the bounds, this will be equal to the given x.
	 * 			If it's too small or too large, it will be set to the smallest or largest possible value, respectively.
	 * 			| result = Math.max(0, Math.min(getWorldWidth() - 1, x))
	 */
	protected void setXWithinBounds(double x) {
		if (getWorld() != null && (x < 0 || x >= getWorld().getWorldWidth()))
				terminate();
		setX(Math.max(x, 0));
	}

	/**
	 * Calculates the maximal change in x-position within a given period of time.
	 * Currently also used to calculate the actual change in x-position within a given period of time.
	 * 
	 * @param dt
	 * 			The period of time which should be advanced.
	 * @return	The maximal change in x-position using dt, in pixels.
	 * 			| result = 100*(getVx()*dt + getAx()*Math.pow(dt, 2)/2)
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
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
	 * Returns the new y-position after a given time has passed.
	 * 
	 * @param dt
	 * 			The amount of seconds to be advanced.
	 * @return	The new y-position after dt time has passed.
	 * 			| max_s = maxAdvanceX(dt)
	 * 			| actual_s = maxAdvanceX(dt)
	 * 			| result = getYWithinBounds(getY() + (int) Math.round(Math.min(max_s, actual_s)))
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
	 * Gets an y-position within the bounds of the game world.
	 * 
	 * @param y
	 * 			The y-position which should be converted to a valid one.
	 * @return	The new y-position. If it's already within the bounds, this will be equal to the given y.
	 * 			If it's too small or too large, it will be set to the smallest or largest possible value, respectively.
	 * 			| result = Math.max(0, Math.min(getWorldHeight() - 1, y))
	 */
	protected void setYWithinBounds(double y) {
		if (getWorld() != null && (y < 0 || y >= getWorld().getWorldHeight()))
				terminate();
		setY(Math.max(y, 0));
	}
	
	/**
	 * Calculates the maximal change in y-position within a given period of time.
	 * Currently also used to calculate the actual change in y-position within a given period of time.
	 * 
	 * @param dt
	 * 			The period of time which should be advanced.
	 * @return	The maximal change in y-position using dt, in pixels.
	 * 			| result = 100*(getVy()*dt + getAy()*Math.pow(dt, 2)/2)
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 */
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
	 * @return	The new vertical velocity after dt time has passed in meters per second.
	 * 			| result = getVy() + getAy()*dt
	 * @throws IllegalArgumentException
	 * 			If dt isn't a valid time interval to advance the time with.
	 * 			| !isValidDt(dt)
	 * @throws IllegalStateException
	 * 			If the vertical velocity after updating overflows negatively.
	 * 			| (newvy == Double.NEGATIVE_INFINITY)
	 */
	@Model
	protected double advanceVy(double dt) throws IllegalArgumentException, IllegalStateException {
		if (!isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		if (getY() <= 0) {
			return 0;
		}
		
		double newvy = getVy() + getAy()*dt;
		
		if (newvy == Double.NEGATIVE_INFINITY) {
			throw new IllegalStateException();
		}
		return newvy;
	}
	
	/**
	 * Returns the new vertical acceleration at this game object's current y-position.
	 * 
	 * @return	The new vertical acceleration at this game object's current y-position has passed in meters per second squared.
	 * 			This will be -10 if this game object's y-position is larger than 0, else it'll be 0.
	 * 			| if (getY() == 0)
	 * 			| 	then result = -10
	 * 			| else
	 * 			|	result = getAy()
	 */
	@Model
	protected double advanceAy() {
		
		if (canJump() || (int) getY() <= 0 ) {
			return 0;
		} else {
			return -10;
		}
	}
	
	/**
	 * Return the new time this mazub will be invincible for.
	 * 
	 * @param dt
	 * 			The period of time wich should be advance, in seconds.
	 * @return	The current time this mazub will be invinceble for min the given dt 
	 * 			if this is bigger then or equal to 0 else 0 is returned.
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
			}
		}
		
		collisionHandleFeature(collisions, time);

	}
	
	protected void collisionHandleFeature(List<List<List<Object>>> collisions, double time) {
		
		boolean touched_air = false;
		boolean touched_water = false;
		boolean touched_magma = false;
		
		for(int i = 0; i < collisions.size(); i++) {
			ArrayList<Object> collision_features = (ArrayList<Object>) collisions.get(i).get(1);
		
			if ((collision_features.contains(Feature.air)) && !(touched_air)) {
				collisionHandleAir(time);
				touched_air = true;
			}
			if ((collision_features.contains(Feature.water)) && !(touched_water)) {
				collisionHandleWater(time);
				touched_water = true;
			}
			if ((collision_features.contains(Feature.magma)) && !(touched_magma)) {
				collisionHandleMagma(time);
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
	 * 			| 	then getImages()[1]
	 */
	public Sprite getCurrentSprite() {
		if (this.getVx() <= 0) {
			return getImages()[0];
		}
		return getImages()[1];
	}
	
	protected int mirrorDirection(int direction) {
		if (direction < 4) {
			return (direction + 2)%4;
		} else {
			return (direction + 2)%4 + 4;
		}
	}
}
