/*Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
Follow-up: what if you can't use division?*/

package DailyCodingProblem;

public class DailyCodingProblem_2 {
    public static void main(String[] args) {
        int[] numArray = {1,2,3,4,5};
        int[] prod = arrayProd(numArray);
        for(int i : prod) {
            System.out.println(i);
        }
    }

    public static int[] arrayProd(int[] elems) {
        int[] product = new int[elems.length];
        for(int i = 0; i < elems.length; i++) {
            product[i] = 1;
        }

        for(int i = 0; i < elems.length; i++) {
            for(int j = 0; j < elems.length; j++) {
                if(j == i) {
                    continue;
                }
                product[i] *= elems[j];
            }
        }
        return product;
    }
}
