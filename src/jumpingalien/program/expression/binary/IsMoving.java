package jumpingalien.program.expression.binary;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class IsMoving extends BinaryExpression {

	public IsMoving(Expression expr, Expression direction, SourceLocation sourceLocation) {
		super(expr, direction, sourceLocation);
		
		setType(Type.BOOL);
		if ((expr.getType() != Type.OBJECT) || (direction.getType() != Type.DIRECTION)) Program.printTypeCheckError(sourceLocation);
	}
	
	@Override
	public Boolean getValue() {
		if (getFirstExpression().getValue() instanceof GameObject) {
			switch ((Direction) getSecondExpression().getValue()) {
				case LEFT: return (((GameObject) getFirstExpression().getValue()).getVx() < 0);
				case RIGHT: return (((GameObject) getFirstExpression().getValue()).getVx() > 0);
				case DOWN: return (((GameObject) getFirstExpression().getValue()).getVy() < 0);
				case UP: return (((GameObject) getFirstExpression().getValue()).getVy() > 0);
				default: System.out.println("Direction wasn't valid somehow!"); break;
			}
		}
		
		return false;
	}
	
}
