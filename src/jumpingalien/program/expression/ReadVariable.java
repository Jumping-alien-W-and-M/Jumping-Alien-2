package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.type.Type;

public class ReadVariable extends Expression {
	
	public ReadVariable(String variableName, Type variableType, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.variableName = variableName;
		this.variableType = variableType;
	}
	
	public String getVariableName() {
		return this.variableName;
	}
	
	private final String variableName;
	
	public Type getVariableType() {
		return this.variableType;
	}
	
	private final Type variableType;
	
}
