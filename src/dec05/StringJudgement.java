package dec05;

public class StringJudgement {

    private static final String VOWELS = "aeiou";
    private static final int NUM_VOWELS = 3;
    private static final String[] BLACKLIST = { "ab", "cd", "pq", "xy" };

    public static boolean isNice(String string) {
        return hasAtLeastNVowels(string) && hasAtLeastOneDoubleLetter(string) && hasNoBlacklistedStrings(string);
    }

    public static boolean isNicer(String string) {
        return hasARepeatingLetterPair(string) && hasASkipPair(string);
    }

    private static boolean hasAtLeastNVowels(String string) {
        int numVowels = 0;
        for(int i = 0; i < string.length(); ++i) {
            String character = string.substring(i, i + 1);
            if(isVowel(character))
                numVowels += 1;
        }
        return numVowels >= NUM_VOWELS;
    }

    private static boolean isVowel(String character) {
        return VOWELS.contains(character);
    }

    private static boolean hasAtLeastOneDoubleLetter(String string) {
        for(int i = 0; i < string.length() - 1; ++i) {
            if(string.charAt(i) == string.charAt(i + 1))
                return true;
        }
        return false;
    }

    private static boolean hasNoBlacklistedStrings(String string) {
        for(String s : BLACKLIST) {
            if(string.contains(s))
                return false;
        }
        return true;
    }

    private static boolean hasASkipPair(String string) {
        for(int i = 0; i < string.length() - 2; ++i) {
            if(string.charAt(i) == string.charAt(i + 2))
                return true;
        }
        return false;
    }

    private static boolean hasARepeatingLetterPair(String string) {
        for(int i = 0; i < string.length() - 3; ++i) {
            String pair = string.substring(i, i + 2);
            String searchString = string.substring(i + 2);
            if(searchString.contains(pair))
                return true;
        }
        return false;
    }
}
