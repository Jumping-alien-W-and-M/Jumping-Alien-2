package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsDead extends Checker {
	
	public IsDead(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}

	@Override
	public Boolean getValue(GameObject executingObject) {
		try{
			return ((GameObject) getExpression().getValue(executingObject)).getHitpoints() <= 0;
		} catch(Exception exc) {
			executingObject.getProgram().setRunTimeError(true);
			executingObject.getProgram().setStatementsLeft(0);
			return false;
		}
	}
}