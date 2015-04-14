package jumpingalien.model;

import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.Basic;

public class School {
	
	/**
	 * Gets the world this school is in.
	 */
	@Basic
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Sets the world this school is in.
	 * 
	 * @param world
	 * 			The world this game object should be in.
	 * @post	...
	 * 			| (getWorld() == world)
	 * @effect	...
	 * 			| for(Slime slime : slimes)
	 * 			|	slime.setWorld(world)
	 */
	public void setWorld(World world) {
		
		this.world = world;
		
		for(Slime slime : slimes) {
			slime.setWorld(world);
		}
	}
	
	private World world;
	
	/**
	 * Gets the list of slimes in this school.
	 */
	@Basic
	public ArrayList<Slime> getSlimes() {
		return new ArrayList<Slime>(slimes);
	}
	
	/**
	 * Adds a given slime to this school's list of slimes.
	 * 
	 * @param slime
	 * 			The slime which should be added.
	 * @pre		...
	 * 			| (slime != null)
	 * @pre		...
	 * 			| (!(hasAsSlime(slime)))
	 * @effect	...
	 * 			| slimes.add(slime)
	 * @effect	...
	 * 			| slime.setSchool(this)
	 * @effect	...
	 * 			| slime.setWorld(this.getWorld())
	 */
	public void addSlime(Slime slime) {
		assert(slime != null);
		assert(!(hasAsSlime(slime)));
		
		slimes.add(slime);
		slime.setSchool(this);
		slime.setWorld(this.getWorld());
	}
	
	/**
	 * Checks whether or not the given slime is part of this school.
	 * 
	 * @param slime
	 * 			The slime whose participation in this school should be checked.
	 * @pre		...
	 * 			| (slime != null)
	 * @return	...
	 * 			| result = getSlimes().contains(slime)
	 */
	public boolean hasAsSlime(Slime slime) {
		assert(slime != null);
		
		return getSlimes().contains(slime);
	}
	
	/**
	 * Gets the amount of slimes in this school.
	 * 
	 * @return	...
	 * 			| result = getSlimes().size()
	 */
	public int getNbOfSlimes() {
		return getSlimes().size();
	}
	
	/**
	 * Removes the given slime from this school list of slimes.
	 * 
	 * @param slime
	 * 			The slime which should be removed.
	 * @pre		...
	 * 			| (slime != null)
	 * @pre		...
	 * 			| (hasAsSlime(slime))
	 * @effect	...
	 * 			| slimes.remove(slime)
	 * @effect	...
	 * 			| slime.setWorld(null)
	 * @effect	...
	 * 			| if (getNbOfSlimes() == 0)
	 * 			| 	then getWorld().removeSchool(this)
	 */
	protected void removeSlime(Slime slime) {
		assert(slime != null);
		assert(hasAsSlime(slime));
		
		slimes.remove(slime);
		slime.setWorld(null);
		slime.setSchool(null);
		
		if (getNbOfSlimes() == 0) {
			getWorld().removeSchool(this);
		}
	}
	
	private ArrayList<Slime> slimes = new ArrayList<Slime>();
	
}
