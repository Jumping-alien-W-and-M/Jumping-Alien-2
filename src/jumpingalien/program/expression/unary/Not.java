package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class Not extends UnaryExpression {
	
	public Not(Expression expr, SourceLocation sourceLocation) {
		super(expr, sourceLocation);
		
		setType(Type.BOOL);
		if(expr.getType() != Type.BOOL) Program.printTypeCheckError(sourceLocation);;
	}

	@Override
	public Boolean getValue(GameObject executingObject) {
		return ! ((boolean) getExpression().getValue(executingObject));
	}
	
	
}
