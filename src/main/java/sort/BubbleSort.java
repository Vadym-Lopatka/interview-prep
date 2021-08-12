package sort;


public class BubbleSort {

    public void sort(int[] ints) {
        int n = ints.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (ints[j] > ints[j + 1]) {
                    swap(ints, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] ints, int firstIndex, int secondIndex) {
        int buffer = ints[firstIndex];
        ints[firstIndex] = ints[secondIndex];
        ints[secondIndex] = buffer;
    }
}
