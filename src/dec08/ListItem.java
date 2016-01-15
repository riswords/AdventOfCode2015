package dec08;

public class ListItem {
    private String itemString;

    public ListItem(String itemString) {
        this.itemString = itemString;
    }

    public int getCodeLength() {
        System.out.println(itemString);
        int length = itemString.length() + getQuoteLength() + count("\"");
        length += count("\\") - count("\\x");
        System.out.println("Code length: " + length);
        return length;
    }

    public int getInMemoryLength() {
        int length = itemString.length() - (3 * count("\\x"));
        System.out.println("In-Memory length: " + length);
        return length;
    }

    private int getQuoteLength() {
        return 2;
    }

    private int count(String search) {
        int count = 0;
        int iterations = 1 + itemString.length() - search.length();
        for(int i = 0; i < iterations; ++i) {
            if(itemString.startsWith(search, i))
                count += 1;
        }
        return count;
    }
}
