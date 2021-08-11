package interestingTasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class SearchAutocompleteSystemTest {

    private SearchAutocompleteSystem sutSystem;

    private final String[] sentences = new String[]{"i love programming", "instruction", "iphone", "i love to read"};
    private final int[] usageFrequency = new int[]{6, 4, 3, 3};

    @BeforeEach
    public void setup() {
        sutSystem = new SearchAutocompleteSystem(sentences, usageFrequency);
    }

    @Test
    public void shouldRespondWithEmptyListWhenNoInputButJustFinalSymbol() {
        // given
        char finalSymbol = '#';

        // when
        List<String> result = sutSystem.input(finalSymbol);

        // then
        assertEquals(0, result.size());
    }

    @Test
    public void shouldRespondWithDataWithFrequencyOrderWhenInputMatchesSentencesStore() {
        // given
        char theLetter = 'i';

        // when
        List<String> result = sutSystem.input(theLetter);

        // then
        assertEquals(3, result.size());
        assertIterableEquals(List.of("i love programming", "instruction", "i love to read"), result);
    }

    @Test
    public void shouldRespondWithDataOnNextSymbolWhenHasMatches() {
        // given
        char firstSymbol = 'i';
        char nextSymbol = ' ';

        // when
        sutSystem.input(firstSymbol);
        List<String> result = sutSystem.input(nextSymbol);

        // then
        assertEquals(2, result.size());
        assertIterableEquals(List.of("i love programming", "i love to read"), result);
    }

    @Test
    public void shouldRespondWithEmptyListWhenHasNoMatches() {
        // given
        String input = "i a";
        char[] sequence = input.toCharArray();

        // when
        List<String> result = new ArrayList<>();
        for (char symbol : sequence) {
            result = sutSystem.input(symbol);
        }

        // then
        assertEquals(0, result.size());
    }

    @Test
    public void shouldContinueRespondWithEmptyListOnFinalSymbolWhenHasNoMatches() {
        // given
        String input = "i a#";
        char[] sequence = input.toCharArray();

        // when
        List<String> result = new ArrayList<>();
        for (char symbol : sequence) {
            result = sutSystem.input(symbol);
        }

        // then
        assertEquals(0, result.size());
    }

}