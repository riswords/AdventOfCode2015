package dec10;

public class LookAndSay {

    private StringBuffer currentValue;

    public LookAndSay(String start) {
        currentValue = new StringBuffer(start);
    }

    public StringBuffer lookAndSay(int iterations) {
        for(int i = 0; i < iterations; ++i) {
            StringBuffer nextValue = new StringBuffer();
            for(int j = 0; j < currentValue.length(); ++j) {
                int nextCount = countConsecutive(j, currentValue);
                nextValue.append(Integer.toString(nextCount));
                nextValue.append(currentValue.charAt(j));
                j += nextCount - 1;
            }
            currentValue = nextValue;
            System.out.println(currentValue);
        }
        return currentValue;
    }

    private int countConsecutive(int startIndex, StringBuffer string) {
        int count = 0;
        if(startIndex >= string.length())
            return 0;
        char c = string.charAt(startIndex);
        while(startIndex < string.length() && string.charAt(startIndex) == c) {
            count += 1;
            startIndex += 1;
        }
        return count;
    }
}
