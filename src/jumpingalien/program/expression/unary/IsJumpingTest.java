package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Self;
import jumpingalien.util.Sprite;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IsJumpingTest {

	SourceLocation srceloc = new SourceLocation(5, 6);
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@Test
	public void TestGetValueIsJumping() {		
		Expression self = new Self(srceloc);	
		Expression isJumping = new IsJumping(self , srceloc);
		
		Shark shark = new Shark(0, 0, sprites, null);
		shark.startJump();

		assert((boolean) isJumping.getValue(shark));
	}
	
	@Test
	public void TestGetValueNotDead() {		
		Expression self = new Self(srceloc);	
		Expression isJumping = new IsJumping(self , srceloc);
		
		Shark shark = new Shark(0, 0, sprites, null);

		assert(! (boolean) isJumping.getValue(shark));
	}

}
