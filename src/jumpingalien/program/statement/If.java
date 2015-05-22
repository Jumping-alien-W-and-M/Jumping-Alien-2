package jumpingalien.program.statement;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class If extends Statement {
	public If(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation){
		super(sourceLocation);
		
		assert(ifBody != null);
		
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
		
		if(condition.getType() != Type.BOOL) Program.printTypeCheckError(sourceLocation);
	}
	
	@Basic
	public Expression getCondition(){
		return this.condition;
	}
	
	private final Expression condition;
	
	@Basic
	public Statement getIfBody(){
		return this.ifBody;
	}
	
	private final Statement ifBody;
	
	@Basic
	public Statement getElseBody(){
		return this.elseBody;
	}
	
	private final Statement elseBody;
	
	public Statement getCorrectBody() {
		return ((getConditionValue() == 1)? getIfBody() : getElseBody());
	}
	
	@Basic
	public int getConditionValue() {
		return condition_value;
	}
	
	private void setConditionValue(int condition_value) {
		this.condition_value = condition_value;
	}
	
	private int condition_value = 0;
	
	@Override
	public ExecutionState execute(GameObject executingObject) {
		
		if (getConditionValue() == 0) setConditionValue((boolean) getCondition().getValue(executingObject) ? 1 : 2);
		else executingObject.getProgram().setStatementsLeft(executingObject.getProgram().getStatementsLeft() + 1);
		
		if (getCorrectBody() == null) {
			setConditionValue(0);
			return ExecutionState.DONE;
		}
		
		if (executingObject.getProgram().getStatementsLeft() <= 0) return ExecutionState.NOTDONE;
		
		if (!(getCorrectBody() instanceof Sequence))
			executingObject.getProgram().setStatementsLeft(executingObject.getProgram().getStatementsLeft() - 1);
		ExecutionState state = getCorrectBody().execute(executingObject);
		
		if (state != ExecutionState.NOTDONE) setConditionValue(0);
		
		return state;
	}
}
