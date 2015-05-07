package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

public class ReadVariable extends Expression {
	
	public ReadVariable(String variableName, Type variableType, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.variableName = variableName;
		setType(variableType);
	}
	
	public String getVariableName() {
		return this.variableName;
	}
	
	private final String variableName;
	
}
