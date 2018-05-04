package telran.calculations;

import telran.net.Protocol;
import static telran.calculations.common.CalculationsConstants.*;
public class CalculatorProtocol implements Protocol {


	@Override
	public String getResponse(String request) {
		if(!isRightRequestStructure(request))
			return WRONG_REQUEST;
		return requestProcessing(request);
	}

	private String requestProcessing(String request) {
		String requestType=request.substring
				(0, request.indexOf('#'));
		switch(requestType){
		case ADD:return add(request);
		case SUBTRACT:return subtract(request);
		case MULTIPLY:return multiply(request);
		case DIVIDE:return divide(request);
		case EXPRESSION:return expression(request);
		default: return WRONG_REQUEST;
		}
	}

	private String expression(String request) {
		String expr=request.substring(request.indexOf('#')+1);
		if(!isArithmeticExpression(expr))
			return WRONG_EXPRESSION;
		int res=computeExpression(expr);
		return Integer.toString(res);
	}

	private int computeExpression(String expression) {
		String operands[]=getExpressionOperands(expression);
		String operations[]=getOperations(expression);
		int res=Integer.parseInt(operands[0]);
		for(int i=1;i<operands.length;i++){
			res=computeOne(res,operands[i],operations[i]);
		}
		return res;
		}

	private static int computeOne(int res, String operand, String operation) {
		int number=Integer.parseInt(operand);
		switch(operation){
		case "+": res+=number;break;
		case "-":res-=number;break;
		case "*":res*=number;break;
		case "/":res=(number!=0?res/number:Integer.MAX_VALUE);break;
		default: res=0;System.out.println("unknown operation");
		}
		return res;
	}

	private static boolean isArithmeticExpression(String expression) {
		String regex = "\\s*\\d+(\\s*[*/\\-+]\\s*\\d+)*\\s*";
		return expression.matches(regex);
	}

	public static String[] getOperations(String expression) {
		
		return expression.split("[ \\d]+");
	}

	public static String[] getExpressionOperands(String expression) {
		
		return expression.trim().split("[ */+-]+");
	}

	private String divide(String request) {
		return computeOperation(request,'/');
	}

	private String computeOperation(String request, char operation) 
	{
		String expressionPart=request.substring(request.indexOf('#')+1);
		int res=computeExpression(expressionPart.replace('#', operation));
		return Integer.toString(res);
	}

	private String multiply(String request) {
		return computeOperation(request,'*');
	}

	private String subtract(String request) {
		return computeOperation(request,'-');
	}

	private String add(String request) {
		return computeOperation(request,'+');
	}

	private boolean isRightRequestStructure(String request) {
		
		String operationRequest="[a-z]+#\\d+#\\d+";
		String expressionRequest=EXPRESSION+"#[^#]+";
		String regex=String.format("(%s)|(%s)",
				operationRequest,expressionRequest);
		return request.matches(regex);
	}

}
