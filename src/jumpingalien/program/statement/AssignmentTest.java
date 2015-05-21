package jumpingalien.program.statement;

import static org.junit.Assert.*;

import java.util.HashMap;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.util.Sprite;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AssignmentTest {
	
	Assignment assignement;
	Program program;
	SourceLocation srceloc = new SourceLocation(5, 6);
	DoubleConstant constant = new DoubleConstant(4, srceloc);
	HashMap<String, Type> variables;
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		assignement = new Assignment("Test", Type.DOUBLE, constant, srceloc);
		variables = new HashMap<String, Type>(); 
	}

	@Test
	public void TestConstructorCorrectType() {
		assertEquals(assignement.getVariableName(), "Test");
		assertEquals(assignement.getVariableType(), Type.DOUBLE);
		assertEquals(assignement.getExpression(), constant);
	}
	
	@Test
	public void TestConstructorIncorrectType(){
		try{
			new Assignment("Test", Type.DIRECTION, constant, srceloc);
		} catch(Exception exc){
			assert(true);
			return;
		}
		assert(false);
	}
	
	@Test
	public void TestexecuteVariableExists(){
		variables.put("Test", Type.DOUBLE);
		program = new Program(new Skip(srceloc), variables);
		Shark shark = new Shark(0, 0, sprites, program);
		assertEquals(assignement.execute(shark), ExecutionState.DONE);
		assertEquals(program.getVariableValue("Test", Type.DOUBLE), constant.getValue(shark));
	}
	
	@Test
	public void TestexecuteNonExistingVariable(){
		program = new Program(new Skip(srceloc), variables);
		program.setStatementsLeft(5);
		Shark shark = new Shark(0, 0, sprites, program);
		assertEquals(assignement.execute(shark), ExecutionState.NOTDONE);
		assertEquals(program.getVariableValue("Test", Type.DOUBLE), null);
		assertEquals(program.getStatementsLeft(), 0);
		assert(program.getRunTimeError());
	}
	
	

}
