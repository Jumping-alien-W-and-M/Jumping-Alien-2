package jumpingalien.program.expression.binary;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.False;
import jumpingalien.program.expression.True;

import org.junit.Before;
import org.junit.Test;

public class AndTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getValueValueTest() {
		Expression True = new True(new SourceLocation(5, 6));
		Expression False = new False(new SourceLocation(5, 10));
		
		assertEquals(false, (new And(False, False, new SourceLocation(5, 6))).getValue(null));
		assertEquals(false, (new And(False, True, new SourceLocation(5, 6))).getValue(null));
		assertEquals(false, (new And(True, False, new SourceLocation(5, 6))).getValue(null));
		assertEquals(true, (new And(True, True, new SourceLocation(5, 6))).getValue(null));
	}

}
