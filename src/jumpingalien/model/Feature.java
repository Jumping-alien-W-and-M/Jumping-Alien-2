package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Value;

@Value
public enum Feature {
	
	 air(0), ground(1), water(2), magma(3);
	
	public boolean isPassable(){
		return (this != ground);
	}
	
	/**
	 * Initialize this feature with a given number.
	 * 
	 * @param number
	 * 			The number of this new feature.
	 * @post	The number of this new feature is equal to the given number.
	 * 			| (getNumber() == number)
	 */
	private Feature(int number) {
		this.number = number;
	}
	
	/**
	 * Gets the number of this feature.
	 */
	@Basic @Immutable
	public int getNumber() {
		return this.number;
	}
	
	private final int number;
}
