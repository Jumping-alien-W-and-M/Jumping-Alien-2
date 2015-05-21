package jumpingalien.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;

/**
 * The world class, which stores general information about the state of the game, like the player character, enemies, the environment,
 * the position of the view, ...
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
	 * @param window_width
	 * 			The width of the visible window.
	 * @param window_height
	 * 			The height of the visible window.
	 * @param x_target
	 * 			The x-position of the target tile.
	 * @param y_target
	 * 			The y-position of the target tile
	 * @pre		...
	 * 			| (tile_size > 0)
	 * @pre		...
	 * 			| (tiles_x_amount > 0)
	 * @pre		...
	 * 			| (tiles_y_amount > 0)
	 * @pre		...
	 * 			| (x_target >= 0)
	 * @pre		...
	 * 			| (y_target >= 0)
	 * @pre		...
	 * 			| (x_target < tiles_x_amount)
	 * @pre		...
	 * 			| (y_target < tiles_y_amount)
	 * @post	...
	 * 			| (new.getTileSize() == tile_size)
	 * @post	...
	 * 			| (new.getWorldWidth() == tiles_x_amount*getTileSize())
	 * @post	...
	 * 			| (new.getWorldHeight() == tiles_y_amount*getTileSize())
	 * @post	...
	 * 			| (new.getHashDigitsAmount() == (int) Math.floor(Math.log10(tiles_y_amount) + 1)
	 * @post	...
	 * 			| (new.getXTarget() == x_target)
	 * @post	...
	 * 			| (new.getYTarget() == y_target)
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
		assert(x_target >= 0);
		assert(y_target >= 0);
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
	 * Gets the width of this game world.
	 */
	@Basic @Immutable
	public int getWorldWidth() {
		return world_width;
	}
	
	private final int world_width;
	
	/**
	 * Gets the height of this game world.
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
	 * Gets the X-position of this game world's window (in pixels).
	 */
	@Basic @Immutable
	public int getXWindow() {
		return x_window;
	}
	
	/**
	 * Sets the X-position of this game world's window.
	 * 
	 * @param x
	 * 			The new X-position of this game world's window (in pixels).
	 * @post	...
	 * 			| new.getXWindow() == getProperXWindow(x)
	 */
	@Model
	private void setXWindow(int x) {
		this.x_window = getProperXWindow(x);
	}
	
	private int x_window;
	
	/**
	 * Gets the Y-position of this game world's window (in pixels).
	 */
	@Basic @Immutable
	public int getYWindow() {
		return y_window;
	}
	
	/**
	 * Sets the Y-position of this game world's window.
	 * 
	 * @param y
	 * 			The new Y-position of this game world's window (in pixels).
	 * @post	...
	 * 			| new.getYWindow() == getProperYWindow(y)
	 */
	@Model
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
	 * 			| double x_player = 0
	 * 			| if(this.getMazub() != null)
	 *			| 		x_player = getMazub().getX() 
	 * 			| result = Math.max(0, Math.min(getWorldWidth() - getWindowWidth(),
	 * 			| 	Math.max(x_player + getWindowMargin() - getWindowWidth(), Math.min(x_player - window_margin, x))
	 * 			| ))
	 */
	public int getProperXWindow(int x) {
		double x_player = 0;
		if(this.getMazub() != null)
			x_player = getMazub().getX();
		
		if (x > x_player - getWindowMargin()) {
			// If Mazub is too close to the left wall.
			x = (int) x_player - getWindowMargin();
		} else if (x + getWindowWidth() < x_player + getWindowMargin()) {
			// If Mazub is too close to the right wall.
			x = (int) x_player + getWindowMargin() - getWindowWidth();
		}
		
		return Math.max(0, Math.min(getWorldWidth() - getWindowWidth(), x));
		
	}
	
	/**
	 * Gets a proper window Y-position based on the boundaries of the world and the player's position.
	 * 
	 * @param y
	 * 			The Y-position which should be converted.
	 * @return	...
	 * 			| double y_player = 0
	 *			| if(this.getMazub() != null)
	 *			|		y_player = getMazub().getY()	
	 * 			| result = Math.max(0, Math.min(getWorldHeight() - getWindowHeight(),
	 * 			| 	Math.max(y_player + getWindowMargin() - getWindowHeight(), Math.min(y_player - window_margin, y))
	 * 			| ))
	 */
	public int getProperYWindow(int y) {
		double y_player = 0;
		if(this.getMazub() != null)
			y_player = getMazub().getY();
		
		if (y > y_player - getWindowMargin()) {
			// If Mazub is too close to the left wall.
			y = (int) y_player - getWindowMargin();
		} else if (y + getWindowHeight() < y_player + getWindowMargin()) {
			// If Mazub is too close to the right wall.
			y = (int) y_player + getWindowMargin() - getWindowHeight();
		}
		
		return Math.max(0, Math.min(getWorldHeight() - getWindowHeight(), y));
		
	}
	
	/**
	 * Gets the minimal distance between Mazub and the edges of the window under normal circumstances.
	 */
	@Basic @Immutable
	public static int getWindowMargin() {
		return World.window_margin;
	}
	
	private static final int window_margin = 200;
	
	/**
	 * Updates the position of this window based on the player's position and on the boundaries of the world.
	 * 
	 * @effect	...
	 * 			| setXWindow(getXWindow())
	 * @effect	...
	 * 			| setYWindow(getYWindow())
	 */
	public void updateWindow() {
		setXWindow(getXWindow());
		setYWindow(getYWindow());
	}
	
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
	 * @param window_width
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
	 * @param window_height
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
	 * 			| if (getMazub() == null) result = false		
	 * @return 	...
	 * 			| List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>()
	 *			| for(int i = 0; i < 8; i++) {
	 *			|		List<List<Object>> inner_collisions = new ArrayList<List<Object>>()
	 *			|		for(int j = 0; j < 2; j++) 
	 *			|			inner_collisions.add(new ArrayList<Object>())
	 *			|		collisions.add(inner_collisions)
	 *			| collisionDetectBasic(getMazub(), getXTarget()*getTileSize(), getYTarget()*getTileSize(),
	 *			|	(getXTarget() + 1)*getTileSize(), (getYTarget() + 1)*getTileSize(), 1, 0, 0, 0, collisions)
	 *			| for(int i = 0; i < collisions.size(); i++) 
	 *			|		if (collisions.get(i).get(0).contains(1)) reult = true		
	 *			| result = false
	 */
	public boolean touchesTarget() {
		
		if (getMazub() == null) return false;
		
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		
		collisionDetectBasic(getMazub(), getXTarget()*getTileSize(), getYTarget()*getTileSize(),
				(getXTarget() + 1)*getTileSize(), (getYTarget() + 1)*getTileSize(), 1, 0, 0, 0, collisions);
		
		for(int i = 0; i < collisions.size(); i++) {
			if (collisions.get(i).get(0).contains(1)) return true;
		}
		
		return false;
	}
	
	/**
	 * Gets the position of a position in pixels in the tile coordinate system.
	 * 
	 * @param pos
	 * 			The position which should be checked.
	 * @return	...
	 * 			| result = (int) pos/getTileSize()
	 */
	public int getTilePos(int pos) {
		return (int) pos/getTileSize();
	}
	
	/**
	 * Returns the tile positions of all tiles within the given rectangular
	 * region.
	 * 
	 * @param x1
	 *            The x-coordinate of the left side of the rectangular region.
	 * @param y1
	 *            The y-coordinate of the bottom side of the rectangular region.
	 * @param x2
	 *            The x-coordinate of the right side of the rectangular region.
	 * @param y2
	 *            The y-coordinate of the top side of the rectangular region.
	 * @pre		...
	 * 			| (x1 >= 0 && x1 < getWorldWidth())
	 * @pre		...
	 * 			| (y1 >= 0 && y1 < getWorldHeight())
	 * @return	...
	 * 			| x2 = Math.max(0, Math.min(getWorldWidth() - 1, x2))
  	 *			| y2 = Math.max(0, Math.min(getWorldHeight() - 1, y2))		
	 *			| int lowerboundx = getTilePos(x1)
	 *			| int lowerboundy = getTilePos(y1)
	 *			| int upperboundx = getTilePos(x2)
	 *			| int upperboundy = getTilePos(y2)
	 *			| int[][] positions = new int[(upperboundx - lowerboundx + 1)*(upperboundy - lowerboundy + 1)][2]
	 *			| int index = 0
	 *			| for(int y = lowerboundy; y <= upperboundy; y++) 
	 *			|		for(int x = lowerboundx; x <= upperboundx; x++) 
	 *			|			int[] position = {x, y}
	 *			|			positions[index] = position
	 *			|			index++	
	 *			| result = positions
	 */
	public int[][] getTilePositionsIn(int x1, int y1, int x2, int y2) {
		assert(x1 >= 0 && x1 < getWorldWidth());
		assert(y1 >= 0 && y1 < getWorldHeight());
		
		x2 = Math.max(0, Math.min(getWorldWidth() - 1, x2));
		y2 = Math.max(0, Math.min(getWorldHeight() - 1, y2));
		
		int lowerboundx = getTilePos(x1);
		int lowerboundy = getTilePos(y1);
		int upperboundx = getTilePos(x2);
		int upperboundy = getTilePos(y2);
		int[][] positions = new int[(upperboundx - lowerboundx + 1)*(upperboundy - lowerboundy + 1)][2];
		
		int index = 0;
		for(int y = lowerboundy; y <= upperboundy; y++) {
			for(int x = lowerboundx; x <= upperboundx; x++) {
				int[] position = {x, y};
				positions[index] = position;
				index++;
			}
		}
		
		return positions;
	}
	
	/**
	 * Gets the player inhabiting this world.
	 */
	@Basic
	public Mazub getMazub() {
		return this.player;
	}
	
	/**
	 * Connects a given player with this world, and vice versa.
	 * 
	 * @param player
	 * 			The player which should be associated with the world.
	 * @post	...
	 * 			| (getMazub() == player)
	 * @post	...
	 * 			| new.getMazub() = player
	 * @effect	...
	 * 			| if (getMazub() != null) getMazub().setWorld(null)
	 * 			| if (player != null)
	 * 			| 	then player.setWorld(this)
	 */
	public void setMazub(Mazub player) {
		assert(!(player instanceof Buzam));
		if (getMazub() != null) getMazub().setWorld(null);
		this.player = player;
		if (player != null) player.setWorld(this);
	}
	
	private Mazub player;
	
	/**
	 * Gets the Buzam inhabiting this world.
	 */
	@Basic
	public Mazub getBuzam() {
		return this.buzam;
	}
	
	/**
	 * Connects a given Buzam with this world, and vice versa.
	 * 
	 * @param player
	 * 			The player which should be associated with the world.
	 * @post	...
	 * 			| (getMazub() == player)
	 * @post	...
	 * 			| new.getMazub() = player
	 * @effect	...
	 * 			| if (getMazub() != null) getMazub().setWorld(null)
	 * 			| if (player != null)
	 * 			| 	then player.setWorld(this)
	 */
	public void setBuzam(Buzam buzam) {
		if (getBuzam() != null) getBuzam().setWorld(null);
		this.buzam = buzam;
		if (buzam != null) buzam.setWorld(this);
	}
	
	private Buzam buzam;
	
	/**
	 * Gets the list of sharks in this world.
	 */
	@Basic @Immutable
	public ArrayList<Shark> getSharks() {
		return new ArrayList<Shark>(sharks);
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
	 * 			| sharks.add(shark)
	 * @effect	...
	 * 			| shark.setWorld(this)
	 */
	public void addShark(Shark shark) {
		assert(shark != null);
		assert(!(hasAsShark(shark)));
		
		sharks.add(shark);
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
	 * 			| sharks.remove(shark)
	 * @effect	...
	 * 			| shark.setWorld(null)
	 */
	protected void removeShark(Shark shark) {
		assert(shark != null);
		assert(hasAsShark(shark));
		
		sharks.remove(shark);
		shark.setWorld(null);
	}
	
	private final ArrayList<Shark> sharks = new ArrayList<Shark>();
	
	/**
	 * Gets the list of plants in this world.
	 */
	@Basic @Immutable
	public ArrayList<Plant> getPlants() {
		return new ArrayList<Plant>(plants);
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
	 * 			| plants.add(plant)
	 * @effect	...
	 * 			| plant.setWorld(this)
	 */
	public void addPlant(Plant plant) {
		assert(plant != null);
		assert(!(hasAsPlant(plant)));
		
		plants.add(plant);
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
	 * 			| plants.remove(plant)
	 * @effect	...
	 * 			| plant.setWorld(null)
	 */
	protected void removePlant(Plant plant) {
		assert(plant != null);
		assert(hasAsPlant(plant));
		
		plants.remove(plant);
		plant.setWorld(null);
	}

	private final ArrayList<Plant> plants = new ArrayList<Plant>();
	
	/**
	 * Gets the list of schools in this world.
	 */
	@Basic @Immutable
	public ArrayList<School> getSchools() {
		return new ArrayList<School>(schools);
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
	 * 			| if (getNbOfSchools() < getMaxSchoolsAmount())
	 * 			| 	then schools.add(school)
	 * @effect	...
	 * 			| if (getNbOfSchools() < getMaxSchoolsAmount())
	 * 			| 	then school.setWorld(this)
	 */
	public void addSchool(School school) {
		assert(school != null);
		assert(!(hasAsSchool(school)));
		
		if (getNbOfSchools() < getMaxSchoolsAmount()) {
			schools.add(school);
			school.setWorld(this);
		}
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
	 * Gets the number of schools in this world.
	 * 
	 * @return	...
	 * 			| result = getSchools().size()
	 */
	public int getNbOfSchools() {
		return getSchools().size();
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
	 * 			| schools.remove(school)
	 * @effect	...
	 * 			| school.setWorld(null)
	 */
	protected void removeSchool(School school) {
		assert(school != null);
		assert(hasAsSchool(school));
		
		schools.remove(school);
		school.setWorld(null);
	}
	
	private final ArrayList<School> schools = new ArrayList<School>();
	
	/**
	 * Gets the maximum amount of schools in any world.
	 */
	@Basic @Immutable
	public static int getMaxSchoolsAmount() {
		return max_schools_amount;
	}
	
	private static final int max_schools_amount = 10;
	
	/**
	 * Returns all the slimes currently located in this world..
	 * 
	 * @return	...
	 * 			| slimes = new ArrayList<Slime>()
	 * 			| for(School school : getSchools())
	 * 			| 	slimes.addAll(school.getSlimes())
	 * 			| result = slimes
	 */
	public ArrayList<Slime> getSlimes() {
		ArrayList<Slime> slimes = new ArrayList<Slime>();
		for(School school : getSchools()) {
			slimes.addAll(school.getSlimes());
		}
		return slimes;
	}
	
	/**
	 * Gets the hash of a given x and y position.
	 * 
	 * @param x
	 * 			The X-position which should be converted to a hash.
	 * @param y
	 * 			The Y-position which should be converted to a hash.
	 * @pre		...
	 * 			| x >= 0
	 * @pre		...
	 * 			| y >= 0
	 * @pre		...
	 * 			| x < getWorldWidth()/getTileSize()
	 * @pre		...
	 * 			| y < getWorldHeight()/getTileSize()
	 * @return	...
	 * 			| result = ((int) Math.pow(10, getHashDigitsAmount()))*x + y
	 */
	public int getHash(int x, int y) {
		assert(x >= 0);
		assert(y >= 0);
		assert(x < getWorldWidth()/getTileSize());
		assert(y < getWorldHeight()/getTileSize());
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
	 * 			| if(x >= getWorldWidth() || y >= getWorldHeight())
	 *			|		result = Feature.air
	 * @return	...
	 * 			| feature = getFeatures().get(getHash(getTilePos(x), getTilePos(y)))
	 * 			| if (feature != null) 
	 *			|		result = feature
	 *			| else
	 *			|		result = Feature.air
	 */
	public Feature getFeature(int x, int y) {
		if(x >= getWorldWidth() || y >= getWorldHeight())
			return Feature.air;
		Feature feature = getFeatures().get(getHash(getTilePos(x), getTilePos(y)));
		if (feature != null) {
			return feature;
		}
		return Feature.air;
	}
	
	/**
	 * Sets the feature of the tile with the given position to the feature that fits id.
	 * 
	 * @param x
	 * 			The x-position of the tile, in tiles.
	 * @param y
	 * 			The y-position of the tile, in tiles.
	 * @param id
	 * 			The id of the feature this tile will get.
	 * @effect	...
	 * 			| Feature feature = null;
	 *			| switch (id) 
	 *			|		case 1: feature = Feature.ground  break
	 *			|		case 2: feature = Feature.water  break
	 *			|		case 3: feature = Feature.magma  break
	 *			|		default:
	 *			|			getFeatures().remove(getHash(x, y))
	 *			|			return
	 *			| getFeatures().put(getHash(x, y), feature)
	 */
	public void setFeature(int x, int y, int id) {
		Feature feature = null;
		switch (id) {
			case 1: feature = Feature.ground; break;
			case 2: feature = Feature.water; break;
			case 3: feature = Feature.magma; break;
			default:
				getFeatures().remove(getHash(x, y));
				return;
		}
		
		getFeatures().put(getHash(x, y), feature);
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
	 * Advances this world with a given time.
	 * 
	 * @param dt
	 * 			The time to advance this world with.
	 * @effect	...
	 * 			| getMazub().advanceTime(dt)
	 * @effect	...
	 * 			| for(Shark shark : getSharks()) 
	 *			|		shark.advanceTime(dt)
	 * @effect	...
	 * 			| for(School school : getSchools()) 
	 *			|		for(Slime slime : school.getSlimes()) 
	 *			|			slime.advanceTime(dt)
	 * @effect	...
	 * 			| for(Plant plant : getPlants())
	 *			|		plant.advanceTime(dt)
	 * 			
	 * @throws IllegalArgumentException
	 * 			...
	 * 			| !GameObject.isValidDt(dt)
	 */
	public void advanceTime(double dt) throws IllegalArgumentException {
		if (!GameObject.isValidDt(dt)) {
			throw new IllegalArgumentException();
		}
		
		if (getMazub() == null) return;
		
		getMazub().advanceTime(dt);
		if (getBuzam() != null) getBuzam().advanceTime(dt);
		for(Shark shark : getSharks()) {
			shark.advanceTime(dt);
		}
		for(School school : getSchools()) {
			for(Slime slime : school.getSlimes()) {
				slime.advanceTime(dt);
			}
		}
		for(Plant plant : getPlants()) {
			plant.advanceTime(dt);
		}
	}
	
	/**
	 * Checks the collisions of a given object with all other objects and features during an instant.
	 * 
	 * @param object
	 * 			The object whose collisions with other objects and features should be checked.
	 * @param custom_width
	 * 			An optional custom widht of object. If 0, object.getWidth() will be used.
	 * @param custom_height
	 * 			An optional custom height of object. If 0, object.getHeight() will be used.
	 * @return	...
	 * 			| List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>()		
	 *			|for(int i = 0; i < 8; i++) 
	 *			|		List<List<Object>> inner_collisions = new ArrayList<List<Object>>()
	 *			|		for(int j = 0; j < 2; j++) 
	 *			|			inner_collisions.add(new ArrayList<Object>())
	 *			|		collisions.add(inner_collisions)
	 * 			| if (getMazub() != null)
	 * 			| 		collisionDetectObject(object, getMazub(), custom_width, custom_height, collisions)
	 * 			| if (!(object instanceof Plant)) 
	 * 			|		for(Shark shark : getSharks())
	 * 			|			collisionDetectObject(object, shark, custom_width, custom_height, collisions)
	 * 			|		for(Slime slime : getSlimes())
	 * 			|			collisionDetectObject(object, slime, custom_width, custom_height, collisions)
	 * 			| 
	 * 			| if ((object instanceof Mazub) || (object instanceof Plant))
	 * 			|		for(Plant plant : getPlants())
	 * 			|			collisionDetectObject(object, plant, custom_width, custom_height, collisions)
	 *			|
	 *			| int width = object.getWidth()
	 *			| int height = object.getHeight()
	 *			| if (custom_width != 0) width = custom_width
	 *			| if (custom_height != 0) height = custom_height;
	 *			| int[][] tile_pos = getTilePositionsIn((int) object.getX(), (int) object.getY(),
	 *			| 	(int) object.getX() + width, (int) object.getY() + height)
	 *			| for(int[] tile : tile_pos) 
	 *			|		collisionDetectFeature(object, tile[0]*getTileSize(), tile[1]*getTileSize(), custom_width, custom_height, collisions)
	 * 			| result = collisions
	 */
	public List<List<List<Object>>> collisionDetect(GameObject object, int custom_width, int custom_height) {
		
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		
		// Checks collisions with the player, sharks, slimes and plants
		if (getMazub() != null)	collisionDetectObject(object, getMazub(), custom_width, custom_height, collisions);
		if (getBuzam() != null)	collisionDetectObject(object, getBuzam(), custom_width, custom_height, collisions);
		if (!(object instanceof Plant)) {
			for(Shark shark : getSharks()) {
				collisionDetectObject(object, shark, custom_width, custom_height, collisions);
			}
			for(Slime slime : getSlimes()) {
				collisionDetectObject(object, slime, custom_width, custom_height, collisions);
			}
		}
		if ((object instanceof Mazub) || (object instanceof Plant)) {
			for(Plant plant : getPlants()) {
				collisionDetectObject(object, plant, custom_width, custom_height, collisions);
			}
		}
		
		// Checks collisions with features
		int width = object.getWidth();
		int height = object.getHeight();
		if (custom_width != 0) width = custom_width;
		if (custom_height != 0) height = custom_height;
		int[][] tile_pos = getTilePositionsIn((int) object.getX(), (int) object.getY(),
				(int) object.getX() + width, (int) object.getY() + height);
		for(int[] tile : tile_pos) {
			collisionDetectFeature(object, tile[0]*getTileSize(), tile[1]*getTileSize(), custom_width, custom_height, collisions);
		}
		
		return collisions; 
	}
	
	/**
	 * Checks the collisions  between two given objects.
	 * 
	 * @param object1
	 * 			The first object whose collisions with the second object should be checked.
	 * @param object2
	 * 			The second object whose collisions with the first object should be checked.
	 * @param custom_width
	 * 			An optional custom width of object1. If 0, objec1.getWidth() will be used.
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
	 * 			| collisionDetectBasic(object1, x1, y1, x2, y2, object2, 0, custom width, custom_height, collision_objects)
	 */
	@Model
	private void collisionDetectObject(GameObject object1, GameObject object2, int custom_width, int custom_height, 
			List<List<List<Object>>> collision_objects)  {
		
		if (object1 == object2) {
			return;
		}
		
		// Defining temporary variables
		int x1 = (int) object2.getX();
		int y1 = (int) object2.getY();
		int x2 = x1 + object2.getWidth();
		int y2 = y1 + object2.getHeight();
		
		collisionDetectBasic(object1, x1, y1, x2, y2, object2, 0, custom_width, custom_height, collision_objects);
		
	}
	
	/**
	 * Checks the collisions in between a given object and a given feature.
	 * 
	 * @param object
	 * 			The object whose collisions with the feature should be checked.
	 * @param x
	 * 			The x-position of the feature whose collisions with the object should be checked.
	 * @param y
	 * 			The y-position of the feature whose collisions with the object should be checked.
	 * @param custom_width
	 * 			An optional custom width of object. If 0, object.getWidth() will be used.
	 * @param custom_height
	 * 			An optional custom height of object. If 0, object.getHeight() will be used.
	 * @param collision_objects
	 * 			The 3-dimensional arraylist which stores the collisions of object.
	 * @effect	...
	 * 			| feature = getFeature(x, y)
	 * 			| collisionDetectBasic(object, x, y, x + getTileSize(), y + getTileSize(), feature, 1, custom width, 
	 * 			|			custom_height, collision_objects)
	 */
	@Model
	private void collisionDetectFeature(GameObject object, int x, int y, int custom_width, int custom_height, 
			List<List<List<Object>>> collision_objects)  {
		
		Feature feature = getFeature(x, y);
		
		collisionDetectBasic(object, x, y, x + getTileSize(), y + getTileSize(), feature, 1,
							custom_width, custom_height, collision_objects);
		
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
	 * @param custom_width
	 * 			An optional custom width of object1. If 0, object1.getWidth() will be used.
	 * @param custom_height
	 * 			An optional custom height of object1. If 0, object1.getHeight() will be used.
	 * @param collision_objects
	 * 			The 3-dimensional arraylist which stores the collisions of object.
	 * @effect	...
	 * 			| mx1 = (int) object1.getX()
	 * 			| my1 = (int) object1.getY()
	 * 			| mx2 = mx1
	 * 			| if (custom_width == 0)
	 * 			|	then mx2 += object1.getWidth()
	 * 			| else
	 * 			|	then mx2 += custom_width
	 * 			| my2 = my1
	 * 			| if (custom_height == 0) 
	 *			|	then my2 += object1.getHeight();
	 *			| else 
	 *			|	then my2 += custom_height;
	 * 			| 
	 * 			| overlaps = {sx2 - mx1, my2 - sy1,mx2 - sx1, sy2 - my1}
	 * 			| for(int i = 0; i < overlaps.length; i++) 
	 *			|		int j = i - 1
	 *			|		if(j < 0)
	 *			|			then j = 3
	 *			|		if (overlaps[i] == 1) {
	 *			|			if (!(overlaps[(i + 1)%4] == 1) && !(overlaps[j] == 1)) collisionAdd(collision_objects, i, index, object2);
	 *			|			else if(overlaps[j] == 1)
	 *			|				collisionAdd(collision_objects, j + 4, index, object2);
	 *			|			else if(overlaps[(i + 1)%4] == 1)
	 *			|				collisionAdd(collision_objects, i + 4, index, object2);
	 *			|			has_central_collision = false;
	 *			|		}
	 * 			|
	 * 			| if (has_central_collision) {
	 *			|	then collisionAdd(collision_objects, 0, index, object2);
	 */
	@Model
	private void collisionDetectBasic(GameObject object1, int sx1, int sy1, int sx2, int sy2, Object object2, int index,
			 							int custom_width, int custom_height, List<List<List<Object>>> collision_objects) {
		
		// Defining temporary variables
		int mx1 = (int) object1.getX();
		int my1 = (int) object1.getY();
		int mx2 = mx1;
		if (custom_width == 0) {
			mx2 += object1.getWidth();
		} else {
			mx2 += custom_width;
		}
		int my2 = my1;
		if (custom_height == 0) {
			my2 += object1.getHeight();
		} else {
			my2 += custom_height;
		}
		
		// Collisions: left, up, right, down, upperleft, upperright, bottomright, bottomleft
		// Central collisions can be anywhere
		int[] overlaps = {sx2 - mx1, my2 - sy1, mx2 - sx1, sy2 - my1};
		for(int i = 0; i < 4; i++) {
			if (overlaps[i] < 1) {
				return;
			}
		}
		// Direction-dependent collisions
		boolean has_central_collision = true;
		for(int i = 0; i < overlaps.length; i++) {
			int j = i - 1;
			if(j < 0)
				j = 3;
			if (overlaps[i] == 1) {
				if (!(overlaps[(i + 1)%4] == 1) && !(overlaps[j] == 1)) collisionAdd(collision_objects, i, index, object2);
				else if(overlaps[j] == 1)
					collisionAdd(collision_objects, j + 4, index, object2);
				else if(overlaps[(i + 1)%4] == 1)
					collisionAdd(collision_objects, i + 4, index, object2);
				
				has_central_collision = false;
			}
		}

		// Direction-independent collisions
		if (has_central_collision) {
			collisionAdd(collision_objects, 0, index, object2);
		}
		
	}
	
	/**
	 * Adds an object to a give 3 dimensional list at a given place.
	 * 
	 * @param list
	 * 			The 3 dimensional list object should be added to.
	 * @param i
	 * 			The first index of the place object should be added to.
	 * @param j
	 * 			The second indes of the place object should be added to.
	 * @param object
	 * 			The object to add to list.
	 * @effect	...
	 * 			| if (!list.get(i).get(j).contains(object)) 
	 *			|		list.get(i).get(j).add(object)
	 */
	private static void collisionAdd(List<List<List<Object>>> list, int i, int j, Object object) {
		if (!list.get(i).get(j).contains(object)) {
			list.get(i).get(j).add(object);
		}
	}
	
}
