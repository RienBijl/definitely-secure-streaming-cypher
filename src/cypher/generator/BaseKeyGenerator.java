package cypher.generator;

public class BaseKeyGenerator {

    /**
     * Generate a key that ensures different output for equal input
     *
     * @param temporaryKey user-supplied temporary key
     * @param nonce        user-supplied number used once
     * @return actually usable key
     */
    public static boolean[] generateKey(boolean[] temporaryKey, boolean[] nonce) {
        // Create a bit array that will represent the usable key
        boolean[] key = new boolean[temporaryKey.length];

        // Loop through all keys in key-array and nonce-array
        for (int i = 0; i < temporaryKey.length; i++) {
            // Xor the nonce and key bit value to ensure they are dependent on each other
            key[i] = temporaryKey[i] ^ nonce[i];
        }

        return key;
    }

}
