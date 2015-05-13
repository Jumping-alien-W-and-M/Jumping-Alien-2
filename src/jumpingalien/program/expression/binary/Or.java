package jumpingalien.program.expression.binary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class Or extends BoolBinaryExpression {

	public Or(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	@Override
	public Boolean getValue(GameObject executingObject) {
		return (boolean) getFirstExpression().getValue(executingObject) 
					|| (boolean) getSecondExpression().getValue(executingObject);
	}
	
}
