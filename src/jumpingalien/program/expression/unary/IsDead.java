package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsDead extends Checker {
	
	public IsDead(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}