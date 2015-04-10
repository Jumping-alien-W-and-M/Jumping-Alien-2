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
		test_world = new World(10, 200, 200, 200, 200, 19, 0);
		
		slime_sprites = new Sprite[2];
		slime_sprites[0] = JumpingAlienSprites.ALIEN_SPRITESET[0];
		slime_sprites[1] = JumpingAlienSprites.ALIEN_SPRITESET[1];
		
		test_school = new School();
		test_world.addSchool(test_school);
		
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
		assertEquals(2, test_slime.getMinActionTime(), Util.DEFAULT_EPSILON);
		assertEquals(6, test_slime.getMaxActionTime(), Util.DEFAULT_EPSILON);
		assertEquals(100, test_slime.getHitpoints());
		
		Shark shark = new Shark(100, 200, slime_sprites);
		assertEquals(1, shark.getMinActionTime(), Util.DEFAULT_EPSILON);
		assertEquals(4, shark.getMaxActionTime(), Util.DEFAULT_EPSILON);
		assertEquals(100, shark.getHitpoints());
	}

	@Test
	public void actionTimeTest() {
		for(int i = 0; i < 100; i++) {
			test_slime = new Slime(1000, 0, slime_sprites, test_school);
			
			assertEquals(0, test_slime.getActionTime(), Util.DEFAULT_EPSILON);
			test_slime.advanceTime(0.1);
			double action_time = test_slime.getActionTime();
			assert(action_time >= test_slime.getMinActionTime() - 0.1);
			assert(action_time < test_slime.getMaxActionTime() - 0.1);
			
			for(double j = 0; (j < 10) && (action_time > 0.15); j++) {
				action_time -= 0.15;
				test_slime.advanceTime(0.15);
				assertEquals(action_time, test_slime.getActionTime(), Util.DEFAULT_EPSILON);
			}
			
			test_school.removeSlime(test_slime);
		}
	}

}