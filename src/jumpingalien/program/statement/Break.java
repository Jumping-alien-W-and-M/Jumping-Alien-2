package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;

public class Break extends Statement {
	public Break(SourceLocation sourcelocation){
		this.sourceLocation = sourcelocation;
	}
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;
	
}
