package jumpingalien.program.expression.binary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class NotEquals extends CompBinaryExpression {

	public NotEquals(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	@Override
	public Boolean getValue(GameObject executingObject) {
		if (getFirstExpression().getType() == Type.DOUBLE)
			return !getFirstExpression().getValue(executingObject).equals(getSecondExpression().getValue(executingObject));
		return (Double.compare((double) getFirstExpression().getValue(executingObject)
					, (double) getSecondExpression().getValue(executingObject)) != 0);
	}
	
}
