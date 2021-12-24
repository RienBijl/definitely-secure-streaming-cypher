package cypher;

import cypher.generator.BaseKeyGenerator;
import cypher.generator.KeyStreamGenerator;

public class DSSC {

    private final KeyStreamGenerator generator;

    /**
     * Generate a new DSSC operation
     *
     * @param key   a secret key
     * @param nonce a number used once
     */
    public DSSC(boolean[] key, boolean[] nonce) {
        // Key and nonce need to be equal in size
        if (key.length != nonce.length) {
            throw new IllegalArgumentException("Nonce and key are not equal in size");
        }

        this.generator = new KeyStreamGenerator(BaseKeyGenerator.generateKey(key, nonce));
    }

    /**
     * Encrypt a new 256 bit block
     *
     * @param bits 256 bit block
     * @return encrypted 256 bit block
     */
    public boolean[] pass(boolean[] bits) {
        if (bits.length != 256) {
            throw new IllegalArgumentException("Only 256-bit sized blocks are accepted, try padding");
        }

        boolean[] streamingKeyPart = this.generator.next();
        boolean[] result = new boolean[256];

        for (int i = 0; i < 256; i++) {
            result[i] = streamingKeyPart[i] ^ bits[i];
        }

        return result;
    }

}
