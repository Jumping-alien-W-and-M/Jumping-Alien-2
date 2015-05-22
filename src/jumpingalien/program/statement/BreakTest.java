package jumpingalien.program.statement;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.util.Sprite;

import org.junit.Test;

public class BreakTest {

	SourceLocation srceloc = new SourceLocation(5, 6);
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@Test
	public void TestExecute() {
		Break assignement = new Break(srceloc);
		Shark shark = new Shark(0, 0, sprites, null);
		assertEquals(ExecutionState.BREAK, assignement.execute(shark));
	}

}
