package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.Expression;

import org.junit.Test;

public class MathUnaryExpressionTest {

	Expression expr;
	SourceLocation srceloc = new SourceLocation(2,3);

	@Test
	public void TestConstructorCorrectType() {
		expr = new DoubleConstant(2, srceloc);
		MathUnaryExpression sqrt = new Sqrt(expr, srceloc);
		assertEquals(sqrt.getType(), Type.DOUBLE);
	}

}
