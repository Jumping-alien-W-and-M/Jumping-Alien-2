package jumpingalien.program.expression.binary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class Equals extends CompBinaryExpression {

	public Equals(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	@Override
	public Boolean getValue(GameObject executingObject) {
		if (getFirstExpression().getType() == Type.DOUBLE)
			return (Double.compare((double) getFirstExpression().getValue(executingObject),
					(double) getSecondExpression().getValue(executingObject)) == 0);
		if (getFirstExpression().getValue(executingObject) == null)
			return (getSecondExpression().getValue(executingObject) == null);
		return getFirstExpression().getValue(executingObject).equals(getSecondExpression().getValue(executingObject));
	}
	
}
