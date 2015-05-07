package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class IsAir extends Checker {
	
	public IsAir(Expression expr, SourceLocation sourceLocation){
		super(expr, sourceLocation);
	}
}