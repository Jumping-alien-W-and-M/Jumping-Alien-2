package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class Random extends UnaryExpression {
	public Random(Expression maxValue, SourceLocation sourceLocation){
		super(maxValue, sourceLocation);
	}
}
