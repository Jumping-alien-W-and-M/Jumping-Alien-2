package jumpingalien.program.expression.binary;

import static org.junit.Assert.*;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.program.expression.DirectionConstant;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Self;

import org.junit.Before;
import org.junit.Test;

public class IsMovingTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getValueValueTest() {

		World world = new World(10, 100, 100, 1000, 1000, 0, 0);
		Mazub mazub = new Mazub(900, 100, JumpingAlienSprites.ALIEN_SPRITESET, null);
		world.setMazub(mazub);
		
		for(int x = 0; x < 100; x++) {
			world.setFeature(x, 0, 1);
		}
		
		Buzam buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, null);
		world.setBuzam(buzam);
		
		for(int i = 0; i < 20; i++) {
			world.advanceTime(0.15);
		}
		checkMovement(false, false, false, false, buzam);
		
		buzam.startMove("left");
		world.advanceTime(0.15);
		checkMovement(true, false, false, false, buzam);
		buzam.startJump();
		world.advanceTime(0.15);
		checkMovement(true, false, true, false, buzam);
		buzam.endMove("left");
		world.advanceTime(0.15);
		checkMovement(false, false, true, false, buzam);
		
		buzam.endJump();
		world.advanceTime(0.15);
		checkMovement(false, false, false, true, buzam);
		buzam.startMove("right");
		world.advanceTime(0.15);
		checkMovement(false, true, false, true, buzam);
		buzam.endMove("right");
		world.advanceTime(0.15);
		checkMovement(false, false, false, true, buzam);
		
	}

	private void checkMovement(boolean left, boolean right, boolean up, boolean down, Buzam buzam) {
		
		Expression self = new Self(new SourceLocation(5, 6));
		Expression isMovingLeft = new IsMoving(self, new DirectionConstant(Direction.LEFT, new SourceLocation(5, 6)),
												new SourceLocation(5, 6));
		Expression isMovingRight = new IsMoving(self, new DirectionConstant(Direction.RIGHT, new SourceLocation(5, 6)),
												new SourceLocation(5, 6));
		Expression isMovingUp = new IsMoving(self, new DirectionConstant(Direction.UP, new SourceLocation(5, 6)),
												new SourceLocation(5, 6));
		Expression isMovingDown = new IsMoving(self, new DirectionConstant(Direction.DOWN, new SourceLocation(5, 6)),
				new SourceLocation(5, 6));
		
		assertEquals(left, isMovingLeft.getValue(buzam));
		assertEquals(right, isMovingRight.getValue(buzam));
		assertEquals(up, isMovingUp.getValue(buzam));
		assertEquals(down, isMovingDown.getValue(buzam));
		
	}

}
