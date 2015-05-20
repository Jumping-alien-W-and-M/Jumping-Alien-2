package jumpingalien.program.expression.binary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.False;
import jumpingalien.program.expression.Null;
import jumpingalien.program.expression.True;

import org.junit.Before;
import org.junit.Test;

public class BoolBinaryExpressionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getValueTypingTest() {
		Expression True = new True(new SourceLocation(5, 6));
		Expression False = new False(new SourceLocation(5, 10));
		Expression null_expr = new Null(new SourceLocation(5, 14));
		
		try {
			assertEquals(Type.BOOL, (new And(True, False, new SourceLocation(5, 6))).getType());
			assertEquals(Type.BOOL, (new And(False, True, new SourceLocation(5, 6))).getType());
		} catch (IllegalArgumentException exc1) {
			fail();
		}
		try {
			new And(True, null_expr, new SourceLocation(5, 6));
			fail();
		} catch (IllegalArgumentException exc1) {
			try {
				new And(null_expr, True, new SourceLocation(5, 6));
				fail();
			} catch (IllegalArgumentException exc2) {
				return;
			}
		}
	}

}
