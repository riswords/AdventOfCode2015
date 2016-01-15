package dec04;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFinder {
    private String secretKey;
    private static final int NUM_LEADING_ZEROES = 6;

    public HashFinder(String key) {
        secretKey = key;
    }

    public int findIntToHashWithLeading0s() {
        MessageDigest digest = getMessageDigest();

        int testInt = 1;
        while(true) {
            String testKey = secretKey + Integer.toString(testInt);
            byte[] bytes = digest.digest(testKey.getBytes());
            String s = bytesToHex(bytes);
            boolean allZero = true;
            for(int i = 0; i < NUM_LEADING_ZEROES; ++i) {
                if(s.charAt(i) != '0')
                    allZero = false;
            }
            if(allZero)
                return testInt;
            testInt += 1;
        }
    }

    /*
     * this method shamelessly stolen (with attribution) from:
     * http://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to
     * -a-hex-string-in-java
     */
    private String bytesToHex(byte[] bytes) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for(int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
