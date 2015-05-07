package jumpingalien.program.statement;

import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class ForEach extends Statement {
	
	public ForEach(String variableName, Kind variableKind, Expression where, Expression sort, SortDirection sortDirection, Statement body,
			SourceLocation sourceLocation) {
		super(sourceLocation);
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
}
