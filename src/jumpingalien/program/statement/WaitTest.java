package jumpingalien.program.statement;

import static org.junit.Assert.*;

import java.util.HashMap;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.model.World;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DirectionConstant;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.Expression;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class WaitTest {

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
	public void BasicConstructorTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Expression duration = new DoubleConstant(0.5, source);
		Wait wait = new Wait(duration, source);
		
		assertEquals(duration, wait.getDuration());
		assertEquals(0, wait.getDurationLeft(), Util.DEFAULT_EPSILON);
		
	}
	
	@Test
	public void ConstructorTypingTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Expression duration = new DirectionConstant(Direction.LEFT, source);
		try {
			new Wait(duration, source);
		} catch(IllegalArgumentException exc) {
			return;
		}
		fail();
	}
	
	@Test
	public void ExecuteTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Expression duration = new DoubleConstant(0.05, source);
		Wait wait = new Wait(duration, source);
		
		Program program = new Program(wait, empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);
		
		for(int j = 0; j < 5; j++) {
			
			program.setStatementsLeft(0);
			assertEquals(ExecutionState.NOTDONE, wait.execute(buzam));
			assertEquals(0.049, wait.getDurationLeft(), 0.000001);
			
			for(int i = 0; i < 48; i++) {
				program.setStatementsLeft(0);
				assertEquals(ExecutionState.NOTDONE, wait.execute(buzam));
				assertEquals(0.048 - i*0.001, wait.getDurationLeft(), 0.000001);
			}
			
			program.setStatementsLeft(0);
			assertEquals(ExecutionState.DONE, wait.execute(buzam));
			assertEquals(0, wait.getDurationLeft(), 0.000001);
		}
		
	}

}
