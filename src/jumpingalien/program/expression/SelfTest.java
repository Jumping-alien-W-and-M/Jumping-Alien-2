package jumpingalien.program.expression;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

import org.junit.Before;
import org.junit.Test;

public class SelfTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void BasicConstructorTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Self self_constant = new Self(source);
		assertEquals(null, self_constant.getValue(null));
		assertEquals(Type.OBJECT, self_constant.getType());
		Mazub mazub = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, null);
		assertEquals(mazub, self_constant.getValue(mazub));
		assertEquals(Type.OBJECT, self_constant.getType());
	}

}
