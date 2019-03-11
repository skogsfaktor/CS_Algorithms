/*
Three functions
cons(a, b) constructs a pair
car(pair) returns the first element of a pair
car(pair) returns the last element of a pair
Examples:
car(cons(3,4)) returns 3
cdr(cons(3,4)) returns 4
 */

package DailyCodingProblem;

public class DailyCodingProblem_5 {
    public static void main(String[] args) {
        System.out.println(new Cons(3, 4).car());
    }
}

class Cons {
    int a;
    int b;

    public Cons(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int car() {
        return a;
    }

    public int cdr() {
        return b;
    }
}
