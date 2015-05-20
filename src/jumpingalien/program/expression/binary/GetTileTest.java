package jumpingalien.program.expression.binary;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Buzam;
import jumpingalien.model.Feature;
import jumpingalien.model.Program;
import jumpingalien.model.World;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.DoubleConstant;
import jumpingalien.program.expression.Expression;

import org.junit.Before;
import org.junit.Test;

public class GetTileTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getValueValueTest() {

		World world = new World(10, 100, 100, 1000, 1000, 0, 0);
		Map<String, Type> globals = new HashMap<String, Type>();
		globals.put("var1", Type.DIRECTION);
		
		Program program = new Program(null, globals);
		Buzam buzam = new Buzam(0, 0, JumpingAlienSprites.ALIEN_SPRITESET, program);
		world.setBuzam(buzam);
		
		Expression number1 = new DoubleConstant(14.5, new SourceLocation(5, 6));
		Expression number2 = new DoubleConstant(27.3, new SourceLocation(5, 6));
		Expression getTile = new GetTile(number1, number2, new SourceLocation(5, 6));
		
		assertEquals(Feature.air, getTile.getValue(buzam));
		world.setFeature(1, 2, 2);
		assertEquals(Feature.water, getTile.getValue(buzam));
		world.setFeature(1, 2, 3);
		assertEquals(Feature.magma, getTile.getValue(buzam));
		
	}

}
