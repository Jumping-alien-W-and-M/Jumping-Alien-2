package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.model.Slime;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsSlime extends Checker {
	
	public IsSlime(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
	
	@Override
	public Boolean getValue(GameObject executingObject) {
		return getExpression().getValue(executingObject) instanceof Slime;
	}
}