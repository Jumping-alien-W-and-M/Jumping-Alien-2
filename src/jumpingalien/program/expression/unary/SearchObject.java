package jumpingalien.program.expression.unary;

import java.util.List;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ProgramExecutor;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;
import jumpingalien.util.Sprite;

public class SearchObject extends UnaryExpression {
	
	public SearchObject(Expression direction, SourceLocation sourceLocation){
		super(direction, sourceLocation);
		
		setType(Type.OBJECT);
		if(direction.getType() != Type.DIRECTION) Program.printTypeCheckError(sourceLocation);
	}

	@Override
	public Object getValue() {
		GameObject startobject = ProgramExecutor.getExecutingObject();
		
		
		Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};
		Shark searchobject = new Shark(startobject.getX(), startobject.getY(), sprites, null);
		
		boolean found = false;
		List<List<List<Object>>> collisions
		while((searchobject.getX() + startobject.getWidth()) < startobject.getWorld().getWorldWidth()){
			collisions = searchobject.getWorld.getCollisions();
		}
	}
	
	
}
