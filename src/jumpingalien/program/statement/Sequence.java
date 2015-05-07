package jumpingalien.program.statement;

import java.util.ArrayList;
import java.util.List;

import jumpingalien.part3.programs.SourceLocation;

public class Sequence extends Statement {
	
	public Sequence(List<Statement> statements, SourceLocation sourceLocation) {
		super(sourceLocation);
		
		this.statements = statements;
	}
	
	public List<Statement> getStatements() {
		return new ArrayList<Statement>(statements);
	}
	
	private final List<Statement> statements;
	
}
