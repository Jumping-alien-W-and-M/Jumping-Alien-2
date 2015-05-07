package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.type.Type;

public class Assignment extends Statement {
	
	public Assignment(String variableName, Type variableType, Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.variableName = variableName;
		this.variableType = variableType;
		this.value = value;
	}
	
	public String getVariableName() {
		return this.variableName;
	}
	
	private final String variableName;
	
	public Type getVariableType() {
		return this.variableType;
	}
	
	private final Type variableType;
	
	public Expression getValue() {
		return this.value;
	}
	
	private final Expression value;
	

	
}
