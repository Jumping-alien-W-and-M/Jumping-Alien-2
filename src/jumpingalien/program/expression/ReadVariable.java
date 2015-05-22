package jumpingalien.program.expression;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

public class ReadVariable extends Expression {
	
	public ReadVariable(String variableName, Type variableType, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.variableName = variableName;
		setType(variableType);
	}
	
	@Basic @Immutable
	public String getVariableName() {
		return this.variableName;
	}
	
	private final String variableName;
	
	@Override
	public Object getValue(GameObject executingObject) {
		if (!executingObject.getProgram().containsVariable(getVariableName(), getType())) {
			executingObject.getProgram().setStatementsLeft(0);
			executingObject.getProgram().setRunTimeError(true);
			return 0.0;
		}
		
		return executingObject.getProgram().getVariableValue(getVariableName(), getType());
	}
	
}
