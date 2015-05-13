package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class GetX extends Getter {
	
	public GetX(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}

	@Override
	public Object getValue(GameObject executingObject) {
		try{
			return (double) ((GameObject) getExpression().getValue(executingObject)).getX();
		} catch(Exception exc) {
			return 0.0;
		}
	}
}
