/*
Find the first missing positive integer in linear time and constant space.
[3, 4, -1, 1] -> 2
[1, 2, 0] -> 3
 */

package DailyCodingProblem;

import java.util.HashSet;

public class DailyCodingProblem_4 {
    public static void main(String [] args) {
        int[] numbers = {3,4,-1,1};
        System.out.println(findSmallestMissing(numbers));
    }

    public static int findSmallestMissing(int[] arr) {
        HashSet<Integer> numberSet = new HashSet<Integer>();
        for(int i = 0; i < arr.length; i++) {
            numberSet.add(arr[i]);
        }

        for(int i = 1; i < arr.length; i++) {
            if(!numberSet.contains(i)) {
                return i;
            }
        }

        return arr.length;
    }
}
