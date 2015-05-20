package jumpingalien.program.expression.binary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.util.Util;

public class Division extends MathBinaryExpression {

	public Division(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	@Override
	public Double getValue(GameObject executingObject) {
		
		if (Math.abs((double) getSecondExpression().getValue(executingObject)) < Util.DEFAULT_EPSILON) {
			executingObject.getProgram().setStatementsLeft(0);
			executingObject.getProgram().setRunTimeError(false);
		}
		
		return (double) getFirstExpression().getValue(executingObject) 
					/ (double) getSecondExpression().getValue(executingObject);
	}
	
}
