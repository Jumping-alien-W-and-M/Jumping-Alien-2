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
		this.non_jumping_periods = nonjumpingperiods;
	}

	private int non_jumping_periods;
	
	@Override
	public void advanceTime(double time){
		super.advanceTime(time);
	}

	@Override
	protected void performRandomAction() {
		performRandomHorizontalAction();
		performRandomVerticalAction();
		
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
			nextaction = rand.nextInt(2);
		else
			nextaction = rand.nextInt(1);
		
		if(nextaction == 1){
			startJump();
		}						
		else if(canDiveOrRise())
			startRiseOrDive();	
	}
	
	private boolean canDiveOrRise(){
		ArrayList<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0);
		if(collisions.get(3).get(1).contains(Feature.water) || submerged(collisions))
			return true;
		return false;
	}
	
	private boolean submerged(ArrayList<List<List<Object>>> collisions){
		if( collisions.get(1).get(1).contains(Feature.water))
			return true;
		return false;
	}
	
	private void startRiseOrDive(){
		Random rand = new Random(); 
		setNonJumpingPeriods(getNonJumpingPeriods() + 1);
		double abs_value_ax = getAxi()*rand.nextDouble();
		if(rand.nextInt(2) == 0)
			setAx(-abs_value_ax);
		else
			setAx(abs_value_ax);
	}
	
	@Override
	public boolean canJump(){
		ArrayList<List<List<Object>>> collisions = getWorld().collisionDetect(this, 0);
		
		if(collisions.get(3).get(1).contains(Feature.ground) || collisions.get(3).get(1).contains(Feature.water)
				|| collisions.get(3).get(1).size() != 0)
			return true;
		
		return false;
	}
	

	@Override
	protected double advanceAy() {
		if(getAy() >= -0.2 && getAy() <= 0.2 && canDiveOrRise())
			return(getAy());
		if(getAy() == -10 && (! canJump() || ! submerged(getWorld().collisionDetect(this,0))))
			return(getAy());
		return 0;			
	}
	private void startJump(){
		setVy(2);
		setNonJumpingPeriods(0);
	}
	
	@Override
	protected double advanceVy(double time){
		if(submerged(getWorld().collisionDetect(this,0)))
			return 0;
		return(super.advanceVy(time));
	}
	
	@Override
	protected void advanceY(double time){
		if(submerged(getWorld().collisionDetect(this,0)))
			setY(getY());
		super.advanceY(time);
	}
	

	@Override
	protected void terminate() {
		assert(this.getWorld() != null);
		this.getWorld().removeShark(this);
		this.setWorld(null);		
	}	
	

}
