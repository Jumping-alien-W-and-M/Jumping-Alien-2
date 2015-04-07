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
	public void TestIsValidXWithWorldNotEqualTonull() {		
		assert(player.isValidX(-1));
		assert(player.isValidX(0));
		assert(player.isValidX(199.1));
		assert(player.isValidX(200));
	}
	
	@Test
	public void TestIsValidYWithWorldNotEqualTonull(){
		assert(player.isValidY(-1));
		assert(player.isValidY(0));
		assert(player.isValidY(199.5));
		assert(player.isValidY(200));		
	}
	
	
	@Test
	public void TestIsValidXWithWorldEqualTonull() {		
		assert(player.isValidX(-0.1));
		assert(player.isValidX(0));
	}
	
	@Test
	public void TestIsValidYWithWorldEqualTonull(){		
		assert(player.isValidY(-0.1));
		assert(player.isValidY(0));	
	}
	
	@Test
	public void TestIsValidVxWithValidVx(){
		assert(player.isValidVx(player.getVxmax()));
		assert(player.isValidVx(- player.getVxmax()));
		assert(player.isValidVx(player.getVxi()));
		assert(player.isValidVx(- player.getVxi()));
		assert(player.isValidVx(0));
	}
	
	@Test
	public void TestIsValidVxWithNonValidVx(){
		assert(! player.isValidVx(player.getVxmax() + 2));
		assert(! player.isValidVx(- player.getVxmax() - 2));
	}
	
	@Test
	public void TestisValidAxvalidAx(){
		assert(player.isValidAx(0));
		assert(player.isValidAx(player.getAxi()));
		assert(player.isValidAx(- player.getAxi()));
	}
	
	@Test
	public void TestisValidAxillegalAx(){
		assert(!player.isValidAx(player.getAxi() + 2));
	}
	
	@Test
	public void TestisValidAyvalidAy(){
		assert(player.isValidAy(0));
		assert(player.isValidAy(-10));
	}
	
	@Test
	public void TestisValidAyIllegalAy(){
		assert(! player.isValidAy(5));
	}
	
	@Test
	public void TestgetWidth(){
		assert(player.getWidth() == player.getCurrentSprite().getWidth());
		player.setVx(2);
		assert(player.getWidth() == player.getCurrentSprite().getWidth());
	}
	
	@Test
	public void TestgetHeigth(){
		assert(player.getHeight() == player.getCurrentSprite().getHeight());
		player.setVy(2);
		assert(player.getHeight() == player.getCurrentSprite().getHeight());
	}
	
	@Test
	public void TestadvanceTimeupdatingxpositionnewxwithinbounds(){
		player.setVx(1);
		player.advanceTime(0.1);
		assert(player.getX() == 
				 (player.getVx()*0.1 + 1/2*player.getAx()*Math.pow(0.1, 2)));
	}
	
	@Test
	public void TestadvanceTimeupdatingxpositionnewxoutsidebounds(){
		player.setVx(-1);
		player.setAx(-1);
		player.advanceTime(0.1);
		assert(player.getX() == 0);
		assert(player.getWorld() == null);
		player.setWorld(world);
		player.setVx(1);
		player.setAx(1);
		player.setX(player.getWorld().getWorldWidth()-1);
		player.advanceTime(0.1);
		assert(player.getX() == player.getWorld().getWorldWidth()-1);
		assert(player.getWorld() == null);
	}
	
	@Test
	public void TestadvanceTimeUpdatingXPositionWithoutWorld(){
		player.setWorld(null);
		player.setVx(1);
		player.advanceTime(0.1);
		assert(player.getX() == 
				 (player.getVx()*0.1 + 1/2*player.getAx()*Math.pow(0.1, 2)));
	}
	
	
	
	
}
