package telran.calculator;

import java.util.*;
import java.util.function.BinaryOperator;

public class Calculator {
	static HashMap<String, BinaryOperator<Integer>> operations;
	static {
		operations = new HashMap<>();
		operations.put("+", (x, y) -> x + y);
		operations.put("-", (x, y) -> x - y);

	}

	Set<String> getOperations() {
		return operations.keySet();

	}

	int getResult(String operation, int op1, int op2) {
		BinaryOperator<Integer> operator = operations.get(operation);
		if (operator == null) {
			return Integer.MAX_VALUE;
		}
		return operator.apply(op1, op2);
	}
}
