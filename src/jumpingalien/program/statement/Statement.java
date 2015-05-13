package jumpingalien.program.statement;

import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ParserObject;

public abstract class Statement extends ParserObject {

	public Statement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	public abstract ExecutionState execute(GameObject executingObject);
	
}
