package jumpingalien.program.expression.binary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class Multiplication extends MathBinaryExpression {

	public Multiplication(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	@Override
	public Double getValue(GameObject executingObject) {
		return (double) getFirstExpression().getValue(executingObject) 
					* (double) getSecondExpression().getValue(executingObject);
	}
	
}
