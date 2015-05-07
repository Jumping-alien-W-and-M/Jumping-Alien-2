package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

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
