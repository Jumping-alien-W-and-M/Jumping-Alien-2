package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;

public class Print<E> extends Statement {
	
	public Print(E value, SourceLocation sourceLocation){
		this.value = value;
		this.sourceLocation = sourceLocation;
	}
	
	public E getValue(){
		return this.value;
	}
	
	private final E value;
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;
}
