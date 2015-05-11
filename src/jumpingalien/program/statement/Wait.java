package jumpingalien.program.statement;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ProgramExecutor;
import jumpingalien.program.Type;
import jumpingalien.program.expression.*;

public class Wait extends Statement {
	
	public Wait(Expression duration, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.duration = duration;
		
		if(duration.getType() != Type.DOUBLE) Program.printTypeCheckError(sourceLocation);
	}
	
	@Basic
	public Expression getDuration() {
		return this.duration;
	}
	
	private final Expression duration;
	
	@Basic
	public double getDurationLeft() {
		return this.duration_left;
	}
	
	private void setDurationLeft(double duration_left) {
		this.duration_left = duration_left;
	}
	
	private double duration_left = 0;
	
	@Override
	public boolean execute() {
		
		if (getDurationLeft() <= 0) {
			setDurationLeft((double) getDuration().getValue());
		}
		
		while(ProgramExecutor.getStatementsLeft() > 0 && getDurationLeft() > 0) {
			ProgramExecutor.setStatementsLeft(ProgramExecutor.getStatementsLeft() - 1);
			setDurationLeft(getDurationLeft() - 0.001);
		}
		
		return (getDurationLeft() <= 0);
	}
	
}
