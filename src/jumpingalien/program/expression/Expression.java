package jumpingalien.program.expression;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ParserObject;
import jumpingalien.program.Type;

public abstract class Expression extends ParserObject {

	public Expression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	public abstract Object getValue(GameObject executingObject);
	
	@Basic
	public Type getType() {
		return this.type;
	}
	
	protected void setType(Type type) {
		this.type = type;
	}
	
	private Type type;
	
}
