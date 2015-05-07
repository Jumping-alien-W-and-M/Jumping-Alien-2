package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

public class Self extends Expression {
	
	public Self(SourceLocation sourceLocation) {
		super(sourceLocation);
		
		setType(Type.GAMEOBJECT);
	}
	
}
