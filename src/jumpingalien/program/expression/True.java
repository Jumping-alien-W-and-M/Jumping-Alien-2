package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

public class True extends Expression {
	
	public True(SourceLocation sourceLocation) {
		super(sourceLocation);
		
		setType(Type.BOOL);
	}
	
}
