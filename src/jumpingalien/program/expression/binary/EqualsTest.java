package jumpingalien.program.expression.binary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.DirectionConstant;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.Expression;

import org.junit.Before;
import org.junit.Test;

public class EqualsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getValueValueTest() {
		Expression number1 = new DoubleConstant(1.0, new SourceLocation(5, 6));
		Expression number2 = new DoubleConstant(2.0, new SourceLocation(5, 6));
		Expression number3 = new DoubleConstant(3.0, new SourceLocation(5, 6));
		Expression number4 = new DoubleConstant(3.000000001, new SourceLocation(5, 6));
		Expression left = new DirectionConstant(Direction.LEFT, new SourceLocation(5, 6));
		Expression right = new DirectionConstant(Direction.RIGHT, new SourceLocation(5, 6));
		
		assertEquals(false, (new Equals(number1, number2, new SourceLocation(5, 6))).getValue(null));
		assertEquals(false, (new Equals(number2, number1, new SourceLocation(5, 6))).getValue(null));
		assertEquals(false, (new Equals(number1, number3, new SourceLocation(5, 6))).getValue(null));
		assertEquals(false, (new Equals(number3, number1, new SourceLocation(5, 6))).getValue(null));

		assertEquals(true, (new Equals(number1, number1, new SourceLocation(5, 6))).getValue(null));
		assertEquals(true, (new Equals(number2, number2, new SourceLocation(5, 6))).getValue(null));
		assertEquals(true, (new Equals((new Addition(number1, number2, new SourceLocation(5, 6))),
				number3, new SourceLocation(5, 6))).getValue(null));
		assertEquals(true, (new Equals(number3, number4, new SourceLocation(5, 6))).getValue(null));

		assertEquals(false, (new Equals(left, right, new SourceLocation(5, 6))).getValue(null));
		assertEquals(false, (new Equals(right, left, new SourceLocation(5, 6))).getValue(null));
		assertEquals(true, (new Equals(left, left, new SourceLocation(5, 6))).getValue(null));
		assertEquals(true, (new Equals(right, right, new SourceLocation(5, 6))).getValue(null));
	}

}
