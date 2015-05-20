package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class GetWidth extends Getter {
	
	public GetWidth(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}

	@Override
	public Object getValue(GameObject executingObject) {
		try{
			return (double) ((GameObject) getExpression().getValue(executingObject)).getWidth();
		} catch(Exception exc) {
			executingObject.getProgram().setRunTimeError(true);
			executingObject.getProgram().setStatementsLeft(0);
			return 0.0;
		}
	}
	
	
}
