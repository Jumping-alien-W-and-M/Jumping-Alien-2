package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;

public class Statement {

	public Statement(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;
	
}
