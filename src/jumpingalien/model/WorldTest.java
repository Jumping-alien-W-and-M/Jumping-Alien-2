package jumpingalien.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.util.Sprite;

import org.junit.Before;
import org.junit.Test;

public class WorldTest {
	
	private World world;
	private Mazub player;
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@Before
	public void setUp() throws Exception {
		world = new World(10, 20, 20, 200, 200, 19, 0);
		player = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET);
		world.setMazub(player);
	}
	
	

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
	
	@Test
	public void TestgetTilePositionFourTiles(){
		int [][] tilepositions1 = {{0,0},{1,0},{0,1},{1,1}};
		assert(Arrays.deepEquals(tilepositions1, (world.getTilePositionsIn(1, 1, 15, 15))));
		
		int [][] tilepositions2 = {{5,5},{6,5},{5,6},{6,6}};
		assert(Arrays.deepEquals(tilepositions2, (world.getTilePositionsIn(51, 51, 65, 65))));
	}
	
	@Test
	public void TestgetTilePositionSixTiles(){
		int [][] tilepositions1 = {{0,0},{1,0},{2,0},{0,1},{1,1},{2,1}};
		assert(Arrays.deepEquals(tilepositions1, world.getTilePositionsIn(1, 1, 25, 15)));
	}
	
	@Test
	public void TestsetMazubPlayerNotEqualTonull(){
		assertEquals(world.getMazub(), player);
		assertEquals(player.getWorld(), world);		
	}
	
	@Test
	public void TestsetMazubPlayerEqualTonull(){
		world.setMazub(null);
		assertEquals(world.getMazub(), null);
	}
	
	@Test
	public void TestaddSharkValidshark(){
		Shark shark = new Shark(0, 0, sprites);
		world.addShark(shark);
		assert(world.getSharks().contains(shark));
		assertEquals(shark.getWorld(), world);
	}
	
	@Test
	public void TestaddSharksharkIsnull(){
		try {
			world.addShark(null);
		} catch (AssertionError err){
			assert(true);
			return;
		}
		assert(false);
	}
	
	@Test
	public void TestaddSharksharkIsDouble(){
		Shark shark = new Shark(0, 0, sprites);
		world.addShark(shark);
		try{
			world.addShark(shark);
		} catch(AssertionError err){
			assert(true);
			return;
		}
		assert(false);		
	}
	
	@Test
	public void TesthasAsSharksharkIsNotnull(){
		Shark shark = new Shark(0, 0, sprites);
		assert(! world.hasAsShark(shark));
		
		world.addShark(shark);
		assert(world.hasAsShark(shark));
	}
	
	@Test
	public void TesthasAsSharksharkIsnull(){
		try{
			world.hasAsShark(null);
		} catch(AssertionError err){
			assert(true);
			return;
		}
		assert(false);
	}
	
	@Test
	public void TestaddPlantValidplant(){
		Plant plant = new Plant(0, 0, sprites);
		world.addPlant(plant);
		assert(world.getPlants().contains(plant));
		assertEquals(plant.getWorld(), world);
	}
	
	@Test
	public void TestaddPlantplantIsnull(){
		try {
			world.addPlant(null);
		} catch (AssertionError err){
			assert(true);
			return;
		}
		assert(false);
	}
	
	@Test
	public void TestaddPlantplantIsDouble(){
		Plant plant = new Plant(0, 0, sprites);
		world.addPlant(plant);
		try{
			world.addPlant(plant);
		} catch(AssertionError err){
			assert(true);
			return;
		}
		assert(false);		
	}
	
	@Test
	public void TesthasAsPlantplantIsNotnull(){
		Plant plant = new Plant(0, 0, sprites);
		assert(! world.hasAsPlant(plant));
		
		world.addPlant(plant);
		assert(world.hasAsPlant(plant));
	}
	
	@Test
	public void TesthasAsPlantplantIsnull(){
		try{
			world.hasAsPlant(null);
		} catch(AssertionError err){
			assert(true);
			return;
		}
		assert(false);
	}
	
	@Test
	public void TestaddSchoolValidschool(){
		School school = new School();
		world.addSchool(school);
		assert(world.getSchools().contains(school));
		assertEquals(school.getWorld(), world);
	}
	
	@Test
	public void TestaddSchoolschoolIsnull(){
		try {
			world.addSchool(null);
		} catch (AssertionError err){
			assert(true);
			return;
		}
		assert(false);
	}
	
	@Test
	public void TestaddSchoolschoolIsDouble(){
		School school = new School();
		world.addSchool(school);
		try{
			world.addSchool(school);
		} catch(AssertionError err){
			assert(true);
			return;
		}
		assert(false);		
	}
	
	@Test
	public void TestaddSchoolToManySchools(){
		School school = new School();
		world.addSchool(school);
		school = new School();
		world.addSchool(school);
		school = new School();
		world.addSchool(school);
		school = new School();
		world.addSchool(school);
		school = new School();
		world.addSchool(school);
		school = new School();
		world.addSchool(school);
		school = new School();
		world.addSchool(school);
		school = new School();
		world.addSchool(school);
		school = new School();
		world.addSchool(school);
		school = new School();
		world.addSchool(school);
		school = new School();
		world.addSchool(school);
		assert(! world.hasAsSchool(school));
		assertEquals(school.getWorld(), null);
	}
	
	@Test
	public void TesthasAsSchoolschoolIsNotnull(){
		School school = new School();
		assert(! world.hasAsSchool(school));
		
		world.addSchool(school);
		assert(world.hasAsSchool(school));
	}
	
	@Test
	public void TesthasAsSchoolschoolIsnull(){
		try{
			world.hasAsSchool(null);
		} catch(AssertionError err){
			assert(true);
			return;
		}
		assert(false);
	}
	
	@Test
	public void TestgetSlimesNoSlimesInworld(){
		assertEquals(world.getSlimes(), new ArrayList<Slime>());
	}
	
	@Test
	public void TestgetSlimesOneSchool(){
		School school = new School();
		world.addSchool(school);
		Slime slime1 = new Slime(0, 0, sprites, school);
		Slime slime2 = new Slime(0, 0, sprites, school);
		Slime slime3 = new Slime(0, 0, sprites, school);
		ArrayList<Slime> slimes = new ArrayList<Slime>();
		slimes.add(slime1);
		slimes.add(slime2);
		slimes.add(slime3);
		assertEquals(slimes, world.getSlimes());
	}
	
	@Test 
	public void TestgetSlimesMultipleSchools(){
		School school1 = new School();
		world.addSchool(school1);
		Slime slime1 = new Slime(0, 0, sprites, school1);
		Slime slime2 = new Slime(0, 0, sprites, school1);
		Slime slime3 = new Slime(0, 0, sprites, school1);

		School school2 = new School();
		world.addSchool(school2);
		Slime slime4= new Slime(0, 0, sprites, school2);
		Slime slime5 = new Slime(0, 0, sprites, school2);
		Slime slime6 = new Slime(0, 0, sprites, school2);
		
		School school3 = new School();
		world.addSchool(school3);
		Slime slime7= new Slime(0, 0, sprites, school3);
		Slime slime8 = new Slime(0, 0, sprites, school3);
		Slime slime9 = new Slime(0, 0, sprites, school3);
		
		ArrayList<Slime> slimes = new ArrayList<Slime>();
		slimes.add(slime1);
		slimes.add(slime2);
		slimes.add(slime3);
		slimes.add(slime4);
		slimes.add(slime5);
		slimes.add(slime6);
		slimes.add(slime7);
		slimes.add(slime8);
		slimes.add(slime9);
		
		assertEquals(slimes, world.getSlimes());
	}
	
	
}
