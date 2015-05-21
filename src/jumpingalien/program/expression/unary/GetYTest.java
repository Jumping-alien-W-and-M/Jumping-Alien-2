package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Self;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import org.junit.Test;

public class GetYTest {

	SourceLocation srceloc = new SourceLocation(5, 6);
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@Test
	public void TestGetValue() {
		Expression self = new Self(srceloc);
		Shark shark = new Shark(0, 0, sprites, null);
		Expression getY = new GetY(self , srceloc);
		assertEquals((double) getY.getValue(shark), shark.getY(), Util.DEFAULT_EPSILON);
	}

}
