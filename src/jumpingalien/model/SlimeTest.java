package jumpingalien.model;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SlimeTest {

	@Before
	public void setUp() throws Exception {
		slime_sprites = new Sprite[2];
		slime_sprites[0] = JumpingAlienSprites.ALIEN_SPRITESET[0];
		slime_sprites[1] = JumpingAlienSprites.ALIEN_SPRITESET[1];

		null_school = new School();
		test_slime = new Slime(100, 200, slime_sprites, null_school);
		test_school = new School();
	}

	@After
	public void tearDown() throws Exception {
	}

	private Sprite[] slime_sprites;
	private Slime test_slime;
	private School null_school;
	private School test_school;
	
	@Test
	public void constructorTest() {
		assertEquals(test_slime.getX(), 100, Util.DEFAULT_EPSILON);
		assertEquals(test_slime.getY(), 200, Util.DEFAULT_EPSILON);
		assertArrayEquals(test_slime.getImages(), slime_sprites);
		assertEquals(test_slime.getSchool(), null_school);
	}
	
	@Test
	public void setSchoolTest() {
		test_slime.setSchool(test_school);
		assertEquals(test_slime.getSchool(), test_school);
	}
}