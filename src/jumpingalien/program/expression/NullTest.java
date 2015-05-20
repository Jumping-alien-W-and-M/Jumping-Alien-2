package jumpingalien.program.expression;

import static org.junit.Assert.*;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

import org.junit.Before;
import org.junit.Test;

public class NullTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void BasicConstructorTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		Null null_constant = new Null(source);
		assertEquals(null, null_constant.getValue(null));
		assertEquals(Type.OBJECT, null_constant.getType());
	}

}
