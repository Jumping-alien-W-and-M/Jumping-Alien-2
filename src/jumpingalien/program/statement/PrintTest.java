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

import org.junit.Before;
import org.junit.Test;

public class PrintTest {

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
		
		Print print = new Print(new DirectionConstant(Direction.LEFT, source), source);
		
		assertEquals(ExecutionState.DONE, print.execute(buzam));
	}

	@Test
	public void ExecuteTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		DirectionConstant direction = new DirectionConstant(Direction.LEFT, source);
		Print print = new Print(direction, source);
		
		Program program = new Program(new Wait(new DoubleConstant(2.0, source), source), empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);
		
		program.setStatementsLeft(0);
		assertEquals(direction, print.getValue());
	}

}
