package jumpingalien.program.expression.unary;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.Expression;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Test;

public class RandomTest {

	SourceLocation srceloc = new SourceLocation(5, 6);
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@Test
	public void TestgetValue(){
		Expression number = new DoubleConstant(5, srceloc);
		Expression random = new Random(number, srceloc);
		
		Shark shark = new Shark(0, 0, sprites, null);
		
		assert((double) random.getValue(shark) < 5  + Util.DEFAULT_EPSILON);
		assert((double) random.getValue(shark) > -5 - Util.DEFAULT_EPSILON);
	}

}
