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
	public Boolean getValue(GameObject executingObject) {
		if(getExpression().getValue(executingObject) instanceof Mazub){
			return ((Mazub) getExpression().getValue(executingObject)).getJumping();
		}
		else if(getExpression().getValue(executingObject) instanceof Shark){
			return((Shark) getExpression().getValue(executingObject)).getIsJumping();
		}
		else{
			executingObject.getProgram().setRunTimeError(true);
			executingObject.getProgram().setStatementsLeft(0);
			return false;
		}
	}
}