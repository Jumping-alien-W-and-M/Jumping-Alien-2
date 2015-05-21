package jumpingalien.program.expression.unary;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Shark;
import jumpingalien.model.World;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.binary.GetTile;
import jumpingalien.util.Sprite;

import org.junit.Test;

public class IsMagmaTest {

	SourceLocation srceloc = new SourceLocation(5, 6);
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@Test
	public void TestgetValueEqualToMagma() {
		
		Expression xpos = new DoubleConstant(0, srceloc);
		Expression ypos = new DoubleConstant(0, srceloc);
		Expression getTile = new GetTile(xpos, ypos, srceloc);		
		Expression isMagma = new IsMagma(getTile , srceloc);
		
		Shark shark = new Shark(0, 0, sprites, null);
		World world = new World(10, 20, 20, 200, 200, 19, 0);
		world.addShark(shark);
		world.setFeature(0, 0, 3);
		assert((boolean) isMagma.getValue(shark));
	}
	
	@Test
	public void TestgetValueNotEqualToMagma(){

		Expression xpos = new DoubleConstant(0, srceloc);
		Expression ypos = new DoubleConstant(0, srceloc);
		Expression getTile = new GetTile(xpos, ypos, srceloc);		
		Expression isMagma = new IsMagma(getTile , srceloc);
		
		Shark shark = new Shark(0, 0, sprites, null);
		World world = new World(10, 20, 20, 200, 200, 19, 0);
		world.addShark(shark);
		assert(! (boolean) isMagma.getValue(shark));
	}

}
