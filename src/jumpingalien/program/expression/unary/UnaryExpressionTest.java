package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.True;

import org.junit.Before;
import org.junit.Test;

public class UnaryExpressionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void BasicConstructorTest() {
		SourceLocation source = new SourceLocation(5, 6);
		Expression True = new True(source);
		UnaryExpression unary = new Not(True, source);
		
		assertEquals(True, unary.getExpression());
	}

}
