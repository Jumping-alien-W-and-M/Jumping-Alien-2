package jumpingalien.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.util.Util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WorldTest {

	@Before
	public void setUp() throws Exception {
		world = new World(10, 20, 20, 200, 200, 19, 0);
		player = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET);
		world.setMazub(player);
	}
	
	private World world;
	private Mazub player;

	@Test
	public void TestConstructor() {
		assertEquals(world.getTileSize(), 10); 
		assertEquals(world.getWorldWidth(), 20 * world.getTileSize());
		assertEquals(world.getWorldHeight(), 20 * world.getTileSize());
		assertEquals(world.getHashDigitsAmount(), (int) Math.floor(Math.log10(20) + 1));
		assertEquals(world.getWindowWidth(), 200);
		assertEquals(world.getWindowHeight(), 200);
		assertEquals(world.getXWindow(), 0);
		assertEquals(world.getYWindow(), 0);
		assertEquals(world.getXTarget(), 19);
		assertEquals(world.getYTarget(), 0);
	}
	
	@Test
	public void TestgetProperXWindowProperXWindowWithinBounds(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(player);
		player.setX(400);
		assertEquals(world.getProperXWindow(100), 100);
		assertEquals(world.getProperXWindow(200), 200);
	}
	
	@Test
	public void TestGetProperXWindowProperXWindowOutsideBounds(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(player);
		player.setX(5);
		assertEquals(world.getProperXWindow(-10), 0);
		player.setX(800);
		assertEquals(world.getProperXWindow(550), 500);
	}
	
	@Test
	public void TestgetProperXWindowToCloseToLeftWithinBounds(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(player);
		player.setX(400);
		assertEquals(world.getProperXWindow(300), 200);
	}
	
	@Test 
	public void TestgetProperXWindowToCloseToLeftOutsideBounds(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(player);
		player.setX(100);
		assertEquals(world.getProperXWindow(50), 0);
	}
	
	@Test
	public void TestgetProperXWindowToCloseToRigthWithinBounds(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(player);
		player.setX(700);
		assertEquals(world.getProperXWindow(300), 400);
	}
	
	@Test
	public void TestgetProperXWindowToCloseToRigthOutsideBounds(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(player);
		player.setX(900);
		assertEquals(world.getProperXWindow(300), 500);
	}
	
	@Test
	public void TestgetProperXWindowNoPlayer(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(null);
		assertEquals(world.getProperXWindow(900), 0);
		assertEquals(world.getProperXWindow(5), 0);
		assertEquals(world.getProperXWindow(200), 0);
	}
	
	@Test
	public void TestgetProperYWindowProperYWindowWithinBounds(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(player);
		player.setY(400);
		assertEquals(world.getProperYWindow(100), 100);
		assertEquals(world.getProperYWindow(200), 200);
	}
	
	@Test
	public void TestGetProperYWindowProperYWindowOutsideBounds(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(player);
		player.setY(5);
		assertEquals(world.getProperYWindow(-10), 0);
		player.setY(800);
		assertEquals(world.getProperYWindow(550), 500);
	}
	
	@Test
	public void TestgetProperYWindowToCloseToBottomWithinBounds(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(player);
		player.setY(400);
		assertEquals(world.getProperYWindow(300), 200);
	}

	@Test 
	public void TestgetProperYWindowToCloseToBottomOutsideBounds(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(player);
		player.setY(100);
		assertEquals(world.getProperYWindow(150), 0);
	}
	
	@Test
	public void TestgetProperYWindowToCloseToTopWithinBounds(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(player);
		player.setY(700);
		assertEquals(world.getProperYWindow(300), 400);
	}
	
	@Test
	public void TestgetProperYWindowToCloseToTopOutsideBounds(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(player);
		player.setY(900);
		assertEquals(world.getProperYWindow(300), 500);
	}
	
	@Test
	public void TestgetProperYWindowNoPlayer(){
		world = new World(10, 100, 100, 500, 500, 99, 0);
		world.setMazub(null);
		assertEquals(world.getProperYWindow(900), 0);
		assertEquals(world.getProperYWindow(5), 0);
		assertEquals(world.getProperYWindow(200), 0);
	}
	
	@Test
	public void TestisGameOverGameOver(){
		assertEquals(world.isGameOver(), world.touchesTarget());
		player.setX(195);
		assertEquals(world.isGameOver(), world.touchesTarget());
		world.setMazub(null);
		assert(world.isGameOver());
	}
	
	@Test
	public void TestisGameOverNotGameOver(){
		assert(! world.isGameOver());
	}
	
	@Test
	public void TesttouchesTargetTouchesTarget(){
		player.setX(195);
		assert(world.touchesTarget());
		player.setX(190);
		assert(world.touchesTarget());
		player.setX(199);
		assert(world.touchesTarget());
		player.setY(9);
		assert(world.touchesTarget());
	}
	
	@Test
	public void TestTouchesTargetnotTouchingTarget(){
		assert(! world.touchesTarget());
		player.setX(189);
		assert(! world.touchesTarget());
		player.setY(10);
		assert(! world.touchesTarget());
	}
	
	@Test
	public void TestgetTilePositionsInOneTile(){
		int [][] tilepositions = {{0,0}};
		assert(Arrays.deepEquals(tilepositions, (world.getTilePositionsIn(1, 1, 5, 5))));
	}
}
