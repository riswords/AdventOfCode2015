package dec11;

public abstract class AbstractPasswordGenerator {
    public static final String INVALID_CHARS = "iol";

    /**
     * Hopefully self-documenting: increment to the next password, check if it's
     * valid, and either return if it is or recur if it isn't.
     */
    public String getNextPassword(String previousPassword) {
        String nextPassword = incrementPassword(previousPassword);
        if(isValidPassword(nextPassword))
            return nextPassword;
        else
            return getNextPassword(nextPassword);
    }

    /**
     * Increment to the alphabetically next String (may or may not be a valid
     * password) by incrementing the far right character. Once it reaches 'z',
     * wrap around to 'a' and increment the next character to the left (and so
     * on).
     */
    public String incrementPassword(String previousPassword) {
        StringBuffer nextPassword = new StringBuffer(previousPassword);
        boolean wrapIn = true;
        // increment from right to left, with Christmas-themed carry (wrapIn)
        for(int i = previousPassword.length() - 1; i >= 0; --i) {
            if(wrapIn) {
                char nextChar = incrementChar(previousPassword.charAt(i));
                wrapIn = (nextChar == 'a');
                nextPassword.replace(i, i + 1, Character.toString(nextChar));
            }
            // we don't really need the else, just a short-circuit to avoid the
            // rest of the loop...(you could break instead of returning if
            // you're into that)
            else
                return nextPassword.toString();
        }
        return nextPassword.toString();
    }

    /**
     * "Increment" an alphabetic character to the next letter, wrapping around
     * from 'z' to 'a'. Assumes lower-case letter input. Not responsible for
     * output resulting from non-conformant inputs.
     */
    public final char incrementChar(char c) {
        return (char) ('a' + ((c + 1 - 'a') % 26));
    }

    /**
     * Validate password against security elf's latest requirements.
     * 
     * 1. must contain at least one straight of 3 consecutive letters ("abc",
     * "xyz", etc.)
     * 
     * 2. must not contain any i, o, or l characters (too confusing)
     * 
     * 3. must contain at least 2 non-overlapping pairs ("aa", "xx", etc.)
     */
    public boolean isValidPassword(String password) {
        return containsStraightTriple(password) && containsNoInvalidChars(password)
                && containsTwoNonOverlappingPairs(password);
    }

    public abstract boolean containsStraightTriple(String password);

    public abstract boolean containsNoInvalidChars(String password);

    public abstract boolean containsTwoNonOverlappingPairs(String password);
}
