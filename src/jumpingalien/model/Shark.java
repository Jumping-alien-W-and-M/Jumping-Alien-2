package jumpingalien.model;

import java.util.ArrayList;
import java.util.List;

import jumpingalien.util.Sprite;

public class Shark extends Enemy {

	public Shark(double x, double y, Sprite[] images) {
		super(x, y, images, 1.5, 0, 1, 4);
		setHitpoints(100);

		setVxmax(4);
	}
	
	/**
	 * Gets the time this shark is in the air.
	 */
	public double getTimeInAir(){
		return this.time_in_air;
	}
	
	/**
	 * Sets the time this shark is in the air.
	 * 
	 * @param time
	 * 			The new time this shark is in the air.
	 * @post	The time this shark is in the air will be equal to time.
	 * 			| new.getTimeInAir() = time 
	 */
	private void setTimeInAir(double time){
		this.time_in_air = time;
	}
	
	private double time_in_air;
	
	
	

}
