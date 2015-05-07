package jumpingalien.program.expression.binary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public abstract class BinaryExpression extends Expression {
	
	public BinaryExpression(Expression first, Expression second, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.firstExpression = first;
		this.secondExpression = second;
	}
	
	public Expression getFirstExpression() {
		return this.firstExpression;
	}
	
	private final Expression firstExpression;
	
	public Expression getSecondExpression() {
		return this.secondExpression;
	}
	
	private final Expression secondExpression;
}
