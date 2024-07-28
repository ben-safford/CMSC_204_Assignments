
/**
 * Utils class for calculating infix / postfix conversions
 */
public class Notation {

	/**
	 * Converts an infix expression to a postfix one
	 * @param infix: the infix string
	 * @return the same expression in postfix format
	 * @throws InvalidNotationFormatException
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		// postfix expression cannot have more characters than infix so we can allocate infix length as maximum postfix length
		MyQueue<Character> solution = new MyQueue<Character>(infix.length());
		MyStack<Character> pStack = new MyStack<Character>(infix.length());
		try {
			for(int i = 0; i < infix.length(); i++) {
				char c = infix.charAt(i);
				if(c==' ')
					continue;
				if(Character.isDigit(c))
					solution.enqueue(c);
				if(c=='(')
					pStack.push(c);
				if(isOperator(c))
					handleOperator_Infix(solution, pStack, c);
				
				if(c==')') {
					handleRightParen_Infix(solution, pStack);
				}
			}
			while(!pStack.isEmpty()) {
				char c = pStack.pop();
				solution.enqueue(c);
				
				if(c == '(')
					throw new InvalidNotationFormatException();
			}
		} catch (Exception e) {
			if(e instanceof InvalidNotationFormatException)
				throw (InvalidNotationFormatException)e;
			else
				throw new InvalidNotationFormatException();
		}
		
		return solution.toString();
	}

	private static void handleRightParen_Infix(MyQueue<Character> solution, MyStack<Character> pStack)
			throws StackUnderflowException, QueueOverflowException, InvalidNotationFormatException {
		char c2 = '?';
		while(c2 != '(' && !pStack.isEmpty()) {
			c2 = pStack.pop();
			if(c2 == '(')
				break;
			else
				solution.enqueue(c2);
		}
		if(c2!= '(')
			throw new InvalidNotationFormatException();
	}

	private static void handleOperator_Infix(MyQueue<Character> solution, MyStack<Character> pStack, char operator)
			throws StackUnderflowException, QueueOverflowException, StackOverflowException {
		boolean pushingOps = !pStack.isEmpty();
		while(pushingOps) {
			char stackTop = pStack.pop();
			if(equalHigherPrecedence(operator, stackTop)) {
				solution.enqueue(stackTop);
				pushingOps = !pStack.isEmpty();
			} else {
				pStack.push(stackTop);
				pushingOps = false;
			}
		}
		pStack.push(operator);
	}

	/**
	 * Converts a postfix expression to an infix one
	 * @param postfix: the postfix string
	 * @return the same expression in infix format
	 * @throws InvalidNotationFormatException
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		//don't feel like calculating maximum length so i'm just gonna throw 100 at it and call it a day
		//this will work for all reasonable use cases.
		MyStack<String> pStack = new MyStack<String>(100);
		try {
			for(int i = 0; i < postfix.length(); i++) {
				char c = postfix.charAt(i);
				if(c == ' ')
					continue;
				if(Character.isDigit(c))
					pStack.push(String.valueOf(c));
				if(isOperator(c)) {
					if(pStack.isEmpty())
						throw new InvalidNotationFormatException();
					String postOp = pStack.pop();
					String preOp = pStack.pop();
					pStack.push("(" + preOp + c + postOp + ")");
				}
			}
			if(pStack.size() != 1)
				throw new InvalidNotationFormatException();
			return pStack.pop();
		} catch (Exception e) {
			if(e instanceof InvalidNotationFormatException)
				throw (InvalidNotationFormatException)e;
			else {
				throw new InvalidNotationFormatException();
			}
		}
	}

	/**
	 * calculates the double result of a postfix expression
	 * @param postfixExpr: the expression to evaulate
	 * @return the value of the expression
	 * @throws InvalidNotationFormatException
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		MyStack<Double> pStack = new MyStack<Double>(100);
		try {
			for(int i = 0; i < postfixExpr.length(); i++) {
				char c = postfixExpr.charAt(i);
				if(c == ' ')
					continue;
				if(Character.isDigit(c))
					pStack.push((double)Character.digit(c, 10));
				if(isOperator(c)) {
					if(pStack.size() < 2)
						throw new InvalidNotationFormatException();
					double b = pStack.pop();
					double a = pStack.pop();
					switch(c) {
					case '+':
						pStack.push(a + b);
						break;
					case '-':
						pStack.push(a - b);
						break;
					case '*':
						pStack.push(a * b);
						break;
					case '/':
						pStack.push(a / b);
						break;
					}
				}
			}
			if(pStack.size() != 1)
				throw new InvalidNotationFormatException();
			return pStack.pop();
		} catch(Exception e) {
			if(e instanceof InvalidNotationFormatException)
				throw (InvalidNotationFormatException)e;
			else {
				throw new InvalidNotationFormatException();
			}
		}
	}

	private static boolean isOperator(char c) {
		return (c == '+' || c == '-' || c == '*' || c == '/');
	}
	
	/**
	 * does c2 have equal or higher precedence than c1
	 * @param c1
	 * @param c2
	 * @return
	 */
	private static boolean equalHigherPrecedence(char c1, char c2) {
		if(!isOperator(c2))
			return false;
		if(c1 == '+' || c1 =='-') //plus/minus have lowest precedence
			return true;
		return (c2 != '+' && c2 != '-'); //if c2 is mult/div, it's equal-higher, otherwise it's not
	}
}
