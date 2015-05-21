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

public class StopRunTest {

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
		
		Expression direction = new DirectionConstant(Direction.LEFT, source);
		StopRun run = new StopRun(direction, source);
		
		assertEquals(direction, run.getDirection());
		
	}
	
	@Test
	public void ConstructorTypingTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Expression direction = new DoubleConstant(0.5, source);
		try {
			new StopRun(direction, source);
		} catch(IllegalArgumentException exc) {
			return;
		}
		fail();
	}
	
	@Test
	public void ExecuteLeftTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		StartRun start_run = new StartRun(new DirectionConstant(Direction.LEFT, source), source);
		StopRun stop_run = new StopRun(new DirectionConstant(Direction.LEFT, source), source);
		
		Program program = new Program(start_run, empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, start_run.execute(buzam));
		assertEquals(-buzam.getVxi(), buzam.getVx(), Util.DEFAULT_EPSILON);
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, stop_run.execute(buzam));
		assertEquals(0, buzam.getVx(), Util.DEFAULT_EPSILON);
		
	}
	
	@Test
	public void ExecuteRightTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		StartRun start_run = new StartRun(new DirectionConstant(Direction.RIGHT, source), source);
		StopRun stop_run = new StopRun(new DirectionConstant(Direction.RIGHT, source), source);
		
		Program program = new Program(start_run, empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, start_run.execute(buzam));
		assertEquals(buzam.getVxi(), buzam.getVx(), Util.DEFAULT_EPSILON);
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, stop_run.execute(buzam));
		assertEquals(0, buzam.getVx(), Util.DEFAULT_EPSILON);
		
	}
	
	@Test
	public void ExecuteUpTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		StartRun start_run = new StartRun(new DirectionConstant(Direction.LEFT, source), source);
		StopRun stop_run = new StopRun(new DirectionConstant(Direction.UP, source), source);
		
		Program program = new Program(start_run, empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);

		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, start_run.execute(buzam));
		assertEquals(-buzam.getVxi(), buzam.getVx(), Util.DEFAULT_EPSILON);
		program.setStatementsLeft(5);
		assertEquals(ExecutionState.DONE, stop_run.execute(buzam));
		assertEquals(-buzam.getVxi(), buzam.getVx(), Util.DEFAULT_EPSILON);
		assertEquals(0, program.getStatementsLeft());
		assertEquals(true, program.getRunTimeError());
		
	}
	
	@Test
	public void ExecuteFalseLeftTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		StartRun start_run = new StartRun(new DirectionConstant(Direction.RIGHT, source), source);
		StopRun stop_run = new StopRun(new DirectionConstant(Direction.LEFT, source), source);
		
		Program program = new Program(start_run, empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);

		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, start_run.execute(buzam));
		assertEquals(buzam.getVxi(), buzam.getVx(), Util.DEFAULT_EPSILON);
		program.setStatementsLeft(5);
		assertEquals(ExecutionState.DONE, stop_run.execute(buzam));
		assertEquals(buzam.getVxi(), buzam.getVx(), Util.DEFAULT_EPSILON);
		assertEquals(0, program.getStatementsLeft());
		assertEquals(true, program.getRunTimeError());
		
	}
	
	@Test
	public void ExecuteFalseRightTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		StartRun start_run = new StartRun(new DirectionConstant(Direction.LEFT, source), source);
		StopRun stop_run = new StopRun(new DirectionConstant(Direction.RIGHT, source), source);
		
		Program program = new Program(start_run, empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);

		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, start_run.execute(buzam));
		assertEquals(-buzam.getVxi(), buzam.getVx(), Util.DEFAULT_EPSILON);
		program.setStatementsLeft(5);
		assertEquals(ExecutionState.DONE, stop_run.execute(buzam));
		assertEquals(-buzam.getVxi(), buzam.getVx(), Util.DEFAULT_EPSILON);
		assertEquals(0, program.getStatementsLeft());
		assertEquals(true, program.getRunTimeError());
		
	}
	
	@Test
	public void ExecuteDownTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		StartRun start_run = new StartRun(new DirectionConstant(Direction.RIGHT, source), source);
		StopRun stop_run = new StopRun(new DirectionConstant(Direction.DOWN, source), source);
		
		Program program = new Program(start_run, empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);

		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, start_run.execute(buzam));
		assertEquals(buzam.getVxi(), buzam.getVx(), Util.DEFAULT_EPSILON);
		program.setStatementsLeft(5);
		assertEquals(ExecutionState.DONE, stop_run.execute(buzam));
		assertEquals(buzam.getVxi(), buzam.getVx(), Util.DEFAULT_EPSILON);
		assertEquals(0, program.getStatementsLeft());
		assertEquals(true, program.getRunTimeError());
		
	}

}
