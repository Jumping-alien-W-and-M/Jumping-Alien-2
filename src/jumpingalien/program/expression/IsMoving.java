package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class IsMoving extends BinaryExpression {

	public IsMoving(Expression expr, Expression direction, SourceLocation sourceLocation) {
		super(expr, direction, sourceLocation);
	}
	
}
