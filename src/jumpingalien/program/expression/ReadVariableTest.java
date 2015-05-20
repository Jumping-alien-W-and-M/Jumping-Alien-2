package jumpingalien.program.expression;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Buzam;
import jumpingalien.model.Program;
import jumpingalien.model.World;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.program.Type;

import org.junit.Before;
import org.junit.Test;

public class ReadVariableTest {

	@Before
	public void setUp() throws Exception {
		world = new World(10, 200, 200, 200, 200, 19, 0);
	}
	
	private World world;

	@Test
	public void BasicConstructorAndGetValueTest() {
		
		Map<String, Type> globals = new HashMap<String, Type>();
		globals.put("var1", Type.DIRECTION);
		
		Program program = new Program(null, globals);
		Buzam buzam = new Buzam(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);
		SourceLocation source = new SourceLocation(5, 6);
		
		ReadVariable read = new ReadVariable("var1", Type.DIRECTION, source);
		assertEquals("var1", read.getVariableName());
		assertEquals(Direction.LEFT, read.getValue(buzam));
		assertEquals(Type.DIRECTION, read.getType());
		
		program.setVariableValue("var1", Type.DIRECTION, Direction.DOWN);
		assertEquals(Direction.DOWN, read.getValue(buzam));
	}

}
