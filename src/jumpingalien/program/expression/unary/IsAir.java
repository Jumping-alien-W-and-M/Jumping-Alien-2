package jumpingalien.program.expression.unary;

import jumpingalien.model.Feature;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsAir extends Checker {
	
	public IsAir(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}

	@Override
	public Boolean getValue() {
		return getExpression().getValue() == Feature.air;		
	}
}