package jumpingalien.model;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameObjectTest {
	
	private static World world;
	private GameObject player;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		world = new World(10, 20, 20, 200, 200, 190, 0);
	}

	@Before
	public void setUp() throws Exception {
		player = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET);
		world.setMazub((Mazub) player);
	}

	@Test
	public void TestCoordinateSettersCorrectParameters() {		
		player.setX(10);
		assertEquals(player.getX(), 10, Util.DEFAULT_EPSILON);
		player.setY(100);
		assertEquals(player.getY(), 100, Util.DEFAULT_EPSILON);
		
		player.setX(0);
		assertEquals(player.getX(),0, Util.DEFAULT_EPSILON);
		player.setY(0);
		assertEquals(player.getY(),0, Util.DEFAULT_EPSILON);
		
		player.setX(1023);
		assertEquals(player.getX(), 199, Util.DEFAULT_EPSILON);
		player.setY(767);
		assertEquals(player.getY(), 199, Util.DEFAULT_EPSILON);
		
	}

}
