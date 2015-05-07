package jumpingalien.program.expression;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

public class DoubleConstant extends Expression {
	
	public DoubleConstant(double value, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		setType(Type.DOUBLE);
		
		this.value = value;
	}
	
	@Basic @Immutable @Override
	public Double getValue() {
		return this.value;
	}
	
	private final double value;
	
}
