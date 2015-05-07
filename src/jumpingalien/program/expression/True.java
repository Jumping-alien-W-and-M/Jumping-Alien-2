package jumpingalien.program.expression;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

public class True extends Expression {
	
	public True(SourceLocation sourceLocation) {
		super(sourceLocation);
		
		setType(Type.BOOL);
		
		this.value = true;
	}
	
	@Basic @Immutable @Override
	public Boolean getValue() {
		return this.value;
	}
	
	private final boolean value;
}
