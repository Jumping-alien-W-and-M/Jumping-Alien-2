package jumpingalien.program.expression.binary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.False;
import jumpingalien.program.expression.True;

import org.junit.Before;
import org.junit.Test;

public class OrTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getValueValueTest() {
		Expression True = new True(new SourceLocation(5, 6));
		Expression False = new False(new SourceLocation(5, 10));
		
		assertEquals(false, (new Or(False, False, new SourceLocation(5, 6))).getValue(null));
		assertEquals(true, (new Or(False, True, new SourceLocation(5, 6))).getValue(null));
		assertEquals(true, (new Or(True, False, new SourceLocation(5, 6))).getValue(null));
		assertEquals(true, (new Or(True, True, new SourceLocation(5, 6))).getValue(null));
	}

}
