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
		world = new World(10, 20, 20, 200, 200, 19, 0);
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
		assertEquals(player.getWidth(), player.getCurrentSprite().getWidth());
		player.setVx(2);
		assertEquals(player.getWidth(), player.getCurrentSprite().getWidth());
	}
	
	@Test
	public void TestgetHeigth(){
		assertEquals(player.getHeight(), player.getCurrentSprite().getHeight());
		player.setVy(2);
		assertEquals(player.getHeight(), player.getCurrentSprite().getHeight());
	}
	
	@Test
	public void TestadvanceTimeStepupdatingxpositionnewxwithinbounds(){
		player.setVx(1);
		player.advanceTimeStep(0.1);
		assertEquals(player.getX(),
				 (player.getVx()*0.1 + 1/2*player.getAx()*Math.pow(0.1, 2)), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepupdatingxpositionnewxoutsidebounds(){
		player.setVx(-1);
		player.setAx(-1);
		player.advanceTimeStep(0.1);
		assertEquals(player.getX(), 0, Util.DEFAULT_EPSILON);
		assertEquals(player.getWorld(), null);
		player.setWorld(world);
		player.setVx(1);
		player.setAx(1);
		player.setX(player.getWorld().getWorldWidth()-1);
		player.advanceTimeStep(0.1);
		assertEquals(player.getX(), player.getWorld().getWorldWidth()-1, Util.DEFAULT_EPSILON);
		assertEquals(player.getWorld(), null);
	}
	
	@Test
	public void TestadvanceTimeStepUpdatingXPositionWithoutWorld(){
		player.setWorld(null);
		player.setVx(1);
		player.advanceTimeStep(0.1);
		assertEquals(player.getX(),
				 (player.getVx()*0.1 + 1/2*player.getAx()*Math.pow(0.1, 2)), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestAdvanceTimeStepUpdatingXPositionWithCollision(){
		world.setFeature(100, 0, 1);
		player.setVx(1);
		player.setX((100 - player.getWidth() + 1));
		player.advanceTimeStep(0.1);
		assertEquals(player.getX(), (100 - player.getWidth()), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepupdatinghorizontalvelocitynewvxwithinbounds(){
		player.setAx(1);
		player.advanceTimeStep(0.01);
		assertEquals(player.getVx(), player.getVx() + player.getAx()*0.01, Util.DEFAULT_EPSILON);		
	}
	
	@Test
	public void TestadvanceTimeStepupdatinghorizontalvelocitynewvxoutsidebounds(){
		player.setAx(-1);
		player.setVx(-player.getVxmax());
		player.advanceTimeStep(0.1);
		assertEquals(player.getVx(), -player.getVxmax(), Util.DEFAULT_EPSILON);
		player.setAx(1);
		player.setVx(player.getVxmax());
		player.advanceTimeStep(0.1);
		assertEquals(player.getVx(), player.getVxmax(), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepupdatingypositionnewywithinbounds(){
			player.setVy(6);
			player.advanceTimeStep(0.1);
			assertEquals(player.getY(),
					player.getY() + player.getVy()*0.1 + 1/2*player.getAy()*Math.pow(0.1, 2), Util.DEFAULT_EPSILON);
		}		
	
	@Test
	public void TestadvanceTimeStepupdatingypositionnewyoutsidebounds(){
		player.setVy(-8);
		player.setAy(-10);
		player.advanceTimeStep(0.1);
		assertEquals(player.getY(), 0, Util.DEFAULT_EPSILON);
		player.setVy(1);
		player.setY(player.getWorld().getWorldHeight()-1);
		player.advanceTimeStep(0.1);
		assertEquals(player.getY(), player.getWorld().getWorldHeight()-1, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepUpdatingYPositionWithoutWorld(){
		player.setWorld(null);
		player.setVy(6);
		player.advanceTimeStep(0.1);
		assertEquals(player.getY(), 
				player.getY() + player.getVy()*0.1 + 1/2*player.getAy()*Math.pow(0.1, 2), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepUpdatingYPositionWithCollision(){
		world.setFeature(0, 100, 1);
		player.setY(100 - player.getHeight() + 1);
		player.setVy(3);
		player.advanceTimeStep(0.1);
		assertEquals(player.getY() ,(100 - player.getHeight()), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepupdatingverticalvelocitynewvywithinbounds(){
		player.setVy(7);
		player.setAy(-10);
		player.advanceTimeStep(0.1);
		assertEquals(player.getVy(), player.getVy() + player.getAy()*0.1, Util.DEFAULT_EPSILON);		
	}
	
	@Test
	public void TestadvanceTimeStepupdatingverticalacceleration(){
		player.advanceTime(0.1);
		assertEquals(player.getAy(), 0, Util.DEFAULT_EPSILON);
		player.setY(5);
		player.advanceTimeStep(0.1);
		assertEquals(player.getAy(), -10, Util.DEFAULT_EPSILON);
		player.setY(10);
		world.setFeature(0, 0, 1);
		player.advanceTimeStep(0.1);
		assertEquals(player.getAx(), 0, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepBreakCollisionRight(){
		world.setFeature(100,10,1);
		player.setX((100 - player.getWidth() + 1));
		player.setAy(0);
		player.setVy(3);
		player.advanceTimeStep(0.1);
		assertEquals (player.getAy(), -10, Util.DEFAULT_EPSILON); 
		assertEquals(player.getVy(), 0, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepBreakCollisionLeft(){
		world.setFeature(100,10,1);
		player.setX(100 + world.getTileSize());
		player.setAy(0);
		player.setVy(3);
		player.advanceTimeStep(0.1);
		assertEquals(player.getAy(), -10, Util.DEFAULT_EPSILON);
		assertEquals(player.getVy(), 0, Util.DEFAULT_EPSILON);
	}
	
	
}
