package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class StartRun extends Statement {

	public StartRun(Expression direction, SourceLocation sourceLocation){
		super(sourceLocation);
		this.direction = direction;
	}
	
	public Expression getDirection(){
		return this.direction;
	}
	private final Expression direction;
}