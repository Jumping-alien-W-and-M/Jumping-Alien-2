package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsTerrain extends UnaryExpression {
	
	public IsTerrain(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}