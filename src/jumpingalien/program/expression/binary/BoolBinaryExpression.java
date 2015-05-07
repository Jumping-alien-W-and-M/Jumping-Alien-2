package jumpingalien.program.expression.binary;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public abstract class BoolBinaryExpression extends BinaryExpression {

	public BoolBinaryExpression(Expression first, Expression second, SourceLocation sourceLocation) {
		super(first, second, sourceLocation);
		
		setType(Type.BOOL);
		if ((first.getType() != Type.BOOL) || (second.getType() != Type.BOOL)) Program.printTypeCheckError(sourceLocation);
	}
}
