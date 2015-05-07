package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

public class Null extends Expression {
	
	public Null(SourceLocation sourceLocation) {
		super(sourceLocation);
		
		setType(Type.GAMEOBJECT);
	}
	
}
