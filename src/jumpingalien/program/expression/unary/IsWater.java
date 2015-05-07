package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsWater extends UnaryExpression {
	
	public IsWater(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}