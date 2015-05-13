package jumpingalien.model;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.Type;
import jumpingalien.program.statement.ActionStatement;
import jumpingalien.program.statement.Break;
import jumpingalien.program.statement.ForEach;
import jumpingalien.program.statement.If;
import jumpingalien.program.statement.Sequence;
import jumpingalien.program.statement.Statement;
import jumpingalien.program.statement.While;

public class Program {
	
	public Program(Statement mainStatement, Map<String, Type> globalVariables) throws IllegalArgumentException {
		this.mainStatement = mainStatement;
		
		for(String key : globalVariables.keySet()) {
			Type type = globalVariables.get(key);
			if (type == Type.DOUBLE) globalDoubles.put(key, 0.0);
			else if (type == Type.BOOL) globalBools.put(key, false);
			else if (type == Type.OBJECT) globalObjects.put(key, null);
			else globalDirections.put(key, Direction.LEFT);
		}
		
		if (!isValidProgram()) {
			System.out.println("The given program is not valid!");
			
			throw new IllegalArgumentException();
		}
	}
	
	public Statement getMainStatement() {
		return this.mainStatement;
	}
	
	private final Statement mainStatement;
	
	private final Map<String, Double> globalDoubles = new HashMap<String, Double>();
	private final Map<String, Boolean> globalBools = new HashMap<String, Boolean>();
	private final Map<String, Object> globalObjects = new HashMap<String, Object>();
	private final Map<String, Direction> globalDirections = new HashMap<String, Direction>();
	
	public Object getVariableValue(String name, Type type) {
		if (type == Type.DOUBLE) return globalDoubles.get(name);
		if (type == Type.BOOL) return globalBools.get(name);
		if (type == Type.OBJECT) return globalObjects.get(name);
		return globalDirections.get(name);
	}
	
	public void setVariableValue(String name, Type type, Object value){
		if (type == Type.DOUBLE) globalDoubles.put(name, (double) value);
		else if(type == Type.BOOL) globalBools.put(name, (Boolean) value);
		else if(type == Type.OBJECT) globalObjects.put(name, value);
		else if(type == Type.DIRECTION) globalDirections.put(name, (Direction) value);
	}
	
	public static void printTypeCheckError(SourceLocation sourceLocation) throws IllegalArgumentException {
		System.out.println("The parser encountered an expression violating the typing rules!");
		System.out.println("line: " + sourceLocation.getLine() + ", column: " + sourceLocation.getColumn());
		
		throw new IllegalArgumentException();
	}
	
	public boolean isValidProgram() {
		return isValidStatement(getMainStatement(), false, true);
	}
	
	public boolean isValidStatement(Statement statement, boolean canHaveBreak, boolean canHaveAction) {
		
		if (statement instanceof Sequence) {
			for(Statement next_statement : ((Sequence) statement).getStatements()) {
				if (!isValidStatement(next_statement, canHaveBreak, canHaveAction))
					return false;
			}
		} else if (statement instanceof While) {
			if (!isValidStatement(((While) statement).getBody(), true, canHaveAction))
				return false;
		} else if (statement instanceof ForEach) {
			if (!isValidStatement(((ForEach) statement).getBody(), true, false))
				return false;
		} else if (statement instanceof If) {
			if (!isValidStatement(((If) statement).getIfBody(), canHaveBreak, canHaveAction) ||
					!isValidStatement(((If) statement).getElseBody(), canHaveBreak, canHaveAction))
				return false;
		} else if (statement instanceof Break && !canHaveBreak) {
			return false;
		} else if (statement instanceof ActionStatement && !canHaveAction) {
			return false;
		}
		
		return true;
	}
}
