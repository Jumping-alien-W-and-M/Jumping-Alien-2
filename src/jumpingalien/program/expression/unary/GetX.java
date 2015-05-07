package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class GetX extends UnaryExpression {
	
	public GetX(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}
