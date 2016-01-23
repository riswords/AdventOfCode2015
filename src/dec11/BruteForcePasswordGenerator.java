package dec11;

public class BruteForcePasswordGenerator extends AbstractPasswordGenerator {

    /**
     * Make sure the password contains at least one set of 3 consecutive
     * (ascending) letters in a row. Could easily be generalized to straights of
     * arbitrary length by parameterizing the "3"s in the first two lines.
     */
    @Override
    public boolean containsStraightTriple(String password) {
        for(int i = 0; i < password.length() - 3; ++i) {
            String test = password.substring(i, i + 3);
            boolean straightIntact = true;
            for(int j = 0; j < test.length() - 1 && straightIntact; ++j) {
                // really there's no need for all these variables, but it's
                // easier to read, yes?
                char currentChar = test.charAt(j);
                char actualNextChar = test.charAt(j + 1);
                char expectedNextChar = incrementChar(currentChar);
                if(expectedNextChar != actualNextChar)
                    straightIntact = false;
            }
            if(straightIntact)
                return true;
        }
        return false;
    }

    /**
     * Make sure the password has no invalid characters, as specified in the
     * INVALID_CHARS String.
     */
    @Override
    public boolean containsNoInvalidChars(String password) {
        for(char c : INVALID_CHARS.toCharArray())
            if(password.chars().anyMatch((passChar -> {
                // technically an IntStream, but this will work all the same
                return c == passChar;
            })))
                return false;
        return true;
    }

    /**
     * Check that a password contains at least two non-overlapping "pairs" (two
     * of the same character in a row).
     */
    @Override
    public boolean containsTwoNonOverlappingPairs(String password) {
        int firstPair = indexOfNextPair(password, 0);
        // the concise version with ternary operator:
        // return firstPair>= 0 ?
        //      indexOfNextPair(password,firstPair+2) > 0 :
        //      false;
        // the (probably) more readable version:
        if(firstPair >= 0 && indexOfNextPair(password, firstPair + 2) >= 0)
            return true;
        else
            return false;
    }

    /**
     * Returns the index of the next "pair" (two of the same character in a
     * row), beginning the search at startingIndex, or -1 if there is no pair
     * where the index of the first character is greater than or equal to
     * startingIndex.
     */
    private int indexOfNextPair(String password, int startingIndex) {
        for(int i = startingIndex; i < password.length() - 1; ++i) {
            if(password.charAt(i) == password.charAt(i + 1))
                return i;
        }
        return -1;
    }
}
