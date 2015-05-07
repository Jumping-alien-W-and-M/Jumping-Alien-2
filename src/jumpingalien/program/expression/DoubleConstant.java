package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

public class DoubleConstant extends Expression {
	
	public DoubleConstant(double value, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		setType(Type.DOUBLE);
		
		this.value = value;
	}
	
	@Override
	public double getValue() {
		return this.value;
	}
	
	private final double value;
	
}
