package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class Print extends Statement {
	
	public Print(Expression value, SourceLocation sourceLocation){
		super(sourceLocation);
		this.value = value;
	}
	
	public Expression getValue(){
		return this.value;
	}
	
	private final Expression value;
	
	@Override
	public boolean execute() {
		
		System.out.println(getValue().getValue());
		return true;
	}
}
