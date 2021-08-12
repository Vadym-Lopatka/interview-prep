package sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BubbleSortTest {

    private BubbleSort bubbleSort;

    @BeforeEach
    public void setup() {
        bubbleSort = new BubbleSort();
    }

    @Test
    public void should() {
        // given
        int[] arr = new int[]{0, -1, 9, 5, 7};

        // when
        bubbleSort.sort(arr);

        // then
        boolean condition = Arrays.equals(new int[]{-1, 0, 5, 7, 9}, arr);
        assertTrue(condition);
    }

}