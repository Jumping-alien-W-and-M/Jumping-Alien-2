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
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Self;
import jumpingalien.program.expression.binary.LessThan;
import jumpingalien.program.expression.unary.GetX;

import org.junit.Before;
import org.junit.Test;

public class IfTest {

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
		
		Expression condition = new LessThan(new GetX(new Self(source), source),
											new DoubleConstant(200.0, source), source);
		StartDuck startduck = new StartDuck(source);
		If if_statement = new If(condition, startduck, null, source);
		
		assertEquals(condition, if_statement.getCondition());
		assertEquals(startduck, if_statement.getIfBody());
		assertEquals(null, if_statement.getElseBody());
		assertEquals(0, if_statement.getConditionValue());
	}

	@Test
	public void ElseConstructorTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Expression condition = new LessThan(new GetX(new Self(source), source),
											new DoubleConstant(200.0, source), source);
		StartDuck startduck = new StartDuck(source);
		StopDuck stopduck = new StopDuck(source);
		If if_statement = new If(condition, startduck, stopduck, source);
		
		assertEquals(condition, if_statement.getCondition());
		assertEquals(startduck, if_statement.getIfBody());
		assertEquals(stopduck, if_statement.getElseBody());
		assertEquals(0, if_statement.getConditionValue());
	}

	@Test
	public void NullConstructorTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Expression condition = new LessThan(new GetX(new Self(source), source),
											new DoubleConstant(200.0, source), source);
		try {
			new If(condition, null, null, source);
			fail();
		} catch(AssertionError error) {
			return;
		}
	}

	@Test
	public void BasicExecuteTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Expression condition = new LessThan(new GetX(new Self(source), source),
											new DoubleConstant(200.0, source), source);
		StartDuck startduck = new StartDuck(source);
		If if_statement = new If(condition, startduck, null, source);
		
		Program program = new Program(new Wait(new DoubleConstant(2.0, source), source), empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);
		
		assertEquals(condition, if_statement.getCondition());
		assertEquals(startduck, if_statement.getIfBody());
		assertEquals(null, if_statement.getElseBody());
		assertEquals(0, if_statement.getConditionValue());
		
		assertEquals(false, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.NOTDONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(1, if_statement.getConditionValue());
		assertEquals(startduck, if_statement.getCorrectBody());
		assertEquals(false, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(0, if_statement.getConditionValue());
		assertEquals(true, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.NOTDONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(1, if_statement.getConditionValue());
		assertEquals(startduck, if_statement.getCorrectBody());
		assertEquals(true, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(0, if_statement.getConditionValue());
		assertEquals(true, buzam.getDucking());
		
		buzam.startMove("right");
		for(int i = 0; i < 3; i++) world.advanceTime(0.1);
		
		assertEquals(true, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.NOTDONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(1, if_statement.getConditionValue());
		assertEquals(startduck, if_statement.getCorrectBody());
		assertEquals(true, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(0, if_statement.getConditionValue());
		assertEquals(true, buzam.getDucking());
		
		buzam.startMove("right");
		for(int i = 0; i < 7; i++) world.advanceTime(0.1);
		
		assertEquals(true, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(0, if_statement.getConditionValue());
		assertEquals(true, buzam.getDucking());
	}

	@Test
	public void ElseExecuteTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Expression condition = new LessThan(new GetX(new Self(source), source),
											new DoubleConstant(200.0, source), source);
		StartDuck startduck = new StartDuck(source);
		StopDuck stopduck = new StopDuck(source);
		If if_statement = new If(condition, startduck, stopduck, source);
		
		Program program = new Program(new Wait(new DoubleConstant(2.0, source), source), empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);
		
		assertEquals(condition, if_statement.getCondition());
		assertEquals(startduck, if_statement.getIfBody());
		assertEquals(stopduck, if_statement.getElseBody());
		assertEquals(0, if_statement.getConditionValue());
		
		assertEquals(false, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.NOTDONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(1, if_statement.getConditionValue());
		assertEquals(startduck, if_statement.getCorrectBody());
		assertEquals(false, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(0, if_statement.getConditionValue());
		assertEquals(true, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.NOTDONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(1, if_statement.getConditionValue());
		assertEquals(startduck, if_statement.getCorrectBody());
		assertEquals(true, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(0, if_statement.getConditionValue());
		assertEquals(true, buzam.getDucking());
		
		buzam.startMove("right");
		for(int i = 0; i < 3; i++) world.advanceTime(0.1);
		
		assertEquals(true, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.NOTDONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(1, if_statement.getConditionValue());
		assertEquals(startduck, if_statement.getCorrectBody());
		assertEquals(true, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(0, if_statement.getConditionValue());
		assertEquals(true, buzam.getDucking());
		
		buzam.startMove("right");
		for(int i = 0; i < 7; i++) world.advanceTime(0.1);
		
		assertEquals(true, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.NOTDONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(2, if_statement.getConditionValue());
		assertEquals(stopduck, if_statement.getCorrectBody());
		assertEquals(true, buzam.getDucking());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.DONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(0, if_statement.getConditionValue());
		assertEquals(false, buzam.getDucking());
	}

	@Test
	public void BreakExecuteTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Expression condition = new LessThan(new GetX(new Self(source), source),
											new DoubleConstant(200.0, source), source);
		Break break_statement = new Break(source);
		If if_statement = new If(condition, break_statement, null, source);
		
		Program program = new Program(new Wait(new DoubleConstant(2.0, source), source), empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);
		
		assertEquals(condition, if_statement.getCondition());
		assertEquals(break_statement, if_statement.getIfBody());
		assertEquals(null, if_statement.getElseBody());
		assertEquals(0, if_statement.getConditionValue());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.NOTDONE, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(1, if_statement.getConditionValue());
		assertEquals(break_statement, if_statement.getCorrectBody());
		
		program.setStatementsLeft(0);
		assertEquals(ExecutionState.BREAK, if_statement.execute(buzam));
		assertEquals(0, program.getStatementsLeft());
		assertEquals(0, if_statement.getConditionValue());
	}

}
