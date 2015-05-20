package jumpingalien.program.expression.binary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DirectionConstant;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.False;
import jumpingalien.program.expression.Null;
import jumpingalien.program.expression.True;

import org.junit.Before;
import org.junit.Test;

public class CompBinaryExpressionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getValueTypingTest() {
		Expression True = new True(new SourceLocation(5, 6));
		Expression False = new False(new SourceLocation(5, 10));
		Expression left = new DirectionConstant(Direction.LEFT, new SourceLocation(5, 10));
		Expression right = new DirectionConstant(Direction.RIGHT, new SourceLocation(5, 10));
		Expression null_expr = new Null(new SourceLocation(5, 14));
		
		try {
			assertEquals(Type.BOOL, (new Equals(True, False, new SourceLocation(5, 6))).getType());
			assertEquals(Type.BOOL, (new Equals(False, True, new SourceLocation(5, 6))).getType());
			assertEquals(Type.BOOL, (new Equals(left, right, new SourceLocation(5, 6))).getType());
			assertEquals(Type.BOOL, (new Equals(right, left, new SourceLocation(5, 6))).getType());
		} catch (IllegalArgumentException exc1) {
			fail();
		}
		try {
			new Equals(True, null_expr, new SourceLocation(5, 6));
			fail();
		} catch (IllegalArgumentException exc1) {
			try {
				new Equals(null_expr, left, new SourceLocation(5, 6));
				fail();
			} catch (IllegalArgumentException exc2) {
				try {
					new Equals(False, right, new SourceLocation(5, 6));
					fail();
				} catch (IllegalArgumentException exc3) {
					return;
				}
			}
		}
	}

}
