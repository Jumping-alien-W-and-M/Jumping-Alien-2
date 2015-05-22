package jumpingalien.model;

import static org.junit.Assert.*;

import java.util.HashMap;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.statement.Skip;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.Test;

public class GameObjectTest {
	
	private static World world;
	private Mazub player;


	@Before
	public void setUp() throws Exception {
		player = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, null);
		world = new World(10, 20, 20, 200, 200, 19, 0);
		world.setMazub((Mazub) player);
	}

	@Test
	public void TestendJumpPositiveVerticalVelocity(){
		player.setVy(2);
		player.endJump();
		assertEquals(0, player.getVy(), Util.DEFAULT_EPSILON);
		player.endJump();
		assertEquals(0, player.getVy(), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestendJumpNegativeVerticalVelocity(){
		player.setVy(-2);
		player.endJump();
		assertEquals(-2, player.getVy(), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestendMovedirectionEqualsgetPrevMove(){
		player.setPrevMove("left");
		player.startMove("right");
		player.endMove("left");
		assertEquals(player.getPrevMove(), "");
		assert(player.getAx() > 0);
	}
	
	@Test
	public void TestendMoveConflictBetweendirectionAndgetAx(){
		Skip skip = new Skip(new SourceLocation(5, 6));
		HashMap<String, Type> variables = new HashMap<String, Type>();
		Program program = new Program(skip, variables);
		program.setStatementsLeft(5);
		Buzam buzam = new Buzam(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, program);
		buzam.setAx(buzam.getAxi());
		buzam.endMove("left");
		assertEquals(0, program.getStatementsLeft());
		assert(program.getRunTimeError());
		
		program.setStatementsLeft(5);
		program.setRunTimeError(false);
		buzam.setAx(-buzam.getAxi());
		buzam.endMove("right");
		assertEquals(0, program.getStatementsLeft());
		assert(program.getRunTimeError());		
	}
	
	@Test
	public void TestendMovePrevMoveIsNewMovement(){
		player.setPrevMove("left");
		player.setAx(player.getAxi());
		player.endMove("right");
		assertEquals(- player.getAxi(), player.getAx(), Util.DEFAULT_EPSILON);
		assertEquals("", player.getPrevMove());
		
		player.setPrevMove("right");
		player.setAx(- player.getAxi());
		player.endMove("left");
		assertEquals( player.getAxi(), player.getAx(), Util.DEFAULT_EPSILON);
		assertEquals("", player.getPrevMove());		
	}
	
	@Test
	public void TestIsValidXWithWorldNotEqualTonull() {		
		assert(! player.isValidX(-1));
		assert(player.isValidX(0));
		assert(player.isValidX(199.1));
		assert(! player.isValidX(200));
	}
	
	@Test
	public void TestIsValidYWithWorldNotEqualTonull(){
		assert(! player.isValidY(-1));
		assert(player.isValidY(0));
		assert(player.isValidY(199.5));
		assert(! player.isValidY(200));		
	}
	
	
	@Test
	public void TestIsValidXWithWorldEqualTonull() {		
		assert(! player.isValidX(-0.1));
		assert(player.isValidX(0));
	}
	
	@Test
	public void TestIsValidYWithWorldEqualTonull(){		
		assert(! player.isValidY(-0.1));
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
				 (100*(player.getVx()*0.1 + (1/2)*player.getAx()*Math.pow(0.1, 2))), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepupdatingxpositionnewxoutsidebounds(){
		player.setVx(-1);
		player.setAx(-1);
		player.advanceTimeStep(0.1);
		assertEquals(player.getX(), 0, Util.DEFAULT_EPSILON);
		assertEquals(player.getWorld(), null);
		world.setMazub(player);
		player.setVx(1);
		player.setAx(1);
		player.setX(player.getWorld().getWorldWidth()-1);
		double predicted_x = player.getX() + 100*(player.getVx()*0.1 + (1/2)*player.getAx()*Math.pow(0.1, 2));
		player.advanceTimeStep(0.1);
		assertEquals(player.getX(), predicted_x, Util.DEFAULT_EPSILON);
		assertEquals(player.getWorld(), null);
	}
	
	@Test
	public void TestadvanceTimeStepUpdatingXPositionWithoutWorld(){
		player.setWorld(null);
		player.setVx(1);
		double predicted_position = player.getX() + 100*(player.getVx()*0.1 + 1/2*player.getAx()*Math.pow(0.1, 2));
		player.advanceTimeStep(0.1);
		assertEquals(player.getX(), predicted_position, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestAdvanceTimeStepUpdatingXPositionWithCollisionRight(){
		world.setFeature(10, 0, 1);
		player.setVx(1);
		player.setX(100 - player.getWidth());
		player.advanceTimeStep(0.01);
		assertEquals(player.getX(), (100 - player.getWidth() + 1), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestAdvanceTimeStepUpdatingXPositionWithCollisionLeft(){
		world.setFeature(10, 0, 1);
		player.setX(100 + world.getTileSize() - 1);
		player.setVx( -3);
		player.advanceTimeStep(0.1);
		assertEquals(player.getX(), 100 + world.getTileSize() - 1, Util.DEFAULT_EPSILON);
		
	}
	
	@Test
	public void TestadvanceTimeStepupdatinghorizontalvelocitynewvxwithinbounds(){
		player.setAx(1);
		player.advanceTimeStep(0.01);
		assertEquals(player.getVx(), player.getVx() + player.getAx()*0.01, Util.DEFAULT_EPSILON);		
	}
	
	@Test
	public void TestadvanceTimeStepupdatinghorizontalvelocitynewvxoutsidebounds(){
		player.setAx(- player.getAxi());
		player.setVx(-player.getVxmax());
		player.advanceTimeStep(0.1);
		assertEquals(player.getVx(), -player.getVxmax(), Util.DEFAULT_EPSILON);
		player.setAx(player.getAxi());
		player.setVx(player.getVxmax());
		player.advanceTimeStep(0.1);
		assertEquals(player.getVx(), player.getVxmax(), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepupdatingypositionnewywithinbounds(){
			player.setVy(6);
			double predicted_height = player.getY() + 100*(player.getVy()*0.1 + 1/2*player.getAy()*Math.pow(0.1, 2));
			player.advanceTimeStep(0.1);
			assertEquals(player.getY(), predicted_height, Util.DEFAULT_EPSILON);
		}		
	
	@Test
	public void TestadvanceTimeStepupdatingypositionnewyoutsidebounds(){
		player.setVy(-8);
		player.setAy(-10);
		player.advanceTimeStep(0.1);
		assertEquals(player.getY(), 0, Util.DEFAULT_EPSILON);
		assertEquals(player.getWorld(), null);
		world.setMazub(player);
		player.setVy(1);
		player.setY(player.getWorld().getWorldHeight()-1);
		double predicted_height = player.getY() + 100*(player.getVy()*0.1 + 1/2*player.getAy()*Math.pow(0.1, 2));
		player.advanceTimeStep(0.1);
		assertEquals(player.getY(), predicted_height, Util.DEFAULT_EPSILON);
		assertEquals(player.getWorld(), null);
	}
	
	@Test
	public void TestadvanceTimeStepUpdatingYPositionWithoutWorld(){
		player.setWorld(null);
		player.setVy(6);
		double predicted_height = player.getY() + 100*(player.getVy()*0.1 + 1/2*player.getAy()*Math.pow(0.1, 2));
		player.advanceTimeStep(0.1);
		assertEquals(player.getY(), predicted_height, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepUpdatingYPositionWithCollisionUp(){
		world.setFeature(0, 10, 1);
		player.setY(100 - player.getHeight() + 1);
		player.setVy(3);
		player.advanceTimeStep(0.1);
		assertEquals(player.getY(), 100 - player.getHeight() + 1, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepUpdatingYPositionWithCollisionUnder(){
		world.setFeature(0, 10, 1);
		player.setY(100 + world.getTileSize() -1);
		player.setVy(-1);
		player.advanceTimeStep(0.1);
		assertEquals(player.getY(), 100 + world.getTileSize() - 1, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepupdatingverticalvelocitynewvywithinbounds(){
		player.setVy(7);
		player.setAy(-10);
		player.advanceTimeStep(0.1);
		assertEquals(player.getVy(), 7 +(-10)*0.1, Util.DEFAULT_EPSILON);		
	}
	
	@Test
	public void TestadvanceTimeStepupdatingverticalacceleration(){
		player.advanceTimeStep(0.1);
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
	public void TestadvanceTimeStepBreakCollisionFeatureRight(){
		world.setFeature(10,3,1);
		player.setX((100 - player.getWidth() + 1));
		player.setAy(0);
		player.setVy(3);
		player.advanceTimeStep(0.1);
		assertEquals (player.getAy(), 0, Util.DEFAULT_EPSILON); 
		assertEquals(player.getVy(), 0, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepBreakCollisionFeatureLeft(){
		world.setFeature(10,3,1);
		player.setX(100 + world.getTileSize() - 1);
		player.setAy(0);
		player.setVy(3);
		player.advanceTimeStep(0.1);
		assertEquals(player.getAy(), 0, Util.DEFAULT_EPSILON);
		assertEquals(player.getVy(), 0, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimeStepBreakCollisionFeatureUp(){
		world.setFeature(0, 10, 1);
		player.setY(100 - player.getHeight() + 1);
		player.setAy(0);
		player.setVy(4);
		player.advanceTimeStep(0.1);
		assertEquals(player.getAy(), -10, Util.DEFAULT_EPSILON);
		assertEquals(player.getVy(), 0, Util.DEFAULT_EPSILON);
	}
	

	
	@Test
	public void TestadvandTimeStepUpdatingTimeInvincibleWithinBounds(){
		player.setTimeInvincible(0.5);
		player.advanceTimeStep(0.1);
		assertEquals(player.getTimeInvincible() , 0.4, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestadvanceTimesStepUpdatingTimeInvincibleOutsideBound(){
		player.advanceTimeStep(0.1);
		assertEquals(player.getTimeInvincible(), 0, Util.DEFAULT_EPSILON);
		player.setTimeInvincible(0.05);
		player.advanceTimeStep(0.1);
		assertEquals(player.getTimeInvincible(), 0, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestisValidDtValidDt(){
		assert(GameObject.isValidDt(0.1));
		assert(GameObject.isValidDt(0.001));
		assert(GameObject.isValidDt(0.199));
	}
	
	@Test
	public void TestisValidDtNonValidDt(){
		assert(!GameObject.isValidDt(0));
		assert(!GameObject.isValidDt(0.2));
		assert(!GameObject.isValidDt(0.5));
		assert(!GameObject.isValidDt(- 0.5));
	}
	
	@Test
	public void TestgetTimeStepFormulaAsResult(){
		player.setVx(1);
		assertEquals(player.getTimestep(0.2,0), (0.01/(Math.sqrt(Math.pow(player.getVx(), 2) + 
				Math.pow(player.getVy(), 2)) + Math.sqrt(Math.pow(player.getAx(), 2)))), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestgetTimeStepFormulaNotAsResult(){
		player.setVx(player.getVxmax());
		player.setAy(player.getAxi());
		assertEquals(player.getTimestep(0.1, 0.1), 0, Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestgetCurrentSprite(){
		Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};
		Plant plant = new Plant(0 , 0, sprites, null);
		assertEquals(plant.getCurrentSprite(), JumpingAlienSprites.ALIEN_SPRITESET[1]);
		plant.setVx(- plant.getVxi());
		assertEquals(plant.getCurrentSprite(), JumpingAlienSprites.ALIEN_SPRITESET[0]);
		plant.setVx(0);
		assertEquals(plant.getCurrentSprite(), JumpingAlienSprites.ALIEN_SPRITESET[0]);
	}
	
	@Test
	public void TestsetXValidX(){
		player.setX(5);
		assertEquals(5, player.getX(), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestsetXInvalidX(){
		try{
			player.setX(-1);
		} catch(IllegalArgumentException exc){
			assert(true);
			return;
		}
		assert(false);
	}
	
	@Test
	public void TestsetYValidY(){
		player.setY(5);
		assertEquals(5, player.getY(), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TestsetYInvalidY(){
		try{
			player.setY(-1);
		} catch(IllegalArgumentException exc){
			assert(true);
			return;
		}
		assert(false);
	}
	
	@Test
	public void TeststartMoveMovingAndPrevMoveIsEmpty(){
		player.setAx(player.getAxi());
		player.startMove("left");
		assertEquals("right", player.getPrevMove());
		assertEquals(-player.getAxi(), player.getAx(), Util.DEFAULT_EPSILON);
		assertEquals(-player.getVxi(), player.getVx(), Util.DEFAULT_EPSILON);
		
		player.setPrevMove("");
		player.setAx(0);
		player.setAx(-player.getAxi());
		player.startMove("right");
		assertEquals("left", player.getPrevMove());
		assertEquals(player.getAxi(), player.getAx(), Util.DEFAULT_EPSILON);
		assertEquals(player.getVxi(), player.getVxi(), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void TeststartMoveConflictBetweendirectionAndgetAx(){
		Skip skip = new Skip(new SourceLocation(5, 6));
		HashMap<String, Type> variables = new HashMap<String, Type>();
		Program program = new Program(skip, variables);
		program.setStatementsLeft(5);
		Buzam buzam = new Buzam(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, program);
		buzam.setAx(buzam.getAxi());
		buzam.endMove("left");
		assertEquals(0, program.getStatementsLeft());
		assert(program.getRunTimeError());
		
		program.setStatementsLeft(5);
		program.setRunTimeError(false);
		buzam.setAx(-buzam.getAxi());
		buzam.endMove("right");
		assertEquals(0, program.getStatementsLeft());
		assert(program.getRunTimeError());
	}
	
	
	
}
