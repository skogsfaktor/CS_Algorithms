package Algorithms;

public class SortingAlgorithms {
    public static void insertionSort(int[] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = i; j > 0; j--) {
                if(array[j-1] > array[j]) {
                    System.out.println("Swapping " + j + " and " + (j-1));
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }
                Tester.printArray(array);
            }
        }
    }

    public static void mergeSort(int[] array) {

    }
}
