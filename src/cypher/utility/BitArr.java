package cypher.utility;

import java.util.Arrays;
import java.util.BitSet;

public class BitArr {

    /**
     * Transform a byte[] to a bit[]
     *
     * @param bytes byte[]
     * @return a bit[]
     */
    public static boolean[] toBitArray(byte[] bytes) {
        BitSet bitSet = BitSet.valueOf(bytes);
        boolean[] bits = new boolean[bytes.length * 8];
        for (int i = bitSet.nextSetBit(0); i != -1; i = bitSet.nextSetBit(i + 1)) {
            bits[i] = true;
        }
        return bits;
    }

    /**
     * Transform a bit[] to a byte[]
     *
     * @param bits a bit[]
     * @return a byte[]
     */
    public static byte[] toByteArray(boolean[] bits) {
        BitSet bitSet = new BitSet(bits.length);
        for (int i = 0; i < bits.length; i++) {
            if (bits[i]) {
                bitSet.set(i);
            }
        }

        byte[] bytes = bitSet.toByteArray();
        if (bytes.length * 8 >= bits.length) {
            return bytes;
        } else {
            return Arrays.copyOf(bytes, bits.length / 8 + (bits.length % 8 == 0 ? 0 : 1));
        }
    }

    /**
     * Convert an int to a byte[]
     *
     * @param value an integer
     * @return a byte[]
     */
    public static byte[] intToByteArray(int value) {
        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value
        };
    }


}
