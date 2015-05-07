package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public abstract class UnaryEpression extends Expression {
	
	public UnaryEpression(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
	
	private final Expression expression;
}
