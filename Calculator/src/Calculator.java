import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BinaryOperator;

public class Calculator {
	private static final Map<Character, BinaryOperator<Double>> operationMap = createMap();
	
	private static Map<Character, BinaryOperator<Double>> createMap() {
        Map<Character, BinaryOperator<Double>> result = new HashMap<Character, BinaryOperator<Double>>();
        result.put(new Character('+'), (a, b) -> a + b);
        result.put(new Character('-'), (a, b) -> a - b);
        result.put(new Character('*'), (a, b) -> a * b);
        result.put(new Character('/'), (a, b) -> b / a);
        return Collections.unmodifiableMap(result);
    }
	
	// If op2 has higher precedence return true, false otherwise 
    private boolean precedence(char op1, char op2) 
    { 
        return op2 != '(' && op2 != ')' && op1 != '*' && op1 != '/'; 
    }
    
    private boolean isDouble(char currentChar) {
    	return Character.isDigit(currentChar) || currentChar == '.';
    }
	
	public double calculate(double n1, double n2, char op) {
		return operationMap.get(op).apply(n1, n2);
	}
	
	public String calculate(String expression) {
		char[] charFromExpression = expression.toCharArray();
		  
       // Stack of numbers 
       Stack<Double> numbers = new Stack<Double>(); 
 
       // Stack of Operators 
       Stack<Character> ops = new Stack<Character>(); 
 
       for (int i = 0; i < charFromExpression.length; i++) {
    	   
            // Skip whitespace
           if (charFromExpression[i] == ' ') 
               continue; 
           
           // Push to numbers stack
           if (this.isDouble(charFromExpression[i])) { 
               String sdouble = ""; 
               // Get complete number, until number ends 
               while (i < charFromExpression.length && this.isDouble(charFromExpression[i])) 
            	   sdouble += charFromExpression[i++];
               --i;
               numbers.push(Double.parseDouble(sdouble));
           }
           
    	   // Push '(' to ops stack
    	   if (charFromExpression[i] == '(')
               ops.push(charFromExpression[i]);
    	   
           // Evaluate inner () expressions and add the result to numbers stack
           if (charFromExpression[i] == ')') { 
               while (ops.peek() != '(')
            	   numbers.push(this.calculate(numbers.pop(), numbers.pop(), ops.pop())); 
               ops.pop(); 
           }
           
    	   // Current char is an operator
    	   if (operationMap.containsKey(charFromExpression[i])) { 
	           // Make partial operations of the higher precedence ops  
	           while (!ops.empty() && this.precedence(charFromExpression[i], ops.peek())) 
	        	   numbers.push(this.calculate(numbers.pop(), numbers.pop(), ops.pop())); 
	 
	           // Push current char to ops stack 
	           ops.push(charFromExpression[i]); 
           } 
       }
 
       // Calculate remaining 
       while (!ops.empty()) 
    	   numbers.push(this.calculate(numbers.pop(), numbers.pop(), ops.pop())); 
 
       DecimalFormat df = new DecimalFormat("###.##");

       // Return result at the top
       return df.format(numbers.pop()); 
	}
}
