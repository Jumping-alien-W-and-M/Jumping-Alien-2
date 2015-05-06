package jumpingalien.program.statement;

import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.part3.programs.SourceLocation;

public class ForEach<E, S> extends Statement {
	
	public ForEach(String variableName, Kind variableKind, E where, E sort, SortDirection sortDirection, S body,
			SourceLocation sourceLocation) {
		this.variableName = variableName;
		this.variableKind = variableKind;
		this.where = where;
		this.sort = sort;
		this.sortDirection = sortDirection;
		this.body = body;
		this.sourceLocation = sourceLocation;
	}
	
	public String getVariableName() {
		return this.variableName;
	}
	
	private final String variableName;
	
	public Kind getVariableKind() {
		return this.variableKind;
	}
	
	private final Kind variableKind;
	
	public E getWhere() {
		return this.where;
	}
	
	private final E where;
	
	public E getSort() {
		return this.sort;
	}
	
	private final E sort;
	
	public SortDirection getSortDirection() {
		return this.sortDirection;
	}
	
	private final SortDirection sortDirection;
	
	public S getBody() {
		return this.body;
	}
	
	private final S body;
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;
	
}
