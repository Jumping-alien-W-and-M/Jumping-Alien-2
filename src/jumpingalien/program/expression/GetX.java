package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class GetX extends UnaryExpression {
	
	public GetX(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}
