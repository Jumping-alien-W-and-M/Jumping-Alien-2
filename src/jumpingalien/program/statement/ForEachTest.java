package jumpingalien.program.statement;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.*;
import jumpingalien.part3.programs.*;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.program.*;
import jumpingalien.program.expression.*;
import jumpingalien.program.expression.unary.GetHeight;
import jumpingalien.program.expression.unary.GetHitpoints;
import jumpingalien.program.expression.unary.IsJumping;
import jumpingalien.util.Sprite;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ForEachTest {
	
	SourceLocation srceloc = new SourceLocation(5, 6);
	DoubleConstant doubleconstant = new DoubleConstant(4, srceloc);
	True boolconstant = new True(srceloc);
	Print print = new Print(doubleconstant, srceloc);
	HashMap<String, Type> variables;
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};
	World world = new World(10, 20, 20, 200, 200, 19, 0);

	@Before
	public void setUp() throws Exception {
		variables = new HashMap<String, Type>();
	}

	@Test
	public void TestConstructorCorrectTypes() {
		ForEach foreach = new ForEach("Test", Kind.MAZUB, boolconstant, doubleconstant, SortDirection.ASCENDING,
											print, srceloc);
		assertEquals("Test", foreach.getVariableName());
		assertEquals(Kind.MAZUB, foreach.getVariableKind());
		assertEquals(boolconstant, foreach.getWhere());
		assertEquals(doubleconstant, foreach.getSort());
		assertEquals(SortDirection.ASCENDING, foreach.getSortDirection());
		assertEquals(print, foreach.getBody());
	}
	
	@Test
	public void TestConstructorIncorrectTypes(){
		try{
			new ForEach("Test", Kind.MAZUB, doubleconstant, doubleconstant, SortDirection.ASCENDING,
												print, srceloc);
		} catch(IllegalArgumentException exc0){
			try{
				new ForEach("Test", Kind.MAZUB, boolconstant, boolconstant, SortDirection.ASCENDING,
						print, srceloc);
			} catch(IllegalArgumentException exc1){
				assert(true);
				return; 
			}
		assert(false);
		}
	}
	
	@Test
	public void TestGetCorrectObjects(){
		ReadVariable readvariable = new ReadVariable("Test", Type.OBJECT, srceloc);
		IsJumping where = new IsJumping(readvariable, srceloc);
		GetHitpoints sort = new GetHitpoints(readvariable, srceloc);
		ForEach foreach = new ForEach("Test", Kind.ANY, where, sort, SortDirection.ASCENDING, print, srceloc);
		variables.put("Test", Type.OBJECT);
		Program program = new Program(foreach, variables);
		Shark shark0 = new Shark(0, 0, sprites, program);
		Shark shark1 = new Shark(0, 0, sprites, null);
		shark1.startJump();
		shark1.setHitpoints(2);
		Mazub player = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, null);
		player.setJumping(true);
		Buzam buzam = new Buzam(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, null);
		world.addShark(shark0);
		world.addShark(shark1);
		world.setMazub(player);
		world.setBuzam(buzam);
		Object[] expected = {shark1, player};
		Object[] result = foreach.getCorrectObjects(shark0);
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++){
			assertEquals(expected[i], result[i]);
		}
		
	}
	
	@Test
	public void TestExecuteLoopIsDone(){
		Program program = new Program(print, variables);
		Shark shark = new Shark(0, 0, sprites, program);
		world.addShark(shark);
		ForEach foreach = new ForEach("Test", Kind.MAZUB, boolconstant, doubleconstant, SortDirection.ASCENDING,
				print, srceloc);
		assertEquals(ExecutionState.DONE, foreach.execute(shark));
	}
	
	@Test
	public void TestExecuteNotDoneAfterAssignement(){
		Assignment assignement = new Assignment("Test1", Type.DOUBLE, doubleconstant, srceloc);
		ForEach foreach = new ForEach("Test", Kind.ANY, null, null, SortDirection.ASCENDING, assignement, srceloc);
		variables.put("Test", Type.OBJECT);
		variables.put("Test1", Type.DOUBLE);
		Program program = new Program(foreach, variables);
		program.setVariableValue("Test1", Type.DOUBLE, 0.0);
		program.setStatementsLeft(1 - 1);
		Shark shark0 = new Shark(0, 0, sprites, program);
		Mazub player = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, null);
		world.addShark(shark0);
		world.setMazub(player);
		assertEquals(ExecutionState.NOTDONE, foreach.execute(shark0));
		assertEquals(1, foreach.getCurrentIndex());
		assertEquals(0.0 , program.getVariableValue("Test1", Type.DOUBLE));
	}
	
	@Test
	public void TestExecuteBodyNotInstanceOfSequenceReturnDone(){
		Assignment assignement = new Assignment("Test1", Type.DOUBLE, doubleconstant, srceloc);
		ForEach foreach = new ForEach("Test", Kind.ANY, null, null, SortDirection.ASCENDING, assignement, srceloc);
		variables.put("Test", Type.OBJECT);
		variables.put("Test1", Type.DOUBLE);
		Program program = new Program(foreach, variables);
		program.setVariableValue("Test1", Type.DOUBLE, 0.0);
		program.setStatementsLeft(1);
		Shark shark0 = new Shark(0, 0, sprites, program);
		world.addShark(shark0);
		assertEquals(ExecutionState.DONE, foreach.execute(shark0));
		assertEquals(1, foreach.getCurrentIndex());
		assertEquals(4.0 , program.getVariableValue("Test1", Type.DOUBLE));
	}
	
	@Test
	public void TestExecuteBodyNotInstanceOfSequenceReturnNotDone(){
		Assignment assignement = new Assignment("Test1", Type.DOUBLE, doubleconstant, srceloc);
		ForEach foreach = new ForEach("Test", Kind.ANY, null, null, SortDirection.ASCENDING, assignement, srceloc);
		variables.put("Test", Type.OBJECT);
		variables.put("Test1", Type.DOUBLE);
		Program program = new Program(foreach, variables);
		program.setVariableValue("Test1", Type.DOUBLE, 0.0);
		program.setStatementsLeft(1);
		Shark shark0 = new Shark(0, 0, sprites, program);
		Mazub player = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, null);
		world.addShark(shark0);
		world.setMazub(player);
		assertEquals(ExecutionState.NOTDONE, foreach.execute(shark0));
		assertEquals(1, foreach.getCurrentIndex());
		assertEquals(4.0 , program.getVariableValue("Test1", Type.DOUBLE));
	}
	
	@Test
	public void TestExecuteBodyInstanceOfSequenceDone(){
		
	}
	
	@Test
	public void TestExecuteBreak(){
		Break breakstatement = new Break(srceloc);
		ForEach foreach = new ForEach("Test", Kind.ANY, null, null, SortDirection.ASCENDING, breakstatement, srceloc);
		variables.put("Test", Type.OBJECT);
		Program program = new Program(foreach, variables);
		program.setVariableValue("Test1", Type.DOUBLE, 0.0);
		program.setStatementsLeft(5);
		Shark shark0 = new Shark(0, 0, sprites, program);
		Mazub player = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, null);
		world.addShark(shark0);
		world.setMazub(player);
		assertEquals(ExecutionState.DONE, foreach.execute(shark0));
		assertEquals(1, foreach.getCurrentIndex());
		assertEquals(4, program.getStatementsLeft());
	}
}
