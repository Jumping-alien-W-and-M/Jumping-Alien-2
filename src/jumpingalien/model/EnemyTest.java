package jumpingalien.model;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EnemyTest {

	@Before
	public void setUp() throws Exception {
		slime_sprites = new Sprite[2];
		slime_sprites[0] = JumpingAlienSprites.ALIEN_SPRITESET[0];
		slime_sprites[1] = JumpingAlienSprites.ALIEN_SPRITESET[1];
		
		test_school = new School();
		test_school.setWorld(test_world);
		
		test_slime = new Slime(100, 200, slime_sprites, test_school);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private Sprite[] slime_sprites;
	private Slime test_slime;
	private School test_school;
	private World test_world;

	@Test
	public void constructorTest() {
		assertEquals(test_slime.getMinActionTime(), 2, Util.DEFAULT_EPSILON);
		assertEquals(test_slime.getMaxActionTime(), 6, Util.DEFAULT_EPSILON);
		assertEquals(test_slime.getHitpoints(), 100);
		
		Shark shark = new Shark(100, 200, slime_sprites);
		assertEquals(shark.getMinActionTime(), 1, Util.DEFAULT_EPSILON);
		assertEquals(shark.getMaxActionTime(), 4, Util.DEFAULT_EPSILON);
		assertEquals(shark.getHitpoints(), 100);
	}

	@Test
	public void actionTimeTest() {
		assertEquals(test_slime.getActionTime(), 0, Util.DEFAULT_EPSILON);
		assertEquals(test_slime.getWorld(), test_world);
		test_slime.advanceTime(0.1);
		assertEquals(test_slime.getActionTime(), 0.1, Util.DEFAULT_EPSILON);
		test_slime.advanceTime(0.1);
		assertEquals(test_slime.getActionTime(), 0.2, Util.DEFAULT_EPSILON);
		
		// TO BE CONTINUED
	}

}
