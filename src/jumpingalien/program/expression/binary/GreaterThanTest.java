package jumpingalien.program.expression.binary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.Expression;

import org.junit.Before;
import org.junit.Test;

public class GreaterThanTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getValueValueTest() {
		Expression number1 = new DoubleConstant(1.0, new SourceLocation(5, 6));
		Expression number2 = new DoubleConstant(2.0, new SourceLocation(5, 6));
		Expression number3 = new DoubleConstant(2.00000001, new SourceLocation(5, 6));
		Expression number4 = new DoubleConstant(1.99999999, new SourceLocation(5, 6));
		
		assertEquals(false, (new GreaterThan(number1, number2, new SourceLocation(5, 6))).getValue(null));
		assertEquals(true, (new GreaterThan(number2, number1, new SourceLocation(5, 6))).getValue(null));
		assertEquals(false, (new GreaterThan(number1, number3, new SourceLocation(5, 6))).getValue(null));
		assertEquals(true, (new GreaterThan(number3, number1, new SourceLocation(5, 6))).getValue(null));
		
		assertEquals(false, (new GreaterThan(number3, number2, new SourceLocation(5, 6))).getValue(null));
		assertEquals(false, (new GreaterThan(number2, number3, new SourceLocation(5, 6))).getValue(null));
		assertEquals(false, (new GreaterThan(number2, number4, new SourceLocation(5, 6))).getValue(null));
		assertEquals(false, (new GreaterThan(number4, number2, new SourceLocation(5, 6))).getValue(null));
	}

}
