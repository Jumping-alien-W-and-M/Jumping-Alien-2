package jumpingalien.model;

import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.Basic;

public class School {
	
	/**
	 * Gets the list of slimes which this school contains.
	 */
	@Basic
	public ArrayList<Slime> getSlimes() {
		return this.slimes;
	}
	
	/**
	 * Checks whether or not the given slime is part of this school.
	 * 
	 * @param slime
	 * 			The slime's whose participation in this school should be checked.
	 * @pre		The given slime should be an existing slime.
	 * 			| (slime != null)
	 * @return	True if this slime is part of this school, false if not.
	 * 			| (getSlimes().contains(slime))
	 */
	public boolean hasAsSlime(Slime slime) {
		assert(slime != null);
		return getSlimes().contains(slime);
	}
	
	/**
	 * Adds a slime to this school.
	 * 
	 * @param slime
	 * 			The slime which should be added.
	 * @pre		The given slime should be an existing slime.
	 * 			| (slime != null)
	 * @pre		The given slime should not be part of this school.
	 * 			| (!hasAsSlime(slime))
	 * @post	The given slime will be part of this school.
	 * 			| (hasAsSlime(slime))
	 */
	public void addSlime(Slime slime) {
		assert(slime != null);
		assert(!hasAsSlime(slime));
		this.slimes.add(slime);
	}
	
	/**
	 * Removes a slime from this school.
	 * 
	 * @param slime
	 * 			The slime which should be removed.
	 * @pre		The given slime should be part of this school.
	 * 			| (hasAsSlime(slime))
	 * @post	The given slime will not be part of this school.
	 * 			| (!hasAsSlime(slime))
	 */
	public void removeSlime(Slime slime) {
		assert(hasAsSlime(slime));
		this.slimes.remove(slime);
	}
	
	private ArrayList<Slime> slimes = new ArrayList<Slime>();
	
}
