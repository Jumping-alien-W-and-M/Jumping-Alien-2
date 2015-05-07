package jumpingalien.model;

import java.util.Map;

import jumpingalien.program.Type;
import jumpingalien.program.statement.Statement;

public class Program {
	
	public Program(Statement mainStatement, Map<String, Type> globalVariables) {
		this.mainStatement = mainStatement;
		this.globalVariables = globalVariables;
	}
	
	public Statement getMainStatement() {
		return this.mainStatement;
	}
	
	private final Statement mainStatement;
	
	public Map<String, Type> getGlobalVariables() {
		return this.globalVariables;
	}
	
	private final Map<String, Type> globalVariables;
	
}
