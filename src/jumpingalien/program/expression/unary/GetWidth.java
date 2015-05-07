package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class GetWidth extends UnaryExpression {
	
	public GetWidth(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}
