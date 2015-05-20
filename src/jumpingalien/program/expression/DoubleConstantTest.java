package jumpingalien.program.expression;

import static org.junit.Assert.*;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class DoubleConstantTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void BasicConstructorTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		DoubleConstant number = new DoubleConstant(1.5, source);
		assertEquals(true, number.getValue(null) instanceof Double);
		assertEquals(1.5, (double) number.getValue(null), Util.DEFAULT_EPSILON);
		assertEquals(Type.DOUBLE, number.getType());
	}

}
