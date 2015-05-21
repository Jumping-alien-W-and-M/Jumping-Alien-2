package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.util.Util;

public class Sqrt extends MathUnaryExpression{
	
	public Sqrt(Expression expr, SourceLocation sourceLocation) {
		super(expr, sourceLocation);
	}

	@Override
	public Double getValue(GameObject executingObject) {
		
		double value = (double) getExpression().getValue(executingObject);
		
		// Handles values near zero and below
		if (value <= Util.DEFAULT_EPSILON) {
			if (value < -Util.DEFAULT_EPSILON) {
				executingObject.getProgram().setStatementsLeft(0);
				executingObject.getProgram().setRunTimeError(true);
			}
			return 0.0;
		}
		
		return Math.sqrt(value);
	}
}
