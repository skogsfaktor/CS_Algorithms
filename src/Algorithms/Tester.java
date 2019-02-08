package Algorithms;

public class Tester {
    public static void main(String[] args) {
        System.out.println("Hi!");
        int testArray[] = {4,3,2,1};
        printArray(testArray);
        SortingAlgorithms.insertionSort(testArray);

        System.out.println(SearchAlgorithms.linearSearch(3, testArray));
    }

    public static void printArray(int[] array) {
        for(int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
