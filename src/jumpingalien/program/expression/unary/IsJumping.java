package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsJumping extends UnaryExpression {
	
	public IsJumping(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}