package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class GetHitpoints extends Getter{
	
	public GetHitpoints(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
	
	public Double getValue(GameObject executingObject){
		try{
			return (double) ((GameObject) getExpression().getValue(executingObject)).getHitpoints();
		} catch(Exception exc) {
			executingObject.getProgram().setRunTimeError(true);
			executingObject.getProgram().setStatementsLeft(0);
			return 0.0;
		}
	}
}
