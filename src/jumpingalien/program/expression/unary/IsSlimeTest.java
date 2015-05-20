package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Self;
import jumpingalien.util.Sprite;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IsSlimeTest {


	SourceLocation srceloc = new SourceLocation(5, 6);
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@Test
	public void TestGetValueSlime() {		
		Expression self = new Self(srceloc);	
		Expression isSlime = new IsSlime(self , srceloc);
		
		Slime slime = new Slime(0, 0, sprites, new School(), null);

		assert((boolean) isSlime.getValue(slime));
	}
	
	@Test
	public void TestGetValueNotSlime() {		
		Expression self = new Self(srceloc);	
		Expression isSlime = new IsSlime(self , srceloc);
		
		Plant plant = new Plant(0, 0, sprites, null);

		assert(! (boolean) isSlime.getValue(plant));
	}

}
