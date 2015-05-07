package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class DoubleConstant extends Expression {
	
	public DoubleConstant(double value, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.value = value;
	}
	
	public double getValue() {
		return this.value;
	}
	
	private final double value;
	
}
