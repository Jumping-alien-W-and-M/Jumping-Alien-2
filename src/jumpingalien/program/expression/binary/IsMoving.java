package jumpingalien.program.expression.binary;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class IsMoving extends BinaryExpression {

	public IsMoving(Expression expr, Expression direction, SourceLocation sourceLocation) {
		super(expr, direction, sourceLocation);
		
		setType(Type.BOOL);
		if ((expr.getType() != Type.GAMEOBJECT) || (direction.getType() != Type.DIRECTION)) Program.printTypeCheckError(sourceLocation);
	}
	
}
