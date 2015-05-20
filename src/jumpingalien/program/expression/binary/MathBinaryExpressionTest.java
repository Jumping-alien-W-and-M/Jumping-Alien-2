package jumpingalien.program.expression.binary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Null;

import org.junit.Before;
import org.junit.Test;

public class MathBinaryExpressionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getValueTypingTest() {
		Expression expr1 = new DoubleConstant(1.5, new SourceLocation(5, 6));
		Expression expr2 = new DoubleConstant(2.5, new SourceLocation(5, 10));
		Expression null_expr = new Null(new SourceLocation(5, 14));
		
		try {
			assertEquals(Type.DOUBLE, (new Addition(expr1, expr2, new SourceLocation(5, 6))).getType());
			assertEquals(Type.DOUBLE, (new Addition(expr2, expr1, new SourceLocation(5, 6))).getType());
		} catch (IllegalArgumentException exc1) {
			fail();
		}
		try {
			new Addition(expr1, null_expr, new SourceLocation(5, 6));
			fail();
		} catch (IllegalArgumentException exc1) {
			try {
				new Addition(null_expr, expr1, new SourceLocation(5, 6));
				fail();
			} catch (IllegalArgumentException exc2) {
				return;
			}
		}
	}

}
