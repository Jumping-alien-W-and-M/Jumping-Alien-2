package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class If extends Statement {
	public If(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation){
		super(sourceLocation);
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	public Expression getCondition(){
		return this.condition;
	}
	
	private final Expression condition;
	
	public Statement getIfBody(){
		return this.ifBody;
	}
	
	private final Statement ifBody;
	
	public Statement getElseBody(){
		return this.elseBody;
	}
	
	private final Statement elseBody;
}
