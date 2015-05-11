package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ProgramExecutor;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class Assignment extends Statement {
	
	public Assignment(String variableName, Type variableType, Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.variableName = variableName;
		this.variableType = variableType;
		this.expression = value;
	}
	
	public String getVariableName() {
		return this.variableName;
	}
	
	private final String variableName;
	
	public Type getVariableType() {
		return this.variableType;
	}
	
	private final Type variableType;
	
	public Expression getExpression() {
		return this.expression;
	}
	
	private final Expression expression;
	
	public boolean execute(){
		ProgramExecutor.getExecutingObject().getProgram().setVariableValue(getVariableName(), 
				getVariableType(), getExpression().getValue());
		return true;
	}
	
}
