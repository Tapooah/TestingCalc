package telran.calculations.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import telran.calculations.CalculatorProtocol;
import telran.calculations.common.CalculationsConstants;

public class CalculatorProtocolTest {
CalculatorProtocol calculator;
	@Before
	public void setUp() throws Exception {
		calculator=new CalculatorProtocol();
	}

	@Test
	public void testRightOperations() {
		assertEquals("10",calculator.getResponse("add#7#3"));
		assertEquals("10",calculator.getResponse("subtract#13#3"));
		assertEquals("10",calculator.getResponse("multiply#5#2"));
		assertEquals("10",calculator.getResponse("divide#20#2"));
	}
	@Test
	public void testRightExpressions(){
		assertEquals("13",calculator.getResponse("expression#7*3-1/2+3"));
		assertEquals("13",calculator.getResponse("expression# 7 *3 -1/2+3 "));
	}
	@Test
	public void testWrongExpressions(){
		assertEquals(CalculationsConstants.WRONG_EXPRESSION,
				calculator.getResponse("expression#7**3-1/2+3"));
		assertEquals(CalculationsConstants.WRONG_EXPRESSION,
				calculator.getResponse("expression#/7*3-1/2+3"));
		assertEquals(CalculationsConstants.WRONG_EXPRESSION,
				calculator.getResponse("expression#7*3-1/a+3"));
	}
	@Test
	public void testWrongRequest(){
		assertEquals(CalculationsConstants.WRONG_REQUEST,
				calculator.getResponse("expresion#7*3-1/2+3"));
		assertEquals(CalculationsConstants.WRONG_REQUEST,
				calculator.getResponse("add#7#3#5"));
	}

}
