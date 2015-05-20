package jumpingalien.model;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class BuzamTest {

	@Before
	public void setUp() throws Exception {
		world = new World(10, 200, 200, 200, 200, 19, 0);
		this.buzam = new Buzam(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, null);
		world.setBuzam(buzam);
	}
	
	private World world;
	private Buzam buzam;

	@Test
	public void TestBasicConstructorCorrectParameters() {
		assertEquals(buzam.getX(), 0, Util.DEFAULT_EPSILON);
		assertEquals(buzam.getY(), 0, Util.DEFAULT_EPSILON);
		assertArrayEquals(buzam.getImages(), JumpingAlienSprites.ALIEN_SPRITESET);
		assertEquals(buzam.getVx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(buzam.getVxmax(), 3, Util.DEFAULT_EPSILON);
		assertEquals(buzam.getVy(), 0, Util.DEFAULT_EPSILON);
		assertEquals(buzam.getAx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(buzam.getAxi(), 0.9, Util.DEFAULT_EPSILON);
		assertEquals(buzam.getAy(), 0, Util.DEFAULT_EPSILON);

		assertEquals(buzam.getLastMove(), 0, Util.DEFAULT_EPSILON);

		assertEquals(buzam.getHitpoints(), 500, Util.DEFAULT_EPSILON);
	}
	
}
