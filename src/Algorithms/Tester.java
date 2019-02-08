package Algorithms;

public class Tester {
    public static void main(String[] args) {
        System.out.println("Hi!");
        int testArray[] = {4,3,2,1};
        printArray(testArray);
        SortingAlgorithms.insertionSort(testArray);

        System.out.println(SearchAlgorithms.linearSearch(3, testArray));

        HashishMap<String, Integer> testMap = new HashishMap<String, Integer>(10);
        testMap.put("Hej", 1);
        testMap.put("Nee", 5);
    }

    public static void printArray(int[] array) {
        for(int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
