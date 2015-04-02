package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class Slime extends GameObject {
	
	/**
	 * Gets the school this slime is a part of.
	 */
	@Basic
	public School getSchool() {
		return this.school;
	}
	
	/**
	 * Sets the school of this slime, optionally removing its link with its old school.
	 * 
	 * @param school
	 * 			The new school this slime should be linked to.
	 * @pre		The given school is an existing school.
	 * 			| (school != null)
	 * @post	The slime's old school shall no longer contain this slime, if this slime had an old school.
	 * 			| if (getSchool() != null)
	 * 			|	then (new.(this.getSchool()).hasAsSlime(this)) == false)
	 * @post	The slime's new school is the given school.
	 * 			| new.getSchool() == school
	 * @post	The slime's new school contains this slime.
	 * 			| ((new.getSchool()).hasAsSlime(this) == true)
	 */
	public void setSchool(School school) {
		assert(school != null);
		if (getSchool() != null) getSchool().removeSlime(this);
		this.school = school;
		getSchool().addSlime(this);
	}
	
	/**
	 * Removes this slime from the school it was part of.
	 * 
	 * @pre		This slime is part of a school.
	 * 			| (getSchool() != null)
	 * @post	The slime will not be part of any school anymore.
	 * 			| (new.getSchool() == null)
	 * @post	The slime's old school will not contain this slime anymore.
	 * 			| (new (getSchool().hasAsSlime(this)) == false)
	 */
	public void removeSchool() {
		assert(getSchool() != null);
		getSchool().removeSlime(this);
		this.school = null;
	}
	
	private School school;
	
}
