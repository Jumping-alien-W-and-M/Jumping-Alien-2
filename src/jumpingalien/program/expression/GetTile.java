package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class GetTile extends BinaryExpression {

	public GetTile(Expression first, Expression second, SourceLocation sourceLocation) {
		super(first, second, sourceLocation);
	}
	
}
