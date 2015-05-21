package jumpingalien.program.statement;

import static org.junit.Assert.*;

import java.util.HashMap;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.model.World;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class StartDuckTest {

	@Before
	public void setUp() throws Exception {
		world = new World(10, 100, 100, 1000, 1000, 99, 99);
		for(int x = 0; x < 100; x++) {
			world.setFeature(x, 0, 1);
		}
		
		world.setMazub(new Mazub(900, 100, JumpingAlienSprites.ALIEN_SPRITESET, null));
	}
	
	private World world;
	private Buzam buzam;
	private static final HashMap<String, Type> empty_variables = new HashMap<String, Type>();
	
	@Test
	public void ExecuteTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		StartJump startjump = new StartJump(source);
		
		Program program = new Program(new Wait(new DoubleConstant(2.0, source), source), empty_variables);
		Mazub mazub = new Mazub(900, 100, JumpingAlienSprites.ALIEN_SPRITESET, null);
		world.setMazub(mazub);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);
		
		for(int i = 0; i < 30; i++) world.advanceTime(0.1);
		
		assertEquals(0.0, buzam.getVy(), Util.DEFAULT_EPSILON);
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, startjump.execute(buzam));
		assertEquals(8.0, buzam.getVy(), Util.DEFAULT_EPSILON);
		
		for(int i = 0; i < 10; i++) world.advanceTime(0.1);
		assertEquals(-2.0, buzam.getVy(), Util.DEFAULT_EPSILON);
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, startjump.execute(buzam));
		assertEquals(-2.0, buzam.getVy(), Util.DEFAULT_EPSILON);
		
		for(int i = 0; i < 10; i++) world.advanceTime(0.1);
		assertEquals(0.0, buzam.getVy(), Util.DEFAULT_EPSILON);
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, startjump.execute(buzam));
		assertEquals(8.0, buzam.getVy(), Util.DEFAULT_EPSILON);
		
	}

}
