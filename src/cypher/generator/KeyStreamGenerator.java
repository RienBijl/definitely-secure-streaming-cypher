package cypher.generator;

import cypher.utility.BitArr;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class KeyStreamGenerator {

    private Mac mac;
    private int internalCounter = -1;

    /**
     * Generate new 256-bit key blocks
     *
     * @param key a totally-secret key
     */
    public KeyStreamGenerator(boolean[] key) {
        try {
            this.mac = Mac.getInstance("HmacSHA256");
            this.mac.init(new SecretKeySpec(BitArr.toByteArray(key), "HmacSHA256"));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the next 256-bit key block
     *
     * @return 256-bit key block
     */
    public boolean[] next() {
        this.internalCounter++;
        return BitArr.toBitArray(this.mac.doFinal(BitArr.intToByteArray(this.internalCounter)));
    }
}
