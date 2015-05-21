package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.*;
import jumpingalien.util.Sprite;

import org.junit.Test;

public class GetHeightTest {
	
	SourceLocation srceloc = new SourceLocation(5, 6);
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@Test
	public void TestGetValue() {
		Expression self = new Self(srceloc);
		Shark shark = new Shark(0, 0, sprites, null);
		Expression getHeight = new GetHeight(self , srceloc);
		assertEquals((int) (double) getHeight.getValue(shark), shark.getHeight());
	}
}
