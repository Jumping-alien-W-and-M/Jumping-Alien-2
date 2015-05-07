package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Buzam extends Mazub {
	
	public Buzam(double x, double y, Sprite[] images, Program program) {
		super(x, y, images, program);
		
		setHitpoints(500);
	}
	
}
