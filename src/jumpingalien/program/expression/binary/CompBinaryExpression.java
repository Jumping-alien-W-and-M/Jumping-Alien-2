package jumpingalien.program.expression.binary;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public abstract class CompBinaryExpression extends BinaryExpression {

	public CompBinaryExpression(Expression first, Expression second, SourceLocation sourceLocation) {
		super(first, second, sourceLocation);
		
		setType(Type.BOOL);
		if (first.getType() != second.getType()) Program.printTypeCheckError(sourceLocation);
	}
}
