package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;

public class StartJump {
	public StartJump(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;
}
