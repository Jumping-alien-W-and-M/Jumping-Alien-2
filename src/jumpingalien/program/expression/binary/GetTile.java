package jumpingalien.program.expression.binary;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class GetTile extends BinaryExpression {

	public GetTile(Expression first, Expression second, SourceLocation sourceLocation) {
		super(first, second, sourceLocation);
		
		setType(Type.OBJECT);
		if ((first.getType() != Type.DOUBLE) || (second.getType() != Type.DOUBLE)) Program.printTypeCheckError(sourceLocation);
	}
	
	@Override
	public Object getValue(GameObject executingObject) {
		return executingObject.getWorld().getFeature(
				(int) (double) getFirstExpression().getValue(executingObject),
				(int) (double) getSecondExpression().getValue(executingObject));
	}
	
}
