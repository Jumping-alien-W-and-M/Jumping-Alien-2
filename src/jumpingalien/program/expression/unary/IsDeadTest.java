package jumpingalien.program.expression.unary;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Shark;
import jumpingalien.model.World;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Self;
import jumpingalien.program.expression.binary.GetTile;
import jumpingalien.util.Sprite;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IsDeadTest {

	SourceLocation srceloc = new SourceLocation(5, 6);
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@Test
	public void TestGetValueDead() {		
		Expression self = new Self(srceloc);	
		Expression isDead = new IsDead(self , srceloc);
		
		Shark shark = new Shark(0, 0, sprites, null);
		shark.setHitpoints(0);

		assert((boolean) isDead.getValue(shark));
	}
	
	@Test
	public void TestGetValueNotDead() {		
		Expression self = new Self(srceloc);	
		Expression isDead = new IsDead(self , srceloc);
		
		Shark shark = new Shark(0, 0, sprites, null);

		assert(! (boolean) isDead.getValue(shark));
	}
}
