public class WordFrequency {
    private final String value;
    private final int count;

    public WordFrequency(String word, int count) {
        this.value = word;
        this.count = count;
    }

    public String getValue() {
        return this.value;
    }

    public int getWordCount() {
        return this.count;
    }
}
