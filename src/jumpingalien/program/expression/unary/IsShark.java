package jumpingalien.program.expression.unary;

import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsShark extends Checker {
	
	public IsShark(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
	
	@Override
	public Boolean getValue() {
		return getExpression().getValue() instanceof Shark;
	}
}
