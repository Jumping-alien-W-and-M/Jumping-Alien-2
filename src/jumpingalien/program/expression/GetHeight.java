package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class GetHeight extends UnaryExpression{
	public GetHeight(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}
