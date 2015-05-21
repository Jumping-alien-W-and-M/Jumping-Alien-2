package jumpingalien.program.expression.unary;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Self;

import org.junit.Test;

public class IsDuckingTest {

	SourceLocation srceloc = new SourceLocation(5, 6);

	@Test
	public void TestGetValueDucking() {		
		Expression self = new Self(srceloc);	
		Expression isDucking = new IsDucking(self , srceloc);
		
		Mazub mazub = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, null);
		mazub.startDuck();

		assert((boolean) isDucking.getValue(mazub));
	}
	
	@Test
	public void TestGetValueNotDucking() {		
		Expression self = new Self(srceloc);	
		Expression isDucking = new IsDucking(self , srceloc);
		
		Mazub mazub = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, null);

		assert(! (boolean) isDucking.getValue(mazub));
	}

}
