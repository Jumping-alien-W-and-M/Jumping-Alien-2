package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.False;
import jumpingalien.program.expression.True;
import jumpingalien.util.Sprite;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NotTest {
	
	SourceLocation srceloc = new SourceLocation(2,3);
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@Test
	public void TestConstructorCorrectType() {
		Expression expr = new True(srceloc);
		Not not = new Not(expr, srceloc);
		assertEquals(not.getType(), Type.BOOL);
	}
	
	@Test
	public void TestgetValuetrueAsResult(){
		Expression expr = new False(srceloc);
		Not not = new Not(expr, srceloc);
		
		Shark shark = new Shark(0, 0, sprites, null);
		assert(not.getValue(shark));
	}
	
	@Test
	public void TestgetValuefalseAsResult(){
		Expression expr = new True(srceloc);
		Not not = new Not(expr, srceloc);
		
		Shark shark = new Shark(0, 0, sprites, null);
		assert(! not.getValue(shark));
	}
}
