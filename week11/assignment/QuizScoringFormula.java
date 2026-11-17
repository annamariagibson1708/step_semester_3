import java.util.ArrayDeque;
import java.util.Deque;

public class QuizScoringFormula {

    // Evaluates a mathematical expression using an operand stack and an operator stack in a single pass
    public static int evaluate(String expr) {
        Deque<Integer> values = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        int n = expr.length();

        for (int i = 0; i < n; i++) {
            char ch = expr.charAt(i);

            // Skip spaces
            if (ch == ' ') {
                continue;
            }

            // Parse multi-digit non-negative integers
            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < n && Character.isDigit(expr.charAt(i))) {
                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                i--; // Step back since the for-loop will increment i
                values.push(num);
            } else if (ch == '(') {
                ops.push(ch);
            } else if (ch == ')') {
                // Apply operators until matching '(' is reached
                while (!ops.isEmpty() && ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                if (!ops.isEmpty()) {
                    ops.pop(); // Discard '('
                }
            } else if (isOperator(ch)) {
                // Apply every operator on the stack with greater or equal precedence (stopping at '(')
                while (!ops.isEmpty() && ops.peek() != '(' && precedence(ops.peek()) >= precedence(ch)) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(ch);
            }
        }

        // Apply remaining operators in the stack
        while (!ops.isEmpty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static int precedence(char op) {
        if (op == '*' || op == '/') {
            return 2;
        }
        if (op == '+' || op == '-') {
            return 1;
        }
        return 0;
    }

    // Note: b is popped first (right operand), a is popped second (left operand)
    private static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // Integer division
            default: return 0;
        }
    }

    public static void main(String[] args) {
        // Sample 1
        String expr1 = "2 * (3 + 4) - 10 / 5";
        System.out.println("Sample 1 Output: " + evaluate(expr1));
        // Expected: 12

        // Sample 2
        String expr2 = "(8 - 2) * (1 + 2) / 4";
        System.out.println("Sample 2 Output: " + evaluate(expr2));
        // Expected: 4
    }
}