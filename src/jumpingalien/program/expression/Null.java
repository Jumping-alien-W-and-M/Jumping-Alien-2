package jumpingalien.program.expression;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

public class Null extends Expression {
	
	public Null(SourceLocation sourceLocation) {
		super(sourceLocation);
		
		setType(Type.OBJECT);
		
		this.value = null;
	}
	
	@Basic @Immutable @Override
	public Object getValue() {
		return this.value;
	}
	
	private final Object value;
	
}
