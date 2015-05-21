package jumpingalien.program.expression.unary;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Plant;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Self;
import jumpingalien.util.Sprite;

import org.junit.Test;

public class IsPlantTest {

	SourceLocation srceloc = new SourceLocation(5, 6);
	Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};

	@Test
	public void TestGetValuePlant() {		
		Expression self = new Self(srceloc);	
		Expression isPlant = new IsPlant(self , srceloc);
		
		Plant plant = new Plant(0, 0, sprites, null);

		assert((boolean) isPlant.getValue(plant));
	}
	
	@Test
	public void TestGetValueNotPlant() {		
		Expression self = new Self(srceloc);	
		Expression isPlant = new IsPlant(self , srceloc);
		
		Shark shark = new Shark( 0, 0, sprites, null);

		assert(! (boolean) isPlant.getValue(shark));
	}

}
