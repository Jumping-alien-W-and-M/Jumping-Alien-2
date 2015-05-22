package jumpingalien.program.statement;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.model.World;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DoubleConstant;

import org.junit.Before;
import org.junit.Test;

public class SequenceTest {

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
		
		ArrayList<Statement> statements = new ArrayList<Statement>();
		statements.add(new StartDuck(source));
		statements.add(new StopDuck(source));
		statements.add(new StartDuck(source));
		statements.add(new StartDuck(source));
		statements.add(new StopDuck(source));
		statements.add(new StopDuck(source));
		statements.add(new StopDuck(source));
		statements.add(new StartDuck(source));
		statements.add(new StopDuck(source));
		Sequence sequence = new Sequence(statements, source);
		
		assertEquals(statements, sequence.getStatements());
		assertEquals(0, sequence.getCurrentStatement());
	}

	@Test
	public void BasicExecuteTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		ArrayList<Statement> statements = new ArrayList<Statement>();
		statements.add(new StartDuck(source));
		statements.add(new StopDuck(source));
		statements.add(new StartDuck(source));
		statements.add(new StartDuck(source));
		statements.add(new StopDuck(source));
		statements.add(new StopDuck(source));
		statements.add(new StopDuck(source));
		statements.add(new StartDuck(source));
		statements.add(new StopDuck(source));
		Sequence sequence = new Sequence(statements, source);
		
		Program program = new Program(new Wait(new DoubleConstant(2.0, source), source), empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);
		
		for(int i = 0; i < 5; i++) {
			assertEquals(0, sequence.getCurrentStatement());
			assertEquals(false, buzam.getDucking());
			
			program.setStatementsLeft(0);
			assertEquals(ExecutionState.NOTDONE, sequence.execute(buzam));
			assertEquals(0, program.getStatementsLeft());
			assertEquals(0, sequence.getCurrentStatement());
			assertEquals(false, buzam.getDucking());
			
			program.setStatementsLeft(1);
			assertEquals(ExecutionState.NOTDONE, sequence.execute(buzam));
			assertEquals(0, program.getStatementsLeft());
			assertEquals(1, sequence.getCurrentStatement());
			assertEquals(true, buzam.getDucking());
			
			program.setStatementsLeft(1);
			assertEquals(ExecutionState.NOTDONE, sequence.execute(buzam));
			assertEquals(0, program.getStatementsLeft());
			assertEquals(2, sequence.getCurrentStatement());
			assertEquals(false, buzam.getDucking());
			
			program.setStatementsLeft(1);
			assertEquals(ExecutionState.NOTDONE, sequence.execute(buzam));
			assertEquals(0, program.getStatementsLeft());
			assertEquals(3, sequence.getCurrentStatement());
			assertEquals(true, buzam.getDucking());
			
			program.setStatementsLeft(2);
			assertEquals(ExecutionState.NOTDONE, sequence.execute(buzam));
			assertEquals(0, program.getStatementsLeft());
			assertEquals(5, sequence.getCurrentStatement());
			assertEquals(false, buzam.getDucking());
			
			program.setStatementsLeft(3);
			assertEquals(ExecutionState.NOTDONE, sequence.execute(buzam));
			assertEquals(0, program.getStatementsLeft());
			assertEquals(8, sequence.getCurrentStatement());
			assertEquals(true, buzam.getDucking());
			
			program.setStatementsLeft(1);
			assertEquals(ExecutionState.DONE, sequence.execute(buzam));
			assertEquals(0, program.getStatementsLeft());
			assertEquals(0, sequence.getCurrentStatement());
			assertEquals(false, buzam.getDucking());
		}
	}

	@Test
	public void ExecuteBreakTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		ArrayList<Statement> statements = new ArrayList<Statement>();
		statements.add(new StartDuck(source));
		statements.add(new StopDuck(source));
		statements.add(new StartDuck(source));
		statements.add(new Break(source));
		Sequence sequence = new Sequence(statements, source);
		
		Program program = new Program(new Wait(new DoubleConstant(2.0, source), source), empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);
		
		for(int i = 0; i < 5; i++) {
			assertEquals(0, sequence.getCurrentStatement());
			assertEquals(false, buzam.getDucking());
			
			program.setStatementsLeft(0);
			assertEquals(ExecutionState.NOTDONE, sequence.execute(buzam));
			assertEquals(0, program.getStatementsLeft());
			assertEquals(0, sequence.getCurrentStatement());
			assertEquals(false, buzam.getDucking());
			
			program.setStatementsLeft(1);
			assertEquals(ExecutionState.NOTDONE, sequence.execute(buzam));
			assertEquals(0, program.getStatementsLeft());
			assertEquals(1, sequence.getCurrentStatement());
			assertEquals(true, buzam.getDucking());
			
			program.setStatementsLeft(1);
			assertEquals(ExecutionState.NOTDONE, sequence.execute(buzam));
			assertEquals(0, program.getStatementsLeft());
			assertEquals(2, sequence.getCurrentStatement());
			assertEquals(false, buzam.getDucking());
			
			program.setStatementsLeft(2);
			assertEquals(ExecutionState.BREAK, sequence.execute(buzam));
			assertEquals(0, program.getStatementsLeft());
			assertEquals(0, sequence.getCurrentStatement());
			assertEquals(true, buzam.getDucking());
			
			buzam.endDuck();
		}
	}

}
