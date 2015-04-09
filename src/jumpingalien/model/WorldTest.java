package jumpingalien.model;

import static org.junit.Assert.*;

import java.util.List;

import jumpingalien.common.sprites.JumpingAlienSprites;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WorldTest {

	@Before
	public void setUp() throws Exception {
		world = new World(10, 20, 20, 200, 200, 19, 0);
		player = new Mazub(100, 100, JumpingAlienSprites.ALIEN_SPRITESET);
		world.setMazub(player);
	}
	
	private World world;
	private Mazub player;

	@Test
	public void collisionDetectTest() {
		
		List<List<List<Object>>> collisions = world.collisionDetect(player, 0);
		
	}

}
