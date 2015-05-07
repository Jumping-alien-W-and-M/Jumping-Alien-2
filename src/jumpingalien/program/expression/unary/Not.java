package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class Not extends UnaryExpression {
	
	public Not(Expression expr, SourceLocation sourceLocation) {
		super(expr, sourceLocation);
	}
}
