package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ParserObject;

public abstract class Expression extends ParserObject {

	public Expression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
}
