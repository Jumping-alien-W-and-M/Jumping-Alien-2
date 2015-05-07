package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.statement.*;
import jumpingalien.program.expression.*;
import jumpingalien.program.type.Type;

public class Wait extends Statement {
	
	public Wait(Expression duration, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.duration = duration;
	}
	
	public Expression getDuration() {
		return this.duration;
	}
	
	private final Expression duration;
	
}
