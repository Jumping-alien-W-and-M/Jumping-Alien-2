package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class GetY extends UnaryExpression{
	
	public GetY(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}
