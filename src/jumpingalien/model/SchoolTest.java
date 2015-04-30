package jumpingalien.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.util.Sprite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SchoolTest {

	@Before
	public void setUp() throws Exception {
		null_school = new School();
		test_school = new School();
		test_world = new World(20, 50, 50, 500, 500, 1, 1);
	}

	@After
	public void tearDown() throws Exception {
	}

	private School null_school;
	private School test_school;
	private World test_world;
	
	@Test
	public void worldTest() {
		test_school.setWorld(test_world);
		assertEquals(test_school.getWorld(), test_world);
	}

	@Test
	public void SchoolSlimeAssociationTest() {
		test_school.setWorld(test_world);
		
		assertEquals(test_school.getSlimes(), new ArrayList<Slime>());
		
		ArrayList<Slime> slimes = new ArrayList<Slime>();
		Sprite[] slime_sprites = new Sprite[2];
		slime_sprites[0] = JumpingAlienSprites.ALIEN_SPRITESET[0];
		slime_sprites[1] = JumpingAlienSprites.ALIEN_SPRITESET[1];
		
		for(int i = 0; i < 25; i++) {
			assertEquals(test_school.getNbOfSlimes(), i);
			Slime slime = new Slime(100, 100, slime_sprites, null_school, null);
			test_school.addSlime(slime);
			assertEquals(test_school.hasAsSlime(slime), true);
		}
		Slime extra_slime = new Slime(100, 100, slime_sprites, null_school, null);
		assertEquals(test_school.hasAsSlime(extra_slime), false);
		assertEquals(test_school.getNbOfSlimes(), 25);
		
		for(Slime slime : test_school.getSlimes()) {
			assertEquals(slime.getSchool(), test_school);
			assertEquals(slime.getWorld(), test_world);
			slimes.remove(slime);
		}
		assertEquals(slimes, new ArrayList<Slime>());

		for(int i = 0; i < 5; i++) {
			assertEquals(test_school.getNbOfSlimes(), 25 - i);
			Slime slime = test_school.getSlimes().get(0);
			test_school.removeSlime(slime);
			assertEquals(test_school.hasAsSlime(slime), false);
			assertEquals(slime.getSchool(), null);
		}
	}

}