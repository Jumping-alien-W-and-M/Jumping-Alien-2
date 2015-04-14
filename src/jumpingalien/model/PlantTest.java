package jumpingalien.model;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class PlantTest {

	@Before
	public void setUp() throws Exception {
		test_world = new World(10, 200, 200, 500, 500, 19, 0);
		test_plant = new Plant(0, 0, sprites);
		test_world.addPlant(test_plant);
	}
	
	private World test_world;
	private Plant test_plant;
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@Test
	public void TestConstructor() {
		assertEquals(test_plant.getX(), 0, Util.DEFAULT_EPSILON);
		assertEquals(test_plant.getY(), 0, Util.DEFAULT_EPSILON);
		assertEquals(test_plant.getImages()[0], sprites[0]);
		assertEquals(test_plant.getImages()[1], sprites[1]);
		assertEquals(test_plant.getAxi(), 0, Util.DEFAULT_EPSILON);
		assertEquals(test_plant.getVxi(), 0.5, Util.DEFAULT_EPSILON);
		assertEquals(test_plant.getVxmax(), 0.5, Util.DEFAULT_EPSILON);
		assertEquals(test_plant.getMovementTime(), 0, Util.DEFAULT_EPSILON);
		assertEquals(test_plant.getVx(), test_plant.getVxi(), Util.DEFAULT_EPSILON);
	}

	@Test
	public void movementTimeTest() {
		
		for(int i = 0; i < 100; i++) {
			assertEquals((i*0.1)%0.5, test_plant.getMovementTime(), Util.DEFAULT_EPSILON);
			
			if ((i%10) < 5) assertEquals(test_plant.getVxi(), test_plant.getVx(), Util.DEFAULT_EPSILON);
			else assertEquals(-test_plant.getVxi(), test_plant.getVx(), Util.DEFAULT_EPSILON);
			test_plant.advanceTime(0.1);
			
		}
		
	}
	
	@Test
	public void deathTimeTest() {
		test_plant = new Plant(1000, 0, sprites);
		test_world.addPlant(test_plant);
		test_plant.kill();
		
		for(double i = 0.6; Math.abs(i) > Util.DEFAULT_EPSILON; i -= 0.1) {
			assertEquals(i, test_plant.getDeathTime(), Util.DEFAULT_EPSILON);
			test_plant.advanceTime(0.1);
		}
		
		assertEquals(0, test_plant.getDeathTime(), Util.DEFAULT_EPSILON);
		test_plant.advanceTime(0.001);
		assertEquals(false, test_world.hasAsPlant(test_plant));
		assertEquals(null, test_plant.getWorld());
	}

}
