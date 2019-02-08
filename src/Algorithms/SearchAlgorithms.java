package Algorithms;

public class SearchAlgorithms {
    public static int linearSearch(int element, int[] array) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int element, int[] sortedArray) {
        return -1;
    }
}
