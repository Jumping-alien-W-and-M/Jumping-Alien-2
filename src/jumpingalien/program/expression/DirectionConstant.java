package jumpingalien.program.expression;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;

public class DirectionConstant extends Expression {
	
	public DirectionConstant(Direction value, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		setType(Type.DIRECTION);
		
		this.value = value;
	}
	
	@Basic @Immutable @Override
	public Direction getValue() {
		return this.value;
	}
	
	private final Direction value;
	
}
