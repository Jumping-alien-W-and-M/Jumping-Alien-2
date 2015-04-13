package jumpingalien.model;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.*;
import jumpingalien.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MazubTest {

	@Before
	public void setUp() throws Exception {
		world = new World(10, 200, 200, 200, 200, 19, 0);
		this.player = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET);
		world.setMazub(player);
	}
	
	private World world;
	private Mazub player;
	
	@After
	public void tearDown() throws Exception {
	}
	
	public void generateGroundLayer() {
		player.setX(world.getWorldWidth()/2);
		player.setY(world.getTileSize() - 1);
		for(int x = 0; x < world.getWorldWidth(); x += world.getTileSize()) {
			world.setFeature(x, 0, 1);
		}
	}

	@Test
	public void TestBasicConstructorCorrectParameters() {
		assertEquals(player.getX(), 0, Util.DEFAULT_EPSILON);
		assertEquals(player.getY(), 0, Util.DEFAULT_EPSILON);
		assertArrayEquals(player.getImages(), JumpingAlienSprites.ALIEN_SPRITESET);
		assertEquals(player.getVx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(player.getVxmax(), 3, Util.DEFAULT_EPSILON);
		assertEquals(player.getVy(), 0, Util.DEFAULT_EPSILON);
		assertEquals(player.getAx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(player.getAxi(), 0.9, Util.DEFAULT_EPSILON);
		assertEquals(player.getAy(), 0, Util.DEFAULT_EPSILON);

		assertEquals(player.getLastMove(), 0, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestConstructorIllegalCoordinates() {
		
		try {
			Mazub alien = new Mazub(-5, 0, JumpingAlienSprites.ALIEN_SPRITESET);
			alien.setX(alien.getX());
		} catch (IllegalArgumentException exc1) {
			try {
				Mazub alien = new Mazub(1500, 0, JumpingAlienSprites.ALIEN_SPRITESET);
				alien.setX(alien.getX());
			} catch (IllegalArgumentException exc2) {
				try {
					Mazub alien = new Mazub(0, -10, JumpingAlienSprites.ALIEN_SPRITESET);
					alien.setX(alien.getX());
				} catch (IllegalArgumentException exc3) {
					try {
						Mazub alien = new Mazub(0, world.getWorldHeight() - 100, JumpingAlienSprites.ALIEN_SPRITESET);
						alien.setX(alien.getX());
					} catch (IllegalArgumentException exc4) {
						assert(true);
						return;
					}
				}
			}
		}
		assert(false);
	}
	
	@Test
	public void TestCoordinateSettersCorrectParameters() {
		
		player.setX(10);
		assertEquals(player.getX(), 10, Util.DEFAULT_EPSILON);
		player.setY(100);
		assertEquals(player.getY(), 100, Util.DEFAULT_EPSILON);
		
		player.setX(0);
		assertEquals(player.getX(),0, Util.DEFAULT_EPSILON);
		player.setY(0);
		assertEquals(player.getY(),0, Util.DEFAULT_EPSILON);
		
		player.setX(world.getWorldWidth() - 1);
		assertEquals(player.getX(), world.getWorldWidth() - 1, Util.DEFAULT_EPSILON);
		player.setY(world.getWorldHeight() - 1);
		assertEquals(player.getY(), world.getWorldHeight() - 1, Util.DEFAULT_EPSILON);
		
	}
	
	@Test
	public void TestCoordinateSettersIllegalParameters() {
		
		try {
			player.setX(-1);
		} catch (IllegalArgumentException exc1) {
			try {
				player.setX(world.getWorldWidth());
			} catch (IllegalArgumentException exc2) {
				try {
					player.setY(-1);
				} catch (IllegalArgumentException exc3) {
					try {
						player.setY(world.getWorldHeight());
					} catch (IllegalArgumentException exc4) {
						assert(true);
						return;
					}
				}
			}
		}
		assert(false);
		
	}

	@Test
	public void TestValidCoordinates() {
		
		assertEquals(player.isValidX(-1), false);
		assertEquals(player.isValidX(0), true);
		assertEquals(player.isValidX(world.getWorldWidth() - 1), true);
		assertEquals(player.isValidX(world.getWorldWidth()), false);
		
		assertEquals(player.isValidY(-1), false);
		assertEquals(player.isValidY(0), true);
		assertEquals(player.isValidY(world.getWorldHeight() - 1), true);
		assertEquals(player.isValidY(world.getWorldHeight()), false);
		
	}
	
	@Test
	public void TestVelocitySetters() {

		player.setVx(-3);
		assertEquals(player.getVx(), -3, Util.DEFAULT_EPSILON);
		player.setVx(-1);
		assertEquals(player.getVx(), -1, Util.DEFAULT_EPSILON);
		player.setVx(0);
		assertEquals(player.getVx(), 0, Util.DEFAULT_EPSILON);
		player.setVx(1);
		assertEquals(player.getVx(), 1, Util.DEFAULT_EPSILON);
		player.setVx(3);
		assertEquals(player.getVx(), 3, Util.DEFAULT_EPSILON);

		player.setVy(-16);
		assertEquals(player.getVy(), -16, Util.DEFAULT_EPSILON);
		player.setVy(-8);
		assertEquals(player.getVy(), -8, Util.DEFAULT_EPSILON);
		player.setVy(0);
		assertEquals(player.getVy(), 0, Util.DEFAULT_EPSILON);
		player.setVy(8);
		assertEquals(player.getVy(), 8, Util.DEFAULT_EPSILON);
		player.setVy(16);
		assertEquals(player.getVy(), 16, Util.DEFAULT_EPSILON);
		
	}
	
	@Test
	public void TestVxmaxIsValidX() {
		
		assertEquals(player.isValidVx(-6), false);
		assertEquals(player.isValidVx(-3), true);
		assertEquals(player.isValidVx(-2), true);
		assertEquals(player.isValidVx(-1), true);
		assertEquals(player.isValidVx(-0.5), false);
		assertEquals(player.isValidVx(0), true);
		assertEquals(player.isValidVx(0.5), false);
		assertEquals(player.isValidVx(1), true);
		assertEquals(player.isValidVx(2), true);
		assertEquals(player.isValidVx(3), true);
		assertEquals(player.isValidVx(6), false);
		
		player.setVxmax(5);
		
		assertEquals(player.isValidVx(-6), false);
		assertEquals(player.isValidVx(-5), true);
		assertEquals(player.isValidVx(-2), true);
		assertEquals(player.isValidVx(-1), true);
		assertEquals(player.isValidVx(-0.5), false);
		assertEquals(player.isValidVx(0), true);
		assertEquals(player.isValidVx(0.5), false);
		assertEquals(player.isValidVx(1), true);
		assertEquals(player.isValidVx(2), true);
		assertEquals(player.isValidVx(5), true);
		assertEquals(player.isValidVx(6), false);
		
		player.setVxmax(3);
		
		assertEquals(player.isValidVx(-6), false);
		assertEquals(player.isValidVx(-3), true);
		assertEquals(player.isValidVx(-2), true);
		assertEquals(player.isValidVx(-1), true);
		assertEquals(player.isValidVx(-0.5), false);
		assertEquals(player.isValidVx(0), true);
		assertEquals(player.isValidVx(0.5), false);
		assertEquals(player.isValidVx(1), true);
		assertEquals(player.isValidVx(2), true);
		assertEquals(player.isValidVx(3), true);
		assertEquals(player.isValidVx(6), false);
		
	}
	
	@Test
	public void TestMoving() {
		
		player.setX(500);
		double pos = player.getX();
		
		player.startMove("left");

		assertEquals(player.getVx(), -1, Util.DEFAULT_EPSILON);
		assertEquals(player.getAx(), -0.9, Util.DEFAULT_EPSILON);
		assertEquals(player.getAnimationTime(), 0, Util.DEFAULT_EPSILON);
		
		player.advanceTime(0.1);
		
		player.endMove();
		
		assertEquals(player.getVx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(player.getAx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(pos > player.getX(), true);
		pos = player.getX();
		
		player.startMove("right");

		assertEquals(player.getVx(), 1, Util.DEFAULT_EPSILON);
		assertEquals(player.getAx(), 0.9, Util.DEFAULT_EPSILON);
		assertEquals(player.getAnimationTime(), 0, Util.DEFAULT_EPSILON);
		
		player.advanceTime(0.1);
		
		player.endMove();

		assertEquals(player.getVx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(player.getAx(), 0, Util.DEFAULT_EPSILON);
		assertEquals(pos < player.getX(), true);
	}
	
	@Test
	public void TestJumpingFalling() {
		
		generateGroundLayer();
		
		double pos = player.getY();
		
		player.startJump();

		assertEquals(player.getVy(), 8, Util.DEFAULT_EPSILON);
		
		player.advanceTime(0.1);
		
		player.endJump();
		
		assertEquals(player.getVy(), 0, Util.DEFAULT_EPSILON);
		assertEquals(pos < player.getY(), true);
		pos = player.getY();
		
		player.startJump();
		
		assertEquals(player.getVy(), 0, Util.DEFAULT_EPSILON);
		
		player.endJump();
		
		for(int i = 0; i < 15; i++) {
			player.advanceTime(0.15);
		}
		
		player.startJump();
		
		assertEquals(player.getVy(), 8, Util.DEFAULT_EPSILON);
		
		for(int i = 0; i < 10; i++) {
			player.advanceTime(0.1);
		}
		
		assertEquals(player.getVy(), -2, Util.DEFAULT_EPSILON);
		assertEquals(pos < player.getY(), true);
		pos = player.getY();
		
		player.advanceTime(0.1);
		
		assertEquals(pos > player.getY(), true);
		
		player.endJump();
	}
	
	@Test
	public void TestDucking() {
		
		player.startDuck();
		assertEquals(player.getDucking(), true);
		player.endDuck();
		assertEquals(player.getDucking(), false);
		
	}
	
	@Test
	public void TestGetCurrentSprite() {
		
		generateGroundLayer();
		
		assertEquals(player.getCurrentSprite(), player.getImages()[0]);
		
		player.startDuck();
		assertEquals(player.getCurrentSprite(), player.getImages()[1]);
		player.endDuck();
		
		player.startMove("right");
		player.advanceTime(0.1);
		player.endMove();
		assertEquals(player.getCurrentSprite(), player.getImages()[2]);
		for(int i = 0; i < 9; i++) {
			player.advanceTime(0.1);
		}
		assertEquals(player.getCurrentSprite(), player.getImages()[2]);
		player.advanceTime(0.1);
		assertNotEquals(player.getCurrentSprite(), player.getImages()[2]);
		
		player.startMove("left");
		player.advanceTime(0.1);
		player.endMove();
		assertEquals(player.getCurrentSprite(), player.getImages()[3]);
		for(int i = 0; i < 9; i++) {
			player.advanceTime(0.1);
		}
		assertEquals(player.getCurrentSprite(), player.getImages()[3]);
		player.advanceTime(0.1);
		assertNotEquals(player.getCurrentSprite(), player.getImages()[3]);
		
		player.setX(world.getWorldWidth()/2);
		player.startMove("right");
		player.startJump();
		player.advanceTime(0.1);
		assertEquals(player.getCurrentSprite(), player.getImages()[4]);
		for(int i = 0; i < 10; i++) {
			player.advanceTime(0.1);
		}
		assertEquals(player.getCurrentSprite(), player.getImages()[4]);
		player.endJump();
		player.endMove();
		
		for(int i = 0; i < 100; i++) {
			player.advanceTime(0.15);
		}
		
		player.startMove("left");
		player.startJump();
		player.advanceTime(0.1);
		assertEquals(player.getCurrentSprite(), player.getImages()[5]);
		for(int i = 0; i < 10; i++) {
			player.advanceTime(0.1);
		}
		assertEquals(player.getCurrentSprite(), player.getImages()[5]);
		player.endJump();
		player.endMove();
		for(int i = 0; i < 10; i++) {
			player.advanceTime(0.1);
		}
		
		player.startDuck();
		
		player.startMove("right");
		player.advanceTime(0.1);
		assertEquals(player.getCurrentSprite(), player.getImages()[6]);
		player.endMove();
		for(int i = 0; i < 9; i++) {
			player.advanceTime(0.1);
		}
		assertEquals(player.getCurrentSprite(), player.getImages()[6]);
		player.advanceTime(0.1);
		assertNotEquals(player.getCurrentSprite(), player.getImages()[6]);
		
		player.startMove("left");
		player.advanceTime(0.1);
		assertEquals(player.getCurrentSprite(), player.getImages()[7]);
		player.endMove();
		for(int i = 0; i < 9; i++) {
			player.advanceTime(0.1);
		}
		assertEquals(player.getCurrentSprite(), player.getImages()[7]);
		player.advanceTime(0.1);
		assertNotEquals(player.getCurrentSprite(), player.getImages()[7]);
		
		player.endDuck();
		
		assertEquals((int) player.getY() > world.getTileSize() - 1, false);
		assertEquals(player.getDucking(), false);
		
		player.startMove("right");
		player.advanceTime(0.01);
		for(int i = 0; i < 25; i++) {
			assertEquals(player.getCurrentSprite(), player.getImages()[8 + i%player.getFramesAmount()]);
			player.advanceTime(0.075);
		}
		
		player.endMove();
		
		player.startMove("left");
		player.advanceTime(0.01);
		
		for(int i = 0; i < 25; i++) {
			assertEquals(player.getCurrentSprite(), player.getImages()
					[8 + player.getFramesAmount() + i%player.getFramesAmount()]);
			player.advanceTime(0.075);
		}
		
		player.endMove();
	}
	
	@Test
	public void TestsetAxcorrectparameters() {
		player.setAx(player.getAxi());
		assertEquals(player.getAx(),player.getAxi(),Util.DEFAULT_EPSILON);
		player.setAx(-player.getAxi());
		assertEquals(player.getAx(),-player.getAxi(),Util.DEFAULT_EPSILON);
		player.setAx(0);
		assertEquals(player.getAx(),0,Util.DEFAULT_EPSILON);		
	}
	
	@Test
	public void TestsetAxillegalparameters(){
		try {
			player.setAx(0.1);
		} catch (IllegalArgumentException exc1) {
			try {
				player.setAx(-0.1);
			} catch (IllegalArgumentException exc2) {
				try {
					player.setAx(1);
				} catch (IllegalArgumentException exc3) {
					try {
						player.setAx(-1);
					} catch (IllegalArgumentException exc4) {
						assert(true);
						return;
					}
				}
			}
		}
		assert(false);
	}
	
	@Test
	public void TestisValidAxvalidAx(){
		assert(player.isValidAx(0));
		assert(player.isValidAx(0.9));
		assert(player.isValidAx(-0.9));
	}
	
	@Test
	public void TestisValidAxillegalAx(){
		assert(!player.isValidAx(0.1));
	}
	
	@Test
	public void TestsetAyCorrectparameters(){
		player.setAy(0);
		assertEquals(player.getAy(),0,Util.DEFAULT_EPSILON);
		player.setAy(-10);
		assertEquals(player.getAy(),-10,Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestsetAyIllegalparameters(){
		try {
			player.setAy(0.1);
		} catch (IllegalArgumentException exc1) {
			try {
				player.setAy(-0.1);
			} catch (IllegalArgumentException exc2) {
				try {
					player.setAy(100);
				}catch (IllegalArgumentException exc4) {
						assert(true);
						return;					
				}
			}
		}
		assert(false);
		
	}
	
	@Test
	public void TestisValidAyvalidAy(){
		assert(player.isValidAy(0));
		assert(player.isValidAy(-10));
	}
	
	@Test
	public void TestisValidAyillegalAy(){
		assert(!player.isValidAy(5));
	}
	
	@Test
	public void TestsetLastMovecorrectparameters(){
		player.setLastMove(-1);
		assert(player.getLastMove() == -1);
		player.setLastMove(1);
		assert(player.getLastMove() == 1);
		player.setLastMove(0.5);
		assert(player.getLastMove() == 0.5);		
	}
	
	@Test
	public void TestsetLastMoveillegalparameters(){
		try {
			player.setLastMove(50);
		} catch (IllegalArgumentException exc1) {
			try {
				player.setAx(-50);
			} catch (IllegalArgumentException exc4) {
						assert(true);
						return;					
			}
		}
		assert(false);
	}
	
	@Test
	public void Testgetframesamount(){
		assert(player.getFramesAmount() == (player.getImages().length - 8) /2);
	}
	
	@Test
	public void TestsetAnimationTimesparametersmallerthenthehighestreachableanimationtime(){
		player.setAnimationTime(player.getFramesAmount()*Mazub.getFrameTime()-0.1);
		assert(player.getAnimationTime() 
				== player.getAnimationTime()%(player.getFramesAmount()*Mazub.getFrameTime()));
	}
	
	@Test
	public void TestsetAnimationTimesparameterequaltothehighestreachableanimationtime(){
		player.setAnimationTime(player.getFramesAmount()*Mazub.getFrameTime());
		assert(player.getAnimationTime() 
				== player.getAnimationTime()%(player.getFramesAmount()*Mazub.getFrameTime()));
	}
	
	@Test
	public void TestsetAnimationTimesparametershigherthenthehighestreachableanimationtime(){
		player.setAnimationTime(player.getFramesAmount()*Mazub.getFrameTime()+ 0.1);
		assert(player.getAnimationTime() 
				== player.getAnimationTime()%(player.getFramesAmount()*Mazub.getFrameTime()));
	}
	
	@Test
	public void Testgetcurrentframeindexanimationtimedivisiblebyframetime(){
		player.setAnimationTime(Mazub.getFrameTime() *3);
		assert(player.getAnimationTime() == 3);
	}
	
	@Test
	public void Testgetcurrentframeindexanimationtimenotdivisiblebyframetime(){
		player.setAnimationTime(Mazub.getFrameTime()*2.5);
		assert(player.getAnimationTime() == 2);
	}
	
	@Test
	public void TestisValidDtvalidDt(){
		assert(Mazub.isValidDt(0.13));
	}
	
	@Test
	public void TestisValidDtinvalidDt(){
		assert(! Mazub.isValidDt(5));
		assert(! Mazub.isValidDt(-5));
		assert(! Mazub.isValidDt(0.2));
		assert(! Mazub.isValidDt(0));
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
		player.setX(world.getWorldWidth() - 1);
		world.setMazub(player);
		player.setVx(1);
		player.setAx(1);
		player.advanceTime(0.1);
		assert(player.getX() == world.getWorldWidth()-1);
		assert(player.getWorld() == null);
	}
	
	@Test
	public void TestadvanceTimeupdatinghorizontalvelocitynewvxwithinbounds(){
		player.setAx(1);
		player.advanceTime(0.1);
		assert(player.getVx() == player.getVx() + player.getAx()*0.1);		
	}
	
	@Test
	public void TestadvanceTimeupdatinghorizontalvelocitynewvxoutsidebounds(){
		player.setAx(-1);
		player.setVx(-player.getVxmax());
		player.advanceTime(0.1);
		assert(player.getVx() == -player.getVxmax());
		player.setAx(1);
		player.setVx(player.getVxmax());
		player.advanceTime(0.1);
		assert(player.getVx() == player.getVxmax());
	}
	
	@Test
	public void TestadvanceTimeupdatingypositionnewywithinbounds(){
			player.setVy(6);
			player.advanceTime(0.1);
			assert(player.getY() == 
					player.getY() + player.getVy()*0.1 + 1/2*player.getAy()*Math.pow(0.1, 2));
		}		
	
	@Test
	public void TestadvanceTimeupdatingypositionnewyoutsidebounds(){
		player.setVy(-8);
		player.setAy(-10);
		player.advanceTime(0.1);
		assert(player.getY() == 0);
		player.setY(world.getWorldHeight() - 1);
		assert(player.getWorld() == null);
		world.setMazub(player);
		player.setVy(1);
		player.advanceTime(0.1);
		assert(player.getY() == world.getWorldHeight() - 1);
		assert(player.getWorld() == null);
	}
	
	@Test
	public void TestadvanceTimeupdatingverticalvelocitynewvywithinbounds(){
		player.setVy(7);
		player.setAy(-10);
		player.advanceTime(0.1);
		assert(player.getVy() == player.getVy() + player.getAy()*0.1);		
	}
	
	@Test
	public void TestadvanceTimeupdatingverticalacceleration(){
		player.advanceTime(0.1);
		assert(player.getAy() == 0);
		player.setY(5);
		player.advanceTime(0.1);
		assert(player.getAx() == -10);
	}
	
	@Test
	public void TestadvanceTimeupdatingLastMovewhilemovingtotherightortheleft(){
		player.setVx(2);
		player.advanceTime(0.1);
		assert(player.getLastMove() == 1);
		player.setVx(-1);
		player.advanceTime(0.1);
		assert(player.getLastMove() == -1);
	}
	
	@Test
	public void TestadvanceTimeupdatingLastMovewhilestandingstillandnewLastMovewithinbounds(){
		player.advanceTime(0.1);
		assert(player.getLastMove() == 0);
		player.setLastMove(0.5);
		player.advanceTime(0.1);
		assert(player.getLastMove() == 0.4);
		player.setLastMove(-0.5);
		player.advanceTime(0.1);
		assert(player.getLastMove() == -0.4);		
	}
	
	@Test
	public void TestadvanceTimeupdatingLastMovewhilestandingstillandnewLastMoveoutsidebounds(){
		player.setLastMove(0.05);
		player.advanceTime(0.1);
		assert(player.getLastMove() == 0);
		player.setLastMove(-0.05);
		player.advanceTime(0.1);
		assert(player.getLastMove() == 0);		
	}
	
	@Test
	public void TestadvanceTimeupdatinganimationtimenewanimationtimewithinbounds(){
		player.advanceTime(0.1);
		double animationtimeafteradvancetime = player.getAnimationTime();
		player.setAnimationTime(0);
		player.setAnimationTime(0.1);
		double animationtimeaftersetter = player.getAnimationTime();
		assert(animationtimeafteradvancetime == animationtimeaftersetter);
	}
	
	@Test
	public void TestadvanceTimeupdatinganimationtimenewanimationtimeoutsidebounds(){
		player.setAnimationTime(Mazub.getFrameTime()*player.getFramesAmount()-0.05);
		player.advanceTime(0.1);
		double animationtimeafteradvancetime = player.getAnimationTime();
		player.setAnimationTime(Mazub.getFrameTime()*player.getFramesAmount()-0.05);
		player.setAnimationTime(player.getAnimationTime() + 0.1);
		double animationtimeaftersetter = player.getAnimationTime();
		assert(animationtimeafteradvancetime == animationtimeaftersetter);		
	}
	
	@Test
	public void TestadvanceTimeillegalparameter(){
		try{
			player.advanceTime(0.2);
		} catch (IllegalArgumentException exc){
			try{
				player.advanceTime(0);
			}catch(IllegalArgumentException exc1){
				assert(true);
			}
		}
		assert(false);
	}
	
	@Test
	public void advanceTimeBasicJumpingTest() {
		
		generateGroundLayer();

		assertEquals(world.getTileSize() - 1, (int) player.getY());
		assertEquals(false, player.getJumping());
		assertEquals(false, player.getJustJumped());
		
		player.startJump();

		assertEquals(world.getTileSize() - 1, (int) player.getY());
		assertEquals(true, player.getJumping());
		assertEquals(true, player.getJustJumped());
		
		player.advanceTime(0.001);

		assertEquals(world.getTileSize() - 1, (int) player.getY());
		assertEquals(true, player.getJumping());
		assertEquals(true, player.getJustJumped());
		
		player.advanceTime(0.001);

		assertEquals(world.getTileSize(), (int) player.getY());
		assertEquals(true, player.getJumping());
		assertEquals(false, player.getJustJumped());
		
		for(int i = 0; i < 20; i++) {
			player.advanceTime(0.15);
		}
		
		assertEquals(world.getTileSize() - 1, (int) player.getY());
		assertEquals(false, player.getJumping());
		assertEquals(false, player.getJustJumped());
	}
	
	@Test
	public void advanceTimeWallJumpingTest() {
		
		generateGroundLayer();
		world.setFeature(100, 20, 1);
		world.setFeature(100, 30, 1);
		world.setFeature(100, 40, 1);
		world.setFeature(100, 50, 1);
		world.setFeature(100, 100, 1);
		world.setFeature(120, 100, 1);
		player.setX(109);

		assertEquals(world.getTileSize() - 1, (int) player.getY());
		assertEquals(false, player.getJumping());
		assertEquals(false, player.getJustJumped());
		
		player.startJump();

		assertEquals(world.getTileSize() - 1, (int) player.getY());
		assertEquals(true, player.getJumping());
		assertEquals(true, player.getJustJumped());
		
		player.advanceTime(0.001);

		assertEquals(world.getTileSize() - 1, (int) player.getY());
		assertEquals(false, player.getJumping());
		assertEquals(false, player.getJustJumped());
		
		player.advanceTime(0.001);

		assertEquals(world.getTileSize() - 1, (int) player.getY());
		assertEquals(false, player.getJumping());
		assertEquals(false, player.getJustJumped());
	}
	
	@Test
	public void canJumpHorizontalTest() {
		world.setFeature(100, 0, 1);
		player.setY(9);
		
		player.setX(10);
		assertEquals(false, player.canJump());
		
		player.setX(100 - player.getWidth() + 1);
		assertEquals(false, player.canJump());
		
		player.setX(100 - player.getWidth() + 2);
		assertEquals(true, player.canJump());
		
		player.setX(90);
		assertEquals(true, player.canJump());
		
		player.setX(100);
		assertEquals(true, player.canJump());
		
		player.setX(105);
		assertEquals(true, player.canJump());
		
		player.setX(100 + world.getTileSize() - 2);
		assertEquals(true, player.canJump());
		
		player.setX(100 + world.getTileSize() - 1);
		assertEquals(false, player.canJump());
		
		player.setX(200);
		assertEquals(false, player.canJump());
	}
	
	@Test
	public void canJumpVerticalTest() {
		world.setFeature(100, 0, 1);
		player.setX(100);
		
		player.setY(0);
		assertEquals(false, player.canJump());
		
		player.setY(5);
		assertEquals(false, player.canJump());
		
		player.setY(9);
		assertEquals(true, player.canJump());
		
		player.setY(10);
		assertEquals(false, player.canJump());
		
		player.setY(20);
		assertEquals(false, player.canJump());
	}
	
	@Test
	public void canJumpHorizontalObjectTest() {
		Sprite[] sprites = {player.getImages()[0], player.getImages()[1]};
		Shark shark = new Shark(100, 0, sprites);
		world.addShark(shark);
		player.setY(shark.getHeight() - 1);
		
		player.setX(10);
		assertEquals(false, player.canJump());
		
		player.setX(100 - player.getWidth() + 1);
		assertEquals(false, player.canJump());
		
		player.setX(100 - player.getWidth() + 2);
		assertEquals(true, player.canJump());
		
		player.setX(90);
		assertEquals(true, player.canJump());
		
		player.setX(100);
		assertEquals(true, player.canJump());
		
		player.setX(105);
		assertEquals(true, player.canJump());
		
		player.setX(100 + shark.getWidth() - 2);
		assertEquals(true, player.canJump());
		
		player.setX(100 + shark.getWidth() - 1);
		assertEquals(false, player.canJump());
		
		player.setX(200);
		assertEquals(false, player.canJump());
	}
	
	@Test
	public void canJumpVerticalObjectTest() {
		Sprite[] sprites = {player.getImages()[0], player.getImages()[1]};
		Shark shark = new Shark(100, 0, sprites);
		world.addShark(shark);
		player.setX(100);
		
		player.setY(0);
		assertEquals(false, player.canJump());
		
		player.setY(5);
		assertEquals(false, player.canJump());
		
		player.setY(shark.getHeight() - 1);
		assertEquals(true, player.canJump());
		
		player.setY(shark.getHeight());
		assertEquals(false, player.canJump());
		
		player.setY(shark.getHeight()*2);
		assertEquals(false, player.canJump());
	}
	
	@Test
	public void duckingBlockedTest() {
		generateGroundLayer();
		
		world.setFeature(500, 80, 1);
		player.setX(200);
		player.setY(9);
		player.startMove("right");
		
		for(int i = 0; i < 20; i++) {
			player.advanceTime(0.15);
		}
		
		assertEquals(500 - player.getWidth() + 1, (int) player.getX());
		assertEquals(false, player.getDucking());
		assertEquals(false, player.getTryStand());

		player.startDuck();
		assertEquals(true, player.getDucking());
		assertEquals(false, player.getTryStand());
		
		for(int i = 0; i < 5; i++) {
			player.advanceTime(0.10);
		}
		
		player.endDuck();
		assertEquals(true, player.getDucking());
		assertEquals(true, player.getTryStand());
		
		for(int i = 0; i < 5; i++) {
			player.advanceTime(0.10);
		}
		
		assertEquals(false, player.getDucking());
		assertEquals(false, player.getTryStand());
	}

	@Test
	public void moveConflictTestLeft() {
		assertEquals(0, player.getVx(), Util.DEFAULT_EPSILON);
		assertEquals("", player.getPrevMove());
		
		player.startMove("left");
		assertEquals(-player.getVxi(), player.getVx(), Util.DEFAULT_EPSILON);
		assertEquals("", player.getPrevMove());
		
		player.startMove("right");
		assertEquals(player.getVxi(), player.getVx(), Util.DEFAULT_EPSILON);
		assertEquals("left", player.getPrevMove());
		
		player.endMove();
		assertEquals(-player.getVxi(), player.getVx(), Util.DEFAULT_EPSILON);
		assertEquals("", player.getPrevMove());
		
		player.endMove();
		assertEquals(0, player.getVx(), Util.DEFAULT_EPSILON);
		assertEquals("", player.getPrevMove());
	}

	@Test
	public void moveConflictTestRight() {
		assertEquals(0, player.getVx(), Util.DEFAULT_EPSILON);
		assertEquals("", player.getPrevMove());
		
		player.startMove("right");
		assertEquals(player.getVxi(), player.getVx(), Util.DEFAULT_EPSILON);
		assertEquals("", player.getPrevMove());
		
		player.startMove("left");
		assertEquals(-player.getVxi(), player.getVx(), Util.DEFAULT_EPSILON);
		assertEquals("right", player.getPrevMove());
		
		player.endMove();
		assertEquals(player.getVxi(), player.getVx(), Util.DEFAULT_EPSILON);
		assertEquals("", player.getPrevMove());
		
		player.endMove();
		assertEquals(0, player.getVx(), Util.DEFAULT_EPSILON);
		assertEquals("", player.getPrevMove());
	}
	
}
