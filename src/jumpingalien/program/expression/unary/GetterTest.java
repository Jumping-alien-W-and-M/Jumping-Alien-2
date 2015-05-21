package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Null;
import jumpingalien.program.expression.True;
import org.junit.Test;

public class GetterTest {
	
	Expression expr;
	SourceLocation srceloc = new SourceLocation(2,3);

	@Test
	public void TestConstructorCorrectType() {
		expr = new Null(srceloc);
		Getter getter = new GetHeight(expr, srceloc);
		assertEquals(getter.getType(), Type.DOUBLE);
	}

	@Test
	public void TestConstructorIncorrectType() {
		expr = new True(srceloc);
		try{
			new GetHeight(expr, srceloc);
		} catch(IllegalArgumentException exc){
			assert(true);
			return;
		}
		assert(false);
	}

}
