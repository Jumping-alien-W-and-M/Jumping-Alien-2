package jumpingalien.program.statement;

import java.util.ArrayList;

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
	
	public ForEach(String variableName, Kind variableKind, Expression where, Expression sort, SortDirection sortDirection, Statement body,
			SourceLocation sourceLocation) {
		super(sourceLocation);
		
		if( where.getType() != Type.BOOL) Program.printTypeCheckError(sourceLocation); 
		if( sort.getType() != Type.DOUBLE) Program.printTypeCheckError(sourceLocation); 
		
		this.variableName = variableName;
		this.variableKind = variableKind;
		this.where = where;
		this.sort = sort;
		this.sortDirection = sortDirection;
		this.body = body;
	}
	
	public String getVariableName() {
		return this.variableName;
	}
	
	private final String variableName;
	
	public Kind getVariableKind() {
		return this.variableKind;
	}
	
	private final Kind variableKind;
	
	public Expression getWhere() {
		return this.where;
	}
	
	private final Expression where;
	
	public Expression getSort() {
		return this.sort;
	}
	
	private final Expression sort;
	
	public SortDirection getSortDirection() {
		return this.sortDirection;
	}
	
	private final SortDirection sortDirection;
	
	public Statement getBody() {
		return this.body;
	}
	
	private final Statement body;	
	
	@Override
	public ExecutionState execute(){
		ArrayList<Object> objects = getCorrectObjects();
		ArrayList<Object> filtered_objects = objects.stream().filter(object -> object.(this.getWhere().getValue()));	
		
	}

	private ArrayList<Object> getCorrectObjects() {
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
		return objects;
	}
	
}
