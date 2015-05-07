package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class GetHeight extends Getter{
	
	public GetHeight(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}

	@Override
	public Double getValue() {
		
		return ((GameObject) expr).getHeight();
	}
}
