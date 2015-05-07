package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

public class False extends Expression {
	
	public False(SourceLocation sourceLocation) {
		super(sourceLocation);
		
		setType(Type.BOOL);
	}
	
}
