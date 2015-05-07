package jumpingalien.program;

import jumpingalien.part3.programs.SourceLocation;

public abstract class ParserObject {

	public ParserObject(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;

}
