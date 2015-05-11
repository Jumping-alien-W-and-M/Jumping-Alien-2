package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.*;

public class Wait extends Statement {
	
	public Wait(Expression duration, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.duration = duration;
	}
	
	public Expression getDuration() {
		return this.duration;
	}
	
	private final Expression duration;
	
	public double getDurationLeft() {
		return this.duration_left;
	}
	
	private double duration_left;
	
	@Override
	public boolean execute() {
		
		
		
		return true;
	}
	
}
