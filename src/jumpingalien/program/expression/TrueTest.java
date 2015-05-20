package jumpingalien.program.expression;

import static org.junit.Assert.*;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

import org.junit.Before;
import org.junit.Test;

public class TrueTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void BasicConstructorTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		True true_constant = new True(source);
		assertEquals(true, true_constant.getValue(null) instanceof Boolean);
		assertEquals(true, (boolean) true_constant.getValue(null));
		assertEquals(Type.BOOL, true_constant.getType());
	}

}
