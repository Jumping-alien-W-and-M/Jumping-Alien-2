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
		try{
			return (random * (double) getExpression().getValue(executingObject));
		} catch(Exception exc){
			executingObject.getProgram().setRunTimeError(true);
			executingObject.getProgram().setStatementsLeft(0);
			return 0.0;
		}
	}
	
	
}
