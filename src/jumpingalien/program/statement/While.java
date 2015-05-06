package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;

public class While<E, S> extends Statement {
	
	public While(E condition, S body, SourceLocation sourceLocation) {
		this.condition = condition;
		this.body = body;
		this.sourceLocation = sourceLocation;
	}
	
	public E getCondition() {
		return this.condition;
	}
	
	private final E condition;
	
	public S getBody() {
		return this.body;
	}
	
	private final S body;
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;
	
}
