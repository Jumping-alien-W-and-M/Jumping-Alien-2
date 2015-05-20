package jumpingalien.model;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SharkTest {
	
	private Shark shark;
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		shark = new Shark(0, 0, sprites, null);
	}

	@Test
	public void TestConstructor() {
		assertEquals(shark.getX(), 0, Util.DEFAULT_EPSILON);
		assertEquals(shark.getY(),0, Util.DEFAULT_EPSILON);
		assertEquals(shark.getImages()[0], sprites[0]);
		assertEquals(shark.getImages()[1], sprites[1]);
		assertEquals(shark.getAxi(), 1.5, Util.DEFAULT_EPSILON);
		assertEquals(shark.getVxi(), 0, Util.DEFAULT_EPSILON);
		assertEquals(shark.getVxmax(), 4, Util.DEFAULT_EPSILON);
		assertEquals(shark.getMinActionTime(), 1, Util.DEFAULT_EPSILON);
		assertEquals(shark.getMaxActionTime(), 4, Util.DEFAULT_EPSILON);
		assertEquals(shark.getHitpoints(), 100);
	}
	
	@Test
	public void TestisValidAyValidAy(){
		assert(shark.isValidAy(0));
		assert(shark.isValidAy(-10));
		assert(shark.isValidAy(-0.2));
		assert(shark.isValidAy(0.2));
		assert(shark.isValidAy(0.1));
	}
	
	@Test
	public void TestcanJumpSharkInWater(){
		World world = new World(10, 20, 20, 200, 200, 19, 0);
		world.setFeature(0, 0, 2);
		world.addShark(shark);
		shark.setY(9);
		assert(shark.canJump());
		shark.setY(5);
		assert(shark.canJump());
	}
	
	@Test
	public void TestcanJumpSharkOnGround(){
		World world = new World(10, 20, 20, 200, 200, 19, 0);
		world.setFeature(0, 0, 1);
		world.addShark(shark);
		shark.setY(9);
		assert(shark.canJump());
	}
	
	@Test
	public void TestcanJumpSharkOnOtherGameObject(){
		World world = new World(10, 20, 20, 200, 200, 19, 0);
		Shark shark1 = new Shark(0 , 0, sprites, null);
		world.addShark(shark1);
		world.addShark(shark);
		shark.setY(shark1.getHeight() - 1);
		assert(shark.canJump());
	}
	
	@Test
	public void TestcanJumpSharkUnderOtherGameObject(){
		World world = new World(10, 20, 20, 200, 200, 19, 0);
		Shark shark1 = new Shark(0 , 0, sprites, null);
		world.addShark(shark1);
		world.addShark(shark);
		shark1.setY(shark.getHeight() - 1);
		assert(! shark.canJump());
	}
	
	@Test
	public void TestcanJumpSharkInTheAir(){
		World world = new World(10, 20, 20, 200, 200, 19, 0);
		world.addShark(shark);
		assert(! shark.canJump());
	}
	
	@Test
	public void TeststartJumpCanJump(){
		World world = new World(10, 20, 20, 200, 200, 19, 0);
		world.addShark(shark);
		shark.setY(5);
		world.setFeature(0, 0, 2);
		shark.startJump();
		
		assert(shark.getIsJumping());
		assertEquals(shark.getVy(), 2, Util.DEFAULT_EPSILON);
		assertEquals(shark.getNonJumpingPeriods(), 0, Util.DEFAULT_EPSILON);
		assert(shark.getJustJumped());		
	}
	
	@Test
	public void TeststartJumpCanNotJump(){
		World world = new World(10, 20, 20, 200, 200, 19, 0);
		world.addShark(shark);
		shark.startJump();
		
		assert(shark.getIsJumping());
		assertEquals(shark.getVy(), 2, Util.DEFAULT_EPSILON);
		assertEquals(shark.getNonJumpingPeriods(), 0, Util.DEFAULT_EPSILON);
		assert(! shark.getJustJumped());		
	}
}
