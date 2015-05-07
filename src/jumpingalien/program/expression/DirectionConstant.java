package jumpingalien.program.expression;

import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;

public class DirectionConstant extends Expression {
	
	public DirectionConstant(Direction value, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.value = value;
	}
	
	public Direction getValue() {
		return this.value;
	}
	
	private final Direction value;
	
}
