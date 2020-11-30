import java.util.*;

public class WordFrequencyGame {
    private final String REGEX_WHITESPACE = "\\s+";
    private final String REGEX_NEWLINE = "\n";
    private final String calculateErrorMessage = "Calculate Error";
    public String getResult(String inputSentence) {
        try {
            String[] arrayWord = inputSentence.split(REGEX_WHITESPACE);
            List<WordFrequency> wordFrequencyList = new ArrayList<>();
            for (String word : arrayWord) {
                wordFrequencyList.add(new WordFrequency(word, 1));
            }
            Map<String, List<WordFrequency>> mapWordCount = getWordFrequencyMap(wordFrequencyList);
            List<WordFrequency> listWordCount = new ArrayList<>();
            for (Map.Entry<String, List<WordFrequency>> mapWordCountEntry : mapWordCount.entrySet()) {
                listWordCount.add(new WordFrequency(mapWordCountEntry.getKey(), mapWordCountEntry.getValue().size()));
            }
            wordFrequencyList = listWordCount;
            wordFrequencyList.sort((word1, word2) -> word2.getCount() - word1.getCount());
            StringJoiner wordFrequencyResult = new StringJoiner(REGEX_NEWLINE);
            for (WordFrequency word : wordFrequencyList) {
                wordFrequencyResult.add(createWordFrequencyLine(word));
            }
            return wordFrequencyResult.toString();
        } catch (Exception calculateErrorException) {
            return calculateErrorMessage;
        }
    }

    private String createWordFrequencyLine(WordFrequency word) {
        return String.format("%s %d", word.getWord(), word.getCount());
    }

    private Map<String, List<WordFrequency>> getWordFrequencyMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> mapWordCount = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
            if (!mapWordCount.containsKey(wordFrequency.getWord())) {
                ArrayList listWordCount = new ArrayList<>();
                listWordCount.add(wordFrequency);
                mapWordCount.put(wordFrequency.getWord(), listWordCount);
            } else {
                mapWordCount.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return mapWordCount;
    }
}
