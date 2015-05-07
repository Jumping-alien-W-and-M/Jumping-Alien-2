package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class GetHeight extends UnaryExpression{
	public GetHeight(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}
