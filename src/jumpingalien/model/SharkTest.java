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
	Sprite[] slime_sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		shark = new Shark(0, 0, slime_sprites);
	}

	@Test
	public void TestConstructor() {
		assertEquals(shark.getX(), 0, Util.DEFAULT_EPSILON);
		assertEquals(shark.getY(),0, Util.DEFAULT_EPSILON);
		assertEquals(shark.getImages()[0], slime_sprites[0]);
		assertEquals(shark.getImages()[1], slime_sprites[1]);
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
}
