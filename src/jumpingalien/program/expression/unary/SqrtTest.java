package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Buzam;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.Expression;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class SqrtTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void getValueValueTest() {
		
		Map<String, Type> globals = new HashMap<String, Type>();
		globals.put("var1", Type.DIRECTION);
		
		Program program = new Program(null, globals);
		program.setStatementsLeft(5);
		Buzam buzam = new Buzam(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, program);
		SourceLocation source = new SourceLocation(5, 6);
		
		Expression expr1 = new DoubleConstant(9, new SourceLocation(5, 6));
		Expression expr2 = new DoubleConstant(0, new SourceLocation(5, 10));
		Expression expr3 = new DoubleConstant(-2, new SourceLocation(5, 14));
		Expression expr4 = new DoubleConstant(-0.0001, new SourceLocation(5, 14));
		Expression expr5 = new DoubleConstant(0.00001, new SourceLocation(5, 10));
		Expression expr6 = new DoubleConstant(-0.00001, new SourceLocation(5, 10));
		
		try {
			assertEquals(3, (new Sqrt(expr1, source)).getValue(buzam), Util.DEFAULT_EPSILON);
			assertEquals(false, program.getRunTimeError());
			assertEquals(0, (new Sqrt(expr2, source)).getValue(buzam), Util.DEFAULT_EPSILON);
			assertEquals(false, program.getRunTimeError());
			assertEquals(0, (new Sqrt(expr5, source)).getValue(buzam), Util.DEFAULT_EPSILON);
			assertEquals(false, program.getRunTimeError());
			assertEquals(0, (new Sqrt(expr6, source)).getValue(buzam), Util.DEFAULT_EPSILON);
			assertEquals(false, program.getRunTimeError());
			assertEquals(0, (new Sqrt(expr3, source)).getValue(buzam), Util.DEFAULT_EPSILON);
			NegativeCheck(program);
			assertEquals(0, (new Sqrt(expr4, source)).getValue(buzam), Util.DEFAULT_EPSILON);
			assertEquals(false, program.getRunTimeError());
		} catch (Exception exc) {
			fail();
		}
	}
	
	private void NegativeCheck(Program program) {
		// Check if a runtime error occurred
		assertEquals(0, program.getStatementsLeft());
		assertEquals(true, program.getRunTimeError());
		
		program.setStatementsLeft(5);
		program.setRunTimeError(false);
	}

}
