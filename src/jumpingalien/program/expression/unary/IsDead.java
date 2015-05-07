package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsDead extends Checker {
	
	public IsDead(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}

	@Override
	public Boolean getValue() {
		try{
			return ((GameObject) getExpression().getValue()).getHitpoints() <= 0;
		} catch(Exception exc) {
			return false;
		}
	}
}