package jumpingalien.program.statement;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import jumpingalien.program.expression.Self;
import jumpingalien.program.expression.True;
import jumpingalien.program.expression.binary.LessThan;
import jumpingalien.program.expression.unary.GetX;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class WhileTest {

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
		
		Expression condition = new True(source);
		Statement body = new Break(source);
		While while_statement = new While(condition, body, source);
		
		assertEquals(condition, while_statement.getCondition());
		assertEquals(body, while_statement.getBody());
		assertEquals(false, while_statement.getInBody());
		
	}
	
	@Test
	public void ConstructorTypingTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Expression condition = new DoubleConstant(1.0, source);
		Statement body = new Break(source);
		try {
			new While(condition, body, source);
		} catch(IllegalArgumentException exc) {
			return;
		}
		fail();
	}
	
	@Test
	public void BasicExecuteTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Expression condition = new True(source);
		Statement body = new Break(source);
		While while_statement = new While(condition, body, source);
		
		Program program = new Program(while_statement, empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);
		
		program.setStatementsLeft(0);
		while_statement.execute(buzam);
		assertEquals(true, while_statement.getInBody());
		program.setStatementsLeft(0);
		while_statement.execute(buzam);
		assertEquals(false, while_statement.getInBody());
		
	}
	
	@Test
	public void AdvancedExecuteTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Expression condition = new LessThan(new GetX(new Self(source), source),
											new DoubleConstant(200.0, source), source);
		While while_statement = new While(condition, new Skip(source), source);
		
		ArrayList<Statement> sequence = new ArrayList<Statement>();
		sequence.add(new StartRun(new DirectionConstant(Direction.RIGHT, source), source));
		sequence.add(while_statement);
		sequence.add(new StopRun(new DirectionConstant(Direction.RIGHT, source), source));
		
		Program program = new Program(new If(condition, new Sequence(sequence, source), null, source), empty_variables);
		buzam = new Buzam(100, 100, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);

		world.advanceTime(0.001);
		assertEquals(false, while_statement.getInBody());
		assertEquals(100, buzam.getX(), Util.DEFAULT_EPSILON);
		world.advanceTime(0.001);
		assertEquals(false, while_statement.getInBody());
		assertEquals(100, buzam.getX(), Util.DEFAULT_EPSILON);
		world.advanceTime(0.001);
		assertEquals(true, while_statement.getInBody());
		assertEquals(true, buzam.getX() > 100);
		double pos = buzam.getX();
		world.advanceTime(0.001);
		assertEquals(false, while_statement.getInBody());
		assertEquals(true, buzam.getX() > pos);
		pos = buzam.getX();
		world.advanceTime(0.001);
		assertEquals(true, while_statement.getInBody());
		assertEquals(true, buzam.getX() > pos);
		pos = buzam.getX();
		world.advanceTime(0.001);
		assertEquals(false, while_statement.getInBody());
		assertEquals(true, buzam.getX() > pos);
		
		for(int i = 0; i < 20; i++) {
			System.out.println(i);
			if (i == 4)
				System.out.println("test");
			world.advanceTime(0.15);
		}
		
		assertEquals(false, while_statement.getInBody());
		assertEquals(true, buzam.getX() > 200.0);
		System.out.println(buzam.getX());
		
	}

}
