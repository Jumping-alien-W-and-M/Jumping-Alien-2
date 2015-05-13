package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class Random extends MathUnaryExpression {
	public Random(Expression maxValue, SourceLocation sourceLocation){
		super(maxValue, sourceLocation);
	}

	@Override
	public Double getValue(GameObject executingObject) {
		double random = new java.util.Random().nextDouble();
		return (random * (double) getExpression().getValue(executingObject));
	}
	
	
}
