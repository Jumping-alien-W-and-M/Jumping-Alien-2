package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;

public class Assignment<T, E> extends Statement {
	
	public Assignment(String variableName, T variableType, E value, SourceLocation sourceLocation) {
		this.variableName = variableName;
		this.variableType = variableType;
		this.value = value;
		this.sourceLocation = sourceLocation;
	}
	
	public String getVariableName() {
		return this.variableName;
	}
	
	private final String variableName;
	
	public T getVariableType() {
		return this.variableType;
	}
	
	private final T variableType;
	
	public E getValue() {
		return this.value;
	}
	
	private final E value;
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;
	
}
