package jumpingalien.program;

import static org.junit.Assert.*;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Null;

import org.junit.Before;
import org.junit.Test;

public class ParserObjectTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void BasicConstructorTest() {
		ParserObject Null;
		SourceLocation source = new SourceLocation(5, 6);
		
		Null = new Null(null);
		assertEquals(null, Null.getSourceLocation());
		Null = new Null(source);
		assertEquals(source, Null.getSourceLocation());
	}

}
