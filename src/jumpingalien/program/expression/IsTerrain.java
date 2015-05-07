package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class IsTerrain extends UnaryExpression {
	
	public IsTerrain(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}