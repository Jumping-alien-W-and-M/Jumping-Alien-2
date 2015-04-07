package jumpingalien.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

/**
 * The world class, which stores general information about the state of the game, like the player character, enemies, the environment,
 * the position of the view, ...
 * 
 * @invar	...
 * 			| (getXWindow() == getProperXWindow(getXWindow()))
 * @invar	...
 * 			| (getYWindow() == getProperYWindow(getYWindow()))
 * 
 * @version 1.0
 *
 */
public class World {
	
	/**
	 * Creates a new world.
	 * 
	 * @param tile_size
	 * 			The size (both width and height) of the tiles in this world, in pixels.
	 * @param tiles_x_amount
	 * 			The amount of tiles in the X-direction in this world.
	 * @param tiles_y_amount
	 * 			The amount of tiles in the Y-direction in this world.
	 * @pre		...
	 * 			| (tile_size > 0)
	 * @pre		...
	 * 			| (tiles_x_amount > 0)
	 * @pre		...
	 * 			| (tiles_y_amount > 0)
	 * @pre		...
	 * 			| (x_target > 0)
	 * @pre		...
	 * 			| (y_target > 0)
	 * @pre		...
	 * 			| (x_target < tiles_x_amount)
	 * @pre		...
	 * 			| (y_target < tiles_y_amount)
	 * @post	...
	 * 			| (getTileSize() == tile_size)
	 * @post	...
	 * 			| (getWorldWidth() == tiles_x_amount*getTileSize())
	 * @post	...
	 * 			| (getWorldHeight() == tiles_y_amount*getTileSize())
	 * @post	...
	 * 			| (getXTarget() == x_target)
	 * @post	...
	 * 			| (getYTarget() == y_target)
	 * @effect	...
	 * 			| setWindowWidth(window_width)
	 * @effect	...
	 * 			| setWindowHeight(window_height)
	 * @effect	...
	 * 			| setXWindow(0)
	 * @effect	...
	 * 			| setYWindow(0)
	 */
	public World(int tile_size, int tiles_x_amount, int tiles_y_amount, int window_width, int window_height, int x_target, int y_target) {
		assert(tile_size > 0);
		assert(tiles_x_amount > 0);
		assert(tiles_y_amount > 0);
		assert(x_target > 0);
		assert(y_target > 0);
		assert(x_target < tiles_x_amount);
		assert(y_target < tiles_y_amount);
		
		this.tile_size = tile_size;
		this.world_width = tiles_x_amount*getTileSize();
		this.world_height = tiles_y_amount*getTileSize();
		this.hashDigitsAmount = (int) Math.floor(Math.log10(tiles_y_amount) + 1);
		
		setWindowWidth(window_width);
		setWindowHeight(window_height);
		setXWindow(0);
		setYWindow(0);

		this.x_target = x_target;
		this.y_target = y_target;
	}

	/**
	 * Gets the width of the game world.
	 */
	@Basic @Immutable
	public int getWorldWidth() {
		return world_width;
	}
	
	private final int world_width;
	
	/**
	 * Gets the height of the game world.
	 */
	@Basic @Immutable
	public int getWorldHeight() {
		return world_height;
	}
	
	private final int world_height;
	
	/**
	 * Gets the tile size of this game world.
	 */
	@Basic @Immutable
	public int getTileSize() {
		return tile_size;
	}
	
	private final int tile_size;
	
	/**
	 * Gets the x-position of this world's target tile, in tiles.
	 */
	@Basic @Immutable
	public int getXTarget() {
		return x_target;
	}
	
	private final int x_target;
	
	/**
	 * Gets the y-position of this world's target tile, in tiles.
	 */
	@Basic @Immutable
	public int getYTarget() {
		return y_target;
	}
	
	private final int y_target;
	
	/**
	 * Gets the X-position of this game world's window.
	 */
	@Basic @Immutable
	public int getXWindow() {
		return x_window;
	}
	
	/**
	 * Sets the X-position of this game world's window.
	 * 
	 * @param x
	 * 			The new X-position of this game world's window.
	 * @post	...
	 * 			| new.getXWindow() == getProperXWindow(x)
	 */
	private void setXWindow(int x) {
		this.x_window = getProperXWindow(x);
	}
	
	private int x_window;
	
	/**
	 * Gets the Y-position of this game world's window.
	 */
	@Basic @Immutable
	public int getYWindow() {
		return y_window;
	}
	
	/**
	 * Sets the Y-position of this game world's window.
	 * 
	 * @param y
	 * 			The new Y-position of this game world's window.
	 * @post	...
	 * 			| new.getXWindow() == getProperXWindow(y)
	 */
	private void setYWindow(int y) {
		this.y_window = getProperYWindow(y);
	}
	
	private int y_window;
	
	/**
	 * Gets a proper window X-position based on the boundaries of the world and the player's position.
	 * 
	 * @param x
	 * 			The X-position which should be converted.
	 * @return	...
	 * 			| result = Math.max(0, Math.min(getWorldWidth() - getWindowWidth(),
	 * 			| 	Math.max(getMazub().getX() + window_margin - getWindowWidth(), Math.min(getMazub().getX() - window_margin, x))
	 * 			| ))
	 */
	public int getProperXWindow(int x) {
		
		if (x > getMazub().getX() - window_margin) {
			// If Mazub is too close to the left wall.
			x = (int) getMazub().getX() - window_margin;
		} else if (x + getWindowWidth() < getMazub().getX() + window_margin) {
			// If Mazub is too close to the right wall.
			x = (int) getMazub().getX() + window_margin - getWindowWidth();
		}
		
		return Math.max(0, Math.min(getWorldWidth() - getWindowWidth(), x));
		
	}
	
	/**
	 * Gets a proper window Y-position based on the boundaries of the world and the player's position.
	 * 
	 * @param y
	 * 			The Y-position which should be converted.
	 * @return	...
	 * 			| result = Math.max(0, Math.min(getWorldHeight() - getWindowHeight(),
	 * 			| 	Math.max(getMazub().getY() + window_margin - getWindowHeight(), Math.min(getMazub().getY() - window_margin, y))
	 * 			| ))
	 */
	public int getProperYWindow(int y) {
		
		if (y > getMazub().getY() - window_margin) {
			// If Mazub is too close to the left wall.
			y = (int) getMazub().getY() - window_margin;
		} else if (y + getWindowHeight() < getMazub().getY() + window_margin) {
			// If Mazub is too close to the right wall.
			y = (int) getMazub().getY() + window_margin - getWindowHeight();
		}
		
		return Math.max(0, Math.min(getWorldHeight() - getWindowHeight(), y));
		
	}
	
	private static final int window_margin = 200;
	
	/**
	 * Gets the window width of this game world's window.
	 */
	@Basic @Immutable
	public int getWindowWidth() {
		return window_width;
	}
	
	/**
	 * Sets the window width of this game world's window.
	 * 
	 * @param y
	 * 			The new window width of this game world's window.
	 * @pre		...
	 * 			| (window_width > 0)
	 * @pre		...
	 * 			| (window_width <= getWorldWidth())
	 * @post	...
	 * 			| new.getWindowWidth() == window_width
	 */
	private void setWindowWidth(int window_width) {
		assert(window_width > 0);
		assert(window_width <= getWorldWidth());
		this.window_width = window_width;
	}
	
	private int window_width;
	
	/**
	 * Gets the window height of this game world's window.
	 */
	@Basic @Immutable
	public int getWindowHeight() {
		return window_height;
	}
	
	/**
	 * Sets the window height of this game world's window.
	 * 
	 * @param y
	 * 			The new window height of this game world's window.
	 * @pre		...
	 * 			| (window_height > 0)
	 * @pre		...
	 * 			| (window_height <= getWorldHeight())
	 * @post	...
	 * 			| new.getWindowHeight() == window_height
	 */
	private void setWindowHeight(int window_height) {
		assert(window_height > 0);
		assert(window_height <= getWorldHeight());
		this.window_height = window_height;
	}
	
	private int window_height;
	
	/**
	 * Checks whether or not this world is terminated.
	 * 
	 * @return	...
	 * 			| result = ((getMazub() == null) || (touchesTarget()))
	 */
	public boolean isGameOver() {
		return ((getMazub() == null) || (touchesTarget()));
		

	}
	
	/**
	 * Checks whether or not this world's player is touching the target tile.
	 * 
	 * @return	...
	 * 			| for(x = getMazub().getX(); x <= getMazub().getX() + getMazub().getWidth(); x += getMazub().getWidth())
	 * 			|	for(y = getMazub().getY(); y <= getMazub().getY() + getMazub().getHeight(); y += getMazub().getHeight())
	 * 			| 		if ((x >= getXTarget()) && (x < getXTarget() + getTileSize())
	 * 			|			&& (y >= getYTarget()) && (y < getYTarget() + getTileSize()))
	 * 			|			result = true
	 * 			| result = false
	 */
	private boolean touchesTarget() {
		
		for(int x = (int) getMazub().getX(); x <= getMazub().getX() + getMazub().getWidth(); x += getMazub().getWidth()) {
			for(int y = (int) getMazub().getY(); y <= getMazub().getY() + getMazub().getHeight(); y += getMazub().getHeight()) {
				if ((getTilePos(x) == getXTarget())
						&& (getTilePos(y) == getYTarget())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the position of a position in pixels in the tile coordinate system.
	 * 
	 * @param pos
	 * 			The position which should be checked.
	 * @return	...
	 * 			| result = (int) Math.floor(pos/getTileSize())
	 */
	protected int getTilePos(int pos) {
		return (int) pos/getTileSize();
	}
	
	/**
	 * Gets the player inhabiting this world.
	 */
	public Mazub getMazub() {
		return this.player;
	}
	
	/**
	 * Connects a given player with a this world, and vice versa.
	 * 
	 * @param player
	 * 			The player which should be associated with the world.
	 * @post	...
	 * 			| (getMazub() == player)
	 * @effect	...
	 * 			| if (player != null)
	 * 			| 	then player.setWorld(this)
	 */
	protected void setMazub(Mazub player) {
		this.player = player;
		if (player != null) player.setWorld(this);
	}
	
	private Mazub player;
	
	/**
	 * Gets the list of sharks in this world.
	 */
	@Basic @Immutable
	public ArrayList<Shark> getSharks() {
		return this.sharks;
	}
	
	/**
	 * Adds a given shark to this world's list of sharks.
	 * 
	 * @param shark
	 * 			The shark which should be added.
	 * @pre		...
	 * 			| (shark != null)
	 * @pre		...
	 * 			| (!(hasAsShark(shark)))
	 * @effect	...
	 * 			| getSharks().add(shark)
	 * @effect	...
	 * 			| shark.setWorld(this)
	 */
	protected void addShark(Shark shark) {
		assert(shark != null);
		assert(!(hasAsShark(shark)));
		
		getSharks().add(shark);
		shark.setWorld(this);
	}
	
	/**
	 * Checks whether or not the given shark is part of this world.
	 * 
	 * @param shark
	 * 			The shark whose participation in this world should be checked.
	 * @pre		...
	 * 			| (shark != null)
	 * @return	...
	 * 			| result = getSharks().contains(shark)
	 */
	public boolean hasAsShark(Shark shark) {
		assert(shark != null);
		
		return getSharks().contains(shark);
	}
	
	/**
	 * Removes the given shark from this world's list of sharks.
	 * 
	 * @param shark
	 * 			The shark which should be removed.
	 * @pre		...
	 * 			| (shark != null)
	 * @pre		...
	 * 			| (hasAsShark(shark))
	 * @effect	...
	 * 			| getSharks().remove(shark)
	 * @effect	...
	 * 			| shark.setWorld(null)
	 */
	protected void removeShark(Shark shark) {
		assert(shark != null);
		assert(hasAsShark(shark));
		
		getSharks().remove(shark);
		shark.setWorld(null);
	}
	
	private final ArrayList<Shark> sharks = new ArrayList<Shark>();
	
	/**
	 * Gets the list of plants in this world.
	 */
	@Basic @Immutable
	public ArrayList<Plant> getPlants() {
		return this.plants;
	}
	
	/**
	 * Adds a given plant to this world's list of plants.
	 * 
	 * @param plant
	 * 			The plant which should be added.
	 * @pre		...
	 * 			| (plant != null)
	 * @pre		...
	 * 			| (!(hasAsPlant(plant)))
	 * @effect	...
	 * 			| getPlants().add(plant)
	 * @effect	...
	 * 			| plant.setWorld(this)
	 */
	protected void addPlant(Plant plant) {
		assert(plant != null);
		assert(!(hasAsPlant(plant)));
		
		getPlants().add(plant);
		plant.setWorld(this);
	}
	
	/**
	 * Checks whether or not the given plant is part of this world.
	 * 
	 * @param plant
	 * 			The plant whose participation in this world should be checked.
	 * @pre		...
	 * 			| (plant != null)
	 * @return	...
	 * 			| result = getPlants().contains(plant)
	 */
	public boolean hasAsPlant(Plant plant) {
		assert(plant != null);
		
		return getPlants().contains(plant);
	}
	
	/**
	 * Removes the given plant from this world's list of plants.
	 * 
	 * @param plant
	 * 			The plant which should be removed.
	 * @pre		...
	 * 			| (plant != null)
	 * @pre		...
	 * 			| (hasAsPlant(plant))
	 * @effect	...
	 * 			| getPlants().remove(plant)
	 * @effect	...
	 * 			| plant.setWorld(null)
	 */
	protected void removePlant(Plant plant) {
		assert(plant != null);
		assert(hasAsPlant(plant));
		
		getPlants().remove(plant);
		plant.setWorld(null);
	}

	private final ArrayList<Plant> plants = new ArrayList<Plant>();
	
	/**
	 * Gets the list of schools in this world.
	 */
	@Basic @Immutable
	public ArrayList<School> getSchools() {
		return this.schools;
	}
	
	/**
	 * Adds a given school to this world's list of schools.
	 * 
	 * @param school
	 * 			The school which should be added.
	 * @pre		...
	 * 			| (school != null)
	 * @pre		...
	 * 			| (!(hasAsSchool(school)))
	 * @effect	...
	 * 			| getSchools().add(school)
	 * @effect	...
	 * 			| school.setWorld(this)
	 */
	protected void addSchool(School school) {
		assert(school != null);
		assert(!(hasAsSchool(school)));
		
		getSchools().add(school);
		school.setWorld(this);
	}
	
	/**
	 * Checks whether or not the given school is part of this world.
	 * 
	 * @param school
	 * 			The school whose participation in this world should be checked.
	 * @pre		...
	 * 			| (school != null)
	 * @return	...
	 * 			| result = getSchools().contains(school)
	 */
	public boolean hasAsSchool(School school) {
		assert(school != null);
		
		return getSchools().contains(school);
	}
	
	/**
	 * Removes the given school from this world's list of schools.
	 * 
	 * @param school
	 * 			The school which should be removed.
	 * @pre		...
	 * 			| (school != null)
	 * @pre		...
	 * 			| (hasAsSchool(school))
	 * @effect	...
	 * 			| getSchools().remove(school)
	 * @effect	...
	 * 			| school.setWorld(null)
	 */
	protected void removeSchool(School school) {
		assert(school != null);
		assert(hasAsSchool(school));
		
		getSchools().remove(school);
		school.setWorld(null);
	}
	
	private final ArrayList<School> schools = new ArrayList<School>();
	
	/**
	 * Gets the hash of a given x and y position.
	 * 
	 * @param x
	 * 			The X-position which should be converted to a hash.
	 * @param y
	 * 			The Y-position which should be converted to a hash.
	 * @return	...
	 * 			| result = ((int) Math.pow(10, getHashDigitsAmount()))*x + y
	 */
	public int getHash(int x, int y) {
		return ((int) Math.pow(10, getHashDigitsAmount()))*x + y;
	}
	
	/**
	 * Gets the feature at a given X- and Y-position.
	 * 
	 * @param x
	 * 			The X-position which should be checked, in pixels.
	 * @param y
	 * 			The Y-position which should be checked, in pixels.
	 * @return	...
	 * 			| result = getFeatures().get(getHash(getTilePos(x), getTilePos(y)))
	 */
	public Feature getFeature(int x, int y) {
		Feature feature = getFeatures().get(getHash(getTilePos(x), getTilePos(y)));
		if (feature != null) {
			return feature;
		}
		return Feature.air;
	}
	
	/**
	 * Gets the hashmap containing the features of this world.
	 */
	@Basic @Immutable
	public HashMap<Integer, Feature> getFeatures() {
		return features;
	}
	
	private final HashMap<Integer, Feature> features = new HashMap<Integer,Feature>();
	
	/**
	 * Gets the amount of digits needed to store the Y-position of a tile in base 10 in this world.
	 */
	@Basic @Immutable
	public int getHashDigitsAmount() {
		return this.hashDigitsAmount;
	}
	
	private final int hashDigitsAmount;
	
	/**
	 * Checks the collisions of a given object with all other objects and features during an instant.
	 * 
	 * @param object
	 * 			The object whose collisions with other objects and features should be checked.
	 * @param custom_height
	 * 			An optional custom height of object. If 0, object.getHeight() will be used.
	 * @return	...
	 * 			| collisions = new ArrayList<List<List<Object>>>(
	 * 			| 							Collections.nCopies(4, 
	 * 			| 							Collections.nCopies(2, new ArrayList<Object>()))
	 * 			|						)
	 * 			|
	 * 			| collisionDetectObject(object, getMazub(), custom_height, collisions)
	 * 			| if (!(object instanceof Plant)) {
	 * 			|	for(Shark shark : sharks)
	 * 			|		collisionDetectObject(object, shark, custom_height, collisions)
	 * 			|	for(School school : schools)
	 * 			|		for(Slime slime : school.getSlimes())
	 * 			|			collisionDetectObject(object, slime, custom_height, collisions)
	 * 			|	if (object instanceof Mazub)
	 * 			|		for(Plant plant : plants)
	 * 			|			collisionDetectObject(object, plant, custom_height, collisions)
	 * 			| }
	 *			|
	 *			| for(int i = 0; i < getWorldWidth(); i += getTileSize())
	 *			|	for(int j = 0; j < getWorldHeight(); j += getTileSize())
	 *			|		collisionDetectFeature(object, i, j, custom_height, collisions)
	 * 			|
	 * 			| result = collisions
	 */
	public ArrayList<List<List<Object>>> collisionDetect(GameObject object, int custom_height) {
		
		ArrayList<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>(
																Collections.nCopies(4, 
																Collections.nCopies(2, new ArrayList<Object>()))
															);
		
		// Checks collisions with the player, sharks, slimes and plants
		collisionDetectObject(object, getMazub(), custom_height, collisions);
		if (!(object instanceof Plant)) {
			for(Shark shark : sharks) {
				collisionDetectObject(object, shark, custom_height, collisions);
			}
			for(School school : schools) {
				for(Slime slime : school.getSlimes()) {
					collisionDetectObject(object, slime, custom_height, collisions);
				}
			}
		}
		if ((object instanceof Mazub) || (object instanceof Plant)){
			for(Plant plant : plants) {
				collisionDetectObject(object, plant, custom_height, collisions);
			}
		}
		// Checks collisions with features
		for(int i = 0; i < getWorldWidth(); i += getTileSize()) {
			for(int j = 0; j < getWorldHeight(); j += getTileSize()) {
				collisionDetectFeature(object, i, j, custom_height, collisions);
			}
		}
		
		return collisions; 
	}
	
	/**
	 * Checks the collisions in between two given objects.
	 * 
	 * @param object1
	 * 			The first object whose collisions with the second object should be checked.
	 * @param object2
	 * 			The second object whose collisions with the first object should be checked.
	 * @param custom_height
	 * 			An optional custom height of object1. If 0, object1.getHeight() will be used.
	 * @param collision_objects
	 * 			The 3-dimensional arraylist which stores the collisions of object1.
	 * @effect	...
	 * 			| if (!(object1 == object2)) {
	 * 			|	x1 = (int) object2.getX()
	 * 			|	y1 = (int) object2.getY()
	 * 			|	x2 = x1 + object2.getWidth()
	 * 			|	y2 = y1 + object2.getHeight()
	 * 			| }
	 * 			|
	 * 			| collisionDetectBasic(object1, x1, y1, x2, y2, object2, 0, custom_height, collision_objects)
	 */
	private void collisionDetectObject(GameObject object1, GameObject object2, int custom_height, 
										ArrayList<List<List<Object>>> collision_objects)  {
		
		if (object1 == object2) {
			return;
		}
		
		// Defining temporary variables
		int x1 = (int) object2.getX();
		int y1 = (int) object2.getY();
		int x2 = x1 + object2.getWidth();
		int y2 = y1 + object2.getHeight();
		
		collisionDetectBasic(object1, x1, y1, x2, y2, object2, 0, custom_height, collision_objects);
		
	}
	
	/**
	 * Checks the collisions in between a given object and given feature.
	 * 
	 * @param object
	 * 			The object whose collisions with the feature should be checked.
	 * @param x
	 * 			The x-position of the feature whose collisions with the object should be checked.
	 * @param y
	 * 			The y-position of the feature whose collisions with the object should be checked.
	 * @param custom_height
	 * 			An optional custom height of object. If 0, object.getHeight() will be used.
	 * @param collision_objects
	 * 			The 3-dimensional arraylist which stores the collisions of object.
	 * @effect	...
	 * 			| feature = getFeature(x, y)
	 * 			| if (feature != Feature.air) {
	 * 			|	collisionDetectBasic(object, x, y, x + getTileSize(), y + getTileSize(), feature, 1, custom_height,
	 * 			|							collision_objects)
	 * 			| }
	 */
	private void collisionDetectFeature(GameObject object, int x, int y, int custom_height, 
										ArrayList<List<List<Object>>> collision_objects)  {
		
		Feature feature = getFeature(x, y);
		if (feature == Feature.air) {
			return;
		}
		
		collisionDetectBasic(object, x, y, x + getTileSize(), y + getTileSize(), feature, 1, custom_height, collision_objects);
		
	}
	
	/**
	 * A basic collision detection method, which checks the collisions between a given object and a given rectangle.
	 * 
	 * @param object1
	 * 			The object whose collisions with the rectangle should be checked.
	 * @param sx1
	 * 			The x-position of the left boundary of the rectangle whose collisions with the object should be checked.
	 * @param sy1
	 * 			The y-position of the bottom boundary of the rectangle whose collisions with the object should be checked.
	 * @param sx2
	 * 			The x-position of the right boundary of the rectangle whose collisions with the object should be checked.
	 * @param sy2
	 * 			The y-position of the upper boundary of the rectangle whose collisions with the object should be checked.
	 * @param object2
	 * 			The object which is represented by the rectangle and which will be stored if there is a collision.
	 * @param index
	 * 			Indicates whether the given object2 is a Game object or a Feature.
	 * 			0 represents Game object, 1 represents Feature.
	 * @param custom_height
	 * 			An optional custom height of object1. If 0, object1.getHeight() will be used.
	 * @param collision_objects
	 * 			The 3-dimensional arraylist which stores the collisions of object.
	 * @effect	...
	 * 			| mx1 = (int) object1.getX()
	 * 			| my1 = (int) object1.getY()
	 * 			| mx2 = mx1 + object1.getWidth()
	 * 			| my2 = my1
	 * 			| if (custom_height == 0)
	 * 			|	then my2 += object1.getHeight()
	 * 			| else
	 * 			|	then my2 += custom_height
	 * 			| 
	 * 			| overlaps = {sx2 - mx1, mx2 - sx1, sy2 - my1, my2 - sy1}
	 * 			| if ((overlaps[0] >= 1) && (overlaps[1] >= 1) && (overlaps[2] >= 1) && (overlaps[3] >= 1)) {
	 * 			|	has_central_collision = true
	 * 			|	for(int i = 0; i < 4; i++) {
	 * 			|		collision_objects.get(i).get(index).add(object2)
	 * 			|		has_central_collision = false
	 * 			|	}
	 * 			|	if (has_central_collision)
	 * 			|		then collision_objects.get(0).get(1).add(object2)
	 * 			| }
	 */
	private void collisionDetectBasic(GameObject object1, int sx1, int sy1, int sx2, int sy2, Object object2, int index,
										int custom_height, ArrayList<List<List<Object>>> collision_objects) {
		
		// Defining temporary variables
		int mx1 = (int) object1.getX();
		int my1 = (int) object1.getY();
		int mx2 = mx1 + object1.getWidth();
		int my2 = my1;
		if (custom_height == 0) {
			my2 += object1.getHeight();
		} else {
			my2 += custom_height;
		}
		
		// Returns collisions in order: left, up, right, down
		int[] overlaps = {sx2 - mx1, mx2 - sx1, sy2 - my1, my2 - sy1};
		for(int i = 0; i < 4; i++) {
			if (overlaps[i] < 1) {
				return;
			}
		}
		// Direction-dependent collisions
		boolean has_central_collision = true;
		for(int i = 0; i < 4; i++) {
			if (overlaps[i] == 1) {
				collision_objects.get(i).get(index).add(object2);
				has_central_collision = false;
			}
		}
		if (has_central_collision) {
			collision_objects.get(0).get(1).add(object2);
		}
		
	}
	
}
