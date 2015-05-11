package jumpingalien.program.expression.unary;

import jumpingalien.model.Plant;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsPlant extends Checker {
	
	public IsPlant(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}

	@Override
	public Boolean getValue() {
		return getExpression().getValue() instanceof Plant;
	}
}