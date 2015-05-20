package jumpingalien.program.expression.binary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DirectionConstant;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Null;

import org.junit.Before;
import org.junit.Test;

public class OrderBinaryExpressionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getValueTypingTest() {
		Expression number1 = new DoubleConstant(2.0, new SourceLocation(5, 6));
		Expression number2 = new DoubleConstant(-1.0, new SourceLocation(5, 10));
		Expression left = new DirectionConstant(Direction.LEFT, new SourceLocation(5, 10));
		Expression right = new DirectionConstant(Direction.RIGHT, new SourceLocation(5, 10));
		Expression null_expr = new Null(new SourceLocation(5, 14));
		
		try {
			assertEquals(Type.BOOL, (new GreaterThan(number1, number2, new SourceLocation(5, 6))).getType());
			assertEquals(Type.BOOL, (new GreaterThan(number2, number1, new SourceLocation(5, 6))).getType());
		} catch (IllegalArgumentException exc1) {
			fail();
		}
		try {
			new GreaterThan(left, right, new SourceLocation(5, 6));
			fail();
		} catch (IllegalArgumentException exc1) {
			try {
				new GreaterThan(null_expr, left, new SourceLocation(5, 6));
				fail();
			} catch (IllegalArgumentException exc2) {
				return;
			}
		}
	}

}
