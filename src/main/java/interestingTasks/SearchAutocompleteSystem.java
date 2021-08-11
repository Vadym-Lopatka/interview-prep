package interestingTasks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SearchAutocompleteSystem {
    private static class TrieNode {
        TrieNode[] sub;
        List<String> top3;

        TrieNode() {
            sub = new TrieNode[27];
            top3 = new LinkedList<>();
        }
    }

    private static final int ALPHABET_SIZE = 26;

    TrieNode root;
    Map<String, Integer> frequency;
    StringBuilder currentInput;

    public List<String> input(char c) {
        if (c != '#') {
            currentInput.append(c);
            TrieNode node = root;
            for (int i = 0; i < currentInput.length(); i++) {
                char ch = currentInput.charAt(i);
                int index = ch == ' ' ? ALPHABET_SIZE : ch - 'a';
                if (node.sub[index] == null) return new LinkedList<>();
                else node = node.sub[index];
            }
            return node.top3;
        }
        else {
            String s = currentInput.toString();
            currentInput = new StringBuilder();
            updateTrie(s, 1);
            return new LinkedList<>();
        }
    }

    public SearchAutocompleteSystem(String[] sentences, int[] times) {
        currentInput = new StringBuilder();
        frequency = new HashMap<>();
        root = new TrieNode();

        initiateState(sentences, times);
    }

    private void initiateState(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) {
            updateTrie(sentences[i], times[i]);
        }
    }

    private void updateTrie (String sentence, int time) {
        frequency.put(sentence, frequency.getOrDefault(sentence, 0) + time);
        TrieNode curr = root;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            int index = c == ' ' ? ALPHABET_SIZE : c - 'a';
            if (curr.sub[index] == null) curr.sub[index] = new TrieNode();
            curr = curr.sub[index];
            updateTop3(sentence, curr.top3);
        }
    }

    private void updateTop3 (String sentence, List<String> top3) {
        if (top3.contains(sentence)) top3.remove(sentence);
        if (top3.size() == 0) top3.add(sentence);
        else {
            for (int i = 0; i < top3.size(); i++) {
                if (compare(sentence, top3.get(i)) < 0) {
                    top3.add(i, sentence);
                    break;
                }
            }
            if (!top3.contains(sentence) && top3.size() < 3) top3.add(top3.size(), sentence);

        }
        while (top3.size() > 3) top3.remove(top3.size() - 1);
    }

    private int compare (String s1, String s2) {
        return frequency.get(s2) - frequency.get(s1) == 0 ? s1.compareTo(s2) : frequency.get(s2) - frequency.get(s1);
    }


}
