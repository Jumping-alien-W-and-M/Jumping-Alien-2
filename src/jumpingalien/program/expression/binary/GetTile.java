package jumpingalien.program.expression.binary;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class GetTile extends BinaryExpression {

	public GetTile(Expression first, Expression second, SourceLocation sourceLocation) {
		super(first, second, sourceLocation);
	}
	
}
