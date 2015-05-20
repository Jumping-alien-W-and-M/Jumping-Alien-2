package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsDucking extends Checker {
	
	public IsDucking(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}

	@Override
	public Boolean getValue(GameObject executingObject) {
		try{
			return ((Mazub) getExpression().getValue(executingObject)).getDucking();
		} catch(Exception exc) {
			executingObject.getProgram().setRunTimeError(true);
			executingObject.getProgram().setStatementsLeft(0);
			return false;
		}
	}
}