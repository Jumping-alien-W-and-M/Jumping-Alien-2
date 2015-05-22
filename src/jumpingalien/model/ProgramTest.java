package jumpingalien.model;

import static org.junit.Assert.*;

import java.util.HashMap;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.statement.Break;
import jumpingalien.program.statement.Skip;

import org.junit.Before;
import org.junit.Test;

public class ProgramTest {

	@Before
	public void setUp() throws Exception {
		
	}
	
	private SourceLocation source = new SourceLocation(5, 6);
	private static final HashMap<String, Type> empty_variables = new HashMap<String, Type>();
	
	@Test
	public void BasicConstructorTest() {
		
		Skip skip = new Skip(source);
		Program program = new Program(skip, empty_variables);
		
		assertEquals(skip, program.getMainStatement());
		assertEquals(0, program.getStatementsLeft());
		assertEquals(false, program.getRunTimeError());
		assertEquals(false, program.containsVariable("random variable which doesn't exist", Type.DOUBLE));
	}

}
