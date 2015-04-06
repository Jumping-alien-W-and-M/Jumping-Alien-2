package jumpingalien.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.Sprite;

public class Shark extends Enemy {

	public Shark(double x, double y, Sprite[] images) {
		super(x, y, images, 1.5, 0, 1, 4);
		setHitpoints(100);

		setVxmax(4);
	}
	
	/**
	 * Gets the number of periods this shark has not jumped.
	 */
	@Basic
	public int getNonJumpingPeriods() {
		return non_jumping_periods;
	}

	/**
	 * Sets the number of periods this shark has not jumped.
	 * 
	 * @param nonjumpingperiods
	 * 			the new number of periods this shark has not jumped.
	 * @pre		...
	 * 			| nonjumpinperiods >= 0
	 * @post	...
	 * 			| new.getNonJumpingPeriods() = nonjumpinperiods
	 */
	public void setNonJumpingPeriods(int nonjumpingperiods) {
		this.non_jumping_periods = non_jumping_periods;
	}

	private int non_jumping_periods;
	
	@Override
	public void advanceTime(double time){
		
	}

	@Override
	protected void performRandomAction() {
		performRandomHorizontalAction();
		
		
	}
	
	
	protected void performRandomHorizontalAction(){
		setVx(0);
		Random rand = new Random();
		if (rand.nextInt(2) == 0) 
			setAx(getAxi());
		else 
			setAx(-getAxi());	
	}
	
	protected void performRandomVerticalAction(){
		setVy(0);
		if(getNonJumpingPeriods() > 1)
			setAy(0);
		Random rand = new Random();
		int nextaction;
		if(getNonJumpingPeriods() > 4 && canJump())
			nextaction = rand.nextInt(3);
		else
			nextaction = rand.nextInt(2);
		
		if(nextaction == 3){
			startJump();
		}						
		else if( (nextaction  == 0) && canDiveOrRise()){
			startRise();
		}
		else if(canDiveOrRise()){
			startDive();
		}		
	}
	
	private boolean canDiveOrRise(){
		ArrayList<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0);
		if(! collisions.get(3).get(1).contains(Feature.water) && ! submerged(collisions))
			return true;
		return false;
	}
	
	private boolean submerged(ArrayList<List<List<Object>>> collisions){
		if( collisions.get(1).get(1).contains(Feature.water))
			return true;
		return false;
	}
	
	private void startJump(){
		setVy(2);
		setNonJumpingPeriods(0);
	}
	
	private void startDive(){
		setNonJumpingPeriods(getNonJumpingPeriods() + 1);
		setAy(-0.2);
	}
	
	private void startRise(){
		setNonJumpingPeriods(getNonJumpingPeriods() + 1);
		setAy(0.2);
	}
	

	@Override
	protected void terminate() {
		assert(this.getWorld() != null);
		this.getWorld().removeShark(this);
		this.setWorld(null);		
	}	
	

}
