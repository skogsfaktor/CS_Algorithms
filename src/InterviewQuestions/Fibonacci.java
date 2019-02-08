package InterviewQuestions;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println("Fib 5: " + recursiveFib(5));
    }

    public static int recursiveFib(int n) {
        if(n == 0) {
            return 0;
        } else if(n == 1) {
            return 1;
        } else {
            return recursiveFib(n-1) + recursiveFib(n-2);
        }
    }
}
