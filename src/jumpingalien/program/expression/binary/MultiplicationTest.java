package jumpingalien.program.expression.binary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.Expression;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class MultiplicationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getValueValueTest() {
		Expression expr1 = new DoubleConstant(1.5, new SourceLocation(5, 6));
		Expression expr2 = new DoubleConstant(0, new SourceLocation(5, 10));
		Expression expr3 = new DoubleConstant(-2, new SourceLocation(5, 14));
		
		try {
			assertEquals(0, (new Multiplication(expr1, expr2, new SourceLocation(5, 6))).getValue(null), Util.DEFAULT_EPSILON);
			assertEquals(0, (new Multiplication(expr2, expr1, new SourceLocation(5, 6))).getValue(null), Util.DEFAULT_EPSILON);
			assertEquals(-3, (new Multiplication(expr1, expr3, new SourceLocation(5, 6))).getValue(null), Util.DEFAULT_EPSILON);
			assertEquals(-3, (new Multiplication(expr3, expr1, new SourceLocation(5, 6))).getValue(null), Util.DEFAULT_EPSILON);
			assertEquals(0, (new Multiplication(expr2, expr3, new SourceLocation(5, 6))).getValue(null), Util.DEFAULT_EPSILON);
			assertEquals(0, (new Multiplication(expr3, expr2, new SourceLocation(5, 6))).getValue(null), Util.DEFAULT_EPSILON);
		} catch (IllegalArgumentException exc) {
			fail();
		}
	}

}
