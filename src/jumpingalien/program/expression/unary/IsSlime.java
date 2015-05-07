package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsSlime extends UnaryExpression {
	
	public IsSlime(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}