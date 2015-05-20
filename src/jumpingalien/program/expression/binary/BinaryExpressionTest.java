package jumpingalien.program.expression.binary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.True;

import org.junit.Before;
import org.junit.Test;

public class BinaryExpressionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void BasicConstructorTest() {
		SourceLocation source = new SourceLocation(5, 6);
		Expression True = new True(source);
		Expression False = new True(source);
		BinaryExpression bin = new And(True, False, source);
		
		assertEquals(True, bin.getFirstExpression());
		assertEquals(False, bin.getSecondExpression());
	}

}
