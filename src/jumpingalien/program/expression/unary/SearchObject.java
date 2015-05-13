package jumpingalien.program.expression.unary;

import java.util.List;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Feature;
import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
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
	public Object getValue(GameObject executingObject) {		
		Direction dir = (Direction) getExpression().getValue(executingObject);
		if(dir == Direction.RIGHT) return getValueRight(executingObject);
		else if(dir == Direction.LEFT) return getValueLeft(executingObject);
		else if(dir == Direction.UP) return getValueUp(executingObject);
		else if(dir == Direction.DOWN) return getValueDown(executingObject);
		else return null;
	}
	
	private Object getValueRight(GameObject executingObject){
		GameObject startobject = executingObject;		
		
		Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};
		Shark searchobject = new Shark(startobject.getX(), startobject.getY(), sprites, null);
		startobject.getWorld().addShark(searchobject);
		
		List<List<List<Object>>> collisions;
		while((searchobject.getX() + startobject.getWidth()) < startobject.getWorld().getWorldWidth()){
			collisions = searchobject.getWorld().collisionDetect(searchobject, startobject.getWidth(), startobject.getHeight());
			
			if(collisions.get(2).get(0) != null)
				return collisions.get(2).get(0).get(0);
			else if(collisions.get(2).get(1).contains(Feature.ground))
				return Feature.ground;
			
			searchobject.setX(searchobject.getX() + 1);
		}
		return null;
	}
	
	private Object getValueLeft(GameObject executingObject){
		GameObject startobject = executingObject;		
		
		Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};
		Shark searchobject = new Shark(startobject.getX(), startobject.getY(), sprites, null);
		startobject.getWorld().addShark(searchobject);
		
		List<List<List<Object>>> collisions;
		while(searchobject.getX() > 0){
			collisions = searchobject.getWorld().collisionDetect(searchobject, startobject.getWidth(), startobject.getHeight());
			
			if(collisions.get(0).get(0) != null)
				return collisions.get(0).get(0).get(0);
			else if(collisions.get(0).get(1).contains(Feature.ground))
				return Feature.ground;
			
			searchobject.setX(searchobject.getX() - 1);
		}
		return null;
	}
	
	private Object getValueUp(GameObject executingObject){
		GameObject startobject = executingObject;		
		
		Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};
		Shark searchobject = new Shark(startobject.getX(), startobject.getY(), sprites, null);
		startobject.getWorld().addShark(searchobject);
		
		List<List<List<Object>>> collisions;
		while((searchobject.getY() + startobject.getHeight()) < startobject.getWorld().getWorldHeight()){
			collisions = searchobject.getWorld().collisionDetect(searchobject, startobject.getWidth(), startobject.getHeight());
			
			if(collisions.get(1).get(0) != null)
				return collisions.get(1).get(0).get(0);
			else if(collisions.get(1).get(1).contains(Feature.ground))
				return Feature.ground;
			
			searchobject.setY(searchobject.getY() + 1);
		}
		return null;
	}
	
	private Object getValueDown(GameObject executingObject){
		GameObject startobject = executingObject;		
		
		Sprite[] sprites = {JumpingAlienSprites.ALIEN_SPRITESET[0], JumpingAlienSprites.ALIEN_SPRITESET[1]};
		Shark searchobject = new Shark(startobject.getX(), startobject.getY(), sprites, null);
		startobject.getWorld().addShark(searchobject);
		
		List<List<List<Object>>> collisions;
		while(searchobject.getY() > 0){
			collisions = searchobject.getWorld().collisionDetect(searchobject, startobject.getWidth(), startobject.getHeight());
			
			if(collisions.get(3).get(0) != null)
				return collisions.get(3).get(0).get(0);
			else if(collisions.get(3).get(1).contains(Feature.ground))
				return Feature.ground;
			
			searchobject.setY(searchobject.getY() - 1);
		}
		return null;
	}
	
	
	
}
