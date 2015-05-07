package jumpingalien.program.expression.binary;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public abstract class MathBinaryExpression extends BinaryExpression {

	public MathBinaryExpression(Expression first, Expression second, SourceLocation sourceLocation) {
		super(first, second, sourceLocation);
		
		setType(Type.DOUBLE);
		if ((first.getType() != Type.DOUBLE) || (second.getType() != Type.DOUBLE)) Program.printTypeCheckError(sourceLocation);
	}

}
