package jumpingalien.program.expression;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

public class Null extends Expression {
	
	public Null(SourceLocation sourceLocation) {
		super(sourceLocation);
		
		setType(Type.OBJECT);
	}
	
	@Basic @Immutable @Override
	public Object getValue(GameObject executingObject) {
		return null;
	}
	
}
