package jumpingalien.program.expression.unary;

import jumpingalien.model.Feature;
import jumpingalien.model.Plant;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsTerrain extends Checker {
	
	public IsTerrain(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
	
	@Override
	public Boolean getValue() {
		return getExpression().getValue() instanceof Feature;
	}
}