package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Shark;
import jumpingalien.model.World;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Self;
import jumpingalien.util.Sprite;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SearchObjectTest {

	SourceLocation srceloc = new SourceLocation(5, 6);
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};


	@Test
	public void TestGetValueRightWithObjects() {
		Expression self = new Self(srceloc);
		Shark startshark = new Shark(0, 0, sprites, null);
		Shark firstshark = new Shark(150, 0, sprites, null);
		Shark secondshark = new Shark(300, 0, sprites, null);
		World world = new World(10, 50, 50, 500, 500, 49, 0);
		world.addShark(startshark);
		world.addShark(firstshark);
		world.addShark(secondshark);
		Expression searchObject = new SearchObject(self, srceloc);
		assertEquals(searchObject.getValue(startshark), firstshark);
	}

}
