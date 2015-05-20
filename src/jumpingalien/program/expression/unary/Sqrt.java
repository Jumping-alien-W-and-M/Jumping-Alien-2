package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class Sqrt extends MathUnaryExpression{
	
	public Sqrt(Expression expr, SourceLocation sourceLocation) {
		super(expr, sourceLocation);
	}

	@Override
	public Double getValue(GameObject executingObject) {
		try{
			return Math.sqrt((double) getExpression().getValue(executingObject));
		} catch(Exception exc){
			executingObject.getProgram().setRunTimeError(true);
			executingObject.getProgram().setStatementsLeft(0);
			return 0.0;
		}
	}
}
