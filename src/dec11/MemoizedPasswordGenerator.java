package dec11;

/**
 * Implement a better password generator that keeps track of some indices and only re-evaluates 
 * password requirements when necessary.
 * 
 * Cache the rightmost/max index of the leftmost straight (lastIndexOfFirstStraight), and only 
 * re-evaluate whether there's a straight if the character at this index changes.
 * 
 * Cache the rightmost/max index of each of the leftmost pairs (maxPairIndices), and only 
 * re-evaluate one or both of the pairs if one or both of the characters at those indices change, 
 * respectively.
 * 
 * Cache the leftmost index that changed and only check for invalid characters from there to the 
 * end of the string.
 *
 */
public class MemoizedPasswordGenerator extends AbstractPasswordGenerator {

    // see class documentation above for descriptions
    private int leftmostChangedIndex = 0;
    private int lastIndexOfFirstStraight = 0;
    private int[] maxPairIndices = { 0, 0 };

    /**
     * Increment to the alphabetically next String (may or may not be a valid
     * password) by incrementing the far right character. Once it reaches 'z',
     * wrap around to 'a' and increment the next character to the left (and so
     * on).
     * 
     * Same as superclass, but also update leftmostChangedIndex when finished.
     */
    @Override
    public String incrementPassword(String previousPassword) {
        StringBuffer nextPassword = new StringBuffer(previousPassword);
        boolean wrapIn = true;
        // increment from right to left, with Christmas-themed carry (wrapIn)
        for(int i = previousPassword.length() - 1; i >= 0; --i) {
            if(wrapIn) {
                char nextChar = incrementChar(previousPassword.charAt(i));
                nextPassword.replace(i, i + 1, Character.toString(nextChar));
                wrapIn = (nextChar == 'a');
            }
            // we don't really need the else, just a short-circuit to avoid the
            // rest of the loop...(you could break instead of returning if
            // you're into that)
            else {
                updateCacheData(nextPassword.toString(), i + 1);
                return nextPassword.toString();
            }
        }
        return nextPassword.toString();
    }

    /**
     * Reset cached indices to 0 if they're greater than the leftmost changed index.
     * It's okay to use 0 (even though that is a valid String index) because 0 isn't actually a 
     * valid value for the rightmost index in either a straight or a pair, and it's more convenient 
     * because we can always start a for loop from it. Smrt.
     */
    private void updateCacheData(String nextPassword, int leftmostChangedIndex) {
        this.leftmostChangedIndex = leftmostChangedIndex;
        if(lastIndexOfFirstStraight >= leftmostChangedIndex)
            lastIndexOfFirstStraight = 0;
        for(int i = 0; i < maxPairIndices.length; ++i) {
            if(maxPairIndices[i] >= leftmostChangedIndex)
                maxPairIndices[i] = 0;
        }
    }

    @Override
    public boolean containsStraightTriple(String password) {
        // if we have a cached index, return it
        if(lastIndexOfFirstStraight > 1)
            return true;
        // otherwise, calculate!
        return checkForStraightTriple(password);
    }

    private boolean checkForStraightTriple(String password) {
        for(int i = lastIndexOfFirstStraight; i < password.length() - 3; ++i) {
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
            if(straightIntact) {
                lastIndexOfFirstStraight = i + 2;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsNoInvalidChars(String password) {
        // start at leftmost changed index when checking
        for(int i = leftmostChangedIndex; i < password.length(); ++i) {
            final int currentIndex = i;
            if(INVALID_CHARS.chars().anyMatch(invalidChar -> {
                return invalidChar == password.charAt(currentIndex);
            }))
                return false;
        }
        return true;
    }

    @Override
    public boolean containsTwoNonOverlappingPairs(String password) {
        // both pair indices still cached
        if(maxPairIndices[1] > 0)
            return true;
        // room for one pair but not two (two possible cases)
        else if((maxPairIndices[0] >= password.length() - 2)
                || (maxPairIndices[0] <= 0 && password.length() - leftmostChangedIndex < 4))
            return false;

        // calculate one pair
        else if(maxPairIndices[0] > 0) {
            int startSearchIndex = Math.max(maxPairIndices[0] + 1, leftmostChangedIndex);
            int secondPairLowerIndex = indexOfNextPair(password, startSearchIndex);
            maxPairIndices[1] = secondPairLowerIndex + 1;
            return secondPairLowerIndex > 0;
        }
        // calculate both pairs
        else {
            int firstPairLowerIndex = indexOfNextPair(password, leftmostChangedIndex);
            maxPairIndices[0] = firstPairLowerIndex + 1;
            if(firstPairLowerIndex >= 0) {
                int secondPairLowerIndex = indexOfNextPair(password, maxPairIndices[0] + 1);
                maxPairIndices[1] = secondPairLowerIndex + 1;
                return secondPairLowerIndex > 0;
            }
            else
                return false;
        }
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
