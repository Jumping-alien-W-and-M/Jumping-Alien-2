package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Feature;
import jumpingalien.model.Shark;
import jumpingalien.model.World;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.*;
import jumpingalien.program.expression.binary.GetTile;
import jumpingalien.util.Sprite;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IsAirTest {

	SourceLocation srceloc = new SourceLocation(5, 6);
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@Test
	public void TestgetValueEqualToAir() {
		
		Expression xpos = new DoubleConstant(0, srceloc);
		Expression ypos = new DoubleConstant(0, srceloc);
		Expression getTile = new GetTile(xpos, ypos, srceloc);		
		Expression isAir = new IsAir(getTile , srceloc);
		
		Shark shark = new Shark(0, 0, sprites, null);
		World world = new World(10, 20, 20, 200, 200, 19, 0);
		world.addShark(shark);
		assert((boolean) isAir.getValue(shark));
	}
	
	@Test
	public void TestgetValueNotEqualToAir(){

		Expression xpos = new DoubleConstant(0, srceloc);
		Expression ypos = new DoubleConstant(0, srceloc);
		Expression getTile = new GetTile(xpos, ypos, srceloc);		
		Expression isAir = new IsAir(getTile , srceloc);
		
		Shark shark = new Shark(0, 0, sprites, null);
		World world = new World(10, 20, 20, 200, 200, 19, 0);
		world.addShark(shark);
		world.setFeature(0, 0, 2);
		assert(! (boolean) isAir.getValue(shark));
	}

}
