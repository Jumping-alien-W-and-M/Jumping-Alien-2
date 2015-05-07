package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsDucking extends Checker {
	
	public IsDucking(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}

	@Override
	public Boolean getValue() {
		try{
			return ((Mazub) getExpression().getValue()).getDucking();
		} catch(Exception exc) {
			return false;
		}
	}
}