import java.util.*;

public class WordFrequencyGame {
    private final String REGEX_WHITESPACE = "\\s+";
    private final String REGEX_NEWLINE = "\n";
    private final String calculateErrorMessage = "Calculate Error";
    public String getResult(String inputSentence) {
        try {
            //split the input string with 1 to n pieces of spaces
            String[] arrayWord = inputSentence.split(REGEX_WHITESPACE);
            List<Input> inputList = new ArrayList<>();
            for (String word : arrayWord) {
                Input input = new Input(word, 1);
                inputList.add(input);
            }
            //get the map for the next step of sizing the same word
            Map<String, List<Input>> mapWordCount = getListMap(inputList);
            List<Input> listWordCount = new ArrayList<>();
            for (Map.Entry<String, List<Input>> entry : mapWordCount.entrySet()) {
                Input input = new Input(entry.getKey(), entry.getValue().size());
                listWordCount.add(input);
            }
            inputList = listWordCount;
            inputList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());
            StringJoiner joiner = new StringJoiner(REGEX_NEWLINE);
            for (Input word : inputList) {
                String wordCountLine = word.getValue() + " " + word.getWordCount();
                joiner.add(wordCountLine);
            }
            return joiner.toString();
        } catch (Exception calculateErrorException) {
            return calculateErrorMessage;
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> mapWordCount = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!mapWordCount.containsKey(input.getValue())) {
                ArrayList listWordCount = new ArrayList<>();
                listWordCount.add(input);
                mapWordCount.put(input.getValue(), listWordCount);
            } else {
                mapWordCount.get(input.getValue()).add(input);
            }
        }
        return mapWordCount;
    }
}
