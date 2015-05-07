package jumpingalien.program.expression.unary;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class GetHeight extends Getter{
	
	public GetHeight(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}

	@Override
	public Double getValue() {
		try{
			return (double) ((GameObject) getExpression().getValue()).getHeight();
		} catch(Exception exc){
			return 0.0;
		}
	}
}
