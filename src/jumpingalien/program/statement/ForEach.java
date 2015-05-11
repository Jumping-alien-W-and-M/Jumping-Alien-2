package jumpingalien.program.statement;

import java.util.ArrayList;
import java.util.stream.Stream;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.ProgramExecutor;
import jumpingalien.program.Type;
import jumpingalien.program.expression.Expression;

public class ForEach extends Statement {
	
	public ForEach(String variableName, Kind variableKind, Expression where, Expression sort, SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		if(where.getType() != Type.BOOL) Program.printTypeCheckError(sourceLocation); 
		if(sort.getType() != Type.DOUBLE) Program.printTypeCheckError(sourceLocation); 
		
		this.variableName = variableName;
		this.variableKind = variableKind;
		this.where = where;
		this.sort = sort;
		this.sortDirection = sortDirection;
		this.body = body;
	}

	@Basic
	public String getVariableName() {
		return this.variableName;
	}
	
	private final String variableName;

	@Basic
	public Kind getVariableKind() {
		return this.variableKind;
	}
	
	private final Kind variableKind;

	@Basic
	public Expression getWhere() {
		return this.where;
	}
	
	private final Expression where;

	@Basic
	public Expression getSort() {
		return this.sort;
	}
	
	private final Expression sort;

	@Basic
	public SortDirection getSortDirection() {
		return this.sortDirection;
	}
	
	private final SortDirection sortDirection;

	@Basic
	public Statement getBody() {
		return this.body;
	}
	
	private final Statement body;	
	
	@Basic
	public Object[] getObjects() {
		return this.objects;
	}
	
	private void setObjects(Object[] objects) {
		this.objects = objects;
	}
	
	private Object[] objects = null;

	@Basic
	public int getCurrentIndex() {
		return this.current_index;
	}
	
	private void setCurrentIndex(int current_index) {
		this.current_index = current_index;
	}
	
	private int current_index;
	
	@Basic
	public boolean getInBody()  {
		return this.in_body;
	}
	
	private void setInBody(boolean in_body) {
		this.in_body = in_body;
	}
	
	private boolean in_body = false;
	
	@Override
	public ExecutionState execute() {
		
		ProgramExecutor.setStatementsLeft(ProgramExecutor.getStatementsLeft() + 1);
		
		if (getObjects() == null) {
			setObjects(getCorrectObjects());
			setCurrentIndex(0);
		}
		
		while(ProgramExecutor.getStatementsLeft() > 0) {
			
			if (!getInBody()) {
				if (current_index == getObjects().length) {
					setObjects(null);
					return ExecutionState.DONE;
				}
				ProgramExecutor.setStatementsLeft(ProgramExecutor.getStatementsLeft() - 1);
				assign(getObjects()[current_index++]);
				setInBody(true);
			}
			
			if (ProgramExecutor.getStatementsLeft() <= 0) return ExecutionState.NOTDONE;
			if (!(getBody() instanceof Sequence)) ProgramExecutor.setStatementsLeft(ProgramExecutor.getStatementsLeft() - 1);
			
			ExecutionState state = getBody().execute();
			if (state == ExecutionState.DONE) setInBody(false);
			if (state == ExecutionState.BREAK) {
				setInBody(false);
				setObjects(null);
				return ExecutionState.DONE;
			}
		}
		
		return ExecutionState.NOTDONE;
		
	}

	private Object[] getCorrectObjects() {
		
		ArrayList<Object> objects = new ArrayList<Object>();
		
		if(getVariableKind() == Kind.MAZUB || getVariableKind() == Kind.ANY){
			Mazub player = ProgramExecutor.getExecutingObject().getWorld().getMazub();
			if(player != null) objects.add(player);
		}
		if(getVariableKind() == Kind.BUZAM || getVariableKind() == Kind.ANY){
			Buzam buzam = (Buzam) ProgramExecutor.getExecutingObject().getWorld().getBuzam();
			if(buzam != null) objects.add(buzam);
		}
		if(getVariableKind() == Kind.SLIME || getVariableKind() == Kind.ANY)
			objects.addAll(ProgramExecutor.getExecutingObject().getWorld().getSlimes());
		if(getVariableKind() == Kind.SHARK || getVariableKind() == Kind.ANY)
			objects.addAll(ProgramExecutor.getExecutingObject().getWorld().getSharks());
		if(getVariableKind() == Kind.PLANT || getVariableKind() == Kind.ANY)
			objects.addAll(ProgramExecutor.getExecutingObject().getWorld().getPlants());
		if(getVariableKind() == Kind.TERRAIN || getVariableKind() == Kind.ANY)
			objects.addAll(ProgramExecutor.getExecutingObject().getWorld().getFeatures().values());
		
		Stream<Object> stream = objects.stream();
		if (getWhere() != null) stream = stream.filter(object -> {
			assign(object);
			return ((boolean) getWhere().getValue());
		});
		if (getSort() != null) stream = stream.sorted((object1, object2) -> {
			assign(object1);
			double value1 = (double) getWhere().getValue();
			assign(object2);
			double value2 = (double) getWhere().getValue();
			if (getSortDirection() == SortDirection.ASCENDING) return Double.compare(value1, value2);
			return -Double.compare(value1, value2);
		});
		
		return stream.toArray();
	}
	
	private void assign(Object object) {
		ProgramExecutor.getExecutingObject().getProgram().setVariableValue(getVariableName(), Type.OBJECT, object);
	}
}
