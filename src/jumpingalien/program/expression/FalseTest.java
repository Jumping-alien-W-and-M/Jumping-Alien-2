package jumpingalien.program.expression;

import static org.junit.Assert.*;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

import org.junit.Before;
import org.junit.Test;

public class FalseTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void BasicConstructorTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		False false_constant = new False(source);
		assertEquals(true, false_constant.getValue(null) instanceof Boolean);
		assertEquals(false, (boolean) false_constant.getValue(null));
		assertEquals(Type.BOOL, false_constant.getType());
	}

}
