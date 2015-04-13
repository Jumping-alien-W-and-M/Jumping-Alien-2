package jumpingalien.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public void TestConstructorLegalParameters() {
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
	public void TestConstructorIllegalParameters(){
		try{
			world = new World(0, 20, 20, 200, 200, 19, 0);
		} catch( AssertionError err0){
			try{
				world = new World(10, 0, 20, 200, 200, 19, 0);
			} catch( AssertionError err1){
				try{
					world = new World(10, 20, 0, 200, 200, 19, 0);
				} catch( AssertionError err2){
					try{
						world = new World(10, 20, 20, 200, 200, -5, 0);
					} catch( AssertionError err3){
						try{
							world = new World(10, 20, 20, 200, 200, 19, -5);
						} catch( AssertionError err4){
							try{
								world = new World(10, 20, 20, 200, 200, 21, 0);
							} catch(AssertionError err5){
								try{
									world = new World(10, 20, 20, 200, 200, 19, 21);
								} catch(AssertionError err6){
									assert(true);
									return;
								}
							}							
						}
					}
				}
			}
		}
		assert(false);
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
	public void TestgetTilePositionsInFourTiles(){
		int [][] tilepositions1 = {{0,0},{1,0},{0,1},{1,1}};
		assert(Arrays.deepEquals(tilepositions1, (world.getTilePositionsIn(1, 1, 15, 15))));
		
		int [][] tilepositions2 = {{5,5},{6,5},{5,6},{6,6}};
		assert(Arrays.deepEquals(tilepositions2, (world.getTilePositionsIn(51, 51, 65, 65))));
	}
	
	@Test
	public void TestgetTilePositionsInSixTiles(){
		int [][] tilepositions1 = {{0,0},{1,0},{2,0},{0,1},{1,1},{2,1}};
		assert(Arrays.deepEquals(tilepositions1, world.getTilePositionsIn(1, 1, 25, 15)));
	}
	
	@Test
	public void TestgetTilePositionsInY2OutOfBounds(){
		int[][] tilepositions = {{0,17},{1,17},{0,18},{1,18},{0,19},{1,19}};
		assert(Arrays.deepEquals(tilepositions, world.getTilePositionsIn(5, 175, 15, 250)));
	}
	
	@Test
	public void TestgetTilePositionsInX2OutOfBounds(){
		int[][] tilepositions = {{17,0},{18,0},{19,0},{17,1},{18,1},{19,1}};
		assert(Arrays.deepEquals(tilepositions, world.getTilePositionsIn(175, 5, 300, 15)));
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
	
	@Test
	public void TestgetHashNoAssertionErrors(){
		assertEquals(world.getHash(19, 19), (((int) Math.pow(10, world.getHashDigitsAmount()))* 19 + 19));
	}
	
	@Test
	public void TestgetHashXOutsideBounds(){
		try{
			world.getHash(world.getWorldWidth() / world.getTileSize(), 5);
		} catch( AssertionError err){
			try{
				world.getHash(world.getWorldWidth()/ world.getTileSize() + 100, 50);
			} catch( AssertionError err1){			
				assert(true);
				return;
			}
		}
		assert(false);
	}
	
	@Test
	public void TestgetHashYOutsideBounds(){
		try{
			world.getHash( 5, world.getWorldHeight() / world.getTileSize());
		} catch( AssertionError err){
			try{
				world.getHash( 10, world.getWorldHeight() / world.getTileSize() + 50);
			} catch( AssertionError err1){			
				assert(true);
				return;
			}
		}
		assert(false);
	}
	
	@Test
	public void TestgetFeatureFeatureInFeatures(){
		world.setFeature(50, 50, 1);
		assertEquals(world.getFeature(50, 50), 
					world.getFeatures().get(world.getHash(world.getTilePos(50), world.getTilePos(50))));
	}
	
	@Test
	public void TestgetFeatureFeatureNotInFeatures(){
		assertEquals(world.getFeature(50, 50), Feature.air);
	}
	
	@Test
	public void TestsetFeatureGroundMagmaAndWater(){
		world.setFeature(150, 160, 1);
		assertEquals(world.getFeature(150, 160), Feature.ground);
		world.setFeature(150, 160, 2);
		assertEquals(world.getFeature(150, 160), Feature.water);
		world.setFeature(150, 160, 3);
		assertEquals(world.getFeature(150, 160), Feature.magma);
	}
	
	@Test
	public void TestsetFeatureAir(){
		world.setFeature(150, 160, 0);
		assertEquals(world.getFeature(150, 160), Feature.air);
		world.setFeature(150, 160, -5);
		assertEquals(world.getFeature(150, 160), Feature.air);
		world.setFeature(150, 160, 7);
		assertEquals(world.getFeature(150, 160), Feature.air);
	}
	
	@Test
	public void TestcollisionDetectMazubObjectsLeft(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		player.setX(world.getWorldWidth() - 5);
		Shark shark = new Shark(0, 0, sprites);
		shark.setX(player.getX() - shark.getWidth() + 1);
		world.addShark(shark);
		Shark shark1 = new Shark(shark.getX(), 0, sprites);
		world.addShark(shark1);
		Plant plant = new Plant(0, 0 , sprites);
		plant.setX(player.getX() - plant.getWidth() + 1);
		world.addPlant(plant);
		collisions.get(0).get(0).add(shark);
		collisions.get(0).get(0).add(shark1);
		collisions.get(0).get(0).add(plant);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	
	@Test
	public void TestcollisionDetectMazubObjectsRight(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		Shark shark = new Shark(player.getX() + player.getWidth() - 1, 0, sprites);
		world.addShark(shark);
		Shark shark1 = new Shark(player.getX() + player.getWidth() - 1, 0, sprites);
		world.addShark(shark1);
		Plant plant = new Plant(player.getX() + player.getWidth() - 1, 0, sprites);
		world.addPlant(plant);
		collisions.get(2).get(0).add(shark);
		collisions.get(2).get(0).add(shark1);
		collisions.get(2).get(0).add(plant);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectMazubObjectsUp(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		Shark shark = new Shark(0, player.getY() + player.getHeight() - 1, sprites);
		world.addShark(shark);
		Shark shark1 = new Shark(0, player.getY() + player.getHeight() - 1, sprites);
		world.addShark(shark1);
		Plant plant = new Plant(0, player.getY() + player.getHeight() - 1, sprites);
		world.addPlant(plant);
		collisions.get(1).get(0).add(shark);
		collisions.get(1).get(0).add(shark1);
		collisions.get(1).get(0).add(plant);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectMazubObjectsUnder(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		player.setY(world.getWorldHeight() - 5 );
		Shark shark = new Shark(0, 0, sprites);
		shark.setY(player.getY() - shark.getHeight() + 1);
		world.addShark(shark);
		Shark shark1 = new Shark(0, shark.getY(), sprites);
		world.addShark(shark1);
		Plant plant = new Plant(0, 0, sprites);
		plant.setY(player.getY() - plant.getHeight() + 1);
		world.addPlant(plant);
		collisions.get(3).get(0).add(shark);
		collisions.get(3).get(0).add(shark1);
		collisions.get(3).get(0).add(plant);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectMazubObjetsUpperRight(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		Shark shark = new Shark(player.getX() + player.getWidth() - 1, player.getY() + player.getHeight() - 1, sprites);
		world.addShark(shark);
		Shark shark1 = new Shark(player.getX() + player.getWidth() - 1, player.getY() + player.getHeight() - 1, sprites);
		world.addShark(shark1);
		Plant plant = new Plant(player.getX() + player.getWidth() - 1,  player.getY() + player.getHeight() - 1, sprites);
		world.addPlant(plant);
		collisions.get(5).get(0).add(shark);
		collisions.get(5).get(0).add(shark1);
		collisions.get(5).get(0).add(plant);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectMazubObjectsBottomRight(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		player.setY(world.getWorldHeight() - 5);
		Shark shark = new Shark(player.getX() + player.getWidth() - 1, 0, sprites);
		shark.setY(player.getY() - shark.getHeight() + 1);
		world.addShark(shark);
		Shark shark1 = new Shark(player.getX() + player.getWidth() - 1, shark.getY(), sprites);
		world.addShark(shark1);
		Plant plant = new Plant(player.getX() + player.getWidth() - 1, 0, sprites);
		plant.setY( player.getY() - plant.getHeight() +1 );
		world.addPlant(plant);
		collisions.get(6).get(0).add(shark);
		collisions.get(6).get(0).add(shark1);
		collisions.get(6).get(0).add(plant);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectMazubObjectsUpperLeft(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		player.setX(world.getWorldWidth() - 5);
		Shark shark = new Shark(0, player.getY() + player.getHeight() - 1, sprites);
		shark.setX(player.getX() - shark.getWidth() + 1);
		world.addShark(shark);
		Shark shark1 = new Shark(shark.getX(), player.getY() + player.getHeight() - 1, sprites);
		world.addShark(shark1);
		Plant plant = new Plant(0, player.getY() + player.getHeight() - 1, sprites);
		plant.setX( player.getX() - plant.getWidth() +1 );
		world.addPlant(plant);
		collisions.get(4).get(0).add(shark);
		collisions.get(4).get(0).add(shark1);
		collisions.get(4).get(0).add(plant);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectMazubObjestBottomLeft(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		player.setX(world.getWorldWidth() - 5);
		player.setY(world.getWorldHeight() - 5);
		Shark shark = new Shark(0, 0, sprites);
		shark.setX(player.getX() - shark.getWidth() + 1);
		shark.setY(player.getY() - shark.getHeight() + 1);
		world.addShark(shark);
		Shark shark1 = new Shark(shark.getX(), shark.getY(), sprites);
		world.addShark(shark1);
		Plant plant = new Plant(0, 0, sprites);
		plant.setX( player.getX() - plant.getWidth() +1 );
		plant.setY(player.getY() - plant.getHeight() + 1);
		world.addPlant(plant);
		collisions.get(7).get(0).add(shark);
		collisions.get(7).get(0).add(shark1);
		collisions.get(7).get(0).add(plant);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectSlimeAndSharkObjectsLeft(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Shark shark = new Shark(0, 0, sprites);
		shark.setX(world.getWorldWidth() - 5);
		world.addShark(shark);
		player.setX(shark.getX() - player.getWidth() + 1);
		Shark shark1 = new Shark(0, 0, sprites);
		shark1.setX(shark.getX() - shark1.getWidth() + 1);
		world.addShark(shark1);
		Plant plant = new Plant(0, 0 , sprites);
		plant.setX(shark.getX() - plant.getWidth() + 1);
		world.addPlant(plant);
		collisions.get(0).get(0).add(player);
		collisions.get(0).get(0).add(shark1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(shark, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectSlimeAndSharkObjectsRight(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Shark shark = new Shark(0, 0, sprites);
		world.addShark(shark);
		player.setX(shark.getX() + shark.getWidth() - 1);
		Shark shark1 = new Shark(shark.getX() + shark.getWidth() - 1, 0, sprites);
		world.addShark(shark1);
		Plant plant = new Plant(shark.getX() + shark.getWidth() - 1, 0 , sprites);
		world.addPlant(plant);
		collisions.get(2).get(0).add(player);
		collisions.get(2).get(0).add(shark1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(shark, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectSlimeAndSharkObjectsUp(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Shark shark = new Shark(0, 0, sprites);
		world.addShark(shark);
		player.setY(shark.getY() + shark.getHeight() - 1);
		Shark shark1 = new Shark(0, shark.getY() + shark.getHeight() - 1, sprites);
		world.addShark(shark1);
		Plant plant = new Plant(0, shark.getY() + shark.getHeight() - 1 , sprites);
		world.addPlant(plant);
		collisions.get(1).get(0).add(player);
		collisions.get(1).get(0).add(shark1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(shark, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectSlimeAndSharkObjectsUnder(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Shark shark = new Shark(0, 0, sprites);
		world.addShark(shark);
		shark.setY(world.getWorldHeight() - 5);
		player.setY(shark.getY() - player.getHeight() + 1);
		Shark shark1 = new Shark(0, 0, sprites);
		shark1.setY(shark.getY() - shark1.getHeight() + 1);
		world.addShark(shark1);
		Plant plant = new Plant(0, 0, sprites);
		plant.setY(shark.getY() - plant.getHeight() + 1);
		world.addPlant(plant);
		collisions.get(3).get(0).add(player);
		collisions.get(3).get(0).add(shark1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(shark, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectSlimeAndSharkObjectsUpperRight(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Shark shark = new Shark(0, 0, sprites);
		world.addShark(shark);
		player.setY(shark.getY() + shark.getHeight() - 1);
		player.setX(shark.getX() + shark.getWidth() - 1);
		Shark shark1 = new Shark(shark.getX() + shark.getWidth() - 1, shark.getY() + shark.getHeight() - 1, sprites);
		world.addShark(shark1);
		Plant plant = new Plant(shark.getX() + shark.getWidth() - 1, shark.getY() + shark.getHeight() - 1, sprites);
		world.addPlant(plant);
		collisions.get(5).get(0).add(player);
		collisions.get(5).get(0).add(shark1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(shark, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectSlimeAndSharkObjectsBottomRight(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Shark shark = new Shark(0, 0, sprites);
		world.addShark(shark);
		shark.setY(world.getWorldHeight() - 5);
		player.setY(shark.getY() - player.getHeight() + 1);
		player.setX(shark.getX() + shark.getWidth() - 1);
		Shark shark1 = new Shark(shark.getX() + shark.getWidth() - 1, 0, sprites);
		shark1.setY(shark.getY() - shark1.getHeight() + 1);
		world.addShark(shark1);
		Plant plant = new Plant(shark.getX() + shark.getWidth() - 1, 0, sprites);
		plant.setY(shark.getY() - plant.getHeight() + 1);
		world.addPlant(plant);
		collisions.get(6).get(0).add(player);
		collisions.get(6).get(0).add(shark1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(shark, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectSlimeAndSharkObjectsUpperLeft(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Shark shark = new Shark(0, 0, sprites);
		world.addShark(shark);
		shark.setX(world.getWorldWidth() - 5);
		player.setY(shark.getY() + shark.getHeight() - 1);
		player.setX(shark.getX() - player.getWidth() + 1);
		Shark shark1 = new Shark(0, shark.getY() + shark.getHeight() - 1, sprites);
		shark1.setX(shark.getX() - shark1.getWidth() + 1);
		world.addShark(shark1);
		Plant plant = new Plant(0, shark.getY() + shark.getHeight() - 1, sprites);
		plant.setX(shark.getX() - plant.getWidth() + 1);
		world.addPlant(plant);
		collisions.get(4).get(0).add(player);
		collisions.get(4).get(0).add(shark1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(shark, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectSlimeAndSharkObjecstBottomLeft(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Shark shark = new Shark(0, 0, sprites);
		world.addShark(shark);
		shark.setX(world.getWorldWidth() - 5);
		shark.setY(world.getWorldHeight() - 5);
		player.setY(shark.getY() - player.getHeight() + 1);
		player.setX(shark.getX() - player.getWidth() + 1);
		Shark shark1 = new Shark(0, 0, sprites);
		shark1.setX(shark.getX() - shark1.getWidth() + 1);
		shark1.setY(shark.getY() - shark1.getHeight() + 1);
		world.addShark(shark1);
		Plant plant = new Plant(0, 0, sprites);
		plant.setX(shark.getX() - plant.getWidth() + 1);
		plant.setY(shark.getY() - plant.getHeight() + 1);
		world.addPlant(plant);
		collisions.get(7).get(0).add(player);
		collisions.get(7).get(0).add(shark1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(shark, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}		
	}
	
	@Test
	public void TestcollisionDetectPlantObjectsLeft(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Plant plant = new Plant(0, 0, sprites);
		world.addPlant(plant);
		plant.setX(world.getWorldWidth() - 5);
		player.setX(plant.getX() - player.getWidth() + 1);
		Shark shark = new Shark(0, 0, sprites);
		shark.setX(plant.getX() - shark.getWidth() + 1);
		world.addShark(shark);
		Plant plant1 = new Plant(0, 0 , sprites);
		plant1.setX(plant.getX() - plant1.getWidth() + 1);
		world.addPlant(plant1);
		collisions.get(0).get(0).add(player);
		collisions.get(0).get(0).add(plant1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(plant, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectPlantObjectsRight(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Plant plant = new Plant(0, 0, sprites);
		world.addPlant(plant);
		player.setX(plant.getX() + plant.getWidth() - 1);
		Shark shark = new Shark(plant.getX() + plant.getWidth() - 1, 0, sprites);
		world.addShark(shark);
		Plant plant1 = new Plant(plant.getX() + plant.getWidth() - 1, 0 , sprites);
		world.addPlant(plant1);
		collisions.get(2).get(0).add(player);
		collisions.get(2).get(0).add(plant1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(plant, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}
	}
	
	@Test
	public void TestcollisionDetectPlantObjectsUp(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Plant plant = new Plant(0, 0, sprites);
		world.addPlant(plant);
		player.setY(plant.getY() + plant.getHeight() - 1);
		Shark shark = new Shark(0, plant.getY() + plant.getHeight() - 1, sprites);
		world.addShark(shark);
		Plant plant1 = new Plant(0, plant.getY() + plant.getHeight() - 1, sprites);
		world.addPlant(plant1);
		collisions.get(1).get(0).add(player);
		collisions.get(1).get(0).add(plant1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(plant, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}	
	}
	
	@Test
	public void TestcollisionDetectPlansObjectsUnder(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Plant plant = new Plant(0, 0, sprites);
		world.addPlant(plant);
		plant.setY(world.getWorldHeight() - 5);
		player.setY(plant.getY() - player.getHeight() + 1);
		Shark shark = new Shark(0, 0, sprites);
		shark.setY(plant.getY() - shark.getHeight() + 1);
		world.addShark(shark);
		Plant plant1 = new Plant(0, 0, sprites);
		plant1.setY(plant.getY() - plant1.getHeight() + 1);
		world.addPlant(plant1);
		collisions.get(3).get(0).add(player);
		collisions.get(3).get(0).add(plant1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(plant, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}	
	}
	
	@Test
	public void TestcollisionDetectPlantObjectsUpperRight(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Plant plant = new Plant(0, 0, sprites);
		world.addPlant(plant);
		player.setY(plant.getY() + plant.getHeight() - 1);
		player.setX(plant.getX() + plant.getWidth() - 1);
		Shark shark = new Shark(plant.getX() + plant.getWidth() - 1, plant.getY() + plant.getHeight() - 1, sprites);
		world.addShark(shark);
		Plant plant1 = new Plant(plant.getX() + plant.getWidth() - 1, plant.getY() + plant.getHeight() - 1, sprites);
		world.addPlant(plant1);
		collisions.get(5).get(0).add(player);
		collisions.get(5).get(0).add(plant1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(plant, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}	
	}
	
	@Test
	public void TestcollisionDetectPlantObjectsBottomRight(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Plant plant = new Plant(0, 0, sprites);
		world.addPlant(plant);
		plant.setY(world.getWorldHeight() - 5);
		player.setY(plant.getY() - player.getHeight() + 1);
		player.setX(plant.getX() + plant.getWidth() - 1);
		Shark shark = new Shark(plant.getX() + plant.getWidth() - 1, 0, sprites);
		shark.setY(plant.getY() - shark.getHeight() + 1);
		world.addShark(shark);
		Plant plant1 = new Plant(plant.getX() + plant.getWidth() - 1, 0, sprites);
		plant1.setY(plant.getY() - plant1.getHeight() + 1);
		world.addPlant(plant1);
		collisions.get(6).get(0).add(player);
		collisions.get(6).get(0).add(plant1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(plant, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}	
	}
	
	@Test
	public void TestcollisionDetectPlantObjectsUpperLeft(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Plant plant = new Plant(0, 0, sprites);
		world.addPlant(plant);
		plant.setX(world.getWorldWidth() - 5);
		player.setY(plant.getY() + plant.getHeight() - 1);
		player.setX(plant.getX() - player.getWidth() + 1);
		Shark shark = new Shark(0, plant.getY() + plant.getHeight() - 1, sprites);
		shark.setX(plant.getX() - shark.getWidth() + 1);
		world.addShark(shark);
		Plant plant1 = new Plant(0, plant.getY() + plant.getHeight() - 1, sprites);
		plant1.setX(plant.getX() - plant1.getWidth() + 1);
		world.addPlant(plant1);
		collisions.get(4).get(0).add(player);
		collisions.get(4).get(0).add(plant1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(plant, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}	
	}
	
	@Test
	public void TestcollisionDetectPlantObjectsBottomLeft(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}		
		Plant plant = new Plant(0, 0, sprites);
		world.addPlant(plant);
		plant.setX(world.getWorldWidth() - 5);
		plant.setY(world.getWorldHeight() - 5);
		player.setY(plant.getY() - player.getHeight() + 1);
		player.setX(plant.getX() - player.getWidth() + 1);
		Shark shark = new Shark(0, 0, sprites);
		shark.setX(plant.getX() - shark.getWidth() + 1);
		shark.setY(plant.getY() - shark.getHeight() + 1);
		world.addShark(shark);
		Plant plant1 = new Plant(0, 0, sprites);
		plant1.setX(plant.getX() - plant1.getWidth() + 1);
		plant1.setY(plant.getY() - plant1.getHeight() + 1);
		world.addPlant(plant1);
		collisions.get(7).get(0).add(player);
		collisions.get(7).get(0).add(plant1);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(plant, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(0), collisions.get(i).get(0));
		}	
	}
	
	@Test
	public void TestcollisionDetectGameObjectFeatureLeft(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		player.setX(9);
		collisions.get(0).get(1).add(Feature.air);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(1), collisions.get(i).get(1));
		}	
	}
	
	@Test
	public void TestcollisionDetectGameObjectFeatureRight(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		player.setX(100 - player.getWidth() + 1);
		collisions.get(0).get(1).add(Feature.air);
		collisions.get(2).get(1).add(Feature.air);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(1), collisions.get(i).get(1));
		}	
	}
	
	@Test
	public void TestcollisionDetectGameObjectFeatureUp(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		player.setY(100 - player.getHeight() + 1);
		collisions.get(0).get(1).add(Feature.air);
		collisions.get(1).get(1).add(Feature.air);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(1), collisions.get(i).get(1));
		}	
	}
	
	@Test
	public void TestcollisionDetectGameObjectFeatureUnders(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		player.setY(9);
		collisions.get(0).get(1).add(Feature.air);
		collisions.get(3).get(1).add(Feature.air);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(1), collisions.get(i).get(1));
		}	
	}
	
	@Test
	public void TestcollisionDetectGameObjectFeatureUpperRight(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		player.setX(100 - player.getWidth() + 1);
		player.setY(100 - player.getHeight() + 1);
		collisions.get(0).get(1).add(Feature.air);
		collisions.get(5).get(1).add(Feature.air);
		collisions.get(1).get(1).add(Feature.air);
		collisions.get(2).get(1).add(Feature.air);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(1), collisions.get(i).get(1));
		}	
	}
	
	@Test
	public void TestcollisionDetectGameObjectFeatureBottomRight(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		player.setX(100 - player.getWidth() + 1);
		player.setY(9);
		collisions.get(0).get(1).add(Feature.air);
		collisions.get(6).get(1).add(Feature.air);
		collisions.get(3).get(1).add(Feature.air);
		collisions.get(2).get(1).add(Feature.air);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(1), collisions.get(i).get(1));
		}	
	}
	
	@Test
	public void TestcollisionDetectGameObjectFeatureUpperLeft(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		player.setX(9);
		player.setY(100 - player.getHeight() + 1);
		collisions.get(0).get(1).add(Feature.air);
		collisions.get(1).get(1).add(Feature.air);
		collisions.get(4).get(1).add(Feature.air);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(1), collisions.get(i).get(1));
		}	
	}
	
	@Test
	public void TestcollisionDetectGameObjectFeatureBottomLeft(){
		List<List<List<Object>>> collisions = new ArrayList<List<List<Object>>>();
		for(int i = 0; i < 8; i++) {
			List<List<Object>> inner_collisions = new ArrayList<List<Object>>();
			for(int j = 0; j < 2; j++) {
				inner_collisions.add(new ArrayList<Object>());
			}
			collisions.add(inner_collisions);
		}
		player.setX(9);
		player.setY(9);
		collisions.get(0).get(1).add(Feature.air);
		collisions.get(3).get(1).add(Feature.air);
		collisions.get(7).get(1).add(Feature.air);
		List<List<List<Object>>> collisionsresult = world.collisionDetect(player, 0, 0);
		for( int i = 0; i < 8; i++){
			assertEquals(collisionsresult.get(i).get(1), collisions.get(i).get(1));
		}	
	}
}
