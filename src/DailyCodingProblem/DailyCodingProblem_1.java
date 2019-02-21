/*Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
Bonus: Can you do this in one pass?*/

package DailyCodingProblem;

import java.util.HashSet;

public class DailyCodingProblem_1 {
    public static void main(String[] args) {
        int[] testArray = {10, 15, 3, 7};
        System.out.println(addTwo(testArray, 10));
        System.out.println(addTwoBonus(testArray, 17));
    }

    public static boolean addTwo(int[] numberList, int k) {
        for(int i = 0; i < numberList.length; i++) {
            for(int j = 0; j < numberList.length; j++) {
                if(i == j) {
                    continue;
                }
                if(numberList[i] + numberList[j] == k) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean addTwoBonus(int[] numberList, int k) {
        HashSet<Integer> numberSet = new HashSet<>();
        for(int i : numberList) {
            numberSet.add(i);
        }

        for(int i = 0; i < numberList.length; i++) {
            if(numberSet.contains(k-numberList[i])) {
                return true;
            }
        }
        return false;
    }
}
