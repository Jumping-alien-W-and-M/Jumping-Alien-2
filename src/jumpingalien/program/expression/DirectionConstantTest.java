package jumpingalien.program.expression;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

import org.junit.Before;
import org.junit.Test;

public class DirectionConstantTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void BasicConstructorTest() {
		SourceLocation source = new SourceLocation(5, 6);
		
		DirectionConstant direction = new DirectionConstant(Direction.RIGHT, source);
		assertEquals(Direction.RIGHT, direction.getValue(null));
		assertEquals(Type.DIRECTION, direction.getType());
	}

}
