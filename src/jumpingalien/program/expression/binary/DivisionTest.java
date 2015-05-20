package jumpingalien.program.expression.binary;

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

public class DivisionTest {

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
		
		Expression expr1 = new DoubleConstant(1.5, new SourceLocation(5, 6));
		Expression expr2 = new DoubleConstant(0, new SourceLocation(5, 10));
		Expression expr3 = new DoubleConstant(-2, new SourceLocation(5, 14));
		
		try {
			assertEquals(0, (new Division(expr1, expr2, source)).getValue(buzam), Util.DEFAULT_EPSILON);
			DBZCheck(program);
			assertEquals(0, (new Division(expr2, expr1, source)).getValue(buzam), Util.DEFAULT_EPSILON);
			assertEquals(-0.75, (new Division(expr1, expr3, source)).getValue(buzam), Util.DEFAULT_EPSILON);
			assertEquals(-1.333333333333333, (new Division(expr3, expr1, source)).getValue(buzam), Util.DEFAULT_EPSILON);
			assertEquals(0, (new Division(expr2, expr3, source)).getValue(buzam), Util.DEFAULT_EPSILON);
			assertEquals(0, (new Division(expr3, expr2, source)).getValue(buzam), Util.DEFAULT_EPSILON);
			DBZCheck(program);
		} catch (IllegalArgumentException exc) {
			fail();
		}
	}
	
	private void DBZCheck(Program program) {
		// Check if division-by-zero-error occurred
		assertEquals(0, program.getStatementsLeft());
		assertEquals(true, program.getRunTimeError());
		
		program.setStatementsLeft(5);
		program.setRunTimeError(false);
	}

}
