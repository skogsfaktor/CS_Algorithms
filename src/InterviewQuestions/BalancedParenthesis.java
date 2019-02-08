package InterviewQuestions;

import java.util.Stack;

public class BalancedParenthesis {
    public static void main(String[] args) {
        System.out.println("Stack: [(]) + " + checkParStack("[(])"));

    }

    //naive solution. Wont work for "[(])"
    public static boolean checkPar(String text) {
        int counter = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ')') {
                counter--;
            } else if (text.charAt(i) == '(') {
                counter++;
            }
        }

        if (counter == 0) {
            return true;
        } else {
            return false;
        }
    }

    //Works well when you have multiple things to check for i.e. (, [ and {
    public static boolean checkParStack(String text) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ')') {
                char popped = stack.pop();
                if(popped != '(') {
                    return false;
                }
            } else if (text.charAt(i) == '(') {
                stack.push('(');
            }
        }

        if(!stack.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
