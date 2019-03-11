/*
XOR linked list. Memory efficient in that it holds a field named "both" instead of next and prev
both is an XOR of the next and previous element
define add(element)
define get(index)
**HARD TO DO IN JAVA**
 */

package DailyCodingProblem;

public class DailyCodingProblem_6 {
    //Have to start traversing from the beginning
    public static void main(String [] args) {
        XORElement[] XOR = new XORElement[3];
        XOR[0] = new XORElement(0, "A");
        XOR[1] = new XORElement(1, "B");
        XOR[2] = new XORElement(2, "C");

        //System.out.println(1 ^ 3 ^ 1);
    }

    public XORElement get(int index) {
        return new XORElement(5, "A");
    }
}

class XORElement {
    int both;
    String value;

    public XORElement(int element, String value) {
        if(element == 0) {
            both = element;
        } else {
            both = element-1 ^ element+1;
        }

        this.value = value;
    }
}
