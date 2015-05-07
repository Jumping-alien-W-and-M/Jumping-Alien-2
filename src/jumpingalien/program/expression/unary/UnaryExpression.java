package jumpingalien.program.expression.unary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public abstract class UnaryExpression extends Expression {
	
	public UnaryExpression(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}
	
	private final Expression expression;
}
