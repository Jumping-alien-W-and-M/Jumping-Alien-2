package jumpingalien.model;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlantTest {
	
	private Plant plant;
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		plant = new Plant(0, 0, sprites);
	}

	@Test
	public void TestConstructor() {
		assertEquals(plant.getX(), 0, Util.DEFAULT_EPSILON);
		assertEquals(plant.getY(), 0, Util.DEFAULT_EPSILON);
		assertEquals(plant.getImages()[0], sprites[0]);
		assertEquals(plant.getImages()[1], sprites[1]);
		assertEquals(plant.getAxi(), 0, Util.DEFAULT_EPSILON);
		assertEquals(plant.getVxi(), 0.5, Util.DEFAULT_EPSILON);
		assertEquals(plant.getVxmax(), 0.5, Util.DEFAULT_EPSILON);
		assertEquals(plant.getMovementTime(), 0, Util.DEFAULT_EPSILON);
		assertEquals(plant.getVx(), plant.getVxi(), Util.DEFAULT_EPSILON);
	}

}
