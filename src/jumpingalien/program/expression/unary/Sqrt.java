package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class Sqrt extends MathUnaryExpression{
	
	public Sqrt(Expression expr, SourceLocation sourceLocation) {
		super(expr, sourceLocation);
	}

	@Override
	public Double getValue() {
		return Math.sqrt((double) getExpression().getValue());
	}
}
