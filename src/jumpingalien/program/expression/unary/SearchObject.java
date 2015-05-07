package jumpingalien.program.expression.unary;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class SearchObject extends UnaryExpression {
	
	public SearchObject(Expression direction, SourceLocation sourceLocation){
		super(direction, sourceLocation);
		
		setType(Type.OBJECT);
		if(direction.getType() != Type.DIRECTION) Program.printTypeCheckError(sourceLocation);
	}
}
