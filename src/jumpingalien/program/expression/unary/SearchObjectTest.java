package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Shark;
import jumpingalien.model.World;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DirectionConstant;
import jumpingalien.program.expression.Expression;
import jumpingalien.util.Sprite;

import org.junit.Test;

public class SearchObjectTest {

	SourceLocation srceloc = new SourceLocation(5, 6);
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};


	@Test
	public void TestConstructorCorrectType(){
		Expression expr = new DirectionConstant(Direction.LEFT, srceloc);
		SearchObject searchObject = new SearchObject(expr, srceloc);
		assertEquals(searchObject.getType(), Type.OBJECT);
	}
	
	@Test
	public void TestGetValueRightWithObjects() {
		Expression direction = new DirectionConstant(Direction.RIGHT, srceloc);
		Shark startshark = new Shark(0, 0, sprites, null);
		Shark firstshark = new Shark(150, 0, sprites, null);
		Shark secondshark = new Shark(300, 0, sprites, null);
		World world = new World(10, 50, 50, 500, 500, 49, 0);
		world.addShark(startshark);
		world.addShark(firstshark);
		world.addShark(secondshark);
		Expression searchObject = new SearchObject(direction, srceloc);
		assertEquals(searchObject.getValue(startshark), firstshark);
	}

	@Test
	public void TestGetValueRightWithoutObjects() {
		Expression direction = new DirectionConstant(Direction.RIGHT, srceloc);
		Shark startshark = new Shark(0, 0, sprites, null);		
		World world = new World(10, 50, 50, 500, 500, 49, 0);
		world.addShark(startshark);
		Expression searchObject = new SearchObject(direction, srceloc);
		assertEquals(searchObject.getValue(startshark), null);
	}
	
	@Test
	public void TestGetValueLeftWithObjects() {
		Expression direction = new DirectionConstant(Direction.LEFT, srceloc);
		Shark startshark = new Shark(400, 0, sprites, null);
		Shark firstshark = new Shark(startshark.getX() - 2 - startshark.getWidth(), 0, sprites, null);
		Shark secondshark = new Shark(50, 0, sprites, null);
		World world = new World(10, 50, 50, 500, 500, 49, 0);
		world.addShark(startshark);
		world.addShark(firstshark);
		world.addShark(secondshark);
		Expression searchObject = new SearchObject(direction, srceloc);
		assertEquals(searchObject.getValue(startshark), firstshark);
	}

	@Test
	public void TestGetValueLeftWithoutObjects() {
		Expression direction = new DirectionConstant(Direction.LEFT, srceloc);
		Shark startshark = new Shark(400, 0, sprites, null);		
		World world = new World(10, 50, 50, 500, 500, 49, 0);
		world.addShark(startshark);
		Expression searchObject = new SearchObject(direction, srceloc);
		assertEquals(searchObject.getValue(startshark), null);
	}
	
	@Test
	public void TestGetValueUpWithObjects() {
		Expression direction = new DirectionConstant(Direction.UP, srceloc);
		Shark startshark = new Shark(0, 0, sprites, null);
		Shark firstshark = new Shark(0, 200, sprites, null);
		Shark secondshark = new Shark(0, 400, sprites, null);
		World world = new World(10, 50, 50, 500, 500, 49, 0);
		world.addShark(startshark);
		world.addShark(firstshark);
		world.addShark(secondshark);
		Expression searchObject = new SearchObject(direction, srceloc);
		assertEquals(searchObject.getValue(startshark), firstshark);
	}

	@Test
	public void TestGetValueUpWithoutObjects() {
		Expression direction = new DirectionConstant(Direction.UP, srceloc);
		Shark startshark = new Shark(0, 0, sprites, null);		
		World world = new World(10, 50, 50, 500, 500, 49, 0);
		world.addShark(startshark);
		Expression searchObject = new SearchObject(direction, srceloc);
		assertEquals(searchObject.getValue(startshark), null);
	}
	
	@Test
	public void TestGetValueDownWithObjects() {
		Expression direction = new DirectionConstant(Direction.DOWN, srceloc);
		Shark startshark = new Shark(0, 350, sprites, null);
		Shark firstshark = new Shark(0, 150, sprites, null);
		Shark secondshark = new Shark(0, 0, sprites, null);
		World world = new World(10, 50, 50, 500, 500, 49, 0);
		world.addShark(startshark);
		world.addShark(firstshark);
		world.addShark(secondshark);
		Expression searchObject = new SearchObject(direction, srceloc);
		assertEquals(searchObject.getValue(startshark), firstshark);
	}

	@Test
	public void TestGetValueDownWithoutObjects() {
		Expression direction = new DirectionConstant(Direction.DOWN, srceloc);
		Shark startshark = new Shark(350, 0, sprites, null);		
		World world = new World(10, 50, 50, 500, 500, 49, 0);
		world.addShark(startshark);
		Expression searchObject = new SearchObject(direction, srceloc);
		assertEquals(searchObject.getValue(startshark), null);
	}
}
