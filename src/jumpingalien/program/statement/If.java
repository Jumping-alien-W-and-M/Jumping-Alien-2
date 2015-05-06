package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;

public class If<E, S> extends Statement {
	public If(E condition, S ifBody, S elseBody, SourceLocation sourceLocation){
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
		this.sourceLocation = sourceLocation;
	}
	
	public E getCondition(){
		return this.condition;
	}
	
	private final E condition;
	
	public S getIfBody(){
		return this.ifBody;
	}
	
	private final S ifBody;
	
	public S getElseBody(){
		return this.elseBody;
	}
	
	private final S elseBody;
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;
}
