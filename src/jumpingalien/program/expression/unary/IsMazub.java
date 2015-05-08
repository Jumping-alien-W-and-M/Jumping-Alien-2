package jumpingalien.program.expression.unary;

import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsMazub extends Checker{
	
	public IsMazub(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}

	@Override
	public Boolean getValue() {
		return (getExpression().getValue() instanceof Mazub && ! (getExpression().getValue() instanceof Buzam));
	}

}
