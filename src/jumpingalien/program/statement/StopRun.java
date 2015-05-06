package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;

public class StopRun<E> extends Statement {

	public StopRun(E direction, SourceLocation sourceLocation){
		this.direction = direction;
		this.sourceLocation = sourceLocation;
	}
	
	public E getDirection(){
		return this.direction;
	}
	private final E direction;
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;
}
