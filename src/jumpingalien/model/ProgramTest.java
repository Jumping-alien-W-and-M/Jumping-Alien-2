package jumpingalien.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DirectionConstant;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.ReadVariable;
import jumpingalien.program.expression.Self;
import jumpingalien.program.expression.binary.GreaterThan;
import jumpingalien.program.expression.binary.LessThan;
import jumpingalien.program.expression.unary.GetHitpoints;
import jumpingalien.program.expression.unary.GetX;
import jumpingalien.program.statement.Break;
import jumpingalien.program.statement.ForEach;
import jumpingalien.program.statement.If;
import jumpingalien.program.statement.Print;
import jumpingalien.program.statement.Sequence;
import jumpingalien.program.statement.Skip;
import jumpingalien.program.statement.StartJump;
import jumpingalien.program.statement.StartRun;
import jumpingalien.program.statement.Statement;
import jumpingalien.program.statement.StopJump;
import jumpingalien.util.Util;

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

		ArrayList<Statement> inner_statements = new ArrayList<Statement>();
		inner_statements.add(new Print(new DoubleConstant(2.0, source), source));
		inner_statements.add(new If(new GreaterThan(new GetX(new Self(source), source),
										new GetX(new ReadVariable("var1", Type.OBJECT, source), source), source), 
									new Print(new DoubleConstant(5.0, source), source),
									new Break(source), source));
		
		ArrayList<Statement> statements = new ArrayList<Statement>();
		statements.add(new Skip(source));
		statements.add(new If(new GreaterThan(new GetHitpoints(new Self(source), source), new DoubleConstant(200.0, source), source),
							new ForEach("var1", Kind.ANY, new LessThan(new GetHitpoints(
										new ReadVariable("var1", Type.OBJECT, source),source),
										new DoubleConstant(100.0, source), source),
										new GetHitpoints(new ReadVariable("var1", Type.OBJECT, source), source),
										SortDirection.ASCENDING, new Sequence(inner_statements, source), source),
							new StartJump(source), source));
		statements.add(new StopJump(source));
		statements.add(new Print(new DoubleConstant(3.0, source), source));
		Sequence sequence = new Sequence(statements, source);

		assertEquals(false, Program.isValidStatement(sequence, false, false));
		assertEquals(true, Program.isValidStatement(sequence, false, true));
		assertEquals(false, Program.isValidStatement(sequence, true, false));
		assertEquals(true, Program.isValidStatement(sequence, true, true));
	}
	
	@Test
	public void AdvancedActionValidStatementTest() {

		ArrayList<Statement> inner_statements = new ArrayList<Statement>();
		inner_statements.add(new Print(new DoubleConstant(2.0, source), source));
		inner_statements.add(new If(new GreaterThan(new GetX(new Self(source), source),
										new GetX(new ReadVariable("var1", Type.OBJECT, source), source), source), 
									new StartRun(new DirectionConstant(Direction.LEFT, source), source),
									new StartRun(new DirectionConstant(Direction.RIGHT, source), source), source));
		
		ArrayList<Statement> statements = new ArrayList<Statement>();
		statements.add(new Skip(source));
		statements.add(new If(new GreaterThan(new GetHitpoints(new Self(source), source), new DoubleConstant(200.0, source), source),
							new ForEach("var1", Kind.ANY, new LessThan(new GetHitpoints(
										new ReadVariable("var1", Type.OBJECT, source),source),
										new DoubleConstant(100.0, source), source),
										new GetHitpoints(new ReadVariable("var1", Type.OBJECT, source), source),
										SortDirection.ASCENDING, new Sequence(inner_statements, source), source),
							new StartJump(source), source));
		statements.add(new StopJump(source));
		statements.add(new Print(new DoubleConstant(3.0, source), source));
		Sequence sequence = new Sequence(statements, source);

		assertEquals(false, Program.isValidStatement(sequence, false, false));
		assertEquals(false, Program.isValidStatement(sequence, false, true));
		assertEquals(false, Program.isValidStatement(sequence, true, false));
		assertEquals(false, Program.isValidStatement(sequence, true, true));
	}
	
	@Test
	public void AdvancedBreakValidStatementTest() {

		ArrayList<Statement> inner_statements = new ArrayList<Statement>();
		inner_statements.add(new Print(new DoubleConstant(2.0, source), source));
		inner_statements.add(new If(new GreaterThan(new GetX(new Self(source), source),
										new GetX(new ReadVariable("var1", Type.OBJECT, source), source), source), 
									new Print(new DoubleConstant(5.0, source), source),
									new Break(source), source));
		
		ArrayList<Statement> statements = new ArrayList<Statement>();
		statements.add(new Skip(source));
		statements.add(new If(new GreaterThan(new GetHitpoints(new Self(source), source), new DoubleConstant(200.0, source), source),
							new ForEach("var1", Kind.ANY, new LessThan(new GetHitpoints(
										new ReadVariable("var1", Type.OBJECT, source),source),
										new DoubleConstant(100.0, source), source),
										new GetHitpoints(new ReadVariable("var1", Type.OBJECT, source), source),
										SortDirection.ASCENDING, new Sequence(inner_statements, source), source),
							new StartJump(source), source));
		statements.add(new Break(source));
		statements.add(new Print(new DoubleConstant(3.0, source), source));
		Sequence sequence = new Sequence(statements, source);

		assertEquals(false, Program.isValidStatement(sequence, false, false));
		assertEquals(false, Program.isValidStatement(sequence, false, true));
		assertEquals(false, Program.isValidStatement(sequence, true, false));
		assertEquals(true, Program.isValidStatement(sequence, true, true));
	}
	
	@Test
	public void PrintTypeCheckErrorTest() {
		
		try {
			Program.printTypeCheckError(source);
			fail();
		} catch(IllegalArgumentException exc) {
			return;
		}
	}
	
	@Test
	public void SetGetVariableValueTest() {
		
		empty_variables.put("number", Type.DOUBLE);
		empty_variables.put("boolean", Type.BOOL);
		Skip skip = new Skip(source);
		Program program = new Program(skip, empty_variables);
		
		assertEquals(0.0, program.getVariableValue("number", Type.DOUBLE));
		program.setVariableValue("number", Type.DOUBLE, 15.8);
		assertEquals(15.8, (double) program.getVariableValue("number", Type.DOUBLE), Util.DEFAULT_EPSILON);
		program.setVariableValue("number", Type.DOUBLE, -1863.0);
		assertEquals(-1863.0, (double) program.getVariableValue("number", Type.DOUBLE), Util.DEFAULT_EPSILON);
		
		assertEquals(false, program.getVariableValue("boolean", Type.BOOL));
		program.setVariableValue("boolean", Type.BOOL, true);
		assertEquals(true, program.getVariableValue("boolean", Type.BOOL));
		program.setVariableValue("boolean", Type.BOOL, false);
		assertEquals(false, program.getVariableValue("boolean", Type.BOOL));
	}
	
	@Test
	public void SetGetVariableValueWrongVariableTest() {

		empty_variables.put("number", Type.DOUBLE);
		Skip skip = new Skip(source);
		Program program = new Program(skip, empty_variables);
		
		try {
			program.getVariableValue("notnumber", Type.DOUBLE);
			fail();
		} catch(AssertionError error1) {
			try {
				program.getVariableValue("number", Type.BOOL);
				fail();
			} catch(AssertionError error2) {
				return;
			}
		}
	}

}
