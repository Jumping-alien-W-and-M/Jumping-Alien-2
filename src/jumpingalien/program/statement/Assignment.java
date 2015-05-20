package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class Assignment extends Statement {
	
	public Assignment(String variableName, Type variableType, Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		if(value.getType() != variableType) Program.printTypeCheckError(sourceLocation);
			
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
	
	public ExecutionState execute(GameObject executingObject) {
		if(executingObject.getProgram().containsVariable(getVariableName(), getVariableType())){
			executingObject.getProgram().setRunTimeError(true);
			executingObject.getProgram().setStatementsLeft(0);
			return ExecutionState.NOTDONE;
		}
		executingObject.getProgram().setVariableValue(getVariableName(), 
				getVariableType(), getExpression().getValue(executingObject));
		return ExecutionState.DONE;
	}
	
}
