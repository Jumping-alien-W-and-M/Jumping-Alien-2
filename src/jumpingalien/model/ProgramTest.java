package jumpingalien.model;

import static org.junit.Assert.*;

import java.util.HashMap;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.statement.Break;
import jumpingalien.program.statement.Print;
import jumpingalien.program.statement.Skip;
import jumpingalien.program.statement.Wait;

import org.junit.Before;
import org.junit.Test;

public class ProgramTest {

	@Before
	public void setUp() throws Exception {
		empty_variables = new HashMap<String, Type>();
	}
	
	private SourceLocation source = new SourceLocation(5, 6);
	private HashMap<String, Type> empty_variables;
	
	@Test
	public void BasicConstructorTest() {
		
		Skip skip = new Skip(source);
		Program program = new Program(skip, empty_variables);
		
		assertEquals(skip, program.getMainStatement());
		assertEquals(0, program.getStatementsLeft());
		assertEquals(false, program.getRunTimeError());
		assertEquals(false, program.containsVariable("random variable which doesn't exist", Type.DOUBLE));
	}
	
	@Test
	public void VariablesConstructorTest() {
		
		empty_variables.put("number", Type.DOUBLE);
		empty_variables.put("true", Type.BOOL);
		Skip skip = new Skip(source);
		Program program = new Program(skip, empty_variables);

		assertEquals(false, program.containsVariable("notanumber", Type.BOOL));
		assertEquals(false, program.containsVariable("notanumber", Type.DOUBLE));
		assertEquals(false, program.containsVariable("number", Type.BOOL));
		assertEquals(true, program.containsVariable("number", Type.DOUBLE));

		assertEquals(false, program.containsVariable("nottrue", Type.DOUBLE));
		assertEquals(false, program.containsVariable("nottrue", Type.BOOL));
		assertEquals(false, program.containsVariable("true", Type.DOUBLE));
		assertEquals(true, program.containsVariable("true", Type.BOOL));
		
		assertEquals(0.0, program.getVariableValue("number", Type.DOUBLE));
		assertEquals(false, program.getVariableValue("true", Type.BOOL));
	}
	
	@Test
	public void IllegalConstructorTest() {
		
		Break break_statement = new Break(source);
		
		try {
			new Program(break_statement, empty_variables);
			fail();
		} catch(IllegalArgumentException exc) {
			return;
		}
	}
	
	@Test
	public void SetRunTimeErrorTest() {
		
		Skip skip = new Skip(source);
		Program program = new Program(skip, empty_variables);

		assertEquals(false, program.getRunTimeError());
		program.setRunTimeError(true);
		assertEquals(true, program.getRunTimeError());
		program.setRunTimeError(false);
		assertEquals(false, program.getRunTimeError());
	}
	
	@Test
	public void BasicValidStatementTest() {
		
		Print print = new Print(new DoubleConstant(2.0, source), source);

		assertEquals(true, Program.isValidStatement(print, false, false));
		assertEquals(true, Program.isValidStatement(print, false, true));
		assertEquals(true, Program.isValidStatement(print, true, false));
		assertEquals(true, Program.isValidStatement(print, true, true));
	}
	
	@Test
	public void BasicActionValidStatementTest() {
		
		Skip skip = new Skip(source);

		assertEquals(false, Program.isValidStatement(skip, false, false));
		assertEquals(true, Program.isValidStatement(skip, false, true));
		assertEquals(false, Program.isValidStatement(skip, true, false));
		assertEquals(true, Program.isValidStatement(skip, true, true));
	}
	
	@Test
	public void BasicBreakValidStatementTest() {
		
		Break break_statement = new Break(source);

		assertEquals(false, Program.isValidStatement(break_statement, false, false));
		assertEquals(false, Program.isValidStatement(break_statement, false, true));
		assertEquals(true, Program.isValidStatement(break_statement, true, false));
		assertEquals(true, Program.isValidStatement(break_statement, true, true));
	}
	
	@Test
	public void AdvancedValidStatementTest() {
		
		Print print = new Print(new DoubleConstant(2.0, source), source);
		
		Sequence sequence

		assertEquals(true, Program.isValidStatement(print, false, false));
		assertEquals(true, Program.isValidStatement(print, false, true));
		assertEquals(true, Program.isValidStatement(print, true, false));
		assertEquals(true, Program.isValidStatement(print, true, true));
	}
	
	@Test
	public void BasicActionValidStatementTest() {
		
		Skip skip = new Skip(source);

		assertEquals(false, Program.isValidStatement(skip, false, false));
		assertEquals(true, Program.isValidStatement(skip, false, true));
		assertEquals(false, Program.isValidStatement(skip, true, false));
		assertEquals(true, Program.isValidStatement(skip, true, true));
	}
	
	@Test
	public void BasicBreakValidStatementTest() {
		
		Break break_statement = new Break(source);

		assertEquals(false, Program.isValidStatement(break_statement, false, false));
		assertEquals(false, Program.isValidStatement(break_statement, false, true));
		assertEquals(true, Program.isValidStatement(break_statement, true, false));
		assertEquals(true, Program.isValidStatement(break_statement, true, true));
	}

}
