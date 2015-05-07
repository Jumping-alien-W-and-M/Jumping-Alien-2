package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class While extends Statement {
	
	public While(Expression condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.condition = condition;
		this.body = body;
	}
	
	public Expression getCondition() {
		return this.condition;
	}
	
	private final Expression condition;
	
	public Statement getBody() {
		return this.body;
	}
	
	private final Statement body;	
}
