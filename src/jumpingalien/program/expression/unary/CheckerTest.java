package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.*;

import org.junit.Test;

public class CheckerTest {
	
	Expression expr;
	SourceLocation srceloc = new SourceLocation(2,3);

	@Test
	public void TestConstructorCorrectType() {
		expr = new Null(srceloc);
		Checker checker = new IsAir(expr, srceloc);
		assertEquals(checker.getType(), Type.BOOL);
	}
}
