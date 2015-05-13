package jumpingalien.program.expression;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

public class False extends Expression {
	
	public False(SourceLocation sourceLocation) {
		super(sourceLocation);
		
		setType(Type.BOOL);
	}
	
	@Basic @Immutable @Override
	public Boolean getValue(GameObject executingObject) {
		return false;
	}
	
}
