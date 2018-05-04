package telran.calculator.dto;

public class CalculateData {
public String operation;
public int op1;
public int op2;
public String getOperation() {
	return operation;
}
public int getOp1() {
	return op1;
}
public int getOp2() {
	return op2;
}
public CalculateData(String operation, int op1, int op2) {
	super();
	this.operation = operation;
	this.op1 = op1;
	this.op2 = op2;
}
public CalculateData() {}
}
