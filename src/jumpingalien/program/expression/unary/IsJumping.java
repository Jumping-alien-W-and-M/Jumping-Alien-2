package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsJumping extends Checker {
	
	public IsJumping(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}

	@Override
	public Boolean getValue() {
		if(getExpression().getValue() instanceof Mazub){
			return ((Mazub) getExpression().getValue()).getJumping();
		}
		else if(getExpression().getValue() instanceof Shark){
			return((Shark) getExpression().getValue()).getIsJumping();
		}
		else{
			return false;
		}
	}
}